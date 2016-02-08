package info.githubcompare.reader

import java.io.{BufferedInputStream, InputStream}
import java.util.zip.GZIPInputStream

import scala.util.Try

class ZipReader(val stream: InputStream) extends Reader {
  override def getStream() = Try { new GZIPInputStream(new BufferedInputStream(stream)) }.toOption
}
