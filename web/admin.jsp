<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
            history.appendChild(line);
            //history.scrollTop = history.scrollHeight;
        }
    </script>
</head>
<body>
    <a href="<s:url action="registerPerson" />">Registar Pessoa</a> <br><br>
    <a href="choose_type_election.jsp">Criar Eleição</a> <br><br>
    <a href="<s:url action="chooseCandidateListElection" />">Criar Lista de Candidatos</a> <br><br>
    <a href="<s:url action="listElections" />" >Listar Eleições e Detalhes</a> <br><br>
    <a href="<s:url action="createVotingTable" />">Adicionar Mesa de Voto</a> <br><br>
    <a href="<s:url action="chooseElectionToChange" />" >Alterar Propriedades de Eleição</a> <br><br>
    <a href="<s:url action="chooseUser" />">Saber em que local votou cada eleitor</a> <br><br>
    <a href="<s:url action="choosePastElections" />">Consultar Detalhes de Eleições Passadas</a> <br><br>
    <div id="users-online">
    </div>
</body>
</html>