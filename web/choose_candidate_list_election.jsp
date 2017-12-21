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

<h2>Escolha a Elei��o para a qual pretende adicionar um Lista</h2>


<c:forEach items="${elections}" var="value">
    <s:url action="createCandidateList" var="electionTag">
        <s:param name="electionName"> <c:out value="${value.name}" /> </s:param>
    </s:url>

    <p><a href="<s:property value="#electionTag" />">
        <c:out value="${value.name}" />
    </a>
    </p>
    <br><br>
</c:forEach>

</body>
</html>