package com.njupt

import com.mongodb.spark.MongoSpark
import com.mongodb.spark.config.{ReadConfig, WriteConfig}
import org.apache.spark.sql.{DataFrame, SparkSession}

object statistics {
  //conf
  val config = Map(
    "spark.cores" -> "local[*]",
    "mongo.uri" -> "mongodb://localhost/fundRecommendSystem.myfund",
  )

  def main(args: Array[String]): Unit = {
    // 创建SparkSession
    val spark: SparkSession = SparkSession.builder()
      .appName(getClass.getSimpleName)
      .master("local[*]")
      .config("spark.mongodb.input.uri", config("mongo.uri"))
      .config("spark.mongodb.output.uri", config("mongo.uri"))
      .getOrCreate()

    val baseFundInfo: DataFrame = ReadMongoDB(spark,"base_fund_info")

    // 统计推荐
    // 1. 统计跟投人数最多的基金组合  plan_code ,follow_num 和sales
    val followMax : DataFrame = baseFundInfo.select("plan_code","follow_num", "sales").orderBy(baseFundInfo("follow_num").desc)

    writeMongoDB(spark,followMax, "danjuan_follow_max")
    // 2.综合排序
    // 持仓时间，收益率，跑赢基准的幅度，需要用类别分类

    // 3.

  }

  /**
   * 使用Spark从MongoDB中读取数据，并转换为DataFrame
   * @param spark           SparkSession
   * @param collectionName  mongodb 的collection名
   * @return                DataFrame
   */
  def ReadMongoDB(spark: SparkSession, collectionName: String): DataFrame = {
    val readConfig = ReadConfig(Map("collection" -> s"${collectionName}", "readPreference.name" -> "secondaryPreferred"), Some(ReadConfig(spark)))
    val customRdd = MongoSpark.load(spark, readConfig)
    return customRdd
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
