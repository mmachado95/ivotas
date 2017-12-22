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
        <h2 class="text-center">Administração</h2>
        <h4 class="text-center">Local e Momento de Voto</h4>
    </div>

    <div class="container" style="margin-top: 5%">
        <c:forEach items="${votes}" var="vote">
            <h6 class="text-center">${vote.election.name} -->
                <c:choose>
                    <c:when test="${vote.department!=null}">
                        ${vote.department.name}
                    </c:when>
                    <c:otherwise>
                        Online
                    </c:otherwise>
                </c:choose></h6>

            <h6 class="text-center">${vote.moment}</h6>
            <br><br>
        </c:forEach>
    </div>

    <div class="col-md-2 text-center" style="margin-top:3%">
        <a href="<s:url action="admin" />" class="text-center"><button type="button" class="btn btn-secondary" style="margin-left: 2%">Voltar</button></a>
    </div>

</body>
</html>