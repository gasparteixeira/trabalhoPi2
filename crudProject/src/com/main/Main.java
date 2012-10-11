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
        Usuario result = null;
        u.setNome("nome");
        u.setSenha("senha1");

        try
        {
            result = d.verificaCredenciaisUsuario(u);
        }
        catch (final Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (result != null)
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("NOK");
        }

    }
}
