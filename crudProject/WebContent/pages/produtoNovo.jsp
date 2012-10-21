	<%@page import="java.util.GregorianCalendar"%>
        <%@page import="java.util.Date"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>

	<div class="frmNovo">
	<h1>Inserindo novo Produto</h1>
	<% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
	  <form action="ProdutoNovo" method="post">
	     <label for="descricao">Nome Produto:</label>
	     <input type="text" id="descricao" name="descricao"  />
	     <label for="quantidade">Quantidade:</label>
	     <input type="text" id="quantidade" name="quantidade" />
	     <div class="clear"></div>
	     <input type="submit" value="Cadastrar" />
	     <input type="button" value="Cancelar" onclick="javascript:location.href='?p=produtoListar'" />
	  </form>
	</div>
<% session.removeAttribute("message"); %>