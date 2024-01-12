package io.github.dfdlSchemas.xmlMultiValidator

import java.net.URL

class XMLMultiValidator (schemaFile: URL) extends Validator {

  private val javaxXmlValidator = JavaxXmlValidator(schemaFile)
  private val woodstoxValidator = WoodstoxValidator(schemaFile)
  private val xercesJValidator = XercesJSAXValidator(schemaFile)
  private val xmlLintValidator = XMLLintValidator(schemaFile)
//  private val xercesCValidator = XercesCValidator(schemaFile)

  override def validate(xmlFile: URL): Unit = {
    xercesJValidator.validate(xmlFile)
    javaxXmlValidator.validate(xmlFile)
    woodstoxValidator.validate(xmlFile)
    xmlLintValidator.validate(xmlFile)
//    xercesCValidator.validate(xmlFile)
  }

}

    