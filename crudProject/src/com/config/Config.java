package com.config;

public class Config {

	public static String getRoute(String p) {
		String page = null;
		if (p != null) {
			String contextPath = "";
			contextPath = "pages/";
			page = p + ".jsp";
			page = contextPath + page;
		} else {
			page = "pages/usuarioListar.jsp";
		}
		return page;
	}

}
