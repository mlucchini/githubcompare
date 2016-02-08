package info.githubcompare.commands

import info.githubcompare.conf.{FileSystemConf, LocalFileSystemConf, LocalSparkConf}
import info.githubcompare.rdd.{RDDFilterByWatchEventSortByRepositoryAndDay, RDDMultipleTextOutputFormat}
import info.githubcompare.util.{DateRange, GitHubArchiveFormats}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.joda.time.DateTime

class AggregateCommand(start: DateTime, end: DateTime, dryRun: Boolean) extends Command {
  override def execute() = {
    val fileSystemConf = new LocalFileSystemConf
    val gitHubArchiveFormats = new GitHubArchiveFormats
    val context = new SparkContext(new LocalSparkConf)

    val dates = DateRange(start, end).make()
    val files = dates.map(date => path(date, fileSystemConf, gitHubArchiveFormats)).mkString(",")
    val data = context.textFile(files)
    val events = new RDDFilterByWatchEventSortByRepositoryAndDay().chain(data)

    store(events, fileSystemConf)
  }

  private def store(events: RDD[(String, String)], conf: FileSystemConf) = {
    if (dryRun) events.foreach(println)
    else events.saveAsHadoopFile(conf.storeDir(), classOf[String], classOf[String], classOf[RDDMultipleTextOutputFormat])
  }

  private def path(date: DateTime, conf: FileSystemConf, fmt: GitHubArchiveFormats) = "%s/%s".format(conf.cacheDir(), fmt.dateToFileFormat(date))
}
