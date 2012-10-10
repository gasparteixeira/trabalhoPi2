package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.DAO.config.DatabaseProperties;

public class Conexao
{

    private String url = "";
    private String user = "";
    private String password = "";
    private final String postgresqlDriver = "org.postgresql.Driver";
    private Connection connection;
    private final DatabaseProperties db = new DatabaseProperties();

    public Conexao()
    {
        //inicializa as configurações do banco        
        db.getProperties();
        url = db.getUrl();
        user = db.getUser();
        password = db.getPass();
    }

    protected Connection criaConexao(final Boolean autocommit)
            throws Exception
    {
        try
        {
            Class.forName(postgresqlDriver);
        }
        catch (final ClassNotFoundException e)
        {
            throw e;
        }
        try
        {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(autocommit);
        }
        catch (final SQLException e)
        {
            throw e;
        }
        return connection;
    }

    protected void fechaConexao()
            throws SQLException
    {
        try
        {
            connection.close();
        }
        catch (final SQLException e)
        {
            throw e;
        }
    }
}
