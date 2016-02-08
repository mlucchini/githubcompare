package info.githubcompare.reader

import java.io.FileInputStream

import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfter, FunSpec}

import scala.io.Source

class ZipReaderTest extends FunSpec with BeforeAndAfter {
  describe("A zip reader") {
    it("should unzip content identical to the original") {
      val zipInputStream = new FileInputStream("src/test/resources/events.json.gz")
      val unzipInputStream = new FileInputStream("src/test/resources/events.json")

      val zipReader = new ZipReader(zipInputStream)
      val zipContent = Source.fromInputStream(zipReader.getStream().get).mkString
      val unzipContent = Source.fromInputStream(unzipInputStream).mkString

      zipContent shouldEqual unzipContent

      zipInputStream.close()
      unzipInputStream.close()
    }
  }
}
