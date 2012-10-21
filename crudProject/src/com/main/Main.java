package com.main;

import java.util.List;

import com.DAO.EstoqueDAO;
import com.entity.Produto;

/**
 *
 */
public class Main
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {

        List<Produto> l = null;
        final EstoqueDAO eDAO = new EstoqueDAO();
        try
        {
            l = eDAO.listarProdutosDoEstoque();
        }
        catch (final Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (l != null)
        {
            System.out.println("idProd\t\tdescricao\tqtd");
            for (final Produto p : l)
            {
                System.out.println(p.getId() + "\t\t" + p.getDescricao() + "\t" + p.getQtd());

            }
        }
        else
        {
            System.out.println("lista vazia");
        }

    }
}
