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
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * </p>
 * @author Kim Morello.
 */

package trivera.security.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an entity class that captures all data for a person.
 *
 * @version  Training Course
 */
	
public class ClientInfo {


/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String username = "";
  private String name = "";
  private String pwd = ""; 
  private List<Account> accounts = new ArrayList<Account>();



/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/


/**
  * Policy constructor sets up empty set of person info.
  */
  public ClientInfo() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/


/**
 * Returns the password.
 * @return String
 */
public String getPwd () {
	return pwd;
}


/**
 * Returns the username.
 * @return String
 */
public String getUsername() {
	return username;
}

/**
 * Sets the password.
 * @param pwd The pwd to set
 */
public void setPassword(String password) {
	this.pwd = password;
}

/**
 * Sets the Name.
 * @param Name The Name to set
 */
public void setName(String newName) {
	this.name = newName;
}

/**
 * Sets the surname.
 * @param surname The surname to set
 */
public void setUsername(String newUserName) {
	this.username = newUserName;
}


public List<Account> getAccounts() {
	return accounts;
}

public void setAccounts(List<Account> accounts) {
	this.accounts = accounts;
}

}
