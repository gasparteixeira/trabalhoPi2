package com.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pessoa.Pessoa;

/**
 *
 */
public class DAO
{

    private static final String url = "jdbc:postgresql://localhost/My_Database";
    private static final String user = "postgres";
    private static final String pass = "abc";
    private String sqlcomando = "";
    private java.sql.Connection conn = null;
    private Statement sql;
    private ResultSet results;

    /**
     * 
     */
    public DAO()
    {

    }

    /*
     * Get connection
     */
    private boolean getConnection()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (final ClassNotFoundException e)
        {
            System.out.println("Problemas no registro do driver");
            e.printStackTrace();
            return false;
        }

        System.out.println("\n" + "Driver registrado" + "\n");

        try
        {
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (final SQLException e)
        {
            System.out.println("Falha na obtenção da conexao");
            e.printStackTrace();
            return false;
        }

        if (conn != null)
        {
            System.out.println("\n" + "conectado!" + "\n");
        }
        else
        {
            System.out.println("Falha na conexao");
            return false;
        }
        return true;
    }

    /*
     * Close connection
     */
    private void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * return all objects from database
     */
    public List<Pessoa> getPessoas()
    {
        final List<Pessoa> list = new ArrayList<Pessoa>();
        Pessoa p;

        if (getConnection())
        {
            try
            {
                sql = conn.createStatement();
                results = sql.executeQuery("SELECT * from pessoa");
                if (results != null)
                {
                    while (results.next())
                    {
                        p = new Pessoa(
                                results.getInt(1),
                                results.getString(2),
                                results.getString(3),
                                results.getString(4),
                                results.getDate(5));
                        list.add(p);
                    }
                    closeConnection();
                    return list;
                }
            }
            catch (final SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        closeConnection();
        return list;
    }

    /*
     * insert into database an object
     */
    public void inserir(final Pessoa p)
    {

        sqlcomando = "INSERT INTO pessoa "
                + "(nome, cidade, endereco, data) "
                + "VALUES ("
                + "'"
                + p.getNome()
                + "'"
                + ","
                + "'"
                + p.getCidade()
                + "'"
                + ","
                + "'"
                + p.getEndereco()
                + "'"
                + ","
                + "'"
                + p.getDate()
                + "')";

        //System.out.println(sqlcomando);
        try
        {
            if (getConnection())
            {
                sql = conn.createStatement();
                sql.executeUpdate(sqlcomando);
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeConnection();
    }

    /*
     * Remove uma pessoa do banco conforme o id
     */
    public void remover(final int id)
    {

        sqlcomando = "DELETE FROM pessoa WHERE id=" + id;

        System.out.println(sqlcomando);
        try
        {
            if (getConnection())
            {
                sql = conn.createStatement();
                sql.executeUpdate(sqlcomando);
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeConnection();
    }

    /*
     * edita pessoa
     */
    public void editar(final Pessoa p)
    {

        sqlcomando = "UPDATE pessoa SET "
                + "nome='"
                + p.getNome()
                + "'"
                + ","
                + "cidade='"
                + p.getCidade()
                + "'"
                + ","
                + "endereco='"
                + p.getEndereco()
                + "'"
                + ","
                + "data='"
                + p.getDate()
                + "'"
                + " WHERE "
                + "id ="
                + p.getId();

        System.out.println(sqlcomando);
        try
        {
            if (getConnection())
            {
                sql = conn.createStatement();
                sql.executeUpdate(sqlcomando);
            }
        }
        catch (final SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeConnection();
    }

}
