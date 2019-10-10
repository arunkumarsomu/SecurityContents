<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="/WEB-INF/timeTag.tld" prefix="lab"%>
<html>  

<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="policyBean"
	class="trivera.security.data.policy.Policy" scope="request">
	<jsp:setProperty name="policyBean" property="*" />
</jsp:useBean>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>RHH Insurance Policy Viewer</title>
</head>
<body bgcolor=#E6E6FF>
<h1 align=center>RHH Insurance</h1>
<h2 align=center><%=policyBean.getInsured().getGivenName()%> <%=policyBean.getInsured().getSurname()%>
Auto Policy Summary</h2>
<h3 align=center>Policy Number: <jsp:getProperty name="policyBean"
	property="policyNumber" /></h3>
<br />
<hr />
<font size=5 color=red>Insured Personal Information: </font>
<br />
<%=policyBean.getInsured().getGivenName()%>
<%=policyBean.getInsured().getSurname()%>
<br />
<%=policyBean.getInsured().getAddr1()%>
<br />
<%=policyBean.getInsured().getCity()%>, 
<%=policyBean.getInsured().getStateProv()%> 
<%=policyBean.getInsured().getPostalCode()%>
<br />
Date of Birth: <%=policyBean.getInsured().getBirthDate()%>
<br />
<hr />
<font size=5 color=red>Vehicle Information: </font>
<br />
<!-- Result Table -->
<TABLE border=3>
	<TBODY>
		<TR>
			<td width=200 align=center>
			<p><font size=4 color=blue>Vehicle</font></p>
			</td>
			<td width=60 align=center>
			<p><font size=4 color=blue>Year</font></p>
			</td>
			<td width=250 align=center>
			<p><font size=4 color=blue>VIN</font></p>
			</td>
			<td width=100 align=center>
			<p><font size=4 color=blue>Driver</font></p>
			</td>
			<td width=350 align=center>
			<p><font size=4 color=blue>Coverages</font></p>
			</td>
		</TR>
		<%
				if (policyBean.getVehicles() != null) {
				java.util.Iterator i = policyBean.getVehicles().iterator();
				int i24 = 0;
				while (i.hasNext()) {
			trivera.security.data.policy.Vehicle resulti24 = (trivera.security.data.policy.Vehicle)i.next();
		%>
		<TR>
			<TD class="result">
			<TABLE>
				<TBODY>

					<TR>
						<TD><%=resulti24.getModel()%></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD class="result">
			<TABLE>
				<TBODY>

					<TR>
						<TD><%=resulti24.getModelYear()%></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD class="result">
			<TABLE>
				<TBODY>

					<TR>
						<TD><%=resulti24.getVehicleIdNumber()%></TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD class="result">
			<TABLE>
				<TBODY>

					<TR>
						<TD>
						<TABLE>
							<TBODY>

								<TR>
									<TD>
									<TABLE>
										<TBODY>

											<TR>
												<TD><%=resulti24.getRatedDriver().getDriverInfo().getGivenName()%>
												</TD>
											</TR>

											<TR>
												<TD><%=resulti24.getRatedDriver().getDriverInfo().getSurname()%>
												</TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD class="result">
			<TABLE border=3>
				<TBODY>

					<tr>
						<td width=150>
						<p><font size=3 color=blue>Type</font></p>
						</td>
						<td width=80 align=center>
						<p><font size=3 color=blue>Limits</font></p>
						</td>
						<td width=50 align=center>
						<p><font size=3 color=blue>Deductible</font></p>
						</td>
					</tr>
					<%
	if (resulti24.getCoverages() != null) {
		java.util.Iterator i2 = resulti24.getCoverages().iterator();
		int i28 = 0;
		while (i2.hasNext()) {
			trivera.security.data.policy.Coverage resulti28 = (trivera.security.data.policy.Coverage)i2.next();
%>
					<TR>
						<TD class="result">
						<TABLE>
							<TBODY>

								<TR>
									<TD><%=resulti28.getCoverageCd()%></TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
						<TD class="result">
						<TABLE>
							<TBODY>

								<%
	if (resulti28.getLimits() != null) {
		java.util.Iterator i3 = resulti28.getLimits().iterator();
		int i29 = 0;
		while (i3.hasNext()) {
			java.lang.String resulti29 = (java.lang.String)i3.next();
%>
								<TR>
									<TD class="result"><%=resulti29%></TD>
								</TR>
								<%
		}
		
		i29++;
	}
%>
							</TBODY>
						</TABLE>
						</TD>
						<TD class="result">
						<TABLE>
							<TBODY>

								<TR>
									<TD><%=resulti28.getCoverageTermLmt()%></TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
					<%
		}
		
		i28++;
	}
%>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
		<%
		}
		
		i24++;
	}
%>
</TABLE>


<hr />
<font size=5 color=red>Driver Information: </font>
<br />
<!-- Result Table -->
<TABLE border="3">
				<TBODY>
	<tr>
		<td width=200 align=center>
		<p><font size=4 color=blue>Driver Name</font></p>
		</td>
		<td width=125 align=center>
		<p><font size=4 color=blue>Birth Date</font></p>
		</td>
	</tr>
					<%
	if (policyBean.getDrivers() != null) {
		java.util.Iterator i = policyBean.getDrivers().iterator();
		int i31 = 0;
		while (i.hasNext()) {
			trivera.security.data.policy.Driver resulti31 = (trivera.security.data.policy.Driver)i.next();
%>
					<TR>
						<TD class="result">
						<TABLE>
							<TBODY>

								<TR>
									<TD>
									<TABLE>
										<TBODY>

											<TR>
												<TD><%=resulti31.getDriverInfo().getGivenName()%></TD>
											</TR>

											<TR>
												<TD><%=resulti31.getDriverInfo().getSurname()%></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
						<TD class="result">
						<TABLE>
							<TBODY>

								<TR>
									<TD>
									<TABLE>
										<TBODY>
											<TR>
												<TD><%=resulti31.getDriverInfo().getBirthDate()%></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
					<%
		}
		
		i31++;
	}
%>
			</TBODY>
			</TABLE>


<hr />
<lab:timeTag></lab:timeTag>
</body>
</html>
