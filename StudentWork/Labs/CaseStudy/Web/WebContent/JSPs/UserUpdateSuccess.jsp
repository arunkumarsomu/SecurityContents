<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<html> 

<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="user" scope="request" class="trivera.security.data.User"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>RHH Insurance User Updated</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>RHH Insurance User Updated</h1>
<hr/>
        <p>Thanks for updating <%= user.getFullName() %>'s information.</p>
           <br/>
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
