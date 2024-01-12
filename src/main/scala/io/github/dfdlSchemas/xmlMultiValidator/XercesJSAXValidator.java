package io.github.dfdlSchemas.xmlMultiValidator;

import org.apache.xerces.jaxp.SAXParserFactoryImpl;
import org.apache.xerces.jaxp.validation.XMLSchemaFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XercesJSAXValidator extends Validator {

  private XMLReader xmlReader = null;

  private ErrorHandler errHandler = new RethrowErrorHandler();

  public XercesJSAXValidator(URL schemaFile) {
    Objects.requireNonNull(schemaFile);
    try {
      SchemaFactory schemaFactory = new XMLSchemaFactory();
      Schema schema = schemaFactory.newSchema(schemaFile);

      SAXParserFactoryImpl saxParserFactory = new SAXParserFactoryImpl();

      saxParserFactory.setSchema(schema);
      saxParserFactory.setNamespaceAware(true);
//      f.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
//      f.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//      f.setValidating(false); // according to javadoc, just controls DTD validation
//      f.setFeature("http://xml.org/sax/features/validation", true);
//      // not recognized by SAXParserFactory
//      // f.setFeature("http://xml.org/sax/features/validation/dynamic", true);
//      f.setFeature("http://apache.org/xml/features/honour-all-schemaLocations", true);
//      f.setFeature("http://apache.org/xml/features/validation/schema", true);
//      f.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);

      xmlReader = saxParserFactory.newSAXParser().getXMLReader();
      xmlReader.setErrorHandler(errHandler);
//      xmlReader.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//      //
//      // We don't actually know what FEATURE_SECURE_PROCESSING disables
//      // So we also set these individually to their secure settings.
//      //
//      xmlReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//      xmlReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
//      xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);

    } catch (Exception e) {
      toss(new ValidatorException("Xerces J Schema Loading Error", e));
    }
  }

  @Override
  public void validate(URL xmlFile) {
    Objects.requireNonNull(xmlFile);
    Objects.requireNonNull(xmlReader);
    try {
      xmlReader.parse(new InputSource(xmlFile.openStream()));
      System.err.println("Validation with Xerces J (SAX) successful.");
    } catch (Exception e) {
      toss(new ValidatorException("Xerces J Validation/Parsing Error", e));
    }
  }
}

class RethrowErrorHandler extends DefaultHandler {

  private ArrayList<SAXParseException> warnings_ = new ArrayList<SAXParseException>();

  public List<SAXParseException> getWarnings() {
    return warnings_;
  }

  @Override
  public void warning(SAXParseException e) throws SAXException {
    warnings_.add(e);
  }

  @Override
  public void error(SAXParseException e) throws SAXException {
    throw e;
  }

  @Override
  public void fatalError(SAXParseException e) throws SAXException {
    throw e;
  }
}


