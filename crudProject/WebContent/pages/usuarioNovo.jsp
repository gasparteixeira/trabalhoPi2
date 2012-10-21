	
<%@page import="com.entity.Usuario" %>

	<div class="frmNovo">
	<h1>Inserindo novo Usuário</h1>
	<% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
	  <form action="Cadastro" method="post">
	     <label for="nome">Nome:</label>
	     <input type="text" id="nome" name="nome"   />
	     <label for="email">E-mail:</label>
	     <input type="text" id="email" name="email"   />
	     <label for="senha">Senha:</label>
	     <input type="password" id="senha" name="senha"  />
	     <div class="clear"></div>
	     <input type="submit" value="Cadastrar" />
	     <input type="button" value="Cancelar" onclick="javascript:location.href='?p=usuarioListar'" />
	  </form>
	</div>
<% session.removeAttribute("message"); %>