package com.entity;

import java.sql.Date;

/**
 *
 */
public class Pessoa
{
    private int id = 0;
    private String nome = "";
    private String cidade = "";
    private String endereco = "";
    private Date date = null;

    /**
     * 
     */
    public Pessoa(final int id, final String nome, final String cidade, final String endereco, final Date date)
    {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
        this.date = date;
    }

    public Pessoa(final String nome, final String cidade, final String endereco, final Date date)
    {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
        this.date = date;
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
    public String getCidade()
    {
        return cidade;
    }

    /**
     * @return the endereco
     */
    public String getEndereco()
    {
        return endereco;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

}
