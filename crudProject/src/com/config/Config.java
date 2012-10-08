package com.config;

public class Config {
	
	public static String getRoute(String p){
		String page = null;
		 if(p!=null){ 
		   	String contextPath="pages/";
		   	page = p+".jsp";
		   	page = contextPath+page;
		 }else{
			page = "pages/listar.jsp";
		 }
		return page;
	}

}
