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
 * Servlet implementation class ProdutoEdit
 */
@WebServlet(
		urlPatterns = { "/admin/ProdutoEdit" }, 
		initParams = { 
				@WebInitParam(name = "idprod", value = "")
		})
public class ProdutoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idprod = Integer.parseInt(request.getParameter("idprod"));
		String descricao = request.getParameter("descricao");
		Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
		
		HttpSession session = request.getSession(true);
		String message = null;
		if(idprod.equals(null)){
			message = "Favor, informar a indicador do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoEditar");
	    	return;
		}
		if(descricao.isEmpty() || descricao == null){
			message = "Favor, informar o nome do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoEditar");
	    	return;
		}
		
		if(quantidade.equals(0) || quantidade.equals(null)){
			message = "Favor, informar a quantidade de produtos.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoEditar");
	    	return;
		}
		
		if(quantidade > 10){
			message = "Favor, corrigir a quantidade. Estoque aceito entre 1 e 10 itens do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoEditar");
	    	return;
		}
		
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setId(idprod);
		produto.setQtd(quantidade);
		produto.setValid(false);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		try{
			Integer i = dao.existEditProduto(produto);
			if(i > 0){
				message = "Favor, já existe um produto com o mesmo nome cadastrado.";
				session.setAttribute("message", message);
				session.setAttribute("produto", produto);
		    	response.sendRedirect("?p=produtoEditar");
		    	return;
			}
		} catch(Exception e){
			throw new ServletException("Não foi possível realizar a verificação do produto.");
		}
		
		try{
			dao.alterarProduto(produto);
			produto.setValid(true);
			message = "Produto alterado com sucesso.";
		} catch(Exception e){
			message = "Ops, não foi possível alterar o produto. Tente novamente! ";
			produto.setValid(false);
		}
		
		if(produto.isValid()){
	    	session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoListar");
		} else {
			session.setAttribute("message", message);
			session.setAttribute("produto",produto); 
			response.sendRedirect("?p=produtoEditar"); 
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idprod = Integer.parseInt(request.getParameter("idprod"));
		HttpSession session = request.getSession(true);
		String message = null;
		if(idprod.equals(null)){
			message = "Favor, informar o indicador do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoListar");
	    	return;
		}
		
		Produto produto = new Produto();
		produto.setId(idprod);
		produto.setValid(false);
		
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = new Produto();
		
		try{
			p = dao.buscaProdutoPorId(produto);
		} catch(Exception e){
			throw new ServletException("Não foi possível realizar a busca por produtos.");
		}
		
		if(p.isValid()){
			session.setAttribute("message", message);
			session.setAttribute("produto", p);
	    	response.sendRedirect("?p=produtoEditar");
		}
	}

}
