package com.controller;

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


@WebServlet("/admin/Cadastro")
public class UsuarioCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UsuarioCadastro() {
        super();
    }
    /**
     * processRequest - Metodo generico utilizado pelo servlet para tratar
     * as operacoes de requisi‹o. Tanto faz se a requisi‹o for get ou post
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String message = null;
		HttpSession session = request.getSession(true);	
		Usuario usuario = new Usuario();

	    String nome = request.getParameter("nome");
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
	    usuario.setNome(nome);
	    usuario.setEmail(email);
	    usuario.setSenha(senha);
	    usuario.setValid(false);
	      
	    if(nome.isEmpty()){
	    	message = "ƒ necess‡rio informar seu Nome.";
	    	session.setAttribute("message", message);
	    	session.setAttribute("usuario", usuario);
	    	response.sendRedirect("?p=usuarioNovo");
	    	return;
	    }
	    if(email.isEmpty()){
	    	message = "ƒ necess‡rio informar seu E-mail.";
	    	session.setAttribute("message", message); 
	    	session.setAttribute("usuario", usuario);
	    	response.sendRedirect("?p=usuarioNovo");
	    	return;
	    }else{
	    	EmailValidator em = new EmailValidator();
	    	if(!em.validate(email)){
	    		message = "O E-mail informado Ž falso.";
		    	session.setAttribute("message", message);
		    	session.setAttribute("usuario", usuario);
		    	response.sendRedirect("?p=usuarioNovo");
		    	return;
	    	}
	    }
	    if(senha.isEmpty()){
	    	message = "ƒ necess‡rio informar sua Senha.";
	    	session.setAttribute("message", message);
	    	session.setAttribute("usuario", usuario);
	    	response.sendRedirect("?p=usuarioNovo");
	    	return;
	    }

	    UsuarioDAO dao = new UsuarioDAO();
	    
	    try {
	    	Integer i = dao.validaExisteEmail(usuario.getEmail());
	    	if(i > 0){
	    		message = "O E-mail '"+usuario.getEmail()+"' j‡ foi cadasatrado anteriormente. Tente outro e-mail.";
	    		session.setAttribute("message", message);
	    		session.setAttribute("usuario", usuario);
		    	response.sendRedirect("?p=usuarioNovo");
		    	return;
	    	}
	    } catch (Exception e) {
	    	message = "Ops, n‹o foi poss’vel verificar o e-mail na base. Tente novamente!";
	    }
	    
	    
	    try{
	      dao.criar(usuario);
	      usuario.setValid(true);
	      message = "Usu‡rio cadastrado com sucesso.";
	    } catch(Exception e){
	    	message = "Ops, n‹o foi poss’vel criar usu‡rio. Tente novamente! ";
	    	usuario.setValid(false);
	    }
	    
	    if(usuario.isValid()){
		    	session.setAttribute("message", message);
		    	session.setAttribute("usuario", usuario);
		    	response.sendRedirect("?p=usuarioListar");
	    } else {
	    	session.setAttribute("message", message);
	        session.setAttribute("usuario",usuario); 
	        response.sendRedirect("?p=usuarioNovo"); 
	    }

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
