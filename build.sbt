import Dependencies._

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.raphailinc"
ThisBuild / organizationName := "Raphailinc"

lazy val root = (project in file("."))
  .settings(
    name := "bigdatalab",
    libraryDependencies += munit % Test,
    testFrameworks += new TestFramework("munit.Framework"),
    Compile / run / mainClass := Some("example.Main"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint"
    )
  )

addCommandAlias("fmt", "scalafmtAll")
addCommandAlias("check", ";scalafmtCheckAll;test")

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
