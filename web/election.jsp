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

<div class="container" style="margin-top: 5%">

    <h6 class="text-center"><s:property value="election.name"/></h6>
    <h6 class="text-center"><s:property value="election.description"/></h6>

    <s:url action="vote" var="voteTag">
        <s:param name="electionName"> <c:out value="${electionName}" /> </s:param>
    </s:url>


    <s:url action="shareElection" var="shareElectionTag">
        <s:param name="electionName"> <c:out value="${electionName}" /> </s:param>
    </s:url>
</div>

<br><br>
<a href="<s:property value="#shareElectionTag" />"><button class="btn btn-secondary" style="margin-left: 2%">Partilhar Eleição</button></a>
<br><br>
<a href="<s:property value="#voteTag" />"><button class="btn btn-secondary" style="margin-left: 2%">Votar</button></a>
<br><br>
<a href="<s:url action="index" />"><button class="btn btn-secondary" style="margin-left: 2%">Voltar</button></a>
</body>
</html>