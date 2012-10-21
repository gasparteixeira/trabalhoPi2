package com.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Usuario;

/**
 * Servlet Filter implementation class SegurancaFiltro
 */
@WebFilter(
		urlPatterns = { "/SegurancaFiltro" }, 
		initParams = { 
				@WebInitParam(name = "filtro", value = "seguro")
		})
public class SegurancaFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public SegurancaFiltro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletResponse res = (HttpServletResponse) response; 	

		Usuario usuario = (Usuario) session
				.getAttribute("currentSessionUsuario");

		if (usuario == null) {
			session.setAttribute("message", "Você não está logado no sistema. Por favor preencha seus dados abaixo!");
			res.sendRedirect("/crudProject");
		} else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
