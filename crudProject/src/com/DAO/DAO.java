package com.DAO;

public interface DAO<T> {
	public void criar(T t) throws Exception;

	public void alterar(T t) throws Exception;

	public void excluir(T t) throws Exception;
}
