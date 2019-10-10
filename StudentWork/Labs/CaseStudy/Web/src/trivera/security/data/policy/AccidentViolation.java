/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2018 Kim Morello
 * </p>
 * @author Kim Morello.
 */

package trivera.security.data.policy;

/**
 * This class is an entity class that captures all data for an accident.
 *
 * @version  Training Course
 */

public class AccidentViolation implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String violationCd = "";
  private String violationDate = "";
  private String violationDes = "";


/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty accident violation.
  */
  public AccidentViolation() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/

/**
 * Returns the violationCd.
 * @return String
 */
public String getViolationCd() {
	return violationCd;
}

/**
 * Returns the violationDate.
 * @return String
 */
public String getViolationDate() {
	return violationDate;
}

/**
 * Returns the violationDes.
 * @return String
 */
public String getViolationDes() {
	return violationDes;
}

/**
 * Sets the violationCd.
 * @param violationCd The violationCd to set
 */
public void setViolationCd(String violationCd) {
	this.violationCd = violationCd;
}

/**
 * Sets the violationDate.
 * @param violationDate The violationDate to set
 */
public void setViolationDate(String violationDate) {
	this.violationDate = violationDate;
}

/**
 * Sets the violationDes.
 * @param violationDes The violationDes to set
 */
public void setViolationDes(String violationDes) {
	this.violationDes = violationDes;
}

}
