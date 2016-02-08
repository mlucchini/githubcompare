package info.githubcompare.conf

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class LocalFileSystemConfTest extends FunSpec {
  describe("A local file system configuration") {
    it("should have a non-empty cache directory") {
      new LocalFileSystemConf cacheDir() should not be empty
    }

    it("should have a non-empty store directory") {
      new LocalFileSystemConf storeDir() should not be empty
    }
  }
}
