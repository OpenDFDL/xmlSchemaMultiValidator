package io.github.dfdlSchemas.xmlMultiValidator;

import java.io.File;
import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;

public class JavaxXmlValidator
    extends io.github.dfdlSchemas.xmlMultiValidator.Validator {

  private javax.xml.validation.Validator validator = null;

  public JavaxXmlValidator(URL schemaFile) {
      try {
        SchemaFactory factory = SchemaFactory.newInstance(
            XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(schemaFile);
        validator = schema.newValidator();
      } catch (Exception e) {
        toss(new ValidatorException("javax.xml.validation: Unable to load schema.", e));
      }
    }

    @Override
    public void validate(URL xmlFile) {
      try {
        validator.validate(new StreamSource(xmlFile.openStream()));
        System.err.println("Validation with javax.xml.validation successful.");
      } catch (Exception e) {
        toss(new ValidatorException("Validation with javax.xml.validation failed", e));
      }
    }
}
