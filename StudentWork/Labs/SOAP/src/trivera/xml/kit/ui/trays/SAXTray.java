/**
 * Title:         SAXTray.java
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


package trivera.xml.kit.ui.trays;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import trivera.xml.kit.XMLTrainingKit;
import trivera.xml.kit.saxtools.SAXTool;
import trivera.xml.kit.ui.utils.FileChooser;

/**
 * This Tray is set up as the SAX "tray" of labs within the kit.  All
 * of the UI and functionality that is associated with this tray revolves
 * around the SAX parsers and the handlers asscoiated with them.
 *
 * @version  Training Course
 */
public class SAXTray extends JPanel {

/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/
  //Parser-related variables
  private SAXTool saxTool;
  private static boolean inErrorCondition;

  //User Interface-related variables
  static private JTextArea messageText;
  private JTextArea sourceText, outputText;
  private JPanel messagePanel, sourcePanel, outputPanel;
  private JToolBar saxToolbar;
  private BorderLayout borderLayout1 = new BorderLayout();
  static public JSplitPane mainSplitPane;
  public JSplitPane upperSplitPane;

  //Holds the filename selected by user
  private String filename;

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * SAXTray constructor build the user interface (createSAXTray) and
  * sets up the DOM parsers (createSAXParsers).  If either of the parsers
  * cannot be created, then the toolbar is hidden from the user and no
  * functionality is available.
  */
  public SAXTray() {
    super();

    //Setup the GUI
    createSAXTray();

    //Setup the parsing tool
    saxTool = new SAXTool();
  }

/********************************************************************/
/*********************Button Response Methods*************************/
/********************************************************************/

  /**Used to generate various counts associated with the
  * XML document of interest.
  */
  public void countXML(){
    //Get a file name from the user
    getFilename();
    if (filename == null)
      return;

    //Reset the SAX tray
    refresh();

    // Set up the source pane, uses a file reader to get the source text
    displayTextFile(filename, sourceText);

    //Invoke the SAXTool to count the elements
    StringBuffer results = saxTool.countXML(filename);

    //Handle the results of the parse.
    if (results.length() > 0)
      outputText.append(results.toString());
    if (!inErrorCondition)
      messageText.append("\n" + filename+" successfully parsed!\n");
  }

  /**Method essentially parses the selected file using a default content
  * handler. Once the user has selected a file, it is displayed in the
  * source panel and then parsed using a validating parse.
  */
  public void parseXML(){
    //Get a file name from the user
    getFilename();
    if (filename == null)
      return;

    //Reset the SAX tray
    refresh();


    //Invoke SAXTool to perform a non-validating SAX parse
    StringBuffer results = saxTool.parseXML(filename);

    // Set up the source pane, uses a file reader to get the source text
    displayTextFile(filename, sourceText);

    //Handle the results of the parse.
    if (results.length() > 0)
      outputText.append(results.toString());
    if (!inErrorCondition)
      messageText.append("\n" + filename+" successfully parsed!\n");
  }

  /**Method essentially parses the selected file using a default content
  * handler. Once the user has selected a file, it is displayed in the
  * source panel and then parsed using a validating parse.
  */
  public void validateXML(){
    //Get a file name from the user
    getFilename();
    if (filename == null)
      return;

    //Reset the SAX tray
    refresh();

    // Set up the source pane, uses a file reader to get the source text
    displayTextFile(filename, sourceText);

    //Invoke SAXTool to perform a validating SAX parse
    saxTool.validateXML(filename);

    if (!inErrorCondition)
      messageText.append("\n" + filename+" is valid.\n");
  }

  /**Used to generate a trace of XML entities associated with the
  * XML document of interest.  Once the user has selected a file, it is displayed in the
  * source panel and then handed off to the SAXTool
  */
  private void traceXML(){
    //Get a file name from the user
    getFilename();
    if (filename == null)
      return;

    //Reset the SAX tray
    refresh();

    // Set up the source pane, uses a file reader to get the source text
    displayTextFile(filename, sourceText);

    //Invoke the SAXTool to count the elements
    StringBuffer results = saxTool.traceXML(filename);

    //Handle the results of the parse.
    if (results.length() > 0)
      outputText.append(results.toString());
    if (!inErrorCondition)
      messageText.append("\n" + filename+" successfully parsed!\n");
 }

