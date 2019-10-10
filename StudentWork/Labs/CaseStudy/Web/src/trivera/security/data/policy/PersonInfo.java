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
 * This class is an entity class that captures all data for a person.
 *
 * @version  Training Course
 */
	
public class PersonInfo implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String surname = "";
  private String givenName = "";
  private String addr1 = "";  
  private String city = "";  
  private String stateProv = "";
  private String postalCode = "";
  private String genderCd = "";
  private String birthDate = "";
  private String maritalStatusCd = "";


/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty set of person info.
  */
  public PersonInfo() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/


/**
 * Returns the street portion of address.
 * @return String
 */
public String getAddr1() {
	return addr1;
}

/**
 * Returns the city.
 * @return String
 */
public String getCity() {
	return city;
}

/**
 * Returns the stateProv.
 * @return String
 */
public String getStateProv() {
	return stateProv;
}

/**
 * Returns the givenName.
 * @return String
 */
public String getGivenName() {
	return givenName;
}

/**
 * Returns the surname.
 * @return String
 */
public String getSurname() {
	return surname;
}

/**
 * Sets the street poriton of the address.
 * @param addr1 The addr1 to set
 */
public void setAddr1(String addr1) {
	this.addr1 = addr1;
}

/**
 * Sets the city.
 * @param city The city to set
 */
public void setCity(String city) {
	this.city = city;
}

/**
 * Sets the stateProv.
 * @param country The stateProv to set
 */
public void setStateProv(String stateProv) {
	this.stateProv = stateProv;
}

/**
 * Sets the givenName.
 * @param givenName The givenName to set
 */
public void setGivenName(String givenName) {
	this.givenName = givenName;
}

/**
 * Sets the surname.
 * @param surname The surname to set
 */
public void setSurname(String surname) {
	this.surname = surname;
}

/**
 * Returns the birthDate.
 * @return String
 */
public String getBirthDate() {
	return birthDate;
}

/**
 * Returns the genderCd.
 * @return String
 */
public String getGenderCd() {
	return genderCd;
}

/**
 * Returns the maritalStatusCd.
 * @return String
 */
public String getMaritalStatusCd() {
	return maritalStatusCd;
}

/**
 * Returns the postalCode.
 * @return String
 */
public String getPostalCode() {
	return postalCode;
}

/**
 * Sets the birthDate.
 * @param birthDate The birthDate to set
 */
public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
}

/**
 * Sets the genderCd.
 * @param genderCd The genderCd to set
 */
public void setGenderCd(String genderCd) {
	this.genderCd = genderCd;
}

/**
 * Sets the maritalStatusCd.
 * @param maritalStatusCd The maritalStatusCd to set
 */
public void setMaritalStatusCd(String maritalStatusCd) {
	this.maritalStatusCd = maritalStatusCd;
}

/**
 * Sets the postalCode.
 * @param postalCode The postalCode to set
 */
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}

}
