package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;
import com.excessao.EmailValidator;


@WebServlet(
		urlPatterns = { "/UsuarioEdit" }, 
		initParams = { 
				@WebInitParam(name = "id", value = "")
		})
public class UsuarioEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		String message = null;
		Usuario u = new Usuario();
		if(id != null){
			u.setId(id);
			u.setValid(false);
			UsuarioDAO dao = new UsuarioDAO();
			
			try{
				u = dao.listarPor(u);
				u.setValid(true);
			} catch (Exception e){
				u.setValid(false);
				message = "N‹o foi poss’vel encontrar o usu‡rio selecionado. Tente novamete!";
			}
			HttpSession session = request.getSession(true);
			
			if(u.isValid()){
				session.setAttribute("message", message);
				session.setAttribute("editUsuario", u);
				response.sendRedirect("?p=usuarioEditar");
				
			}else{
				session.setAttribute("message", message);
				response.sendRedirect("?p=usuarioListar");
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		 HttpSession sess = request.getSession(false);
		 Usuario us = (Usuario) sess.getAttribute("editUsuario");

		Long id = Long.valueOf(us.getId());
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String message = null;
		
		if(nome.isEmpty()){
	    	out.print("<p>ƒ necess‡rio informar seu Nome</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }
	    if(email.isEmpty()){
	    	out.print("<p>ƒ necess‡rio informar seu E-mail</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }else{
	    	EmailValidator em = new EmailValidator();
	    	if(!em.validate(email)){
	    		out.print("<p>O E-mail informado Ž falso.</p>");
	    		out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
		    	return;
	    	}
	    }
	    if(senha.isEmpty()){
	    	out.print("<p>ƒ necess‡rio informar sua Senha</p>");
	    	out.print("<button type=\"button\" name=\"back\" onclick=\"history.back()\">Voltar</button>");
	    	return;
	    }
	    
	    Usuario usuario = new Usuario();
	    usuario.setNome(nome);
	    usuario.setEmail(email);
	    usuario.setSenha(senha);
	    usuario.setId(id);
	    usuario.setValid(false);
	    
	    UsuarioDAO dao = new UsuarioDAO();
	    
	    try{
	    	dao.alterar(usuario);
	    	usuario.setValid(true);
	    	message = "Usu‡rio atualizado com sucesso.";
	    } catch (Exception e){
	    	usuario.setValid(false);
	    	message = "Erro: N‹o foi poss’vel editar o usu‡rio.Tente novamente!";
	    }
	    HttpSession session = request.getSession();
	    if(usuario.isValid()){
	    	session.setAttribute("message", message);
	        response.sendRedirect("?p=usuarioListar");
	    } else {
	    	session.setAttribute("message", message);
			session.setAttribute("editUsuario", usuario);
			response.sendRedirect("?p=usuarioEditar");
	    	
	    }
	    
	}

}
