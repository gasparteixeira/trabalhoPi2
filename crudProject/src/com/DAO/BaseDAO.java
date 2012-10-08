package com.DAO;

import java.sql.Connection;

public abstract class BaseDAO {
	private Conexao con = new Conexao();
	protected Connection conexao;

	public void conectar() throws Exception {
		this.conexao = con.criaConexao(false);
	}

}
