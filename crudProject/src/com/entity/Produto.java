package com.entity;

/**
 *
 */
public class Produto
{
    private int id;
    private String descricao = "";
    private int qtd;
    private Boolean valid;

    /**
     * 
     */
    public Produto()
    {

    }

    /**
     * @param id the id to set
     */
    public void setId(final int id)
    {
        this.id = id;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(final String descricao)
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

    /**
     * @return the qtd
     */
    public int getQtd()
    {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(final int qtd)
    {
        this.qtd = qtd;
    }

	public Boolean isValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

    
}
