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

    <h2>List Elections and Details</h2>

    <c:forEach items="${elections}" var="election">
        <p>${election.name}</p>
        <p>${election.description}</p>
        <p>${election.startDate}</p>
        <p>${election.type}</p>
        <c:forEach items="${election.candidateLists}" var="candidateList">
            <p>${candidateList.name}</p>
        </c:forEach>
        <br><br>
    </c:forEach>

</body>
</html>