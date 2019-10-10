package trivera.security.data;

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

public class User {

	public User() {
	}

	private String fullName;

	private String userName;

	private String street;

	private String zipcode;

	private String city;

	private String ccn;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String newFullName) {
		fullName = newFullName;
	}

	public void setStreet(String newStreet) {
		street = newStreet;
	}

	public String getStreet() {
		return street;
	}

	public void setZipcode(String newZipcode) {
		zipcode = newZipcode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setCity(String newCity) {
		city = newCity;
	}

	public String getCity() {
		return city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCcn() {
		return ccn;
	}

	public void setCcn(String newCcn) {
		this.ccn = newCcn;
	}
}