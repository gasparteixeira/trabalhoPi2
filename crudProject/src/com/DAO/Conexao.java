package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private String url = "jdbc:postgresql://localhost:5432/trabalhoPi2";
	private String user = "postgres";
	private String password = "postgres";
	private String postgresqlDriver = "org.postgresql.Driver";
	private Connection connection;

	protected Connection criaConexao(Boolean autocommit) throws Exception {
		try {
			Class.forName(postgresqlDriver);
		} catch (ClassNotFoundException e) {
			throw e;
		}
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(autocommit);
		} catch (SQLException e) {
			throw e;
		}
		return connection;
	}

	protected void fechaConexao() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw e;
		}
	}
}