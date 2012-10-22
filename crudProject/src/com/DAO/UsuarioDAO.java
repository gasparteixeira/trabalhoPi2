package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.entity.Usuario;

public class UsuarioDAO extends BaseDAO
{

    public UsuarioDAO()
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
     * Metodo para criar novo usuario
     * 
     * @param Usuario t
     * @throws Exception
     */

    public void criar(final Usuario t)
            throws Exception
    {
        final Calendar calendar = Calendar.getInstance();
        final java.sql.Date d = new java.sql.Date(calendar.getTime().getTime());
        PreparedStatement stmt = null;
        final String sql = "insert into usuario (nome, email, senha, data) values (?,?,?,?) returning id;";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getEmail());
            stmt.setString(3, t.getSenha());
            stmt.setDate(4, d);
            ResultSet rs = stmt.executeQuery();
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

    public void alterar(final Usuario t)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "update usuario set nome = ?, email = ? , senha = ? where id = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getEmail());
            stmt.setString(3, t.getSenha());
            stmt.setLong(4, t.getId());
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

    public void excluir(final Usuario t)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "delete from usuario where id = ?";
        try
        {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, t.getId());
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

    public List<Usuario> listar()
            throws Exception
    {
        final List<Usuario> lista_usuario = new ArrayList<Usuario>();

        PreparedStatement stmt = null;
        final String sql = "select * from usuario";
        stmt = conexao.prepareStatement(sql);
        final ResultSet rs = stmt.executeQuery();
        conexao.commit();

        while (rs.next())
        {
            final Usuario usuario = new Usuario();
            usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setData(rs.getDate(5));
            lista_usuario.add(usuario);
        }

        return lista_usuario;
    }

    public Usuario listarPor(final Usuario t)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "select * from usuario where id = ?";
        stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, t.getId());
        final ResultSet rs = stmt.executeQuery();
        conexao.commit();

        final Usuario usuario = new Usuario();
        while (rs.next())
        {
            usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setData(rs.getDate(5));

        }
        return usuario;
    }
    
    public Integer validaExisteEmail(String email) throws Exception{
    	Integer indice = 0;
    	PreparedStatement stmt = null;
    	String sql = "select count(*) count from usuario where email ilike ?";
    	try {
    		stmt = conexao.prepareStatement(sql);
    		stmt.setString(1, email);
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
     * Verifica se o usuario recebido tem permissão de acesso ao sistema<br>
     * retorna um usuario null caso não o encontre
     * 
     * @return Usuario
     */
    public Usuario verificaCredenciaisUsuario(Usuario usuario)
            throws Exception
    {

        PreparedStatement stmt = null;
        final String sql = "SELECT * FROM usuario WHERE email= ? AND senha= ?";
        Usuario resultUsuario = new Usuario();
        stmt = conexao.prepareStatement(sql);
        stmt.setMaxRows(1);
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        final ResultSet rs = stmt.executeQuery();
        conexao.commit();

        if (rs.next())
        {
            resultUsuario.setId(rs.getLong(1));
            resultUsuario.setNome(rs.getString(2));
            resultUsuario.setEmail(rs.getString(3));
            resultUsuario.setData(rs.getDate(5));
            resultUsuario.setValid(true);
            return resultUsuario;
        }else{
        	resultUsuario.setValid(false);
        }
        return resultUsuario;
    }
}
