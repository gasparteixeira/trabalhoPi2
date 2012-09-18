	
	<%@page import="java.util.GregorianCalendar"%>
        <%@page import="java.util.Date"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>
	
	<div class="frmNovo">
	<h1>Inserindo nova Pessoa</h1>
	  <form action="Cadastro" method="post">
	     <label for="nome">Nome:</label>
	     <input type="text" id="nome" name="nome" />
	     <label for="cidade">Cidade:</label>
	     <input type="text" id="cidade" name="cidade" />
	     <label for="endereco">Endereço:</label>
	     <input type="text" id="endereco" name="endereco" />
	     <label for="data">Data:</label>
	     <select name="dia"> 
	         <option value="0">Dia</option>
	     	<% for(int i=1; i < 32; i++){ %>
	     		<option value="<%=i%>"><%=i%></option>
	     	<% } %>
	     </select>
	     <% 
	     List<String> meses = new ArrayList<String>();
	     meses.add("Janeiro");
	     meses.add("Feveiro");
	     meses.add("Março");
	     meses.add("Abril");
	     meses.add("Maio");
	     meses.add("Junho");
	     meses.add("Julho");
	     meses.add("Agosto");
	     meses.add("Setembro");
	     meses.add("Outubro");
	     meses.add("Novembro");
	     meses.add("Dezembro");
	     %>
	     <select name="mes"> 
	         <option value="0">Mês</option>
	     	<% int m = 1; for(String s: meses){ %>
	     		<option value="<%=m%>"><%=s%></option>
	     	<% m++; } %>
	     </select>
	     <select name="ano"> 
	         <option value="0">Ano</option>
	     	<% 
	     	GregorianCalendar calendario = new GregorianCalendar();  
	        Date data = new Date();  
	        calendario.setTime(data);  
	        int a = calendario.get(GregorianCalendar.YEAR);
	     	for(int i=0; i < 5; i++){ %>
	     		<option value="<%=i + a%>"><%=i + a%></option>
	     	<% } %>
	     </select>
	     <div class="clear"></div>
	     <input type="submit" value="Salvar" />
	  </form>
	</div>