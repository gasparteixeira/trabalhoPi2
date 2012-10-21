package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.ProdutoDAO;
import com.entity.Produto;

@WebServlet("/admin/ProdutoNovo")
public class ProdutoNovo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String descricao = request.getParameter("descricao");
		Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
		HttpSession session = request.getSession(true);
		String message = null;
		
		if(descricao.isEmpty() || descricao == null){
			message = "Favor, informar o nome do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoNovo");
	    	return;
		}
		
		if(quantidade.equals(0) || quantidade.equals(null)){
			message = "Favor, informar a quantidade de produtos.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoNovo");
	    	return;
		}
		
		if(quantidade > 10){
			message = "Favor, corrigir a quantidade. Estoque aceito entre 1 e 10 itens do produto.";
			session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoNovo");
	    	return;
		}
		
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setQtd(quantidade);
		produto.setValid(false);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		try{
			Integer i = dao.existProduto(descricao);
			if(i > 0){
				message = "Favor, já existe um produto com o mesmo nome cadastrado.";
				session.setAttribute("message", message);
		    	response.sendRedirect("?p=produtoNovo");
		    	return;
			}
		} catch(Exception e){
			message = "Ops, nao foi possivel verificar a existência do produto. Tente novamente!";
		}
		
		try{
			dao.insereProduto(produto);
			produto.setValid(true);
			message = "Produto cadastrado com sucesso.";
		} catch(Exception e){
			message = "Ops, não foi possível cadastrar o produto. Tente novamente! ";
			produto.setValid(false);
		}
		
		if(produto.isValid()){
	    	session.setAttribute("message", message);
	    	response.sendRedirect("?p=produtoListar");
		} else {
			session.setAttribute("message", message);
			session.setAttribute("produto",produto); 
			response.sendRedirect("?p=produtoNovo"); 
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Este controlador não aceita este tipo de requisição.");
	}



}
