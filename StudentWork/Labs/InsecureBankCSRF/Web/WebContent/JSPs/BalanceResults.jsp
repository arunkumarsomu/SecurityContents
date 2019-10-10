<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="results" scope="request" class="trivera.security.data.Account"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Bozone Bank Balance Query Results</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>Bozone Bank Balance Query</h1>
<p>Account Identifier: ${results.accountNumber}</p>
<p>Account Balance: $${results.balance}</p>  
<br/>
<br/><br/><br/><hr/>
<hr/>
<a href="accountops.html" >Home</a>
<br/>
<hr/>
</body>
</html>
