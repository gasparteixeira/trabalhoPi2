package com.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.entity.Usuario;

public class UsuarioDAO extends BaseDAO {
	
	public UsuarioDAO() {
        try {
            this.conectar();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	/**
	 * Metodo para criar novo usuario
	 * @param Usuario t
	 * @throws Exception
	 */

	public void criar(Usuario t) throws Exception{
		Calendar calendar = Calendar.getInstance();
	    java.sql.Date d = new java.sql.Date(calendar.getTime().getTime());
		PreparedStatement stmt = null;
		String sql = "insert into usuario (nome, email, senha, data) values (?,?,?,?) returning id;";
		try{
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1,t.getNome());
			stmt.setString(2,t.getEmail());
			stmt.setString(3, t.getSenha());
			stmt.setDate(4,d);
			ResultSet rs = stmt.executeQuery();
			conexao.commit();
		} catch(SQLException e){
			conexao.rollback();
			throw e;
		} finally {
			stmt.close();
			conexao.close();
		}
	}

	public void alterar(Usuario t) throws Exception{
		
		PreparedStatement stmt = null;
		String sql = "update usuario set nome = ?, email = ? , senha = ? where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, t.getNome());
			stmt.setString(2, t.getEmail());
			stmt.setString(3, t.getSenha());
			stmt.setLong(4, t.getId());
			stmt.executeUpdate();
            conexao.commit();
		} catch (SQLException e){
			conexao.rollback();
			throw e;
		} finally{
			stmt.close();
		    conexao.close();
		}
	}

	public void excluir(Usuario t) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "delete from usuario where id = ?";
		try{
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, t.getId());
			stmt.executeUpdate();
			conexao.commit();
		} catch (SQLException e){
			conexao.rollback();
			throw e;
		} finally {
			stmt.close();
			conexao.close();
		}
		
	}
	
	public List<Usuario> listar() throws Exception{
		List<Usuario> lista_usuario = new ArrayList<Usuario>();	
		
		PreparedStatement stmt = null;
        String sql = "select * from usuario";
        stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        conexao.commit();
        
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setData(rs.getDate(5));
            lista_usuario.add(usuario);
        }
		
		return lista_usuario;
	}
	
	public Usuario listarPor(Usuario t) throws Exception{
		
		
		PreparedStatement stmt = null;
		String sql = "select * from usuario where id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setLong(1, t.getId());
        ResultSet rs = stmt.executeQuery();
        conexao.commit();
        
		Usuario usuario = new Usuario();
		while(rs.next()) {
			usuario.setId(rs.getLong(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setData(rs.getDate(5));
            
		}
		return usuario;
	}

}
