
val scala212Version = "2.12.15"
val scala213Version = "2.13.8"

val commonSettings = Seq(
  scalaVersion       := scala213Version,
  crossScalaVersions := Seq(scala212Version, scala213Version),
  organization       := "dev.procgen",
)

lazy val scallion = project
  .in(file("."))
  .settings(
    commonSettings,
    name := "scallion",

    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Ypatmat-exhaust-depth", "off"
    ),

    Compile / doc / scalacOptions ++= Seq(
      "-groups",
      "-sourcepath", baseDirectory.value.getAbsolutePath,
      "-doc-source-url", "https://raw.githubusercontent.com/epfl-lara/scallion/master€{FILE_PATH}.scala",
      "-doc-root-content", baseDirectory.value + "/project/root-doc.txt"
    ),

    Compile / doc / target := baseDirectory.value / "docs",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % "test",
      "dev.procgen" %% "silex" % "0.6.0-SNAPSHOT" % "test",
    ),

    licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0")),

    versionScheme := Some(VersionScheme.EarlySemVer),

    publishTo := {
      if (isSnapshot.value)
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/snapshots"))(Resolver.ivyStylePatterns))
      else
        Some(Resolver.file("local-ivy", file(Path.userHome.absolutePath + "/.ivy2/local/releases"))(Resolver.ivyStylePatterns))
    },

    ThisBuild / publishMavenStyle := false,

    releasePublishArtifactsAction := PgpKeys.publishLocalSigned.value,
    usePgpKeyHex("D4CCC34729CBF6A632863E506CB956CFB2863F7A"),
  )
/*
lazy val example = project
  .in(file("example"))
  .settings(
    commonSettings,
    name := "scallion-examples",
    scalaSource in Compile := baseDirectory.value,
    libraryDependencies += "ch.epfl.lara" %% "silex" % "0.6",
  )
  .dependsOn(scallion)

lazy val benchmark = project
  .in(file("benchmark"))
  .settings(
    commonSettings,
    name                   := "scallion-benchmarks",
    fork in run            := true,
    run / baseDirectory    := file("."),
    javaOptions in run     += "-Xss1024K",
    scalaSource in Compile := baseDirectory.value / "src",
    scalaSource in Test    := baseDirectory.value / "src",
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases",
    resolvers += "bintray-djspiewak-maven" at "https://dl.bintray.com/djspiewak/maven",
    libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.19",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    libraryDependencies += "com.codecommit" %% "parseback-core" % "0.3",
    libraryDependencies += "com.codecommit" %% "parseback-cats" % "0.3",
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Test := false,
  )
  .dependsOn(scallion)
*/

