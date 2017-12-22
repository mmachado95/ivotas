<%@ taglib prefix="s" uri="/struts-tags"%>
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

	<s:form action="login" method="post" style="margin: 5%">
		<s:text name="Username:" />
		<s:textfield name="username" /><br>
		<s:text name="Password:" />
		<s:textfield type="password" name="password" /><br>
		<s:submit />
	</s:form>

	<a href="https://www.facebook.com/v2.2/dialog/oauth?client_id=1486604264709457&redirect_uri=http://127.0.0.1:8080/loginFacebook&scope=publish_actions" class="text-center"><button type="button" class="btn btn-primary" style="margin-left: 2%">Login Facebook</button></a>

</body>
</html>