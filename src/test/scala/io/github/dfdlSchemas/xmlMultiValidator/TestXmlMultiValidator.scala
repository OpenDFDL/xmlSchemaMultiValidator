package io.github.dfdlSchemas.xmlMultiValidator

import org.junit.AfterClass
import org.junit.Test

object TestXmlMultiValidator {

}

class TestXmlMultiValidator {

  import TestXmlMultiValidator._

  @Test def test_xmlMultiValidator_01(): Unit = {
    val sf = getClass.getResource("simple.xsd")
    val xf = getClass.getResource("simple.xml")
    val v = XMLMultiValidator(sf)
    v.validate(xf)
  }
}
