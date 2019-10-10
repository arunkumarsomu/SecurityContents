/**
 * Title:         SAXTool.java
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * http://www.triveratech.com   
 * </p>
 * @author Trivera Technologies Tech Team.
 */


package trivera.xml.kit.saxtools;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import trivera.xml.kit.ui.trays.SAXTray;

/**
 * This Tray is set up as the SAX tools within the training kit.
 * All of the functionality that is associated with the SAX parsers
 * and their the handlers is dealt with in this class.
 *
 * @version  Training Course
 */
public class SAXTool {

/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/
  //Parser-related variables
  private SAXParserFactory saxFactory;
  private XMLReader nonSAXParser = null;
  private XMLReader validSAXParser = null;

  //Output Collector
  private StringBuffer outputCollector = new StringBuffer();

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * SAXTool constructor sets up the SAX parsers (createSAXParsers).
  */
  public SAXTool() {
    super();

    //Setup the validating and non-validating parsers (as XMLReaders)
    createSAXParsers();
  }


/********************************************************************/
/*********************SAX Parser Methods*************************/
/********************************************************************/

  /** Method instantiates a factory and generates both a validating
  * and a non-validating SAX XMLReader to be used later.
  * Note that, if there is a problem creating the parsers,
  * the associated XMLReader variable will be set to null.
  */
  private void createSAXParsers(){
    //Create a SAXParserFactory
    saxFactory = SAXParserFactory.newInstance();

    //Create the non-validating XMLReader/Parser
    nonSAXParser = createSAXParser(false);

    //Create the validating XMLReader/Parser
    validSAXParser = createSAXParser(true);

  }

  /*Method creates a SAX parser of the appropriate type and then
  * encapsulates the parser in an XMLReader.
  * @parameter validating boolean flag indicating whether the parser should
  * be validating or not.
  * @return XMLReader or null if there is a problem creating the parser.*/
  private XMLReader createSAXParser(boolean validating){

	  //Set configuration options
	  saxFactory.setValidating(validating);
    saxFactory.setNamespaceAware(true);
    try {
    	saxFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
	} catch (SAXNotRecognizedException e) {
		handleError("Failed to configure parser: " + e.getMessage());
		return null;
	} catch (SAXNotSupportedException e) {
		handleError("Failed to configure parser: " + e.getMessage());
		return null;
	} catch (ParserConfigurationException e) {
		handleError("Failed to configure parser: " + e.getMessage());
		return null;
	}
    //System.setProperty("entityExpansionLimit","10");

	  //Create a JAXP SAX Parser
    SAXParser saxParser;
    try {
      saxParser = saxFactory.newSAXParser();
    } catch (ParserConfigurationException pce) {
			  handleError("Failed to configure parser: " + pce.getMessage());
        return null;
    } catch (org.xml.sax.SAXException saxerr) {
        handleError("Error setting up parser - " + saxerr.getMessage() );
	      return null;
    };
    

    //Setup an encapsulated XMLReader
    XMLReader reader = null;
    try {
	    reader = saxParser.getXMLReader();
//	    reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    	if (validating){
    		reader.setFeature("http://apache.org/xml/features/validation/schema", true);
    	};
    } catch (org.xml.sax.SAXException saxerr) {
        handleError("Error setting up XMLReader " + saxerr.getMessage() );
        return null;
    };
    
System.out.println(System.getProperty("java.version"));


    return reader;
  }

