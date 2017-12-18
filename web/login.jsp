<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hey!</title>
</head>
<body>
	<p><a href="https://www.facebook.com/v2.2/dialog/oauth?client_id=1486604264709457&redirect_uri=http://127.0.0.1:8080/loginFacebook&scope=publish_actions">Login Facebook</a></p><br>

	<s:form action="login" method="post">
		<s:text name="Username:" />
		<s:textfield name="username" /><br>
		<s:text name="Password:" />
		<s:textfield name="password" /><br>
		<s:submit />
	</s:form>
</body>
</html>