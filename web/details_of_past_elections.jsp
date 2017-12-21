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

<h2>Detalhes Eleição</h2>
    <p>Name: ${electionObject.name}</p>

    <c:forEach items="${details.candidatesResults}" var="value">
        <p>Nome Lista: <c:out value="${value.candidateList.name}" /></p>
        <p>Numero Votos: <c:out value="${value.numberOfVotes}" /></p>
        <p>% votos: <c:out value="${value.percentage}" /></p>
        <br><br>
    </c:forEach>

    <p>Votos Branco: ${details.numberOfEmptyVotes}</p>
    <p>% Votos Brancos: ${details.percentageOfEmptyVotes}</p>
    <p>Votos Nulos: ${details.numberOfNullVotes}</p>
    <p>% Votos Nulos: ${details.percentageOfNullVotes}</p>


</html>