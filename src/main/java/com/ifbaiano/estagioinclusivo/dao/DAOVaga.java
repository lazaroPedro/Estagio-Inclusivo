package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOVaga implements DAORepository<Vaga, Integer> {

    private Connection connection;

    public DAOVaga(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Vaga vaga) {
        String sql = "INSERT INTO vaga (id_empresa, id_regiao, id_curso, descricao, requisitos, beneficios) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vaga.getEmpresa().getId());
            stmt.setInt(2, vaga.getRegiao().getId());
            stmt.setString(4, vaga.getDescricao());
            stmt.setString(5, vaga.getRequisitos());
            stmt.setString(6, vaga.getBeneficios());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vaga", e);
        }
    }

    @Override
    public void update(Vaga vaga) {
        String sql = "UPDATE vaga SET id_empresa = ?, id_regiao = ?, id_curso = ?, descricao = ?, requisitos = ?, beneficios = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vaga.getEmpresa().getId());
            stmt.setInt(2, vaga.getRegiao().getId());
            stmt.setString(4, vaga.getDescricao());
            stmt.setString(5, vaga.getRequisitos());
            stmt.setString(6, vaga.getBeneficios());
            stmt.setInt(7, vaga.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM vaga WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar vaga", e);
        }
    }

    @Override
    public List<Vaga> findAll() {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));

                Regiao regiao = new Regiao(rs.getInt("id_regiao"), null, null);

                Curso curso = new Curso(rs.getLong("id_curso"));

                Vaga vaga = new Vaga(
                        rs.getInt("id"),
                        empresa,
                        regiao,
                        curso,
                        rs.getString("descricao"),
                        rs.getString("requisitos"),
                        rs.getString("beneficios")
                );
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas", e);
        }
        return vagas;
    }

    @Override
    public Vaga findById(Integer id) {
        String sql = "SELECT * FROM vaga WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));

                Regiao regiao = new Regiao(rs.getInt("id_regiao"), null, null);

                Curso curso = new Curso(rs.getLong("id_curso"));

                return new Vaga(
                        rs.getInt("id"),
                        empresa,
                        regiao,
                        curso,
                        rs.getString("descricao"),
                        rs.getString("requisitos"),
                        rs.getString("beneficios")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por ID", e);
        }
        return null;
    }
}
