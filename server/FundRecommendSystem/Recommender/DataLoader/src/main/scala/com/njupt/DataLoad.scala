package com.njupt

import com.mongodb.spark.MongoSpark
import com.mongodb.spark.config.WriteConfig
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SparkSession}

// 数据写入MongoDB
case class MongoConfig(uri: String, db: String)

object DataLoader {

  //conf
  val config = Map(
    "spark.cores" -> "local[*]",
    //    "mongo.uri" -> "mongodb://root:you%40xuan123.@192.168.17.129:27017/test.myfund",
    "mongo.uri" -> "mongodb://localhost/fundRecommendSystem.myfund",
    "mongo.db" -> "test",

    "es.httpHosts" -> "hadoop102:9200",
    "es.transportHosts" -> "hadoop102:9300",
    "es.index" -> "recommender",
    "es.cluster.name" -> "elasticsearch"
  )

  def main(args: Array[String]): Unit = {

    // 创建SparkSession
    val spark: SparkSession = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .config("spark.mongodb.output.uri", config("mongo.uri"))
      .getOrCreate()

    // 蛋卷基金数据处理
    danjuanDataLoader(spark)

    // 天天基金数据处理
//    tiantainDataLoader(spark)


//    tiantianDataWriteDB(spark)

    // 关闭sparkSession
    spark.close()

  }

  def tiantianDataWriteDB(spark: SparkSession): Unit = {
    // 加载基金细节数据，并选择出想要的数据
    val fundsDetail: DataFrame = spark.read.json("DataCrawl/data/tiantian/CombinationFundDetail.json")
    writeMongoDB(spark, fundsDetail, "tiantian_fund_detail")
  }


  def tiantainDataLoader(spark: SparkSession): Unit = {
    // 加载数据
    val baseDf: DataFrame = spark.read.json("DataCrawl/data/tiantian/CombinationAllData.json")

    // 注册getDays函数，获取运行天数
    spark.udf.register("getDays", (str: String) => str.substring(2, str.length - 1))

    // 注册clearNull函数，清除   --  符合
    spark.udf.register("clearNull", (str: String) => str.replace("--", "0"))

    // 注册clearDrawDown函数，变成正数
    spark.udf.register("clearDrawDown", (str: String) => str.replace("-", ""))


    // 处理基础数据
    val funds: DataFrame = baseDf
      .selectExpr("nicheng", "subAccountName", "fund_type", "subAccountNo", "SubAsset", "followBuyCount", "clearNull(yearProfit)", "AllProfit", "clearDrawDown(clearNull(qDrawDown))", "clearNull(Quartersharp)", "from", "manager_type")


    // 加载基金细节数据，并选择出想要的数据
    val fundsDetail: DataFrame = spark.read.json("DataCrawl/data/tiantian/CombinationFundDetail.json")
      .selectExpr("subAccountNo", "getDays(WorkDays)")

    val featureDF: DataFrame = funds.join(fundsDetail, "subAccountNo")

    //重命名字段
    val resultDf: DataFrame = featureDF.withColumnRenamed("nicheng", "manager_name")
      .withColumnRenamed("subAccountName", "plan_name")
      .withColumnRenamed("subAccountNo", "plan_code")
      .withColumnRenamed("SubAsset", "sales")
      .withColumnRenamed("followBuyCount", "follow_num")
      .withColumnRenamed("clearNull(yearProfit)", "yield")
      .withColumnRenamed("AllProfit", "all_yield")
      .withColumnRenamed("getDays(WorkDays)", "found_days")
      .withColumnRenamed("clearDrawDown(clearNull(qDrawDown))", "max_drawdown")
      .withColumnRenamed("clearNull(Quartersharp)", "sharpe")


    // 添加新列，计算综合排序
    // 排序方式：成立以来收益率-最大回撤+年化+夏普比率*100% ；
    val finalResult = resultDf.withColumn("final_prior", col("all_yield") - col("max_drawdown") + col("yield") + col("sharpe") * 100)


    finalResult.printSchema()
    finalResult.show()
    print(finalResult.count())

    // 写入数据库
    writeMongoDB(spark, finalResult, "base_fund_info")

    // 组合数据


  }

  def danjuanDataLoader(spark: SparkSession): Unit = {

    // 加载数据
    val baseDf: DataFrame = spark.read.json("DataCrawl/data/danjuan/DanjuanCombinationFund.json")
    val detail: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/detail.json")
    val growth: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/growth.json")
    val homepage: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/hompage.json")
    val indcator: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/indcator.json")
    // withColumnRenamed 给某些字段重命名
    val plan: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/plan.json")
      .withColumnRenamed("yield", "all_yield")
      .withColumnRenamed("plan_name", "plan_name2")
      .withColumnRenamed("manager_name", "manager_name2")
    val summary: DataFrame = spark.read.json("DataCrawl/data/danjuan/combinationdetail/summary.json")


    // 写入数据库
        writeMongoDB(spark, baseDf, "danjuan_fund")
        writeMongoDB(spark, detail, "danjuan_fund_detail")
        writeMongoDB(spark, growth, "danjuan_fund_growth")
        writeMongoDB(spark, homepage, "danjuan_fund_homepage")
        writeMongoDB(spark, indcator, "danjuan_fund_indcator")
        writeMongoDB(spark, plan, "danjuan_fund_plan")
        writeMongoDB(spark, summary, "danjuan_fund_summary")


    // 去除汉字和空格和，
//    spark.udf.register("clearSales", (str: String) => str.substring(2, str.length - 1).replace(",", "").replace(" ", ""))
    spark.udf.register("clearSales", (str: String) => str.replace("--", "0"))


    // 将所需数据进行join,并提取需要使用的字段
    val fundBaseDf: DataFrame = baseDf
      .join(indcator, "plan_code")
      .join(plan, "plan_code")
      .selectExpr("plan_code", "follow_num", "plan_name", "manager_name", "yield",
        "max_drawdown", "sharpe", "clearSales(sales)", "all_yield", "found_days", "fund_type", "from", "manager_type")


    val newBaseOf: DataFrame = fundBaseDf.withColumnRenamed("clearSales(sales)", "sales")


    // 添加新列，计算综合排序
    // 排序方式：成立以来收益率-最大回撤+年化+夏普比率*100% ；
    val finalResult = fundBaseDf.withColumn("final_prior", col("all_yield") - col("max_drawdown") + col("yield") + col("sharpe") * 100)


    finalResult.printSchema()
    finalResult.show(20)
    writeMongoDB(spark, finalResult, "base_fund_info")

  }

  /**
   * 将数据写入数据库
   *
   * @param spark          sc
   * @param data           DataFrame
   * @param collectionName mongodb 的collection名
   */
  def writeMongoDB(spark: SparkSession, data: DataFrame, collectionName: String): Unit = {

    val writeConfig = WriteConfig(Map("collection" -> s"${collectionName}", "writeConcern.w" -> "majority"), Some(WriteConfig(spark)))
    MongoSpark.save(data, writeConfig)
  }

}
