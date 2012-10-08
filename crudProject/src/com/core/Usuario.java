package com.core;

import java.sql.Date;

/**
 *
 */
public class Usuario
{
    private int id = 0;
    private String nome = "";
    private String email = "";
    private String senha = "";
    private Date date = null;

    /**
     * 
     */
    public Usuario(final int id, final String nome, final String email, final String senha, final Date date)
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.date = date;
    }

    public Usuario(final String nome, final String email, final String senha)
    {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @return the cidade
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return the endereco
     */
    public String getSenha()
    {
        return senha;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

}
