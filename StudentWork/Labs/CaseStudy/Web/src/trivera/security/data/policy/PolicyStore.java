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

import java.io.*;
import java.util.*;
import javax.servlet.*;

/**
 * This class provides functionality for interacting with a data store.
 * For initial purposes this will consist of reading in serialized
 * objects from a previously created file.
 *
 * @version  Training Course
 */
public class PolicyStore implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/********************************************************************/
	/*************************Variables**********************************/
	/********************************************************************/

	private String dataFile = "/WEB-INF/data.ser";
	private Vector policies = new Vector();

	/********************************************************************/
	/*************************Constructor********************************/
	/********************************************************************/

	/**
	* PolicyStore constructor sets up an in memory store of policies.
	*/
	public PolicyStore() {
		super();
		readStore();
	}

	/**
	* PolicyStore constructor sets up an in memory store of policies for
	* servlet.  This constructor is used with servlets and prepends the
	* web container's path to the front of the file name.
	*/
	public PolicyStore(ServletContext context) {
		super();
		dataFile = context.getRealPath("/") + dataFile;
		readStore();
	}

	/**
	* readStore sets up an in memory store of policies
	* by reading in from a file of serialized objects.
	*/
	@SuppressWarnings("unchecked")
	private void readStore() {
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream(dataFile);
			ois = new ObjectInputStream(fis);
			Policy thatPolicy = (Policy) (ois.readObject());;
			while (thatPolicy != null) {
				System.out.println(thatPolicy.getPolicyNumber());
				policies.add(thatPolicy);
				thatPolicy = (Policy) (ois.readObject());
			};
			ois.close();
		} catch (EOFException eof) {
		}
		catch (Exception io){
			io.printStackTrace();
		};
	}
	
	/**
	* searchStore takes a string and searches for a match on policy
	* numbers.
	*/
	public Policy searchStore(String targetId) {
		if (targetId == null || targetId.length() < 1)
			return null;
		for (int index = 0; index < policies.size(); index++){
			Policy thisPolicy = (Policy)policies.get(index);
			if (thisPolicy.getPolicyNumber().equals(targetId))
				return thisPolicy;		
		};
		
		return null;
	}
	
} // class PolicyStore
