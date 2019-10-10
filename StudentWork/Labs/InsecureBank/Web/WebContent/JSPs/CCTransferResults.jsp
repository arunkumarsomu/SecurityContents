<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 

<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="sourceacct" scope="request" class="trivera.security.data.Account"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Bozone Bank Transfer Results</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>Bozone Bank Transfer</h1>
<p>$${amt} was successfully transferred from ${sourceacct.accountNumber} to ${targetcc}</p>
<p>The new balance for account ${sourceacct.accountNumber} is $${sourceacct.balance}</p>   
<br/>
<br/><br/><br/><hr/>
<hr/>
<a href="accountops.html" >Home</a>
<br/>
<hr/>
</body>
</html>
