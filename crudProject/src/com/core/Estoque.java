package com.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Estoque
{
    private final List<Produto> l;

    /**
     * 
     */
    public Estoque()
    {
        l = new ArrayList<Produto>();
    }

    /**
     * Adiciona um produto no Estoque<br>
     * 
     * @return void
     */
    public void adicionaProdutoNoEstoque(final Produto produto)
    {
        l.add(produto);
    }

    /**
     * Retorna a lista de produtos do Estoque<br>
     * 
     * @return List<Produto>
     */
    public List<Produto> getListaProdutosdoEstoque()
    {
        return l;
    }

}
