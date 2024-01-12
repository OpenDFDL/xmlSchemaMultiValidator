package io.github.dfdlSchemas.xmlMultiValidator;

import com.ctc.wstx.stax.WstxInputFactory;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;

import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.ctc.wstx.api.WstxInputProperties.PARSING_MODE_DOCUMENT;
import static com.ctc.wstx.api.WstxInputProperties.P_INPUT_PARSING_MODE;

public class WoodstoxValidator extends Validator {

  private WstxInputFactory readerFactory = null;

  WoodstoxValidator(URL schemaFile) {
    init(schemaFile);
  }

  private void init(URL schemaFile) {
    try {
      Objects.requireNonNull(schemaFile);
      XMLValidationSchemaFactory schemaFactory = XMLValidationSchemaFactory.newInstance(
          XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
      XMLValidationSchema schema = schemaFactory.createSchema(schemaFile);

      readerFactory = (WstxInputFactory) WstxInputFactory.newInstance();
      readerFactory.configureForConvenience();
      readerFactory.setProperty(P_INPUT_PARSING_MODE, PARSING_MODE_DOCUMENT);
      // just to be sure the schema is ok in case this validator doesn't check
      // until a reader is created we create and discard a reader
      readerFactory.createXMLStreamReader(
          new ByteArrayInputStream("<fake/>".getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      toss(new ValidatorException(e));
    }
  }

  public void validate(URL xmlFile) {
    Objects.requireNonNull(xmlFile);
    Objects.requireNonNull(readerFactory);
    XMLStreamReader reader = null;
    try (InputStream fileInputStream = xmlFile.openStream()) {
      try  {
         reader = readerFactory.createXMLStreamReader(fileInputStream);
        while (reader.hasNext()) {
          reader.next();
        }
        System.err.println("Validation with woodstox successful.");
      } catch (Exception e) {
        toss(new ValidatorException("Woodstox XML validation failed", e));
      } finally {
        if (reader != null) {
          reader.close();
        }
      }
    } catch (Exception e) {
      toss(new ValidatorException(e));
    }
  }
}