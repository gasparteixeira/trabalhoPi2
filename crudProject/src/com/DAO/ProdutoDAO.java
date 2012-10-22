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
        final String sql = "INSERT INTO produto (descricao) VALUES (?) returning idprod;";

        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            ResultSet rs = stmt.executeQuery();
            conexao.commit();
            
            int id_produto = 0;
            if(rs.next()){
            	id_produto = rs.getInt("idprod");
            }
            for(int i =0; i < p.getQtd(); i++){
            	criarEstoque(id_produto);
            }
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
    public void excluirProduto(Produto p)
            throws Exception
    {
        PreparedStatement stmt = null;
        String sql = "delete from produto where idprod = ?";
        try
        {
        	limparEstoque(p.getId());
        	
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
            if(stmt!=null)
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
    public void alterarProduto(Produto p)
            throws Exception
    {

        PreparedStatement stmt = null;
        String sql = "update produto set descricao = ? where idprod = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setLong(2, p.getId());
            int rs = stmt.executeUpdate();
            conexao.commit();
            int id_produto = 0;
            if(rs > 0){
            	id_produto = p.getId();
            	
            	limparEstoque(id_produto);
            	
	            for(int i =0; i < p.getQtd(); i++){
	            	criarEstoque(id_produto);
	            }
            }
        }
        catch (SQLException e)
        {
            conexao.rollback();
            throw e;
        }
        finally
        {
            if(stmt!=null)
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

        Integer qtd = 0;
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
            qtd = contarEstoque(rs.getInt(1));
            produto.setQtd(qtd);
            lista_produto.add(produto);
        }
        //stmt.close();
        return lista_produto;
    }
    /**
     *  função que retorna o objeto produto pela id deste
     * @param produto
     * @return
     * @throws Exception
     */
	public Produto buscaProdutoPorId(Produto produto) throws Exception {
		Produto p = new Produto();

		Integer qtd = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from produto where idprod = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			 rs = stmt.executeQuery();
			conexao.commit();
		} catch (SQLException e) {

		}
		while(rs.next()){
			p.setId(rs.getInt("idprod"));
			p.setDescricao(rs.getString("descricao"));
			qtd = contarEstoque(rs.getInt("idprod"));
			p.setQtd(qtd);
			p.setValid(true);
		}
		
		return p;
	}
    
    /**
     * criando o estoque de produtos
     * @param id_produto
     * @throws Exception
     */
	public void criarEstoque(int id_produto) throws Exception{
		PreparedStatement stmt = null;
		String sql = "insert into estoque (idprod) values (?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id_produto);
			stmt.executeUpdate();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			throw e;
		} 
	}
	/**
	 *  limpa o estoque antes da atualizacao do usuario
	 * @param id_produto
	 * @throws Exception
	 */
	public void limparEstoque(int id_produto) throws Exception{
		PreparedStatement stmt = null;
		String sql = "delete from estoque where idprod = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id_produto);
			stmt.executeUpdate();
			conexao.commit();
		} catch (SQLException e) {
			conexao.rollback();
			throw e;
		} 
	}
	/**
	 *  verifica quantos produtos tem no estoque.
	 * @param id_produto
	 * @return
	 * @throws Exception
	 */
	public Integer contarEstoque(Integer id_produto) throws Exception{
		Integer indice = 0;
		PreparedStatement stmt = null;
		String sql = "select count(*) count from estoque where idprod = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id_produto);
			ResultSet rs = stmt.executeQuery();
			conexao.commit();
			if(rs.next())
				indice = rs.getInt(1);
		
		} catch (SQLException e){
			conexao.rollback();
			throw e;
		} 
		
		return indice;
	}
	/**
	 * confere se ja existe um produto no estoque com o mesmo nome.
	 * @param descricao
	 * @return
	 * @throws Exception
	 */
	public Integer existProduto(String descricao) throws Exception{
		Integer indice = 0;
		PreparedStatement stmt = null;
		String sql = "select count(*) count from produto where descricao ilike ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1,descricao);
			ResultSet rs = stmt.executeQuery();
			conexao.commit();
			if(rs.next())
				indice = rs.getInt(1);
		} catch( SQLException e){
			conexao.rollback();
			throw e;
		}
		
		return indice;
	}
	/**
	 * verifica antes de atualizar, se exceto o produto da atualizacao nao existe 
	 * outro  com o mesmo nome
	 * @param produto
	 * @return
	 * @throws Exception
	 */
	public Integer existEditProduto(Produto produto) throws Exception{
		Integer indice = 0;
		PreparedStatement stmt = null;
		String sql = "select count(*) count from produto where descricao ilike ? and idprod != ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1,produto.getDescricao());
			stmt.setInt(2, produto.getId());
			ResultSet rs = stmt.executeQuery();
			conexao.commit();
			if(rs.next())
				indice = rs.getInt(1);
		} catch( SQLException e){
			conexao.rollback();
			throw e;
		}
		
		return indice;
	}

}
