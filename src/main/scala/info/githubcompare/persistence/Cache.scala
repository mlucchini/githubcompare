package info.githubcompare.persistence

import java.io.InputStream

trait Cache {
  def getStream: Option[InputStream]
  def writeStream(inputStream: InputStream): Unit
}
