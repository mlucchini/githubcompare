package info.githubcompare.conf

import info.githubcompare.AppInfo
import org.apache.spark.SparkConf

class LocalSparkConf extends SparkConf {
  setAppName(AppInfo.name)
  setMaster("local")
}
