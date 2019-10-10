<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html> 
<head> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="results" scope="request" class="java.util.Vector"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>RHH Insurance Email Query Results</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>RHH Insurance Email Query</h1>
        <p>Results:</p>
<%
for (int icounter = 0; icounter < results.size(); icounter++) {
String temp = (String) results.elementAt(icounter);
%>
<%= temp %>
<% } %>   
<br/>
<br/><br/><br/><hr/>
<a href="emailupdate.html" >Update Email</a>
<br/>
<hr/>
<a href="emailquery.html" >Query for Email</a>
<br/>
<hr/>
<a href="index.html" >Home</a>
<br/>
<hr/>
</body>
</html>
