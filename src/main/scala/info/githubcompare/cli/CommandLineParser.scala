package info.githubcompare.cli

import java.util.Calendar

import info.githubcompare.AppInfo
import info.githubcompare.conf.LocalFileSystemConf

class CommandLineParser extends scopt.OptionParser[CommandLineConfig](AppInfo.name) {
  note("This tool does two things:")
  note("   - Download GitHub events from GitHub Archive for a range of dates")
  note("   - Aggregate time-series events by repository")
  note("The local database is located at %s.\n".format(new LocalFileSystemConf().storeDir()))

  opt[Calendar]('s', "start") required() valueName "<YYYY-MM-DD>" action { (x, c) => c.copy(start = x) } text "Consider events from this date"
  opt[Calendar]('e', "end") optional() valueName "<YYYY-MM-DD>" action { (x, c) => c.copy(end = x) } text "Consider events until this date; defaults to now"

  cmd("download") action { (_, c) => c.copy(command = "download") } text "Download events from GitHub Archive"
  cmd("aggregate") action { (_, c) => c.copy(command = "aggregate") } text "Aggregate stars time-series from cached event archives" children(
    opt[Boolean]('d', "dry-run") optional() valueName "<true>" action { (x, c) => c.copy(dryRun = x) } text "Do not store the time-series locally, instead print the events on the standard output"
  )

  checkConfig {
    case CommandLineConfig(_, _, _, "") => failure("No command given.")
    case _ => success
  }

  override def showUsageOnError = true
}
