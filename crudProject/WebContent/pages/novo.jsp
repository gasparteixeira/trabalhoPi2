	
	<%@page import="java.util.GregorianCalendar"%>
        <%@page import="java.util.Date"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.List"%>

	<div class="frmNovo">
	<h1>Inserindo novo Usuario</h1>
	  <form action="Cadastro" method="post">
	     <label for="nome">Nome:</label>
	     <input type="text" id="nome" name="nome"  />
	     <label for="email">E-mail:</label>
	     <input type="text" id="email" name="email"  />
	     <label for="senha">Senha:</label>
	     <input type="password" id="senha" name="senha" />
	     <div class="clear"></div>
	     <input type="submit" value="Cadastrar" />
	  </form>
	</div>