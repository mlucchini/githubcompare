import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin._

assemblySettings

name := "githubcompare"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test",
  "io.spray" %%  "spray-json" % "1.3.2",
  "joda-time" % "joda-time" % "2.9.2",
  "com.github.scopt" %% "scopt" % "3.3.0",
  "com.github.nscala-time" %% "nscala-time" % "2.8.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.holdenkarau" % "spark-testing-base_2.11" % "1.6.0_0.3.1" exclude("org.slf4j", "slf4j-log4j12"),
  "org.apache.spark" % "spark-core_2.11" % "1.6.0" exclude("org.slf4j", "slf4j-log4j12")
)

resolvers += Resolver.sonatypeRepo("public")

mergeStrategy in assembly <<= (mergeStrategy in assembly) ((old) => {
  case x if Assembly.isConfigFile(x) => MergeStrategy.concat
  case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) => MergeStrategy.rename
  case PathList("META-INF", xs @ _*) => xs map {_.toLowerCase} match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) => MergeStrategy.discard
      case ps @ (x :: _) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") => MergeStrategy.discard
      case "plexus" :: _ => MergeStrategy.discard
      case "services" :: _ => MergeStrategy.filterDistinctLines
      case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) => MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first
    }
  case PathList(_*) => MergeStrategy.first
})