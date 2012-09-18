<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"><%@ include file="WEB-INF/css/main.css" %></style>

<title>Testando</title>
</head>
<body>
<div id="cabecalho">
<h1>Cadastro de Pessoas</h1>
</div>
<div id="menu">
	<ul>
		<li><a href="?">Inicial</a></li>
		<li><a href="?p=novo">Novo</a></li>
		<li><a href="#">Sair</a></li>
	</ul>
</div>
<div id="contente">
<%
String p = null;
 if(request.getParameter("p")!=null){ 
   	String contextPath="includes/";
    p = request.getParameter("p");
   	p=p+".jsp";
   	p=contextPath+p;
 }else{
	p="includes/listar.jsp";
 }
%>    
<jsp:include page="<%=p%>"></jsp:include>



</div>
<div id="base">
&copy; 2012 - Programação para Internet II
</div>
</body>
</html>