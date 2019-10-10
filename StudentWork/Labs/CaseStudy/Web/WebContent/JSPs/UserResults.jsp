<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<html> 

<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="request" class="trivera.security.data.User"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>RHH Insurance User Information Query Results</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>RHH Insurance Information Query</h1>
<hr/>
<h1>Results</h1>
<hr/>
  <table >
    <tr>
      <td>Username</td>
      <td>
 <jsp:getProperty name="user" property="userName" /> <br>
      </td>
    </tr>
    <tr>
      <td>Name</td>
      <td>
 <jsp:getProperty name="user" property="fullName" /> <br>
      </td>
    </tr>
    <tr>
      <td>Street</td>
      <td>
 <jsp:getProperty name="user" property="street" />  <br>
      </td>
    </tr>
    <tr>
      <td>City</td>
      <td>
 <jsp:getProperty name="user" property="city" />   <br>
      </td>
    </tr>
    <tr>
      <td>Zipcode</td>
      <td>
 <jsp:getProperty name="user" property="zipcode" />  <br>
      </td>
    </tr>
    <tr>
      <td>Credit Card Number</td>
      <td>
 <jsp:getProperty name="user" property="ccn" /> <br>
      </td>
    </tr>
</table>
<hr/>
<a href="userupdate.html" >Update User Information</a>
<br/>
<hr/>
<a href="userquery.html" >Query for User Information</a>
<br/>
<hr/>
<a href="index.html" >Home</a>
<br/>
<hr/>
</body>
</html>
