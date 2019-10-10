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
 * This class is an entity class that captures all data for a vehicle.
 *
 * @version  Training Course
 */

public class Vehicle implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String model = "";
  private String modelYear = "";
  private String bodyTypeCd = "";
  private String costNewAmt = "";
  private String plateExpDate = "";
  private String purchaseDate = "";
  private String vehicleIdNumber = "";
  private String vehicleUseCd = "";
  private Driver ratedDriver = null;
  private Vector coverages = new Vector();
  

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty vehicle.
  */
  public Vehicle() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/

/**
 * Returns the bodyTypeCd.
 * @return String
 */
public String getBodyTypeCd() {
	return bodyTypeCd;
}

/**
 * Returns the costNewAmt.
 * @return String
 */
public String getCostNewAmt() {
	return costNewAmt;
}

/**
 * Returns the coverages.
 * @return Vector
 */
public Vector getCoverages() {
	return coverages;
}

/**
 * Returns the model.
 * @return String
 */
public String getModel() {
	return model;
}

/**
 * Returns the modelYear.
 * @return String
 */
public String getModelYear() {
	return modelYear;
}

/**
 * Returns the plateExpDate.
 * @return String
 */
public String getPlateExpDate() {
	return plateExpDate;
}

/**
 * Returns the purchaseDate.
 * @return String
 */
public String getPurchaseDate() {
	return purchaseDate;
}

/**
 * Returns the vehicleIdNumber.
 * @return String
 */
public String getVehicleIdNumber() {
	return vehicleIdNumber;
}

/**
 * Returns the vehicleUseCd.
 * @return String
 */
public String getVehicleUseCd() {
	return vehicleUseCd;
}

/**
 * Sets the bodyTypeCd.
 * @param bodyTypeCd The bodyTypeCd to set
 */
public void setBodyTypeCd(String bodyTypeCd) {
	this.bodyTypeCd = bodyTypeCd;
}

/**
 * Sets the costNewAmt.
 * @param costNewAmt The costNewAmt to set
 */
public void setCostNewAmt(String costNewAmt) {
	this.costNewAmt = costNewAmt;
}

/**
 * Sets the coverages.
 * @param coverages The coverages to set
 */
public void setCoverages(Vector coverages) {
	this.coverages = coverages;
}

/**
 * Sets the model.
 * @param model The model to set
 */
public void setModel(String model) {
	this.model = model;
}

/**
 * Sets the modelYear.
 * @param modelYear The modelYear to set
 */
public void setModelYear(String modelYear) {
	this.modelYear = modelYear;
}

/**
 * Sets the plateExpDate.
 * @param plateExpDate The plateExpDate to set
 */
public void setPlateExpDate(String plateExpDate) {
	this.plateExpDate = plateExpDate;
}

/**
 * Sets the purchaseDate.
 * @param purchaseDate The purchaseDate to set
 */
public void setPurchaseDate(String purchaseDate) {
	this.purchaseDate = purchaseDate;
}

/**
 * Sets the vehicleIdNumber.
 * @param vehicleIdNumber The vehicleIdNumber to set
 */
public void setVehicleIdNumber(String vehicleIdNumber) {
	this.vehicleIdNumber = vehicleIdNumber;
}

/**
 * Sets the vehicleUseCd.
 * @param vehicleUseCd The vehicleUseCd to set
 */
public void setVehicleUseCd(String vehicleUseCd) {
	this.vehicleUseCd = vehicleUseCd;
}

	/**
	 * Returns the ratedDriver.
	 * @return Driver
	 */
	public Driver getRatedDriver() {
		return ratedDriver;
	}

	/**
	 * Sets the ratedDriver.
	 * @param ratedDriver The ratedDriver to set
	 */
	public void setRatedDriver(Driver ratedDriver) {
		this.ratedDriver = ratedDriver;
	}

}
