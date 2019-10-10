/**
 * Title:         XMLTrainingKit.java
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

package trivera.xml.kit;

import trivera.xml.kit.ui.MainWindow;

/**
 * Sandbox for useful XML tools and utilities.  Acts as overall coordinator for
 *  application. <BR><BR>
 * Notes:<BR>
 * 1. The file strings that are passed into the parser work in general.  However,
 * pathes that are associated with Microsoft NT networks do not work.  This is
 * a known problem.  Have not yet determined a work around other than working
 * with mounted drives.
 *
 * @version  Training Course
 */
public class XMLTrainingKit {
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  //Main Window for application
  private static MainWindow mainWindow;

/********************************************************************/
/*************************Constructor**********************************/
/********************************************************************/

  public XMLTrainingKit() {
  }

/********************************************************************/
/*************************Methods and Main***************************/
/********************************************************************/
  /**Accessor for Main Window value...used by various classes as a way to
   * coordinate with other trays and aspects of the user interface.
   * @return Returns the application's main window that is constructed during
   * initialization.
   */
  public static MainWindow getMainWindow(){
    return mainWindow;
  }

  //Main Pain
  public static void main(String[] args) {
    //Create the main GUI
    mainWindow = new MainWindow();

    //Set up to leave properly on exit.
    mainWindow.addWindowListener(new java.awt.event.WindowAdapter() {
    public void windowClosing(java.awt.event.WindowEvent e) {
      System.exit(0);
      }
    });

    //Get the Main Window out there in the right shape/form
    mainWindow.validate();
    mainWindow.pack();

    //Had to explicitly set the divider locations to allow JRE 1.3
//    mainWindow.domTray.upperSplitPane.setDividerLocation(0.55);
//    mainWindow.domTray.mainSplitPane.setDividerLocation(0.88);
//    mainWindow.xslTray.domSplitPane.setDividerLocation(0.5);
//    mainWindow.xslTray.upperSplitPane.setDividerLocation(0.5);
//    mainWindow.xslTray.mainSplitPane.setDividerLocation(0.88);
    mainWindow.saxTray.upperSplitPane.setDividerLocation(0.55);
    mainWindow.saxTray.mainSplitPane.setDividerLocation(0.88);

    mainWindow.setVisible(true);
  }
} // class XMLTrainingKit