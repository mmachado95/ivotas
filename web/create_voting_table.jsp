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

<h2>Adicionar Mesa de Voto</h2>
<s:form action="createVotingTable" method="post">
    <s:text name="Election Name:" />
    <s:textfield name="electionName" /><br>
    <s:text name="Department Name:" />
    <s:textfield name="departmentName" /><br>
    <s:submit />
</s:form>
</body>
</html>