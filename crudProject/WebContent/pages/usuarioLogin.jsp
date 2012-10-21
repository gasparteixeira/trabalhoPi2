
<div class="login">
 <h3>Área Restrita:</h3>
	
 <div class="resposta">
 <% if(session.getAttribute("message") != null){ %>
        <div class="message" style="color:red">${message}</div>
   <% } %>
 </div>
  <form class="frmLogin" action="Login" method="post">
    <label for="email">Seu E-mail:</label>
  	<input type="text" name="email" id="email" />
  	<label for="senha">Sua Senha:</label>
  	<input type="password" name="senha" id="senha" />
  	<input type="submit" value="Acessar" />
  </form>
</div>
<% session.removeAttribute("message"); %>