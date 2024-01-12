package io.github.dfdlSchemas.xmlMultiValidator

import java.io.File
import java.net.URL

class XercesCValidator(schemaFile: URL) 
  extends CommandLineValidator("Xerces C", schemaFile) {

  override protected def commandLine(schemaFile: File, xmlFile: File): os.proc =
    os.proc("xercesCXMLValidator", "--schema", schemaFile.getAbsolutePath, "--file", xmlFile.getAbsolutePath)

}