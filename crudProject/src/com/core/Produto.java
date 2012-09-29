package com.core;

/**
 *
 */
public class Produto
{
    private int id;
    private final String descricao;

    /**
     * 
     */
    public Produto(final String descricao)
    {
        this.descricao = descricao;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao()
    {
        return descricao;
    }

}
