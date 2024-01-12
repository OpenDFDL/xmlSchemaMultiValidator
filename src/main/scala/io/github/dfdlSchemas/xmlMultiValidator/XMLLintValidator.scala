package io.github.dfdlSchemas.xmlMultiValidator

import java.net.URL
import java.io.File
import java.util.Objects

abstract class CommandLineValidator(name: String, schemaFile: URL) extends Validator {
  Objects.requireNonNull(schemaFile)
  Objects.requireNonNull(name)

  protected def commandLine(schemaFile: File, xmlFile: File): os.proc

  final override def validate(xmlFile: URL): Unit = {
    Objects.requireNonNull(xmlFile)
    try {
      val proc = commandLine(File(schemaFile.toURI), File(xmlFile.toURI))
      val result = proc.call(cwd = os.pwd)
      if result.exitCode == 0 then {
        println(s"Validation with $name successful.")
      } else {
        toss(new ValidatorException(s"Validation with $name failed."))
      }
    } catch {
      case e: Exception => toss(new ValidatorException(s"$name error", e))
    }
  }
}

class XMLLintValidator(schemaFile: URL)
  extends CommandLineValidator("xmllint", schemaFile) {

  override protected def commandLine(sf: File, xf: File): os.proc = {
    os.proc("xmllint", "--noout", "--schema", sf.getAbsolutePath, xf.getAbsolutePath)
  }

}
