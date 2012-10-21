<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.config.Config" %>
 <%@page import="com.entity.Usuario" %>
<% Usuario u = (Usuario) session.getAttribute("currentSessionUsuario");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"><%@ include file="/css/main.css" %></style>
<title>Sistema de Compras</title>
</head>
<body>
<div id="cabecalho">
<h1>Sistema de Cadastro de Produtos</h1>
</div>
<div id="menu">
    <div class="logado">
       Bem vindo, <%= u.getNome() %> - (<a onclick="return confirm('Tem certeza que deseja sair do sistema?');" href="UsuarioLogout" title="Sair da Sessão">Sair</a>)	
    </div>
	<ul>
		<li><a href="?p=produtoListar">Produtos</a></li>
		<li><a href="?p=usuarioListar">Usuários</a></li>
	</ul>
</div>
<div id="contente">
<% String p = request.getParameter("p");%>
<jsp:include page="<%= Config.getRoute(p) %>"></jsp:include>

</div>
<div id="base">
&copy; 2012 - Programação para Internet II
</div>
</body>
</html>