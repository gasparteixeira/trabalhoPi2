package com.main;

import com.DAO.UsuarioDAO;
import com.entity.Usuario;

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
        final UsuarioDAO d = new UsuarioDAO();
        final Usuario u = new Usuario();
        u.setNome("nome");
        u.setSenha("senha");

        try
        {
            if (d.verificaCredenciaisUsuario(u))
            {
                System.out.println("usuario encontrado!");
            }
            else
            {
                System.out.println("usuario NAO encontrado!");
            }
        }
        catch (final Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
