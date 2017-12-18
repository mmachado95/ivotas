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

    <h2>Registar Pessoa</h2>
    <s:form action="registerPerson" method="post">
        <s:text name="Username:" />
        <s:textfield name="username" /><br>
        <s:text name="Password:" />
        <s:textfield name="password" /><br>
        <s:text name="Department:" />
        <s:textfield name="departmentName" /><br>
        <s:text name="Faculty:" />
        <s:textfield name="facultyName" /><br>
        <s:text name="Contact:" />
        <s:textfield name="contact" /><br>
        <s:text name="Address:" />
        <s:textfield name="address" /><br>
        <s:text name="CC:" />
        <s:textfield name="cc" /><br>
        <s:text name="Expire Date" />
        <s:textfield name="expireDate" /><br>
        <s:text name="Type:" />
        <s:textfield name="type" /><br>
        <s:submit />
    </s:form>
</body>
</html>