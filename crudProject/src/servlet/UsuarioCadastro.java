package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;
import com.excessao.EmailValidator;


@WebServlet("/Cadastro")
public class UsuarioCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UsuarioCadastro() {
        super();
    }
    /**
     * processRequest - Metodo generico utilizado pelo servlet para tratar
     * as operacoes de requisição. Tanto faz se a requisição for get ou post
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String message = null;
		

	    String nome = request.getParameter("nome");
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
	    if(nome.isEmpty()){
	    	out.print("<p>É necessário informar seu Nome</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }
	    if(email.isEmpty()){
	    	out.print("<p>É necessário informar seu E-mail</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }else{
	    	EmailValidator em = new EmailValidator();
	    	if(!em.validate(email)){
	    		out.print("<p>O E-mail informado é falso.</p>");
	    		out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
		    	return;
	    	}
	    }
	    if(senha.isEmpty()){
	    	out.print("<p>É necessário informar sua Senha</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }
	    Usuario usuario = new Usuario();
	    usuario.setNome(nome);
	    usuario.setEmail(email);
	    usuario.setSenha(senha);
	    usuario.setValid(false);

	    UsuarioDAO dao = new UsuarioDAO();
	    
	    try{
	      dao.criar(usuario);
	      usuario.setValid(true);
	      message = "Usuario cadastrado com sucesso.";
	    } catch(Exception e){
	    	message = "Ops, não foi possivel criar usuario. Erro: " +e.getMessage();
	    	usuario.setValid(false);
	    }
	    
	    if(usuario.isValid()){
	    	response.sendRedirect("/?p=listar");
	    } else {
	    	HttpSession session = request.getSession(true);	    
	        session.setAttribute("usuario",usuario); 
	        response.sendRedirect("/?p=novo"); 
	    }

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
