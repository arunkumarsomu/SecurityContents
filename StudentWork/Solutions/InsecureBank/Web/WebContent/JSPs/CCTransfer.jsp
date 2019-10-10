<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="trivera.security.data.Account" %>
<jsp:useBean id="client" scope="session" class="trivera.security.data.ClientInfo"/> 


<html>
<head>
<title>Bozone Bank Credit Card Transfer</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
</head>
<body>
<hr/>
<h1>Bozone Bank Credit Card Transfer</h1>
<hr/>
Your accounts are:
<%
List accounts = client.getAccounts();
for (int icounter = 0; icounter < accounts.size(); icounter++) {
	Account acct = (Account)accounts.get(icounter);
%><br/>
<%= acct.getType() %>
<% } %> 
<br/>
<form method="get" action="CCTransferRequest">
<p>Please enter the account to transfer from:</p>
<p>Account Identifier <input type="text" name="source" /> </p>

<p>Please enter the credit card number to transfer to:</p>
<p>Credit Card Number <input type="text" name="target" /> </p>

<p>Please enter the amount to transfer:</p>
<p>Amount <input type="text" name="amount" /> </p>

  <input type="Submit" name="Submit" />
</form>
<br/>
<hr/>
<a href="accountops.html" >Home</a>
<br/>
<hr/>
<br/>
<br/>
</body>
</html>
