package info.githubcompare.util

import java.util.Calendar

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateConverter {
  implicit def calendarToDateTime(calendar: Calendar): DateTime = new DateTime(calendar)

  def isoStringToPattern(date: String, pattern: String) = DateTimeFormat.forPattern(pattern).print(DateTime.parse(date))
}
