name := "xmlSchemaMultiValidator"

version := "1.0"

scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "org.codehaus.woodstox" % "woodstox-core-asl" % "4.4.1",
  "org.codehaus.woodstox" % "stax2-api" % "4.2.2",
  "net.java.dev.msv" % "msv-core" % "2011.1",
  "xerces" % "xercesImpl" % "2.12.2",
  "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
  "com.lihaoyi" %% "os-lib" % "0.9.3",
  "junit" % "junit" % "4.13.2" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-no-indent" // disable scala 3's indentation-based syntax
  // "-Xlint"
)