/********************************************************************/
/*********************SAX Processing Methods*************************/
/********************************************************************/

  /**Method essentially parses the selected file using a default content
  * handler.  The content and error handlers are set prior to the parse.
  */
  public StringBuffer parseXML(String filename){
	 //Reset the buffer collecting the output
	 outputCollector.setLength(0);

	    //Check for parser, if not there, drop out....
    if (nonSAXParser == null){
      handleError("There is no non-validating SAX parser available!");
      return outputCollector;
    };

    if (filename == null)
      return outputCollector;

		//Can use the handleError method (used above) to handle any errors that you have.
    
    
	  //Set a Content Handler for events
	  SAXCounter dummyHandler = new SAXCounter(outputCollector);
      nonSAXParser.setContentHandler(dummyHandler);
	  //Set an Error Handler
	  SAXErrorHandler errHndlr = new SAXErrorHandler();
	  nonSAXParser.setErrorHandler(errHndlr);
	  InputSource target = new InputSource(filename);
	  //Begin the Parsing process
    try {
	    nonSAXParser.parse(target);
    } catch (org.xml.sax.SAXException saxerr) {
        handleError("Error parsing document: " + saxerr.getMessage() );
        return outputCollector;
    } catch (IOException ioe) {
        handleError("Error opening document: " + ioe.getMessage() );
        return outputCollector;
    }catch (Exception e) {
        handleError("Error processing document, likely caused trying to process a DTD when not allowed" );
        return outputCollector;
    };
    return outputCollector;
  }

  /**Method essentially parses the selected file using a default content
  * handler.  The content and error handlers are set prior to the parse.
  *
  */
  public void validateXML(String filename){
    //Check for parser, if not there, drop out....
    if (validSAXParser == null){
      handleError("There is no validating SAX parser available!");
      return;
    };

    if (filename == null)
      return;
      
	  //Set a Content Handler for events
	  DefaultHandler dummyHandler = new DefaultHandler();
	  validSAXParser.setContentHandler(dummyHandler);
	  //Set an Error Handler
	  SAXErrorHandler errHndlr = new SAXErrorHandler();
	  validSAXParser.setErrorHandler(errHndlr);
	  //Begin the Parsing process
    try {
	    validSAXParser.parse(filename);
    } catch (org.xml.sax.SAXException saxerr) {
        handleError("Error parsing document: " + saxerr.getMessage() );
        return;
    } catch (IOException ioe) {
        handleError("Error opening document: " + ioe.getMessage() );
        return;
    };

  }

  /**Used to generate various counts associated with the
  * XML document of interest.  After instantiating a SAXCounter instance
  * to handle the SAX events, processing is handed off to a generic
  * parsing routine.  The output is collected and returned.
  */
  public StringBuffer countXML(String filename){

    //Reset the buffer collecting the output
    outputCollector.setLength(0);

    //Check for parser, if not there, drop out....
    if (true){
      handleError("There is no non-validating SAX parser available!");
      return outputCollector;
    };

	  //Set a SAXCounter as Content Handler for events with outputCollector

    //Run the parser on the filename

    return outputCollector;
  }

  /**Used to generate a trace of XML entities associated with the
  * XML document of interest.  After instantiating a SAXTrace instance
  * to handle the SAX events, processing is handed off to a generic
  * parsing routine.  The output is collected and returned.
  */
  public StringBuffer traceXML(String filename){
    //Reset the buffer collecting the output
    outputCollector.setLength(0);
    //Check for parser, if not there, drop out....
    if (true){
      handleError("There is no SAX parser available!");
      return outputCollector;
    };

	  //Set a Content Handler for events with outputCollector passed in.

	  //Run the parser with new content handler.

    return outputCollector;
 }

  /**Used to generate a list of targeted content associated with the
  * XML document of interest.  After instantiating a SAXList instance
  * to handle the SAX events, processing is handed off to a generic
  * parsing routine.  The output is collected and returned.
  */
  public StringBuffer listXML(String filename){
    //Reset the buffer collecting the output
    outputCollector.setLength(0);
    //Check for parser, if not there, drop out....
    if (true){
      handleError("There is no non-validating SAX parser available!");
      return outputCollector;
    };

	  //Set a Content Handler for events with output target passed in.

    //Run the parser
    
    return outputCollector;
  }

/********************************************************************/
/*************************Utility Methods****************************/
/********************************************************************/

  /**Method creates a URL object from either a URL string or a plain
  * file name.  If the string is a valid URL, then a URL object can be
  * directly instantiated.  If that fails, assume the string is a filename
  * and needs appropriate prefix added on to it.
  *
  * @parameter name String containing characters to construct URL with.
  * @return URL constructed from string.
  */
  static protected URL createURL(String name) throws Exception {
    URL u;
    try {
      u = new URL(name);
    } catch (MalformedURLException ex) {
      u = new URL("file:" + new File(name).getAbsolutePath());
    };
    return u;
  }

  /**Method handles error conditions by constructing appropriate string
  * and passing to the SAXTray's handleError method
  *
  * @parameter message String containing characters for error message.
  */
  private void handleError(String baseMessage) {
    SAXTray.handleError(baseMessage);
  }


} // class SAXTool
