package info.githubcompare.util

import com.github.nscala_time.time.DurationBuilder
import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime

object DateRange {
  def apply(start: DateTime, end: DateTime, step: DurationBuilder = 1.hours) = new DateRange(start, end, step)
}

class DateRange(start: DateTime, end: DateTime, step: DurationBuilder) {
  def make(): Iterator[DateTime] = start to end by step
}
