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

package trivera.security.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trivera.security.data.policy.Coverage;
import trivera.security.data.policy.Driver;
import trivera.security.data.policy.PersonInfo;
import trivera.security.data.policy.Policy;
import trivera.security.data.policy.Vehicle;

/**
 * This servlet handles generating an HTML page from an
 * instance of the Policy class that is passed in with request.
 *
 * @version  Training Course
 */
public class PolicyViewer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/********************************************************************/
	/*************************Variables**********************************/
	/********************************************************************/


	/********************************************************************/
	/*************************Servlet Methods****************************/
	/********************************************************************/

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		Policy target = (Policy)req.getAttribute("policyBean");		


		
		PersonInfo insured = target.getInsured();

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body bgcolor=#E6E6FF>");
		out.println("<h1 align=center>Big Kahuna Insurance</h1>");
		out.println("<h2 align=center>");
		out.println(insured.getGivenName() + " " 
				+ insured.getSurname() + " Auto Policy Summary</h2>");
		out.println("<h3 align=center>Policy Number: ");
		out.println(target.getPolicyNumber() + "</h3>");
		out.println("<br/><hr/><font size=5 color=red>Insured Personal Information: </font>");
		out.println("<br/>" + insured.getGivenName() + " " 
				+ insured.getSurname() + "<br/>");
		out.println(insured.getAddr1() + "<br/>");		
		out.println(insured.getCity() + ", " + insured.getStateProv() + " " +
				insured.getPostalCode() + "<br/>");		
		out.println("Date of Birth: " + insured.getBirthDate() + "<br/><hr/>");	
		
		Vector vehicles = target.getVehicles();
		if (vehicles != null && vehicles.size() > 0) {	
			out.println("<font size=5 color=red>Vehicle Information: </font> <br/>");
			out.println("<table border=3><tr>");
			out.println("<td width=200 align=center>");
			out.println("<font size=4 color=blue><p> Vehicle</p></font></td>");
			out.println("<td width=60 align=center>");
			out.println("<font size=4 color=blue><p> Year</p></font></td>");
			out.println("<td width=250 align=center>");
			out.println("<font size=4 color=blue><p> VIN</p></font></td>");
			out.println("<td width=100 align=center>");
			out.println("<font size=4 color=blue><p> Driver</p></font></td>");
			out.println("<td width=350 align=center>");
			out.println("<font size=4 color=blue><p> Coverages</p></font></td></tr>");
			processVehicles(vehicles, out);
			out.println("</table><hr/>");
		}
		else 
			out.println("<font size=5 color=red>No vehicles in policy.</font><br/><hr/>");
				
		
		Vector drivers = target.getDrivers();
		if (drivers != null && drivers.size() > 0){
			out.println("<font size=5 color=red>Driver Information: </font> <br/>");
			out.println("<table border=3><tr>");
			out.println("<td width=200 align=center>");
			out.println("<font size=4 color=blue><p> Driver Name</p></font></td>");
			out.println("<td width=125 align=center>");
			out.println("<font size=4 color=blue><p> Birth Date</p></font></td></tr>");
			processDrivers(drivers, out);
			out.println("</table><hr/>");
		}
		else
			out.println("<font size=5 color=red>No drivers in policy.</font><br/><hr/>");

		out.println("</body></html>");
		out.close();
	}
	
	private void processDrivers(Vector drivers, PrintWriter out){
		for (int index = 0; index < drivers.size();index++){
			Driver thisDriver = (Driver)drivers.get(index);
			PersonInfo thisInfo = thisDriver.getDriverInfo();
			out.println("<tr><td align=center>");
			out.println("<p>" + thisInfo.getGivenName() + " " + 
								thisInfo.getSurname() +  "</p></td>");
			out.println("<td align=center>");
			out.println("<p>" + thisInfo.getBirthDate() +  "</p></td></tr>");								
		}
	}

	private void processVehicles(Vector vehicles, PrintWriter out){
		
		for (int index = 0; index < vehicles.size();index++){
			Vehicle thisVehicle = (Vehicle)vehicles.get(index);
			out.println("<tr><td align=center>");
			out.println("<p>" + thisVehicle.getModel() + "</p></td>");
			out.println("<td align=center>");
			out.println("<p>" + thisVehicle.getModelYear() +  "</p></td>");								
			out.println("<td align=center>");
			out.println("<p>" + thisVehicle.getVehicleIdNumber() +  "</p></td>");	
			out.println("<td align=center>");
			out.println("<p>" + 
					thisVehicle.getRatedDriver().getDriverInfo().getGivenName() + "\n" +
					thisVehicle.getRatedDriver().getDriverInfo().getSurname() +  "</p></td>");
			Vector coverages = thisVehicle.getCoverages();
			if (coverages != null && coverages.size() > 0)
				processCoverages(coverages, out);
			else 
				out.println("<td align=center><p>No coverages for this vehicle.</p></td>"); 
			
			out.println("</tr>");											
		}
	}

	private void processCoverages(Vector coverages, PrintWriter out) {
		out.println("<td align=center><table><tr>");
		out.println("<td width=150>");
		out.println("<font size=3 color=blue><p>Type</p></font></td>");
		out.println("<td width=80 align=center>");
		out.println("<font size=3 color=blue><p>Limit</p></font></td>");
		out.println("<td width=80 align=center>");
		out.println("<font size=3 color=blue><p>Limit</p></font></td>");
		out.println("<td width=50 align=center>");
		out.println("<font size=3 color=blue><p>Deductible</p></font></td></tr>");

		for (int index = 0; index < coverages.size(); index++) {
			Coverage thisCoverage = (Coverage) coverages.get(index);
			out.println("<tr><td colspan=4><hr/></td></tr>");
			out.println("<tr><td>");
			out.println(thisCoverage.getCoverageCd() + "</td>");
			Vector limits = thisCoverage.getLimits();
			if (limits.size() == 2){
				out.println("<td>" + limits.get(0) + "</td>");
				out.println("<td>" + limits.get(1) + "</td>");
			}
			else
				if (limits.size() == 1){
					out.println("<td>" + limits.get(0) + "</td>");
					out.println("<td></td>");
				}
				else {
					out.println("<td></td>");
					out.println("<td></td>");
				};
				
			out.println("<td>" + thisCoverage.getCoverageTermLmt() + "</td></tr>");
		};
		out.println("</table></td>");
	}


}

