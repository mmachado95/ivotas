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

<form action="createCandidateList" method="post">

    <c:forEach items="${elections}" var="value">
        <p>
            <c:out value="${value.name}" />
            <c:out value="${value.description}" />
        </p>
        <br><br>
    </c:forEach>

    <p>Candidate List Name</p>
    <input type="text" name="name"/>
    <p>Election Type</p>
    <input type="text" name="electionType"/>
    <p>Users Type (if election Type == 2)</p>
    <input type="text" name="type"/>
    <p>Users (separated by ,)</p>
    <input type="text" name="users" placeholder="Miguel,Teresa,Joao Santos"/>
    <input type="submit"/>
</form>
</body>
</html>