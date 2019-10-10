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

import java.util.*;

/**
 * This class is an entity class that captures all data for a policy.
 *
 * @version  Training Course
 */


public class Policy implements java.io.Serializable {	

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String policyNumber = "";
  private PersonInfo insured;
  private Vector vehicles = new Vector();
  private Vector drivers = new Vector();

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty policy.
  */
  public Policy() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/
	
	
/**
 * Returns the policyNumber.
 * @return String
 */
public String getPolicyNumber() {
	return policyNumber;
}

/**
 * Sets the policyNumber.
 * @param policyNumber The policyNumber to set
 */
public void setPolicyNumber(String policyNumber) {
	this.policyNumber = policyNumber;
}


/**
 * Returns the drivers.
 * @return Vector
 */
public Vector getDrivers() {
	return drivers;
}

/**
 * Returns the insured.
 * @return PersonInfo
 */
public PersonInfo getInsured() {
	return insured;
}

/**
 * Returns the vehicles.
 * @return Vector
 */
public Vector getVehicles() {
	return vehicles;
}

/**
 * Sets the drivers.
 * @param drivers The drivers to set
 */
public void setDrivers(Vector drivers) {
	this.drivers = drivers;
}

/**
 * Sets the insured.
 * @param insured The insured to set
 */
public void setInsured(PersonInfo insured) {
	this.insured = insured;
}

/**
 * Sets the vehicles.
 * @param vehicles The vehicles to set
 */
public void setVehicles(Vector vehicles) {
	this.vehicles = vehicles;
}

}
