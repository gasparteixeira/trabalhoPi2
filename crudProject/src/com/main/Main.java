package com.main;

import java.util.List;

import com.core.Usuario;
import com.databaseAccess.DAO;

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

        final DAO dao = new DAO();

        final List<Usuario> l = dao.getUsuario();

        for (final Usuario p1 : l)
        {
            System.out.println("Nome: " + p1.getNome());
            System.out.println("email: " + p1.getEmail());
            System.out.println("Senha: " + p1.getSenha());
            System.out.println("Data " + p1.getDate());
            System.out.println("----------------------------------------------");
            System.out.println("");
        }

    }

}
