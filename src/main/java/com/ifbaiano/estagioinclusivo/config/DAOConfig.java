package com.ifbaiano.estagioinclusivo.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOConfig {
    private static Properties properties =  new Properties();
    public static Connection criarConexao() throws IOException, SQLException {

        InputStream inputStream = DAOConfig.class
                            .getClassLoader().getResourceAsStream("dbconfig.properties");
        properties.load(inputStream);
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");

        return DriverManager.getConnection(url,user,password);

    }
}
