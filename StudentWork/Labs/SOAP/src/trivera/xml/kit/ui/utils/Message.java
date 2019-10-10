/**
 * Title:         Message.java
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

import javax.swing.*;
import java.awt.*;

import trivera.xml.kit.XMLTrainingKit;

/**This class is used as a static class to handle various types of messages
* that need to be presented to the user.  Not much too this, simply a utility
* method.  Note that line returns are inserted in the message string to allow
* multi-line message boxes.
*
* @version   Training Course
*/

public class Message {

  public static int YES = JOptionPane.YES_OPTION;
  public static int NO = JOptionPane.NO_OPTION;
  public static int CANCEL = JOptionPane.CANCEL_OPTION;

  /**Handles informational messages with OK as only option
  * @param owner the UI component that owns this message
  * @param message string containing message for user*/
  public static void info(Component owner, String messageString) {
    JFrame ownerFrame;
    String message = wrapMessage(messageString);
    if ((owner == null) ||
                (!owner.getClass().getName().equals("javax.swing.JFrame")))
      ownerFrame = XMLTrainingKit.getMainWindow();
    else
      ownerFrame = (JFrame)owner;
    JOptionPane optPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
    String ownerTitle = ownerFrame.getTitle();
    JDialog thisDialog = optPane.createDialog(owner, "Information concerning " + ownerTitle);
    thisDialog.show();
  }

  /**Handles warning messages with OK as only option
  * @param owner the UI component that owns this message
  * @param message string containing message for user*/
  public static void warning(Component owner, String messageString) {
    JFrame ownerFrame;
    String message = wrapMessage(messageString);
    if ((owner == null) ||
                !(owner instanceof JFrame))
      ownerFrame = XMLTrainingKit.getMainWindow();
    else
      ownerFrame = (JFrame)owner;
    JOptionPane optPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
    String ownerTitle = ownerFrame.getTitle();
    JDialog thisDialog = optPane.createDialog(owner, "Warning concerning " + ownerTitle);
    thisDialog.show();
  }

  /**Handles error messages with OK as only option
  * @param owner the UI component that owns this message
  * @param message string containing message for user*/
  public static void error(Component owner, String messageString) {
    JFrame ownerFrame;
    String message = wrapMessage(messageString);
    if ((owner == null) ||
                !(owner instanceof JFrame))
      ownerFrame = XMLTrainingKit.getMainWindow();
    else
      ownerFrame = (JFrame)owner;
    JOptionPane optPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);

    String ownerTitle = ownerFrame.getTitle();
    JDialog thisDialog = optPane.createDialog(owner, "Error concerning " + ownerTitle);
    thisDialog.show();
  }

   /**Handles yes/no prompt messages with OK, No, and Cancel options
  * @param owner the UI component that owns this message
  * @param message string containing message for user*/
  public static int ynPrompt(Component owner, String messageString) {
    JFrame ownerFrame;
    String message = wrapMessage(messageString);
    if ((owner == null) ||
                !(owner instanceof JFrame))
      ownerFrame = XMLTrainingKit.getMainWindow();
    else
      ownerFrame = (JFrame)owner;
    JOptionPane optPane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE,
                                      JOptionPane.YES_NO_CANCEL_OPTION);

    String ownerTitle = ownerFrame.getTitle();
    JDialog thisDialog = optPane.createDialog(owner, "Question concerning " + ownerTitle);
    thisDialog.show();
    Object resultObject = optPane.getValue();
    if ((resultObject == null) || !(resultObject instanceof Integer))
      return JOptionPane.CANCEL_OPTION;
    else {
      int result = ((Integer)resultObject).intValue();
      if (result == JOptionPane.CLOSED_OPTION)
        return JOptionPane.CANCEL_OPTION;
      else
        return result;
    }
  }

   /**Handles yes/no warning messages with OK, No, and Cancel options
  * @param owner the UI component that owns this message
  * @param message string containing message for user*/
    public static int ynWarning(Component owner, String messageString) {
    JFrame ownerFrame;
    String message = wrapMessage(messageString);
    if ((owner == null) ||
                !(owner instanceof JFrame))
      ownerFrame = XMLTrainingKit.getMainWindow();
    else
      ownerFrame = (JFrame)owner;
    JOptionPane optPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE,
                                      JOptionPane.YES_NO_CANCEL_OPTION);

    String ownerTitle = ownerFrame.getTitle();
    JDialog thisDialog = optPane.createDialog(owner, "Warning concerning " + ownerTitle);
    thisDialog.show();
    Object resultObject = optPane.getValue();
    if ((resultObject == null) || !(resultObject instanceof Integer))
      return JOptionPane.CANCEL_OPTION;
    else {
      int result = ((Integer)resultObject).intValue();
      if (result == JOptionPane.CLOSED_OPTION)
        return JOptionPane.CANCEL_OPTION;
      else
        return result;
    }
  }

  /**Inserts line breaks into the message string at the required locations.
  **/
  private static String wrapMessage(String message){
    String newMessage;
    int targetWidth = 60;
    int counter = 0;
    boolean done = false;
    int targetIndex;
    //Loop until run out of string to wrap
    while (!done){
      counter = counter + targetWidth;
      if (message.length() <= counter){
        done = true;
        continue;
      };
      //Find the next space (go back 10 if too great an interval)
      targetIndex = message.indexOf(" ", counter);
      if (targetIndex > counter + 10)
        targetIndex = message.indexOf(" ", counter - 10);
      //Do string maniupulations if not at end of string
      if ((targetIndex < 0)){
        done = true;
        continue;
      };
      newMessage = message.substring(0, targetIndex);
      newMessage = newMessage.concat("\n");
      newMessage = newMessage.concat(message.substring(targetIndex + 1));
      message = newMessage;
      counter = targetIndex;
    };

    return message;
  }

}
