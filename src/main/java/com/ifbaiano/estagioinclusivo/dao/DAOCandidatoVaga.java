package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.CandidatoVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCandidatoVaga {
    private Connection conexao;

    public DAOCandidatoVaga(Connection conexao) {
        this.conexao = conexao;
    }

    public void insert(CandidatoVaga entity) {
        String sql = "INSERT INTO candidato_vaga (fk_candidato, fk_vaga, data_criacao) VALUES (?, ?, ?)";
        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, entity.getCandidato().getId());
            pp.setLong(2, entity.getVaga().getId());
            pp.setTimestamp(3, Timestamp.valueOf(entity.getData()));
            pp.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Long idCandidato, Long idVaga) {
        String sql = "DELETE FROM candidato_vaga WHERE fk_candidato = ? AND fk_vaga = ?";
        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, idCandidato);
            pp.setLong(2, idVaga);

            pp.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public Optional<CandidatoVaga> findById(Long idCandidato, Long idVaga) {
        String sql = "SELECT FROM candidato_vaga WHERE id_candidato = ? AND fk_vaga = ?";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, idCandidato);
            pp.setLong(2, idVaga);

            ResultSet rs = pp.executeQuery();
            if (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                Vaga v = new Vaga();
                v.setId(rs.getInt("fk_vaga"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_criacao").toLocalDateTime());
                return Optional.of(cv);

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public List<CandidatoVaga> findByVaga(Long idVaga) {
        String sql = "SELECT FROM candidato_vaga WHERE fk_vaga = ?";
        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, idVaga);
            ResultSet rs = pp.executeQuery();
            List<CandidatoVaga> lista = new ArrayList<>();
            while (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                Vaga v = new Vaga();
                v.setId(rs.getInt("fk_vaga"));
                c.setId(rs.getInt("fk_candidato"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_criacao").toLocalDateTime());
                lista.add(cv);

            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public List<CandidatoVaga> findByCandidato(Long idCandidato) {
        String sql = "SELECT FROM candidato_vaga WHERE id_candidato = ?";

        try {
            PreparedStatement pp = conexao.prepareStatement(sql);
            pp.setLong(1, idCandidato);
            ResultSet rs = pp.executeQuery();
            List<CandidatoVaga> lista = new ArrayList<>();
            while (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                Vaga v = new Vaga();
                c.setId(rs.getInt("fk_candidato"));
                v.setId(rs.getInt("fk_vaga"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_criacao").toLocalDateTime());
                lista.add(cv);

            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
