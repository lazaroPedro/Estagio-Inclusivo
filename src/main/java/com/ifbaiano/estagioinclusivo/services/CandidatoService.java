package com.ifbaiano.estagioinclusivo.services;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Candidato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CandidatoService {


    public void cadastrarCandidato(Candidato candidato) {
        DAOFactory factory = new DAOFactory();
        try {
            DAOUsuario daoUsuario = factory.buildDAOUsuario();
            DAOCandidato daoCandidato = factory.buildDAOCandidato();
            factory.openTransaction();
            Optional<Integer> oI = daoUsuario.insert(candidato);
            oI.ifPresent(candidato::setId);
            daoCandidato.insert(candidato);
            factory.closeTransaction();
        } catch (SQLException e) {
            try {
                factory.rollbackTransaction();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao tentar realizar rollback de candidato", ex);
            }
            throw new RuntimeException("Erro ao tentar cadastrar candidato", e);

        } finally {
            factory.close();
        }
    }


}
