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
        String sql = "INSERT INTO candidato_vaga (fk_candidato, fk_vaga) VALUES (?, ?)";
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, entity.getCandidato().getId());
            pp.setInt(2, entity.getVaga().getId());
            pp.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Inserir Candidato", e);
        }
    }


    public void delete(int idCandidato, int idVaga) {
        String sql = "DELETE FROM candidato_vaga WHERE fk_candidato = ? AND fk_vaga = ?";
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, idCandidato);
            pp.setInt(2, idVaga);

            pp.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidato" ,e);
        }


    }


    public Optional<CandidatoVaga> findById(int idCandidato, int idVaga) {
        String sql = "SELECT * FROM candidato_vaga WHERE fk_candidato = ? AND fk_vaga = ?";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, idCandidato);
            pp.setInt(2, idVaga);

            rs = pp.executeQuery();
            if (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                Vaga v = new Vaga();
                v.setId(rs.getInt("fk_vaga"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_cadastro").toLocalDateTime());
                return Optional.of(cv);

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar candidato",e);
        }   finally {
            fechar(rs);
        }


    }
    public List<CandidatoVaga> findByVaga(int idVaga) {
        String sql = "SELECT * FROM candidato_vaga WHERE fk_vaga = ?";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, idVaga);
            rs = pp.executeQuery();
            List<CandidatoVaga> lista = new ArrayList<>();
            while (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                Vaga v = new Vaga();
                v.setId(rs.getInt("fk_vaga"));
                c.setId(rs.getInt("fk_candidato"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_cadastro").toLocalDateTime());
                lista.add(cv);

            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Candidato",e);
        }   finally {
            fechar(rs);
        }
    }
        public List<CandidatoVaga> findByCandidato(int idCandidato) {
        String sql = "SELECT * FROM candidato_vaga WHERE fk_candidato = ?";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, idCandidato);
            rs = pp.executeQuery();
            List<CandidatoVaga> lista = new ArrayList<>();
            while (rs.next()) {
                CandidatoVaga cv = new CandidatoVaga();
                Candidato c = new Candidato();
                Vaga v = new Vaga();
                c.setId(rs.getInt("fk_candidato"));
                v.setId(rs.getInt("fk_vaga"));
                cv.setVaga(v);
                cv.setCandidato(c);
                cv.setData(rs.getTimestamp("data_cadastro").toLocalDateTime());
                lista.add(cv);

            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas",e);
        } finally {
            fechar(rs);
        }

    }





        public void fechar(AutoCloseable closeable) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar conexão",e);
            }
        }

}
