package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.ProdutoDAO;
import com.entity.Produto;

/**
 * Servlet implementation class ProdutoDelete
 */
@WebServlet(
		urlPatterns = { "/admin/ProdutoDelete" }, 
		initParams = { 
				@WebInitParam(name = "idprod", value = "")
		})
public class ProdutoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idprod = Integer.valueOf(request.getParameter("idprod"));
		Produto p = new Produto();
		String message = null;
		HttpSession session = request.getSession(true);
		System.out.println("excluindo o cara. "+ idprod);
		if(idprod != null){
			p.setId(idprod);
			p.setValid(false);
			
			ProdutoDAO dao = new ProdutoDAO();
			
			try {
				dao.excluirProduto(p);
				message = "Produto exclu’do com sucesso!";
				p.setValid(true);
			} catch (Exception e){
				message = "Ops, n‹o foi poss’vel excluir o produto. Tente novamente! ";
				throw new ServletException("Ops, n‹o foi poss’vel excluir o produto. Tente novamente!");
			}

		} else {
			message = "Ops, o identificador do produto n‹o foi informado.";
		}
		session.setAttribute("message", message);
		response.sendRedirect("?p=produtoListar");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
