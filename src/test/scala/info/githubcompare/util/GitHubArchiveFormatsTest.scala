package info.githubcompare.util

import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class GitHubArchiveFormatsTest extends FunSpec {
  val filenameRegex = "\\d{4}-\\d{2}-\\d{2}-\\d+\\..*"

  describe("GitHub Archive format") {
    it("should respect the GitHub Archive file naming regarding year, month, day and hours") {
      val dates = DateRange(DateTime.now, DateTime.now + 1.year).make()
      for (date <- dates) {
        val filename = new GitHubArchiveFormats().dateToFileFormat(date)

        filename should fullyMatch regex filenameRegex
      }
    }

    it("should respect the GitHub Archive URL naming regarding year, month, day and hours") {
      val dates = DateRange(DateTime.now, DateTime.now + 1.year).make()
      for (date <- dates) {
        val url = new GitHubArchiveFormats().dateToUrlFormat(date)

        url should fullyMatch regex s".*/$filenameRegex"
      }
    }
  }
}
