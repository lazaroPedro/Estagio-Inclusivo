package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Curso;

import java.sql.Connection;
import java.util.List;

public class DAOCurso implements DAORepository<Curso, Long> {
    private Connection connection;

    public DAOCurso(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Curso entity) {

    }

    @Override
    public void update(Curso entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Curso> findAll() {
        return List.of();
    }

    @Override
    public Curso findById(Long id) {
        return null;
    }

    public List<Curso> findByFkCandidato(Integer idCandidato) {
        return List.of();
    }
}
