package com.ifbaiano.estagioinclusivo.services;

import com.ifbaiano.estagioinclusivo.config.DAOConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Candidato;

import java.sql.Connection;
import java.util.Optional;

public class CandidatoService {
    DAOCandidato daoCandidato;
    DAOUsuario daoUsuario;

    public void cadastrarCandidato(Candidato candidato) {
        Connection c = DAOConfig.criarConexao();
        daoCandidato = new DAOCandidato(c);
        daoUsuario = new DAOUsuario(c);
        Optional<Integer> oI= daoUsuario.insert(candidato);
        oI.ifPresent(candidato::setId);
        daoCandidato.insert(candidato);
        daoUsuario.fechar();
        daoCandidato.fechar();
    }


}
