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

<h2>Choose Election to Change</h2>


<c:forEach items="${users}" var="value">
    <s:url action="listPlacesUserVoted" var="userTag">
        <s:param name="username"> <c:out value="${value.getName()}" /> </s:param>
    </s:url>

    <p><a href="<s:property value="#userTag" />"><c:out value="${value.name}" /></a></p>
    <br><br>
</c:forEach>

</body>
</html>