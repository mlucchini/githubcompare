package info.githubcompare.json

import info.githubcompare.model.DayStarred
import spray.json.DefaultJsonProtocol

object DayStarredJsonProtocol extends DefaultJsonProtocol {
  implicit val dayStarredFormat = jsonFormat2(DayStarred)
}