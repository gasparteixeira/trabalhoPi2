<%@page import="com.DAO.UsuarioDAO" %>
<%@page import="com.entity.Usuario" %>
<%@page import="java.util.List" %>
<div class="grid">
<% 
UsuarioDAO dao = new UsuarioDAO();
List<Usuario> list = dao.listar();
int i = 0;
String status = "";
if(request.getParameter("success")!=null){
	if(request.getParameter("success").equals("true")){
		status = "Usuário excluído com sucesso!";
	}else{
		status = "Erro: Usuário não foi excluído.";
	}
}
%>
   <div class="response">
   			<%=status %>
   </div>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>E-mail</th>
				<th>Cadastro</th>
				<th colspan="2">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(Usuario u: list) {%>
			<tr class="<%=(i%2==0) ? "claro" : "escuro" %>">
				<td><%=u.getNome() %></td>
				<td><%=u.getEmail() %></td>
				<td><%=u.getData() %></td>
				<td><a href="UsuarioEdit?id=<%=u.getId()%>">Editar</a></td>
				<td><a href="UsuarioDelete?id=<%=u.getId()%>">Excluir</a></td>
			</tr>
			<% i++; } %>
		</tbody>
	</table>
</div>