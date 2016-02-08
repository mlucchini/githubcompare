package info.githubcompare.persistence

import java.io.InputStream
import java.nio.file.{Files, Paths}

import com.typesafe.scalalogging.LazyLogging
import info.githubcompare.conf.FileSystemConf

class FileSystemCache(fileSystemConf: FileSystemConf, filename: String) extends Cache with LazyLogging {
  override def getStream: Option[InputStream] = {
    logger.info(s"Reading $filename from cache")
    if (Files.exists(path)) Some(Files.newInputStream(path)) else None
  }

  override def writeStream(inputStream: InputStream) = {
    logger.info(s"Reading and writing $filename to cache")
    Files.copy(inputStream, path)
  }

  private def path = Paths.get(s"${fileSystemConf.cacheDir()}$filename")
}
