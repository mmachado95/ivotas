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
    <h2 class="text-center">Administra??o</h2>
    <h4 class="text-center">Criar Elei??o - Nucleo de Estudantes</h4>
</div>

<form action="createElectionStudents" method="post" style="margin: 5%">
    <p>Name</p>
    <input type="text" name="name"/>
    <p>Description</p>
    <input type="text" name="description"/>
    <p>Department:</p>
    <input type="text" name="department"/>
    <p>Start Date</p>
    <input type="text" name="startDate" value="dd/MM/yyyy hh:mm:ss"/>
    <p>End Date</p>
    <input type="text" name="endDate" value="dd/MM/yyyy hh:mm:ss"/>
    <input type="submit"/>
</form>

<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:if test="hasActionMessages()">
    <s:actionmessage/>
</s:if>

<div class="col-md-2 text-center" style="margin-top:3%">
    <a href="<s:url action="loggedAdmin" />" class="text-center"><button type="button" class="btn btn-secondary" style="margin-left: 2%">Voltar</button></a>
</div>
</body>
</html>