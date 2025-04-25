package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.CandidatoVaga;

import java.sql.Connection;
import java.util.List;

public class DAOCandidatoVaga implements DAORepository <CandidatoVaga, Long> {
    private Connection conexao;

    public DAOCandidatoVaga(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(CandidatoVaga entity) {

    }

    @Override
    public void update(CandidatoVaga entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<CandidatoVaga> findAll() {
        return List.of();
    }

    @Override
    public CandidatoVaga findById(Long id) {
        return null;
    }
}
