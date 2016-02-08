package info.githubcompare.rdd

import info.githubcompare.json.DayStarredJsonProtocol._
import info.githubcompare.json.EventJsonProtocol._
import info.githubcompare.model.{DayStarred, Event}
import info.githubcompare.util.DateConverter
import org.apache.spark.rdd.RDD
import spray.json._

class RDDFilterByWatchEventSortByRepositoryAndDay {
  def chain(data: RDD[String]): RDD[(String, String)] = {
    data
      .filter(_.contains("WatchEvent"))
      .map(entry => {
        val event = entry.parseJson.convertTo[Event]
        val key = (event.repositoryName, DateConverter.isoStringToPattern(event.date, "YYYY-MM-dd"))
        (key, 1)
      })
      .reduceByKey((a, b) => a + b)
      .sortBy(e => (e._1._1, e._1._2))
      .map {
        case ((repositoryName, date), stars) => (repositoryName, DayStarred(date, stars).toJson.toString)
      }
  }
}
