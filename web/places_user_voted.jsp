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

<h2>Places user voted</h2>

<c:forEach items="${votes}" var="vote">
    <p>${vote.election.name} -->
        <c:choose>
            <c:when test="${vote.department!=null}">
                ${vote.department.name}
            </c:when>
            <c:otherwise>
                Online
            </c:otherwise>
        </c:choose>
    </p>
    <br><br>
</c:forEach>

</body>
</html>