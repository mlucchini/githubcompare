package info.githubcompare.rdd

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class RDDMultipleTextOutputFormatTest extends FunSpec {
  describe("An RDD multiple text output format") {
    it("should generate a String filename from a String key") {
      val filename = new RDDMultipleTextOutputFormat().generateFileNameForKeyValue("Key", "", "")

      filename shouldBe a [String]
    }

    it("should generate a String filename from an Integer key") {
      val filename = new RDDMultipleTextOutputFormat().generateFileNameForKeyValue(42, "", "")

      filename shouldBe a [String]
    }
  }
}
