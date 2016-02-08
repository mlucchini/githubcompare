package info.githubcompare.json

import info.githubcompare.json.DayStarredJsonProtocol._
import info.githubcompare.model.DayStarred
import org.scalatest.FunSpec
import org.scalatest.Matchers._
import spray.json._

class DayStarredJsonProtocolTest extends FunSpec {
  val date = "2016-02-07"
  val stars = 12

  describe("A day-starred JSON protocol") {
    it("should throw on object conversion if the JSON is missing the date") {
      intercept[DeserializationException] {
        s"""{ "stars": $stars }""".parseJson.convertTo[DayStarred]
      }
    }

    it("should throw on object conversion if the JSON is missing the stars") {
      intercept[DeserializationException] {
        s"""{ "date": "$date" }""".parseJson.convertTo[DayStarred]
      }
    }

    it("should convert JSON into a model object with the same properties") {
      val json = s"""{ "date": "$date", "stars": $stars }"""

      val dayStarred = json.parseJson.convertTo[DayStarred]

      dayStarred.date should be (date)
      dayStarred.stars should be (stars)
    }

    it("should convert a model object into JSON with the same properties") {
      val dayStarred = DayStarred(date, stars)

      val json = dayStarred.toJson

      json.asJsObject.getFields("date", "stars") should contain allOf (JsString(date), JsNumber(stars))
    }
  }
}
