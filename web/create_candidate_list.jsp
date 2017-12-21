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

<h2>Criar Lista de Candidatos</h2>

<c:if test="${electionType == 1}">
    <form action="createCandidateList" method="post">
        <p>Candidate List Name</p>
        <input type="text" name="name"/>
        <p>Users (separated by ,)</p>
        <input type="text" name="users" placeholder="Miguel,Teresa,Joao Santos"/>
        <input type="submit"/>
    </form>
</c:if>

<c:if test="${electionType == 2}">
    <form action="createCandidateList" method="post">
        <p>Candidate List Name</p>
        <input type="text" name="name"/>
        <p>Users Type</p>
        <input type="text" name="type"/>
        <p>Users (separated by ,)</p>
        <input type="text" name="users" placeholder="Miguel,Teresa,Joao Santos"/>
        <input type="submit"/>
    </form>
</c:if>

</body>
</html>