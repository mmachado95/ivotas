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

    <script type="text/javascript">
        var websocket = null;

        window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
            connect('ws://' + window.location.host + '/ws');
            console.log(window.location.host);
        };

        function connect(host) { // connect to the host websocket
            if ('WebSocket' in window)
                websocket = new WebSocket(host);
            else if ('MozWebSocket' in window)
                websocket = new MozWebSocket(host);
            else {
                writeToHistory('Get a real browser which supports WebSocket.');
                return;
            }

            websocket.onopen    = onOpen; // set the event listeners below
            websocket.onclose   = onClose;

            document.getElementById("logout-button").addEventListener("click", function() {
                websocket.send("logout:"+"<c:out value="${session.username}" />"+"-"+"<c:out value="${session.password}" />");

            });
        }

        function onOpen(event) {
            <c:if test="${session.firstTime}">
                console.log("passed")
                websocket.send("login:"+"<c:out value="${session.username}" />"+"-"+"<c:out value="${session.password}" />");
                <c:remove var="firstTime" scope="session" />
            </c:if>
        }

        function onClose(event) {
            websocket.send('WebSocket closed.');
        }
    </script>
</head>
<body>

    <div style="margin-top: 5%">
        <h1 class="text-center">iVotas</h1>
        <h2 class="text-center">Administração</h2>
        <h4 class="text-center">Escolher Eleição</h4>
    </div>

    <div class="container" style="margin-top: 5%">

        <c:forEach items="${session.iVotasBean.getAllElections()}" var="value">
            <s:url action="election" var="electionTag">
                <s:param name="electionName"> <c:out value="${value}" /> </s:param>
            </s:url>

            <a href="<s:property value="#electionTag" />">
                <button class="btn btn-primary"><c:out value="${value}" /></button>
            </a>
            <br><br>
        </c:forEach>

    </div>

    <s:if test="hasActionErrors()">
        <s:actionerror/>
    </s:if>
    <s:if test="hasActionMessages()">
        <s:actionmessage/>
    </s:if>

    <a href="https://www.facebook.com/v2.2/dialog/oauth?client_id=1486604264709457&redirect_uri=http://127.0.0.1:8080/connectFacebook&scope=publish_actions"><button type="button" class="btn btn-secondary" style="margin-left: 2%">Connect Facebook</button></a>
    <br><br>
    <a href="<s:url action="logout" />" id="logout-button"><button class="btn btn-secondary" style="margin-left: 2%">Logout</button></a>
</body>
</html>