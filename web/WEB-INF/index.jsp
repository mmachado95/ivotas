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
    <s:if test="hasActionErrors()">
        <s:actionerror/>
    </s:if>
    <s:if test="hasActionMessages()">
        <s:actionmessage/>
    </s:if>

    <p><a href="https://www.facebook.com/v2.2/dialog/oauth?client_id=1486604264709457&redirect_uri=http://127.0.0.1:8080/connectFacebook&scope=publish_actions">Connect Facebook</a></p><br>

	<c:forEach items="${session.iVotasBean.getAllElections()}" var="value">
        <s:url action="election" var="electionTag">
            <s:param name="electionName"> <c:out value="${value}" /> </s:param>
        </s:url>

		<p><a href="<s:property value="#electionTag" />"><c:out value="${value}" /></a></p><br>
	</c:forEach>

    <p><a href="<s:url action="logout" />">Logout</a></p><br>
</body>
</html>