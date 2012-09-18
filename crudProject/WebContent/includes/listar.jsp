<div class="grid">
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Cidade</th>
				<th>Endereço</th>
				<th>Data</th>
				<th colspan="2">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(int i = 0; i < 5; i++) {%>
			<tr class="<%=(i%2==0) ? "claro" : "escuro" %>">
				<td>Gaspar</td>
				<td>Porto Alegre</td>
				<td>Praia de Belas</td>
				<td>20/09/2012</td>
				<td><a href="#">Editar</a></td>
				<td><a href="#">Excluir</a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
</div>