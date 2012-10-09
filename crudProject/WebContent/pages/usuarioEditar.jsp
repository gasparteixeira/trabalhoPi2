<%@page import="com.entity.Usuario" %>
<% Usuario u = (Usuario) session.getAttribute("editUsuario");
%>


<div class="frmNovo">
	<h1>Editando Usuário</h1>
	<% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
	  <form action="UsuarioEdit?id=<%=u.getId() %>" method="post">
	     <label for="nome">Nome:</label>
	     <input type="text" id="nome" name="nome" value="<%=u.getNome() %>"  />
	     <label for="email">E-mail:</label>
	     <input type="text" id="email" name="email" value="<%=u.getEmail() %>"  />
	     <label for="senha">Senha:</label>
	     <input type="password" id="senha" name="senha" value="<%=u.getSenha() %>" />
	     <input type="hidden" id="id" name="id" value="<%=u.getId() %>" />
	     <div class="clear"></div>
	     <input type="submit" value="Atualizar" />
	  </form>
	</div>
<% session.removeAttribute("message"); %>