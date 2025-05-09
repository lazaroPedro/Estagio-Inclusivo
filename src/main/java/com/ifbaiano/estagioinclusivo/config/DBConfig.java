package com.ifbaiano.estagioinclusivo.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    private static final Properties properties =  new Properties();
    public static Connection criarConexao(){


        try {
            InputStream inputStream = DBConfig.class
                .getClassLoader().getResourceAsStream("dbconfig.properties");
            properties.load(inputStream);
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            String driver = properties.getProperty("db.driver");

            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException | IOException e ) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }

    }
    private DBConfig() {}
}
