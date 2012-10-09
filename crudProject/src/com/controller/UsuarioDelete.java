package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;

@WebServlet(
		urlPatterns = { "/UsuarioDelete" }, 
		initParams = { 
				@WebInitParam(name = "id", value = "")
		})
public class UsuarioDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.valueOf(request.getParameter("id"));
		Usuario u = new Usuario();
		String message = null;
		
		if(id != null){
			
			u.setId(id);
			u.setValid(false);
			UsuarioDAO dao = new UsuarioDAO();
			
			try{
				dao.excluir(u);
				u.setValid(true);
				message = "Usu‡rio removido da base de dados.";
			}catch(Exception e){
				u.setValid(false);
				message = "Nao foi poss’vel remover o usu‡rio da base.";
			}    
			
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("message", message);
		response.sendRedirect("?p=usuarioListar");
	}

}
