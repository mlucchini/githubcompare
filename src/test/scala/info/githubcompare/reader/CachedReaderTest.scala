package info.githubcompare.reader

import java.io.InputStream

import info.githubcompare.persistence.Cache
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CachedReaderTest extends FunSpec with MockFactory {
  describe("A cached reader") {
    it("should return the input stream from the cache provided the cache returns the entry") {
      val cache = stub[Cache]
      val reader = stub[Reader]

      val cacheInputStream = Some(stub[InputStream])
      (cache.getStream _).when().returns(cacheInputStream)

      val cachedReader = new CachedReader(cache, reader)

      cachedReader.getStream shouldEqual cacheInputStream
    }

    it("should write the input stream from the reader to the cache provided the cache doesn't return any entry") {
      val cache = stub[Cache]
      val reader = stub[Reader]

      val readerInputStream = Some(stub[InputStream])
      (cache.getStream _).when().returns(None)
      (reader.getStream _).when().returns(readerInputStream)

      val cachedReader = new CachedReader(cache, reader)
      cachedReader.getStream

      (cache.writeStream _).verify(readerInputStream.get).once()
    }
  }
}
