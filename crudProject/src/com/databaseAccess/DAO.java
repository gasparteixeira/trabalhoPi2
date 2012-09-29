package com.databaseAccess;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.core.Produto;
import com.core.Usuario;

/**
 *
 */
public class DAO
{

    private final String url;
    private final String user;
    private final String pass;
    private final DatabaseProperties db = new DatabaseProperties();
    private String sqlcomando = "";
    private java.sql.Connection conn = null;
    private Statement sql;
    private ResultSet results;

    /**
     * 
     */
    public DAO()
    {
        //inicializa as configurações do banco        
        db.getProperties();
        url = db.getUrl();
        user = db.getUser();
        pass = db.getPass();
    }

    /**
     * Retorna uma lista de usuarios
     * 
     * @return List<Usuario>
     */
    public List<Usuario> getAllUsuario()
    {
        final List<Usuario> list = new ArrayList<Usuario>();
        Usuario p;

        if (getConnection())
        {
            try
            {
                sql = conn.createStatement();
                results = sql.executeQuery("SELECT * from usuario");
                if (results != null)
                {
                    while (results.next())
                    {
                        p = new Usuario(
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
        return list;
    }

    /**
     * Insere um usuario na tabela de usuarios <br>
     * não necessita receber uma data, usa o current_date do postgres
     * 
     * @return void
     */
    public void insereUsuario(final Usuario usuario)
    {

        sqlcomando = "INSERT INTO usuario "
                + "(nome, email, senha, data) "
                + "VALUES ("
                + "'"
                + usuario.getNome()
                + "'"
                + ","
                + "'"
                + usuario.getEmail()
                + "'"
                + ","
                + "'"
                + usuario.getSenha()
                + "'"
                + ","
                + "current_date"
                + ")";

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

    /**
     * Remove um usuario do banco conforme o id
     * 
     * @return void
     */
    public void removeUsuario(final int id)
    {

        sqlcomando = "DELETE FROM usuario WHERE id=" + id;

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

    /**
     * Edita um usuario do banco, recebe um objeto Usuario
     * 
     * @return void
     */
    public void editaUsuario(final Usuario usuario)
    {

        sqlcomando = "UPDATE pessoa SET "
                + "nome='"
                + usuario.getNome()
                + "'"
                + ","
                + "email='"
                + usuario.getEmail()
                + "'"
                + ","
                + "senha='"
                + usuario.getSenha()
                + "'"
                + ","
                + "data= current_date"
                + " WHERE "
                + "id ="
                + usuario.getId();

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

    /**
     * Verifica se o usuario recebido tem permissão de acesso ao sistema<br>
     * retorna true se usuario foi encontrado<br>
     * retorna false se usuario não foi encontrado<br>
     * 
     * @return boolean
     */
    public boolean verificaCredenciaisUsuario(final Usuario usuario)
    {
        if (getConnection())
        {
            try
            {
                sql = conn.createStatement();
                results = sql.executeQuery("SELECT COUNT(1) FROM usuario"
                        + " WHERE nome='"
                        + usuario.getNome()
                        + "' "
                        + "AND "
                        + "senha='"
                        + usuario.getSenha()
                        + "'");

                if (results != null)
                {
                    results.next();
                    if (results.getInt(1) > 0)
                    {
                        closeConnection();
                        return true;
                    }
                    closeConnection();
                    return false;
                }
            }
            catch (final SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        closeConnection();
        return false;
    }

    /**
     * Adiciona um produto na lista de produtos
     * 
     * @return void
     */
    public void insereProduto(final Produto produto)
    {
        sqlcomando = "INSERT INTO produto " + "(descricao) " + "VALUES (" + "'" + produto.getDescricao() + "')";

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

    /**
     * Remove um produto do banco conforme o id
     * 
     * @return void
     */
    public void removeProduto(final int id)
    {
        sqlcomando = "DELETE FROM produto WHERE idprod=" + id;

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

    /**
     * Adiciona um produto ao estoque, o produto precisa estar cadastrado.
     * 
     * @return void
     */
    public void insereProdutoNoEstoque(final int idProd)
    {
        sqlcomando = "INSERT INTO estoque " + "(idprod) " + "VALUES (" + "'" + idProd + "')";

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

    /**
     * Remove um produto do Estoque conforme o id
     * 
     * @return void
     */
    public void removeProdutoDoEstoque(final int idProd)
    {
        sqlcomando = "DELETE FROM estoque WHERE idprod=" + idProd;

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
     * ************************************************************************
     *                            Métodos privados                            *
     * ************************************************************************
     */

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
}
