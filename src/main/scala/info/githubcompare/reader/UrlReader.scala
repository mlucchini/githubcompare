package info.githubcompare.reader

import java.net.URL

import scala.util.Try

class UrlReader(val url: String) extends Reader {
  override def getStream() = Try { new URL(url).openStream() }.toOption
}
