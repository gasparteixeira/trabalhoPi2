<%@page import="com.DAO.ProdutoDAO" %>
<%@page import="com.entity.Produto" %>
<%@page import="java.util.List" %>

<div class="grid">
<% 
ProdutoDAO dao = new ProdutoDAO();
List<Produto> list = dao.listarProdutos();
int i = 0;
%>

   <% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
   <h3>Listando os Produtos cadastrados:</h3>
	<table>
		<thead>
			<tr>
				<th>Prodtuo</th>
				<th>Estoque</th>
				<th colspan="2">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(Produto p: list) {%>
			<tr class="<%=(i%2==0) ? "claro" : "escuro" %>">
				<td><%=p.getDescricao() %></td>
				<td align="center"><%=p.getQtd()%></td>
				<td align="center"><a href="ProdutoEdit?idprod=<%=p.getId()%>">Editar</a></td>
				<td align="center"><a href="ProdutoDelete?idprod=<%=p.getId()%>" onclick="javascript:return confirm('Tem certeza que deseja excluir o produto <%=p.getDescricao() %>');" >
					Excluir</a></td>
			</tr>
			<% i++; } %>
		</tbody>
	</table>
	<div class="acoes">
	<a href="?p=produtoListar">Listar</a>
	<a href="?p=produtoNovo">Novo</a>
	</div>
	
</div>
<% session.removeAttribute("message"); %>