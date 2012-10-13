package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.entity.Produto;

/**
 *
 */
public class ProdutoDAO extends BaseDAO
{

    /**
     * 
     */
    public ProdutoDAO()
    {
        try
        {
            this.conectar();
        }
        catch (final Exception ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adiciona um produto na lista de produtos
     * 
     * @return void
     */

    public void insereProduto(final Produto p)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "INSERT INTO produto (descricao) VALUES (?)";

        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.executeQuery();
            conexao.commit();
        }
        catch (final SQLException e)
        {
            conexao.rollback();
            throw e;
        }
        finally
        {
            stmt.close();
            conexao.close();
        }
    }

    /**
     * Remove um produto
     * 
     * @return void
     * @throws Exception
     */
    public void excluirProduto(final Produto p)
            throws Exception
    {
        PreparedStatement stmt = null;
        final String sql = "delete from produto where id = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.executeUpdate();
            conexao.commit();
        }
        catch (final SQLException e)
        {
            conexao.rollback();
            throw e;
        }
        finally
        {
            stmt.close();
            conexao.close();
        }
    }

    /**
     * Altera um produto
     * 
     * @return void
     * @throws Exception
     */
    public void alterarProduto(final Produto p)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "update produto set descricao = ? where id = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setLong(2, p.getId());

            stmt.executeUpdate();
            conexao.commit();
        }
        catch (final SQLException e)
        {
            conexao.rollback();
            throw e;
        }
        finally
        {
            stmt.close();
            conexao.close();
        }
    }

    /**
     * Retorna uma lista de produtos cadastrados
     * 
     * @return List<Produto>
     * @throws Exception
     */
    public List<Produto> listarProdutos()
            throws Exception
    {
        final List<Produto> lista_produto = new ArrayList<Produto>();

        PreparedStatement stmt = null;
        final String sql = "select * from produto";
        stmt = conexao.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        conexao.commit();

        while (rs.next())
        {
            final Produto produto = new Produto();
            produto.setId(rs.getInt(1));
            produto.setDescricao(rs.getString(2));
            lista_produto.add(produto);
        }
        stmt.close();
        return lista_produto;
    }
}
