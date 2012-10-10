package com.DAO.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class DatabaseProperties
{
    private String url;
    private String user;
    private String pass;
    private Properties prop;

    /**
     * 
     */
    public DatabaseProperties()
    {
        // TODO Auto-generated constructor stub
    }

    public void getProperties()
    {
        prop = new Properties();

        try
        {
            //Carrega o arquivo de properties
            prop.load(new FileInputStream(".\\src\\com\\DAO\\config\\databaseConfig.properties"));

            //coleta as propriedades
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.pass = prop.getProperty("pass");

        }
        catch (final IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @return the pass
     */
    public String getPass()
    {
        return pass;
    }

}
