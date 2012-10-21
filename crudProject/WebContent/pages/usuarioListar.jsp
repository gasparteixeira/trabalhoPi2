<%@page import="com.DAO.UsuarioDAO" %>
<%@page import="com.entity.Usuario" %>
<%@page import="java.util.List" %>

<div class="grid">
<% 
UsuarioDAO dao = new UsuarioDAO();
List<Usuario> list = dao.listar();
int i = 0;
%>

   <% if(session.getAttribute("message") != null){ %>
        <div class="message">${message}</div>
   <% } %>
   <h3>Listando os Usurários cadastrados:</h3>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>E-mail</th>
				<th>Data Cadastro</th>
				<th colspan="2">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(Usuario u: list) {%>
			<tr class="<%=(i%2==0) ? "claro" : "escuro" %>">
				<td><%=u.getNome() %></td>
				<td><%=u.getEmail() %></td>
				<%java.text.DateFormat df = new java.text.SimpleDateFormat("EEE, d MMMM yyyy"); %>
				<td align="center"><%=df.format(u.getData()) %></td>
				<td align="center"><a href="UsuarioEdit?id=<%=u.getId()%>">Editar</a></td>
				<td align="center"><a href="UsuarioDelete?id=<%=u.getId()%>" onclick="javascript:return confirm('Tem certeza que deseja excluir o usuário <%=u.getNome() %>');" >
					Excluir</a></td>
			</tr>
			<% i++; } %>
		</tbody>
	</table>
	<div class="acoes">
	<a href="?p=usuarioListar">Listar</a>
	<a href="?p=usuarioNovo">Novo</a>
	</div>
	
</div>
<% session.removeAttribute("message"); %>

