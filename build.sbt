
lazy val commonScalacOptions = Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Wunused:imports,patvars,locals",
  "-Wnumeric-widen",
  "-Xlint:-unused"
)

lazy val root =
  project
    .in(file("."))
    .settings(
      name := "mypacklinkproject",
      organization := "com.packlink",
      version := "1.0.0",
      scalaVersion := "2.13.1",
      libraryDependencies ++= appDependencies,
      libraryDependencies ++= testDependencies,
      scalacOptions ++= commonScalacOptions
    )
    .enablePlugins(JavaAppPackaging)
    .configs(IntegrationTest)
    .settings(
      inConfig(IntegrationTest)(Defaults.itSettings),
      inConfig(IntegrationTest)(org.scalafmt.sbt.ScalafmtPlugin.scalafmtConfigSettings),
      IntegrationTest / fork := true,
      IntegrationTest / testForkedParallel := true
    )

lazy val appDependencies = Seq(
  Library.springBoot,
  Library.springBootWeb,
  Library.springBootJetty,
  Library.springBootActuator,
  Library.springBootAmqp,
  Library.janino,
  Library.jacksonScalaModule,
  Library.cats,
  Library.catsEffect
)

lazy val testDependencies = Seq(
  Library.springBootTest,
  Library.mockitoScala,
  Library.scalaTest,
  Library.archUnit,
  Library.testContainers
)
