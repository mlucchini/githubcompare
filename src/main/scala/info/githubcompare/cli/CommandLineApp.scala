package info.githubcompare.cli

import com.typesafe.scalalogging.LazyLogging
import info.githubcompare.commands.{AggregateCommand, DownloadCommand}
import info.githubcompare.util.DateConverter._

object CommandLineApp extends App with LazyLogging {
  new CommandLineParser().parse(args, CommandLineConfig()) match {
    case Some(CommandLineConfig(start, end, _, "download")) => new DownloadCommand(start, end).execute()
    case Some(CommandLineConfig(start, end, dryRun, "aggregate")) => new AggregateCommand(start, end, dryRun).execute()
    case _ =>
  }
}
