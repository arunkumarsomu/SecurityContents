/**
 * Title:         FileChooser.java
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


package trivera.xml.kit.ui.utils;


import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFrame;

import trivera.xml.kit.ui.MainWindow;

/** Simply a utility method used to do a bit of error checking as part of the
 * file selection process.  A FileChooser instance can handle either an input
 * file selection or an output file selection.  Note that this class is set
 * up to assign a selected file to a coordinator's "holder" of that file
 * reference.  That holder is used to ensure that a folder/file selection
 * that a user has made will persist and that user will not have to repeatably
 * navigate from a default directory to a user directory.
 * @version  Training Course
 */

public class FileChooser {
  //Publically accessible dialog
  public FileDialog dialog;
   //public JFileChooser dialog;
  JFrame parent;

  public FileChooser(JFrame parentFrame) {
    parent = parentFrame;
    //Create a file dialog to prompt for file to write to
    dialog = new FileDialog(parent);
  }

/** Handles the actual requesting and checking of that request
*/
  public void askForOpen(){

    boolean fileNotGotten = true;
    dialog.setDirectory(MainWindow.defaultDirectory.getAbsolutePath());
    dialog.setMode(FileDialog.LOAD);

    //Loop to check and eventually get a file to open
    while (fileNotGotten) {
      dialog.show();

      //Check to see if the user has cancelled out of the file selection process.
      if (dialog.getFile() == null) break;

      String candidateName = dialog.getDirectory().concat(dialog.getFile());
      File candidate_file = new File(candidateName);
      File candidate_directory = new File (dialog.getDirectory());

      //First check to see if we can even read from this directory...
      if (!candidate_directory.canRead())  {
        Message.error(parent, dialog.getDirectory() +
          " cannot be read from.  Please select another filename.");
        dialog.setDirectory(MainWindow.defaultDirectory.getAbsolutePath());
        continue;
      };
      //Check to see if the file exists
      if (!candidate_file.exists()) {
         Message.error(parent, candidateName +
                        " does not exist.  Please select another filename.");
        dialog.setFile(null);
        continue;
      };
      //If it exists and cannot be read, then request another file name.
      if (!candidate_file.canRead()) {
        Message.error(parent, candidateName +
          " exists but cannot be read!  Please select another filename.");
        dialog.setFile(null);
        continue;
      };
      fileNotGotten = false;
    };

    if (fileNotGotten){
      MainWindow.defaultFilename = null;
    }
    else {
      MainWindow.defaultFilename =
        new File(dialog.getDirectory(), dialog.getFile());
      MainWindow.defaultDirectory =
        new File (dialog.getDirectory());
    };
    dialog.dispose();
  }

/** Handles the actual requesting and checking of that request
*/
  public void askForOut() {

    boolean fileNotFound = true;
    dialog.setDirectory(MainWindow.defaultDirectory.getAbsolutePath());
    dialog.setMode(FileDialog.SAVE);

    while (fileNotFound) {
      dialog.show();
      //Check to see if the user has cancelled out of the file selection process.
      if (dialog.getFile() == null) break;

      String candidateName = dialog.getDirectory().concat(dialog.getFile());
      File candidate_file = new File(candidateName);
      File candidate_directory = new File(dialog.getDirectory());
//First check to see if we can even write to this directory...
      if (!candidate_directory.canWrite())  {
        Message.error(parent, dialog.getDirectory() +
                " cannot be written to.  Please select another directory.");
        dialog.setFile(null);
        continue;
      };
//Check to see if the file exists
      if (!candidate_file.exists()) {
         fileNotFound = false;
         break;
      };
//If it exists and cannot be overwritten, then request another file name.
      if (!candidate_file.canWrite()) {
        Message.error(parent, candidateName +
          " already exists and cannot be overwritten!" +
          "  Please select another filename.");
        dialog.setFile(null);
        continue;
      };
      fileNotFound = false;
      break;
    };

    if (fileNotFound){
      MainWindow.defaultFilename = null;
    }
    else {
      MainWindow.defaultFilename =
        new File(dialog.getDirectory(), dialog.getFile());
      MainWindow.defaultDirectory =
        new File (dialog.getDirectory());
    };
    dialog.dispose();
  }

}
