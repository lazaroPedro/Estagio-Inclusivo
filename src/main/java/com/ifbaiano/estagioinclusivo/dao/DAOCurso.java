package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCurso implements DAORepository<Curso, Long> {
    private Connection conexao;

    public DAOCurso(Connection connection) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Curso entity) {
        String sql = "INSERT INTO cursos (instituicao, nome, descricao, data_inicio, data_fim, fk_candidato) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setString(1, entity.getInstituicao());
            pp.setString(2,entity.getNomeCurso());
            pp.setString(3, entity.getDescricao());
            pp.setDate(4, Date.valueOf(entity.getDataInicio()));
            pp.setDate(5, Date.valueOf(entity.getDataFim()));
            pp.setInt(6, entity.getCandidato().getId());
            pp.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Curso entity) {
        String sql = "UPDATE cursos SET instituicao = ?, nome = ?, descricao = ?, data_inicio = ?, data_fim = ?, fk_candidato = ? WHERE id = ?)";
            try {
                PreparedStatement pp = conexao.prepareStatement(sql);
                pp.setString(1, entity.getInstituicao());
                pp.setString(2,entity.getNomeCurso());
                pp.setString(3, entity.getDescricao());
                pp.setDate(4, Date.valueOf(entity.getDataInicio()));
                pp.setDate(5, Date.valueOf(entity.getDataFim()));
                pp.setInt(6, entity.getCandidato().getId());
                pp.setLong(7, entity.getId());
                pp.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, id);
            pp.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Curso> findAll() {
        String sql = "SELECT * FROM cursos";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            ResultSet rs = pp.executeQuery();
            List<Curso> lista = new ArrayList<>();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);
                lista.add(curso);

            }
            return lista;
            }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Curso findById(Long id) {
        String sql = "SELECT * FROM cursos where id = ?";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, id);
            ResultSet rs = pp.executeQuery();
            Curso curso = new Curso();
            if (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);

            }
            return curso;
            }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<Curso> findByFkCandidato(Integer idCandidato) {

         String sql = "SELECT * FROM cursos WHERE fk_candidato = ?";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            ResultSet rs = pp.executeQuery();
            pp.setLong(1, idCandidato);

            List<Curso> lista = new ArrayList<>();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);
                lista.add(curso);

            }
            return lista;
            }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }
}
