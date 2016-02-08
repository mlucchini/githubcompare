package info.githubcompare.util

import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DateRangeTest extends FunSpec {
  val now = DateTime.now

  describe("A date range") {
    it("should throw if the start date is posterior to the end date") {
      intercept[IllegalArgumentException] {
        DateRange(now + 1.hours, now).make()
      }
    }

    it("should return 2 elements if the start date is one hour before the end date") {
      val dates = DateRange(now, now + 1.hours).make()

      dates should have size 2
    }

    it("should return 25 elements if the start date is one day before the end date") {
      val dates = DateRange(now, now + 1.days).make()

      dates should have size 25
    }

    it("should return 24 * 365 elements if the date range is the whole 2015 year") {
      val dates = DateRange(new DateTime(2015, 1, 1, 0, 0), new DateTime(2015, 12, 31, 23, 0)).make()

      dates should have size (24 * 365)
    }
  }
}
