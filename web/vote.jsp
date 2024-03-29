<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <title>iVotas</title>
</head>
<body>

    <div style="margin-top: 5%">
        <h1 class="text-center">iVotas</h1>
        <h2 class="text-center">Mesa de voto</h2>
    </div>

    <c:forEach items="${candidateLists}" var="candidateList">
        <s:url action="createVote" var="voteTag" >
            <s:param name="listName"><c:out value="${candidateList.name}" /></s:param>
        </s:url>
        <p><a href="<s:property value="#voteTag" />&electionName=${electionName}" ><c:out value="${candidateList.name}" /></a></p><br>
    </c:forEach>

<p><a href="<s:url action="index" />">Voltar</a></p><br>
</body>
</html>