<%@page import="com.entity.Produto" %>
<% Produto p = (Produto) session.getAttribute("produto");%>

	<div class="frmNovo">
	<h1>Editando o produto "<%=p.getDescricao() %>"</h1>
	<% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
	  <form action="ProdutoEdit?idprod=<%=p.getId() %>" method="post">
	     <label for="descricao">Nome Produto:</label>
	     <input type="text" id="descricao" name="descricao" value="<%=p.getDescricao()%>"  />
	     <label for="quantidade">Quantidade:</label>
	     <input type="text" id="quantidade" name="quantidade" value="<%=p.getQtd()%>" />
	     <input type="hidden" name="idprod" value="<%=p.getId()%>" />
	     <div class="clear"></div>
	     <input type="submit" value="Atualizar" />
	     <input type="button" value="Cancelar" onclick="javascript:location.href='?p=produtoListar'" />
	  </form>
	</div>
<% session.removeAttribute("message"); %>