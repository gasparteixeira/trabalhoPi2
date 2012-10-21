package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;


@WebServlet("/Login")
public class UsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String message = null;
		
		
		if(email.isEmpty() || senha.isEmpty())
			message = "Você precisa informar seu E-mail e Senha para acessar.";
		
		
		HttpSession session = request.getSession(true);
		if(message != null){
			session.setAttribute("message", message);
			 response.sendRedirect("?");
			 return;
		}
		
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setSenha(senha);
		u.setValid(false);
		
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario uResposta = new Usuario();
		uResposta.setValid(false);

		try{
		   uResposta =	dao.verificaCredenciaisUsuario(u);
		   uResposta.setValid(true);
		} catch (Exception e){
			uResposta.setValid(false);
			//throw new ServletException(e);
		}
		
		
		if(uResposta.isValid()){     
	          session.setAttribute("currentSessionUsuario",uResposta); 
			  response.sendRedirect("admin/?p=produtoListar");
		} else {
			 message = "Usuário e/ou senha inválidos.";
			 session.setAttribute("message", message);
			 response.sendRedirect("?");
		}
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			throw new ServletException("Este controlador não aceita este tipo de requisição.");
		
	}

}
