<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>iVotas</title>
</head>
<body>
    <a href="<s:url action="registerPerson" />">Registar Pessoa</a> <br><br>
    <a href="<s:url action="createElection" />">Criar Eleição</a> <br><br>
    <a>Criar Lista de Candidatos</a> <br><br>
    <a href="<s:url action="listElections" />" >Listar Eleições e Detalhes</a> <br><br>
    <a href="<s:url action="createVotingTable" />">Adicionar Mesa de Voto</a> <br><br>
    <a>Alterar Propriedades de Eleição</a> <br><br>
    <a>Saber em que local votou cada eleitor</a> <br><br>
    <a>Consultar Detalhes de Eleições Passadas</a> <br><br>
</body>
</html>