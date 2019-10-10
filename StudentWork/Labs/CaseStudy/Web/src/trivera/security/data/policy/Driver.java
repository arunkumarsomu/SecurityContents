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

import java.util.Vector;

/**
 * This class is an entity class that captures all data for a driver.
 *
 * @version  Training Course
 */

public class Driver implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private PersonInfo driverInfo;
  private String licenseDt = "";
  private String licenseNumber = "";
  private String stateProvCd = "";
  private Vector accidentViolations = new Vector();

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty driver.
  */
  public Driver() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/

/**
 * Returns the accidentViolations.
 * @return Vector
 */
public Vector getAccidentViolations() {
	return accidentViolations;
}

/**
 * Returns the driverInfo.
 * @return PersonInfo
 */
public PersonInfo getDriverInfo() {
	return driverInfo;
}

/**
 * Returns the licenseDt.
 * @return String
 */
public String getLicenseDt() {
	return licenseDt;
}

/**
 * Returns the licenseNumber.
 * @return String
 */
public String getLicenseNumber() {
	return licenseNumber;
}

/**
 * Returns the stateProvCd.
 * @return String
 */
public String getStateProvCd() {
	return stateProvCd;
}

/**
 * Sets the accidentViolations.
 * @param accidentViolations The accidentViolations to set
 */
public void setAccidentViolations(Vector accidentViolations) {
	this.accidentViolations = accidentViolations;
}

/**
 * Sets the driverInfo.
 * @param driverInfo The driverInfo to set
 */
public void setDriverInfo(PersonInfo driverInfo) {
	this.driverInfo = driverInfo;
}

/**
 * Sets the licenseDt.
 * @param licenseDt The licenseDt to set
 */
public void setLicenseDt(String licenseDt) {
	this.licenseDt = licenseDt;
}

/**
 * Sets the licenseNumber.
 * @param licenseNumber The licenseNumber to set
 */
public void setLicenseNumber(String licenseNumber) {
	this.licenseNumber = licenseNumber;
}

/**
 * Sets the stateProvCd.
 * @param stateProvCd The stateProvCd to set
 */
public void setStateProvCd(String stateProvCd) {
	this.stateProvCd = stateProvCd;
}

}
