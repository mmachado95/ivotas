<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hey!</title>s
</head>
<body>

	<c:choose>
		<c:when test="${session.loggedin == true}">
			<p>Welcome, ${session.username}. Say HEY to someone.</p>
		</c:when>
		<c:otherwise>
			<p>Welcome, anonymous user. Say HEY to someone.</p>
		</c:otherwise>
	</c:choose>

	<c:forEach items="${session.iVotasBean.getAllElections()}" var="value">
        <s:url action="election" var="electionTag">
            <s:param name="electionName"> <c:out value="${value}" /> </s:param>
        </s:url>

		<p><a href="<s:property value="#electionTag" />"><c:out value="${value}" /></a></p><br>
	</c:forEach>

    <p><a href="<s:url action="logout" />">Logout</a></p><br>
</body>
</html>