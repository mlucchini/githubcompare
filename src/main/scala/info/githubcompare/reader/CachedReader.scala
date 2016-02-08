package info.githubcompare.reader

import java.io.InputStream

import com.typesafe.scalalogging.LazyLogging
import info.githubcompare.persistence.Cache

class CachedReader(cache: Cache, reader: Reader) extends Reader with LazyLogging {
  override def getStream: Option[InputStream] = {
    fromCache() match {
      case None => fromReader()
      case x => x
    }
  }

  private def fromCache() = cache.getStream

  private def fromReader() = {
    reader.getStream match {
      case Some(inputStream) =>
        cache.writeStream(inputStream)
        cache.getStream
      case None =>
        logger.warn("No data available in archives")
        None
    }
  }
}
