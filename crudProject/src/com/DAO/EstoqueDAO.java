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
public class EstoqueDAO extends BaseDAO
{
    public EstoqueDAO()
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
     * Adiciona um produto no estoque
     * 
     * @return void
     */

    public void insereProdutoNoEstoque(final Produto p, final int qtd)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "INSERT INTO estoque (idprod, qtd) VALUES (?,?)";

        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            stmt.setInt(2, qtd);
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
     * Remove um produto do estoque
     * 
     * @return void
     * @throws Exception
     */
    public void excluirProdutoDoEstoque(final Produto p)
            throws Exception
    {
        PreparedStatement stmt = null;
        final String sql = "delete from estoque where idprod = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, p.getId());
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
     * Altera um produto do estoque, recebe 2 produtos: <br>
     * p_antigo = produto que esta no estoque<br>
     * p_novo = produto que substituirá o antigo<br>
     * 
     * @return void
     * @throws Exception
     */
    public void alterarProdutoDoEstoque(final Produto p_antigo, final Produto p_novo, final int qtd)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "update estoque set idprod = ?, qtd = ? where id = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, p_novo.getId());
            stmt.setInt(2, qtd);
            stmt.setInt(3, p_antigo.getId());

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
     * Retorna uma lista de produtos cadastrados no estoque<br>
     * cada produto contém um atributo qtd que indica a quantidade no estoque
     * 
     * @return List<Produto>
     * @throws Exception
     */
    public List<Produto> listarProdutosDoEstoque()
            throws Exception
    {
        final List<Produto> lista_produto = new ArrayList<Produto>();

        PreparedStatement stmt = null;
        final String sql = "select estoque.idprod, produto.descricao, estoque.qtd from estoque, produto where estoque.idprod = produto.idprod";
        stmt = conexao.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        conexao.commit();

        while (rs.next())
        {
            final Produto produto = new Produto();
            produto.setId(rs.getInt(1));
            produto.setDescricao(rs.getString(2));
            produto.setQtd(rs.getInt(3));
            lista_produto.add(produto);
        }
        stmt.close();
        return lista_produto;
    }

}
