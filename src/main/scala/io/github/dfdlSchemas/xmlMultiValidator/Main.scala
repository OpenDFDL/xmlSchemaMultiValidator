package io.github.dfdlSchemas.xmlMultiValidator

import java.io.File

class Main extends App {

  val xmlFile = File(args(0)).toURI.toURL
  val schemaFile = File(args(1)).toURI.toURL
  val mv = XMLMultiValidator(schemaFile)
  mv.validate(xmlFile)

}
