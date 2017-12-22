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
    </div>

    <div class="row" style="margin: 5%">
        <div class="container col-sm-6">
            <a href="<s:url action="registerPerson" />">Registar Pessoa</a> <br><br>
            <a href="choose_type_election.jsp">Criar Eleição</a> <br><br>
            <a href="<s:url action="chooseCandidateListElection" />">Criar Lista de Candidatos</a> <br><br>
            <a href="<s:url action="chooseElectionToCheck" />" >Listar Eleições e Detalhes</a> <br><br>
            <a href="<s:url action="createVotingTable" />">Adicionar Mesa de Voto</a> <br><br>
            <a href="<s:url action="chooseElectionToChange" />" >Alterar Propriedades de Eleição</a> <br><br>
            <a href="<s:url action="chooseUser" />">Saber em que local votou cada eleitor</a> <br><br>
            <a href="<s:url action="choosePastElections" />">Consultar Detalhes de Eleições Passadas</a> <br><br>
            <a href="<s:url action="logoutAdmin" />">Sair</a> <br><br>
        </div>
        <jsp:include page="web_sockets.jsp" />
    </div>
</body>
</html>