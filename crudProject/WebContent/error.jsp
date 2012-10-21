<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"><%@ include file="/css/main.css" %></style>
<title>Página de Erro</title>
</head>
<body>
<div id="cabecalho">
<h1>Sistema de Cadastro de Produtos</h1>
</div>
	<div id="contente-home">
		<div class="error">
			<h2>Ops, Ocorreu um erro interno!</h2> 
			
			<h3>Código do Erro: ${statusCode} </h3>
			<p>A mensagem do erro é:<font style="color:red"> ${errorType.message} </font></p>
			
			<div class="acoes">
			   <a href="javascript:history.go(-1)" title="Voltar Página Anterior">Voltar</a>
			   <a href="/crudProject/" title="Ir para Homepage">Home</a>
			</div>
		</div>
	</div>
<div id="base">
&copy; 2012 - Programação para Internet II
</div>
</body>
</html>