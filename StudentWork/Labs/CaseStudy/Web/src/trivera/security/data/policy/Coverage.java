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
 * This class is an entity class that captures all data for a coverage.
 *
 * @version  Training Course
 */
	

public class Coverage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
/********************************************************************/
/*************************Variables**********************************/
/********************************************************************/

  private String coverageCd = "";
  private Vector limits = new Vector();
  private String coverageTermLmt = "";

/********************************************************************/
/*************************Constructor********************************/
/********************************************************************/

  /**
  * Policy constructor sets up empty coverage.
  */
  public Coverage() {
    super();

  }	

/********************************************************************/
/******************Getters and Setters*******************************/
/********************************************************************/

/**
 * Returns the coverageCd.
 * @return String
 */
public String getCoverageCd(){
	return coverageCd;
}

/**
 * Returns the coverageTermLmt.
 * @return String
 */
public String getCoverageTermLmt() {
	return coverageTermLmt;
}

/**
 * Returns the limits as a vector of strings.
 * @return Vector
 */
public Vector getLimits() {
	return limits;
}

/**
 * Sets the coverageCd.
 * @param coverageCd The coverageCd to set
 */
public void setCoverageCd(String coverageCd) {
	this.coverageCd = coverageCd;
}

/**
 * Sets the coverageTermLmt.
 * @param coverageTermLmt The coverageTermLmt to set
 */
public void setCoverageTermLmt(String coverageTermLmt) {
	this.coverageTermLmt = coverageTermLmt;
}

/**
 * Sets the limits.
 * @param limits The limits to set
 */
public void setLimits(Vector limits) {
	this.limits = limits;
}

}
