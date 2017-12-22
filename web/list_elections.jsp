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
            //document.getElementById("chat").focus();
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

            websocket.onmessage = onMessage;
            websocket.onerror   = onError;
        }

        function onMessage(message) { // print the received message
            if(message.data.charAt(0) == "_")
                writeToHistory(message.data);
        }

        function onError(event) {
            writeToHistory('WebSocket error (' + event.data + ').');
            document.getElementById('chat').onkeydown = null;
        }

        function writeToHistory(text) {
            var history = document.getElementById('votes');
            var line = document.createElement('h6');
            line.style.wordWrap = 'break-word';
            line.innerHTML = text;
            history.appendChild(line);
            //history.scrollTop = history.scrollHeight;
        }
    </script>
</head>
<body>

    <div style="margin-top: 5%">
        <h1 class="text-center">iVotas</h1>
        <h2 class="text-center">Administração</h2>
        <h4 class="text-center">Detalhes Eleição</h4>
    </div>

    <div class="container" style="margin-top: 3%">
        <h6 class="text-center">Nome: ${electionObject.name}</h6>
        <h6 class="text-center">Descrição: ${electionObject.description}</h6>
        <h6 class="text-center">Data de Inicio: ${electionObject.startDate}</h6>
        <h6 class="text-center">Data de Fim: ${electionObject.endDate}</h6>
        <h6 class="text-center">Tipo: ${electionObject.type}</h6>
        <h6 class="text-center">Listas de Candidatos:</h6>
        <c:forEach items="${electionObject.candidateLists}" var="candidateList">
            <h6 class="text-center">${candidateList.name}</h6>
        </c:forEach>
        <br><br>

        <h5>Eleitores que votaram ate ao momento</h5>

        <c:forEach items="${details}" var="detail">

            <h5>Nome: ${detail.get("name")}</h5>
            <h5>Tipo: ${detail.get("type")}</h5>
            <h5>Web ou mesa de voto: ${detail.get("place")}</h5>

        </c:forEach>

        <div id="votes"></div>

    </div>

    <div class="col-md-2 text-center" style="margin-top:3%">
        <a href="<s:url action="loggedAdmin" />" class="text-center"><button type="button" class="btn btn-secondary" style="margin-left: 2%">Voltar</button></a>
    </div>

</body>
</html>