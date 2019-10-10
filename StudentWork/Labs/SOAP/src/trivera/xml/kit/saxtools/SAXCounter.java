/**
 * Title:         SAXCounter.java
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

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class is a SAX Event Handler class that counts various types of
 * events and outputs the results to the JTextArea that is passed in when
 * the class is instantiated.
 *
 * @version  Training Course
 */

 public class SAXCounter extends DefaultHandler {
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  /** Elements. */
  private long elements;

  /** Attributes. */
  private long attributes;

  /** Characters. */
  private long characters;

  /** Output Target */
  private StringBuffer out;

/********************************************************************/
/*************************Constructor**********************************/
/********************************************************************/

  /**
  * SAXCounter constructor sets up the text area for outputing the
  * count results
  * @parameter targetOutput JTextArea that will have the count results
  * sent to it.
  */
  public SAXCounter(StringBuffer targetOutput) {
    out = targetOutput;
  }

/********************************************************************/
/*************************Methods**********************************/
/********************************************************************/


  /** Respond to start document by initializing counters.*/
  public void startDocument() {
	  elements = 0;
	  attributes = 0;
	  characters = 0; 

  }

  /** Respond to start element. */
  public void startElement(String uri, String local,
                                          String raw, Attributes attrs) {
	  elements = elements + 1;
	  attributes = attributes + attrs.getLength();

  }

  /** Characters. */
  public void characters(char[] ch, int start, int length) {
    characters = characters + length;

  }

  /** Respond to end document by outputing the counts to the target text area.*/
  public void endDocument() {
    out.append("\n\n*****XML Counts******\n");
    out.append("Number of Elements: " + (new Long(elements)).toString() + "\n");
    out.append("Number of Attributes: " + (new Long(attributes)).toString() + "\n");
    out.append("Number of Parsed Character Data: " + (new Long(characters)).toString() + "\n");

    out.append("**********************\n");
  }
  
}  //class SAXCounter
