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

<c:forEach items="${candidateLists}" var="candidateList">
    <s:url action="createVote" var="voteTag" >
        <s:param name="listName"><c:out value="${candidateList.name}" /></s:param>
    </s:url>
    <p><a href="<s:property value="#voteTag" />&electionName=${electionName}" ><c:out value="${candidateList.name}" /></a></p><br>
</c:forEach>

<p><a href="<s:url action="index" />">Voltar</a></p><br>
</body>
</html>