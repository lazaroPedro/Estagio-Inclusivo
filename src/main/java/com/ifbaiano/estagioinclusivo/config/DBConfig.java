package com.ifbaiano.estagioinclusivo.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    public static Connection criarConexao(){


        try {

            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            String url = System.getenv("DB_URL");
            String driver = System.getenv("DB_DRIVER");

            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }

    }
    private DBConfig() {}
}
