package info.githubcompare.persistence

import info.githubcompare.conf.FileSystemConf
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class FileSystemCacheTest extends FunSpec with MockFactory {
  describe("A cache") {
    it("should return an empty option provided a non-existing path") {
      val fileSystemConf = stub[FileSystemConf]
      (fileSystemConf.cacheDir _).when().returns("src/test/resources/")

      val cache = new FileSystemCache(fileSystemConf, "non-existing-events.json.gz")

      cache.getStream should be (empty)
    }

    it("should return an input stream provided an existing path") {
      val fileSystemConf = stub[FileSystemConf]
      (fileSystemConf.cacheDir _).when().returns("src/test/resources/")

      val cache = new FileSystemCache(fileSystemConf, "events.json.gz")

      cache.getStream should not be empty
    }
  }
}
