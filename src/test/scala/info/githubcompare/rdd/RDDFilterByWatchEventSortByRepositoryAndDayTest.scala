package info.githubcompare.rdd

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfter, FunSpec}

class RDDFilterByWatchEventSortByRepositoryAndDayTest extends FunSpec with SharedSparkContext with BeforeAndAfter {
  var events = null.asInstanceOf[Array[(String, String)]]

  before {
    val data = sc.textFile("src/test/resources/events.json")
    events = new RDDFilterByWatchEventSortByRepositoryAndDay().chain(data).collect()
  }

  describe("An RDD that filters on WatchEvent and sort by repository and day") {
    it("should ignore non-WatchEvent events") {
      events.length should (be >= 1 and be <= 4)
    }

    it("should accumulate stars for identical repository and day") {
      events should contain ("phpsysinfo/phpsysinfo", """{"date":"2015-01-01","stars":2}""")
    }

    it("should sort by repository name and chronologically") {
      events.head should be ("artist/raytracer", """{"date":"2000-01-01","stars":1}""")
      events shouldBe sorted
    }
  }
}
