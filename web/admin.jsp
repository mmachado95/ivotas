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
            writeToHistory(message.data);
        }

        function onError(event) {
            writeToHistory('WebSocket error (' + event.data + ').');
            document.getElementById('chat').onkeydown = null;
        }

        function writeToHistory(text) {
            var history = document.getElementById('users-online');
            var line = document.createElement('p');
            line.style.wordWrap = 'break-word';
            line.innerHTML = text;
            var div = document.createElement('div');
            div.className = 'alert alert-primary';
            div.role = 'alert';
            div.appendChild(line);
            history.appendChild(div);
            //history.scrollTop = history.scrollHeight;
        }
    </script>
</head>
<body>
    <div style="margin-top: 5%">
        <h1 class="text-center">iVotas</h1>
        <h2 class="text-center">Administração</h2>
    </div>

    <div class="row" style="margin: 5%">
        <div class="container col-sm-6">
            <a href="<s:url action="registerPerson" />"><button type="button" class="btn btn-primary">Registar Pessoa</button></a><br><br>
            <a href="choose_type_election.jsp"><button type="button" class="btn btn-primary">Criar Eleição</button></a><br><br>
            <a href="<s:url action="chooseCandidateListElection" />"><button type="button" class="btn btn-primary">Criar Lista de Candidatos</button></a> <br><br>
            <a href="<s:url action="listElections" />" ><button type="button" class="btn btn-primary">Listar Detalhes de Eleição</button></a><br><br>
            <a href="<s:url action="createVotingTable" />"><button type="button" class="btn btn-primary">Criar Mesa de Voto</button></a><br><br>
            <a href="<s:url action="chooseElectionToChange" />" ><button type="button" class="btn btn-primary">Alterar Propriedades de Eleição</button></a><br><br>
            <a href="<s:url action="chooseUser" />"><button type="button" class="btn btn-primary">Saber em que Local votou Eleitor</button></a> <br><br>
            <a href="<s:url action="choosePastElections" />"><button type="button" class="btn btn-primary">Consultar Detalhes de Eleições Passadas</button></a><br><br>
        </div>
        <div class="container col-sm-6" id="users-online" role="alert"></div>
    </div>
</body>
</html>