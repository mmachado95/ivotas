<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hey!</title>
</head>
<body>

<s:property value="election.name"/>
<br>
<s:property value="election.description"/>


<s:url action="vote" var="voteTag">
    <s:param name="electionName"> <c:out value="${electionName}" /> </s:param>
</s:url>


<s:url action="shareElection" var="shareElectionTag">
    <s:param name="electionName"> <c:out value="${electionName}" /> </s:param>
</s:url>

<p><a href="<s:property value="#shareElectionTag" />">Partilhar Eleição</a></p>
<br>
<br>
<br>
<p><a href="<s:property value="#voteTag" />">Votar</a></p><br>
<p><a href="<s:url action="index" />">Voltar</a></p><br>
</body>
</html>