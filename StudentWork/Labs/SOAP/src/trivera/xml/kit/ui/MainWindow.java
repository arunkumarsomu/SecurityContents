/**
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

package trivera.xml.kit.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import trivera.xml.kit.ui.trays.SAXTray;

/**
 * Main window is the central point of the application display.  It is basically
 * set up as a tabbed pane interface with each tab accessing different
 * functionality.
 *
 * @version  Training Course
 */
public class MainWindow extends JFrame{

/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/



  //GUI-related variables
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel contentPane;
  private Dimension preferredSize;
  //Used to determine screen size in setting up window sizing
  private Toolkit thisToolkit = Toolkit.getDefaultToolkit();
  //Main Pane in window used for sizing calculations by other components
  public static JTabbedPane mainPane;
  //The tray for SAX operations
  public static SAXTray saxTray;
  //The tray for DOM operations
//  public static DOMTray domTray;
  //The tray for XSL operations
//  public static XSLTray xslTray;

  // Set up for getting persistent locations for file operations.
  public static File defaultDirectory = new File(System.getProperty("user.dir"));

  //Set up for getting persistent locations for file operations.
  public static File defaultFilename;

/********************************************************************/
/*************************Constructor**********************************/
/********************************************************************/
  /**
  * MainWindow constructor sets up the user interface, catch any errors that
  * might be output during initialization that are not caught elsewhere...
  */
  public MainWindow() {
    try  {
      init();
    }
    catch (Exception e) {
      System.err.println( "Error trying to initialize main window interface." );
      e.printStackTrace(System.err);
    }
  }

/********************************************************************/
/*************************Methods**********************************/
/********************************************************************/

  /**
  * Method directs the initial construction of the main window user interface.
  */
  private void init() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setTitle("XML Training Kit");
    //Handles setting up the menus, toolbar, and various tabbed displays.
    completeInit();
    //Pretty much set the main window to take over the screen's real estate
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int targetHeight = screen.height-70;
    int targetWidth = screen.width-20;
    preferredSize = new Dimension(targetWidth, targetHeight);
    setSize(preferredSize);
  }

  /**Completes initialization of the main window */
  private void completeInit() {
    mainPane = new JTabbedPane();
    getContentPane().add(mainPane, BorderLayout.CENTER);
    saxTray = new SAXTray();
    mainPane.addTab("SAX Tray", saxTray);
//    domTray = new DOMTray();
//    mainPane.addTab("DOM Tray", domTray);
//    xslTray = new XSLTray();
//    mainPane.addTab("XSL Tray", xslTray);

    mainPane.setBackgroundAt(0, Color.lightGray);
//    mainPane.setBackgroundAt(1, Color.lightGray);
//    mainPane.setBackgroundAt(2, Color.lightGray);
  }

  public Dimension getPreferredSize(){
    return preferredSize;
  }

}  //class MainWindow

