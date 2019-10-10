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

import java.io.*;
import java.util.*;
import javax.servlet.*;

/**
 * This class provides functionality for interacting with a data store.
 * For initial purposes will setup a mocked bank with a set of accounts 
 * and clients.
 * @version  Training Course
 */
public class BankInfoStore {


	/********************************************************************/
	/*************************Variables**********************************/
	/********************************************************************/

	private List<ClientInfo> clients = new ArrayList<ClientInfo>();

	/********************************************************************/
	/*************************Constructor********************************/
	/********************************************************************/

	/**
	* PolicyStore constructor sets up an in memory store of policies.
	*/
	public BankInfoStore() {
		super();
		buildStore();
	}

	/**
	* buildStore sets up an in memory store of clients and 
	* accounts
	*/
	private void buildStore() {
		ClientInfo temp = new ClientInfo();
		temp.setName("Dan Do Right");
		temp.setUsername("dan");		
		temp.setPassword("idaho");
		Account acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("01");
		acct.setType("Checking");
		acct.setBalance(1000);
		List<Account> accounts = temp.getAccounts();
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("02");
		acct.setType("Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("03");
		acct.setType("DID Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		clients.add(temp);
		//
		temp = new ClientInfo();
		temp.setName("Carlos GoodGuy");
		temp.setUsername("carlos");
		temp.setPassword("hiking");
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("04");
		acct.setType("Checking");
		acct.setBalance(1000);
		accounts = temp.getAccounts();
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("05");
		acct.setType("Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("06");
		acct.setType("DID Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		clients.add(temp);
		//
		temp = new ClientInfo();
		temp.setName("Jane the Pain");
		temp.setUsername("jane");
		temp.setPassword("pain");
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("07");
		acct.setType("Checking");
		acct.setBalance(1000);
		accounts = temp.getAccounts();
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("08");
		acct.setType("Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		acct = new Account();
		acct.setOwner(temp);
		acct.setAccountNumber("09");
		acct.setType("DID Savings");
		acct.setBalance(1000);
		accounts.add(acct);
		clients.add(temp);
		
		
	}
	
	/**
	* getAccount takes a string and searches for a match on account
	* numbers.
	*/
	public Account getAccount(String acctNumber) {
		if (acctNumber == null || acctNumber.length() < 1)
			return null;
		for (int index = 0; index < clients.size(); index++){
			ClientInfo client = clients.get(index);
			if (client == null) return null;
			List<Account> accts = client.getAccounts();
			for (int i = 0; i < accts.size(); i++){
				Account acct = accts.get(i);
				if (acct == null) break;
				if (acct.getAccountNumber().equals(acctNumber))
					return acct;
			}		
		};
		
		return null;
	}
	
	/**
	* getClient takes a string and searches for a match on client
	* user name.
	*/
	public ClientInfo getClient(String clientUsername) {
		if (clientUsername == null || clientUsername.length() < 1)
			return null;
		for (int index = 0; index < clients.size(); index++){
			ClientInfo client = clients.get(index);
			if (client == null) return null;
			if (client.getUsername().equals(clientUsername))
				return client;		
		};
		
		return null;
	}
	
} // class BankInfoStore
