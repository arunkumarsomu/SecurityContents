<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>  

<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="username" scope="request" class="java.lang.String"/>    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>RHH Insurance Email Updated</title>
</head>
<body bgcolor=#E6E6FF>
<link rel="stylesheet" href="theme/a03.css" type="text/css" />
<hr/>
<h1 align=center>RHH Insurance Email Updated</h1>
<hr/>
        <p>Thanks for updating <%= username %>'s email.</p>
           <br/>
<br/>
<hr/>
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