  /**Used to generate a list of targeted content associated with the
  * XML document of interest.    Once the user has selected a file, it is displayed in the
  * source panel and then handed off to the SAXTool
  */
  private void listXML(){
    //Get a file name from the user
    getFilename();
    if (filename == null)
      return;

    //Reset the SAX tray
    refresh();

    // Set up the source pane, uses a file reader to get the source text
    displayTextFile(filename, sourceText);

    //Invoke the SAXTool to count the elements
    StringBuffer results = saxTool.listXML(filename);

    //Handle the results of the parse.
    if (results.length() > 0)
      outputText.append(results.toString());
    if (!inErrorCondition)
      messageText.append("\n" + filename+" successfully parsed!\n");
  }

  /**Method is used to refresh the SAX tray.  This method is invoked from the
  * button as well as at the completion of parses.  It is meant to handle
  * refreshing whether the parse was sucessful or not.
  */
  private void refresh(){
    inErrorCondition = false;
    outputText.setText("");
    mainSplitPane.setDividerLocation(0.88);
    sourceText.setText("");
    messageText.setText("");
    messageText.setForeground(Color.black);
  }


/********************************************************************/
/*********************User Interface Methods*************************/
/********************************************************************/

  /**Method initializes the SAX GUI, assuming there is no document to parse and
  * display.  This intialization places the SAX Tray in its base state.  The
  * SAX Tray has three panes:
  * message - informational and error messages (typically from parser) go here
  * source - displays the actual text of the file being parsed
  * (including whitespace)
  * output - displays the output generated by a content handler.
  */
  private void createSAXTray() {
    //Basic initialization stuff here
    setLayout(borderLayout1);

    //First create the message text/panel so we can send messages to it...
    messageText = new JTextArea(3,40);
    messageText.setFont(new Font("dialog", Font.PLAIN, 12));
    messagePanel = new JPanel(new BorderLayout());
    messagePanel.add(new JScrollPane(messageText){
        public Dimension getPreferredSize(){
          Dimension traySize =
                        XMLTrainingKit.getMainWindow().mainPane.getSize();
          return new Dimension(traySize.width, traySize.height);
        }
        public Dimension getMinimumSize(){
          return new Dimension(100, 20);
        }
      }, BorderLayout.CENTER);
    messagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createTitledBorder("Messages"),
      BorderFactory.createEmptyBorder(1, 1, 1, 1)));

    //Now create the text/panel for XML source as well as the SAX Toolbar
    sourceText = new JTextArea();
    sourceText.setFont(new Font("monospaced", Font.PLAIN, 12));
    sourceText.setBackground(Color.white);
    sourceText.setForeground(Color.black);
    sourceText.setSelectedTextColor(Color.red);
    sourceText.setSelectionColor(Color.lightGray);
    sourceText.setEditable(false);
    sourcePanel = new JPanel(new BorderLayout());
    createSAXToolbar();
    sourcePanel.add(saxToolbar, BorderLayout.SOUTH);

    sourcePanel.add(new JScrollPane(sourceText){
        public Dimension getPreferredSize(){
          Dimension traySize =
                        XMLTrainingKit.getMainWindow().mainPane.getSize();
          return new Dimension(traySize.width / 2, traySize.height * 4 / 5);
        }
        public Dimension getMinimumSize(){
          return new Dimension(100, 200);
        }
      }, BorderLayout.CENTER);
    sourcePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createTitledBorder("Source View"),
      BorderFactory.createEmptyBorder(1, 1, 1, 1)));

    //Now create the TextArea for SAX output
    outputText = new JTextArea();
    outputText.setFont(new Font("monospaced", Font.PLAIN, 12));
    outputText.setBackground(Color.white);
    outputText.setForeground(Color.black);
    outputText.setSelectedTextColor(Color.red);
    outputText.setSelectionColor(Color.lightGray);
    outputText.setEditable(false);
    outputPanel = new JPanel(new BorderLayout());
    outputPanel.add(new JScrollPane(outputText){
        public Dimension getPreferredSize(){
          Dimension traySize =
                          XMLTrainingKit.getMainWindow().mainPane.getSize();
          return new Dimension(traySize.width / 2, traySize.height * 4 / 5);
        }
        public Dimension getMinimumSize(){
          return new Dimension(100, 200);
        }
      }, BorderLayout.CENTER);
    outputPanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createTitledBorder("SAX Output"),
      BorderFactory.createEmptyBorder(1, 1, 1, 1)));


    //Use upper JSplitPane to dynamically resize between source and tree
    upperSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                        true, sourcePanel, outputPanel);

    //Use main JSplitPane to dynamically resize between DOM and Message
    mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                     true, upperSplitPane, messagePanel);
                                     
    //Add the new pane to this window
    add(mainSplitPane, BorderLayout.CENTER);
  }

  /** Method creates the tool bar for the buttons on the SAX tray.  This is
  * where the various user initiated actions are invoked from.
  */
  private void createSAXToolbar(){
    saxToolbar = new JToolBar();

    //Parse Button and action
    JButton button = new JButton("SAX Parse");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          parseXML();
        }
    });
    saxToolbar.add(button);
    saxToolbar.addSeparator();

    //Validate with DTD Button and action
    button = new JButton("SAX Validate");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          validateXML();
        }
    });
    //saxToolbar.add(button);
    //saxToolbar.addSeparator();

    //Count Button and action
    button = new JButton("SAX Count");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          countXML();
        }
    });
    //saxToolbar.add(button);
    //saxToolbar.addSeparator();

    //List Button and action
    button = new JButton("SAX List");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          listXML();
        }
    });
    //saxToolbar.add(button);
    //saxToolbar.addSeparator();

    //Trace Button and action
    button = new JButton("SAX Trace");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          traceXML();
        }
    });
    //saxToolbar.add(button);
    //saxToolbar.addSeparator();

    //Refresh Button and action
    button = new JButton("Refresh");
    button.setMargin(new Insets(1, 5, 1, 5));
    button.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          refresh();
        }
    });
    //saxToolbar.add(button);
    //saxToolbar.addSeparator();
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


  /** Method reads the xml file from filename and appends it to the JTextArea
  * passed in.  No parsing or other processing of the text file is performed.
  * Any text file could be used in conjunction with this method.
  * @parameter filename String to be converted into URL and accessed as file
  * @parameter ta JTextArea that is target for character stream from file.
  */
  private void displayTextFile(String filename, JTextArea ta) {
    URL file;
    //If nothing out there, then leave.
    if (filename == null || filename.equals(""))
      return;

    InputStream fis = null;
    BufferedReader dis = null;
    //Try to set up the file for reading.  Close it if fail.
    try {
      file = createURL(filename);
      fis = file.openStream();
      dis = new BufferedReader(new InputStreamReader(fis));
    } catch (Exception ex) {
      handleError("ERROR while trying to read XML file: "+ex);
      try{
        fis.close();
      }
      catch (Exception IOException) {
      };
      return;
    }
    //Set up the variables for reading and writing
    String line;
    int i = 0;
    int len = 0;
    Vector textLine = new Vector();
    String nl = "\n";
    int nllen = nl.length();
    StringBuffer sb = new StringBuffer();
    //Read it one line at a time
    try{
      readline:
        while ((line = dis.readLine()) != null) {
          sb.append(line+nl);
          textLine.addElement(new Integer(len));
          len += line.length()+nllen;
          if (len > 500000){
        	  messageText.append("File is too large to completely display\n");
        	  break;
          }
        };
      ta.append(sb.toString());
    } catch (IOException io) {
        handleError(io.getMessage());
        try{
          fis.close();
        }
        catch (Exception IOException) {
        };
        return;
    }

    try{
      fis.close();
    }
    catch (Exception IOException) {
    };
    return;
  }

  /**Method uses the FileChooser utility to guide the user through selecting
  * a file.  No file extensions are filtered or enforced.  The end effect
  * of this method is to set the filename to the selected value.
  */
  private void getFilename(){
    boolean foundXMLFile = false;
    //Get that file from the user, demanding that it have an xml extension
    FileChooser thisChooser = new FileChooser(XMLTrainingKit.getMainWindow());
    thisChooser.dialog.setTitle("XML Document to Load");
    while (!foundXMLFile){
      thisChooser.askForOpen();
      if (XMLTrainingKit.getMainWindow().defaultFilename == null){
        filename = null;
        return;
      };
      foundXMLFile = true;
    };
    filename = XMLTrainingKit.getMainWindow().defaultFilename.getAbsolutePath();
  }

/** Simple method for handling errors.  Set up as static to
* facilitate training by reducing error handling muck in various
* classes.*/
  static public void handleError(String errorMessage){
    inErrorCondition = true;
    messageText.setText("");
    mainSplitPane.setDividerLocation(0.7);
    messageText.setForeground(Color.red);
    messageText.setText(errorMessage + "\n");
    System.err.println(errorMessage);
  }

/**Handles parsing errors that an error handler might catch.
* Set up as static to facilitate training so students do not
* have to deal with muck in their classes.*/
  static public void handleParseError(String errorMessage){
    if (!inErrorCondition)
      handleError(errorMessage);
    messageText.append(errorMessage + "\n");
  }
} // class SAXTray
