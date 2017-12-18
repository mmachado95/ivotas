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

<h2>Criar Eleição</h2>
<s:form action="createElection" method="post">
    <s:text name="Name:" />
    <s:textfield name="name" /><br>
    <s:text name="Description:" />
    <s:textfield name="description" /><br>
    <s:text name="Start Date:" />
    <s:textfield name="startDate" /><br>
    <s:text name="End Date:" />
    <s:textfield name="endDate" /><br>
    <s:text name="Type:" />
    <s:textfield name="type" /><br>
    <s:submit />
</s:form>
</body>
</html>