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

import java.util.*;

/**
 * This class is an entity class that captures all account information.
 *
 * @version  Training Course
 */


public class Account {	


/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String accountNumber = "";
  private String type = "";

  private ClientInfo accountOwner;
  private Integer balance;

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty policy.
  */
  public Account() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/
	
	
/**
 * Returns the account Number.
 * @return String
 */
public String getAccountNumber() {
	return accountNumber;
}

/**
 * Sets the account Number.
 * @param accountNum The account Number to set
 */
public void setAccountNumber(String accountNum) {
	this.accountNumber = accountNum;
}

/**
 * Returns the balance.
 * @return Integer
 */
public Integer getBalance() {
	return balance;
}

/**
 * Returns the owner.
 * @return ClientInfo
 */
public ClientInfo getOwner() {
	return accountOwner;
}

/**
 * Sets the balance.
 * @param newBalance The balance to set
 */
public void setBalance(Integer newBalance) {
	this.balance = newBalance;
}

/**
 * Sets the owner.
 * @param insured The insured to set
 */
public void setOwner(ClientInfo acctOwner) {
	this.accountOwner = acctOwner;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}



}
