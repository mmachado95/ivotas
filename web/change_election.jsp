<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>iVotas</title>
</head>
<body>
<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:if test="hasActionMessages()">
    <s:actionmessage/>
</s:if>

<h2>Alterar Eleição</h2>

<form action="changeElection" method="post">
    <p>Name</p>
    <input type="text" name="name"/>
    <p>Description</p>
    <input type="text" name="description"/>
    <p>Start Date</p>
    <input type="text" name="startDate" value="dd/MM/yyyy hh:mm:ss"/>
    <p>End Date</p>
    <input type="text" name="endDate" value="dd/MM/yyyy hh:mm:ss"/>
    <p>Type</p>
    <input type="text" name="type"/>
    <input type="submit"/>
</form>
</body>
</html>