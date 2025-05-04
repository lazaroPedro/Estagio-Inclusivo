package com.ifbaiano.estagioinclusivo.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    private static Properties properties =  new Properties();
    public static Connection criarConexao(){

        InputStream inputStream = DBConfig.class
                .getClassLoader().getResourceAsStream("dbconfig.properties");
        try {

            properties.load(inputStream);

            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            String driver = properties.getProperty("db.driver");
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
            } catch (SQLException | IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }
    private DBConfig() {}
}
