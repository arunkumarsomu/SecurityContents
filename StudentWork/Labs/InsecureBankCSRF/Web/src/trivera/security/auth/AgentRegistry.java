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

package trivera.security.auth;

/**
 * This class is handles login requests.
 * 
 * @version Training Course
 */
import trivera.security.data.Bank;
import trivera.security.data.BankInfoStore;
import trivera.security.data.ClientInfo;

public class AgentRegistry {
	/** ***************************************************************** */
	/** ***********************Variables********************************* */
	/** ***************************************************************** */

	/** ***************************************************************** */
	/** ***********************Constructor******************************* */
	/** ***************************************************************** */

	/**
	 * Constructor initializes registry.
	 */
	public AgentRegistry() {
		super();

	}

	/** ***************************************************************** */
	/** ****************Methods****************************** */
	/** ***************************************************************** */

	public int checkAgent(String candidateName, String candidatePassword) {
		if (candidateName == null || candidatePassword == null)
			return 0;

		BankInfoStore store = Bank.store;
		ClientInfo potentialClient = store.getClient(candidateName);
		
		if (potentialClient == null) return 1;
		
		if (potentialClient.getUsername().equals(candidateName) &&
				potentialClient.getPwd().equals(candidatePassword)) 
			return 2;
		else 
			return 1;
	
		
	}
}
