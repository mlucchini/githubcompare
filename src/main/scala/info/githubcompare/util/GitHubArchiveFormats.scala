package info.githubcompare.util

import org.joda.time.DateTime

class GitHubArchiveFormats {
  private val dateFormat = "%04d-%02d-%02d-%d.json.gz"

  def dateToFileFormat(date: DateTime) = dateFormat.format(date.year.get, date.monthOfYear.get, date.dayOfMonth.get, date.hourOfDay.get)
  def dateToUrlFormat(date: DateTime) = s"http://data.githubarchive.org/${dateToFileFormat(date)}"
}
