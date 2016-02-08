package info.githubcompare.commands

import java.nio.file.{Files, Paths}

import info.githubcompare.conf.{FileSystemConf, LocalFileSystemConf}
import info.githubcompare.persistence.FileSystemCache
import info.githubcompare.reader.{CachedReader, UrlReader}
import info.githubcompare.util._
import org.joda.time.DateTime

class DownloadCommand(start: DateTime, end: DateTime) extends Command {
  override def execute() = {
    val fileSystemConf = new LocalFileSystemConf
    val gitHubArchiveFormats = new GitHubArchiveFormats

    createCacheDir(fileSystemConf)

    for (date <- DateRange(start, end).make()) download(date, fileSystemConf, gitHubArchiveFormats)
  }

  private def download(date: DateTime, fileSystemConf: FileSystemConf, gitHubArchiveFormats: GitHubArchiveFormats) = {
    val reader = new CachedReader(
      new FileSystemCache(fileSystemConf, gitHubArchiveFormats.dateToFileFormat(date)),
      new UrlReader(gitHubArchiveFormats.dateToUrlFormat(date)))
    reader.getStream
  }

  private def createCacheDir(fileSystemConf: FileSystemConf) = {
    val cachePath = Paths.get(fileSystemConf.cacheDir())
    if (Files.notExists(cachePath)) Files.createDirectories(cachePath)
  }
}
