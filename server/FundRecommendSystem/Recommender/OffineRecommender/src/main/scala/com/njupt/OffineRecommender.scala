package com.njupt

import com.mongodb.spark.MongoSpark
import com.mongodb.spark.config.{ReadConfig, WriteConfig}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.jblas.DoubleMatrix

object OffineRecommender {

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

//    baseFundInfo.rdd
//
//    //基于电影隐特征，计算相似度矩阵，得到电影的相似度列表（mid,list(mid,相似度)）
//    val moveFeatures: RDD[(Int, DoubleMatrix)] = baseFundInfo.map {
//      case (mid, features) => (mid, new DoubleMatrix(features))
//    }
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
