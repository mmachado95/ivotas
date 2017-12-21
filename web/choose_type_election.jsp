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

    <h2>Choose type of election to Create</h2>

    <a href="<s:url action="createElectionStudents" />">Nucleo de Estudantes</a> <br><br>
    <a href="<s:url action="createElection" />">Conselho Geral</a> <br><br>

</body>
</html>