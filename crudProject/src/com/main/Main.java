package com.main;

import java.util.List;

import com.DAO.DAO;
import com.pessoa.Pessoa;

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

        //final Date date = new Date(2011, 01, 30);
        //final Pessoa p = new Pessoa(3, "novo_nome", "novo_cidade", "novo_endereco", date);

        final DAO dao = new DAO();
        //dao.editar(p);

        final List<Pessoa> l = dao.getPessoas();
        for (final Pessoa p1 : l)
        {
            System.out.println("Nome: " + p1.getNome());
            System.out.println("Cidade: " + p1.getCidade());
            System.out.println("Endereço: " + p1.getEndereco());
            System.out.println("Data " + p1.getDate());
            System.out.println("----------------------------------------------");
            System.out.println("");
        }

    }

}
