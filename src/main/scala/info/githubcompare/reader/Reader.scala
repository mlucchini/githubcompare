package info.githubcompare.reader

import java.io.InputStream

trait Reader {
  def getStream: Option[InputStream]
}
