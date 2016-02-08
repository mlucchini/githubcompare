package info.githubcompare.cli

import java.util.Calendar

case class CommandLineConfig(start: Calendar = Calendar.getInstance(), end: Calendar = Calendar.getInstance(),
                             dryRun: Boolean = false, command: String = "")