package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.config.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory implements AutoCloseable {
    private Connection connection;

    public DAOFactory() {
        this.connection = DBConfig.criarConexao();
    }

    public DAOCandidato buildDAOCandidato() {
        return new DAOCandidato(connection);
    }
    public DAOEmpresa buildDAOEmpresa() {
        return new DAOEmpresa(connection);
    }
    public DAOUsuario buildDAOUsuario() {
        return new DAOUsuario(connection);
    }
    public DAOCurso buildDAOCurso() {
        return new DAOCurso(connection);
    }
    public DAOEndereco buildDAOEndereco() {
        return new DAOEndereco(connection);
    }
    public DAOVaga buildDAOVaga() {
        return new DAOVaga(connection);
    }
    public DAOTipoDeficiencia buildDAOTipoDeficiencia() {
        return new DAOTipoDeficiencia(connection);
    }
    public DAOCandidatoVaga buildDAOCandidatoVaga() {
        return new DAOCandidatoVaga(connection);
    }
    public void openTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }
    public void closeTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
    public void close(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
