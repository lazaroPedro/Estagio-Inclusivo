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
    public void insert(Vaga entity) {
        String sql = "INSERT INTO vagas (id_empresa, fk_endereco, descricao, requisitos, beneficios, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getEmpresa().getId());
            stmt.setInt(2, entity.getEndereco().getId());
            stmt.setString(4, entity.getDescricao());
            stmt.setString(5, entity.getRequisitos());
            stmt.setString(6, entity.getBeneficios());
            stmt.setString(7, "ATIVA");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vaga", e);
        }
    }

    @Override
    public void update(Vaga entity) {
        String sql = "UPDATE vagas SET id_empresa = ?, fk_endereco = ?, descricao = ?, requisitos = ?, beneficios = ? status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getEmpresa().getId());
            stmt.setInt(2, entity.getEndereco().getId());
            stmt.setString(4, entity.getDescricao());
            stmt.setString(5, entity.getRequisitos());
            stmt.setString(6, entity.getBeneficios());
            stmt.setString(7, entity.getStatus());
            stmt.setInt(8, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM vagas WHERE id = ?";
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
        String sql = "SELECT * FROM vagas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));

                Endereco endereco = new Endereco(rs.getInt("fk_endereco"), null, null);

                Curso curso = new Curso(rs.getLong("id_curso"));

                Vaga vaga = new Vaga(
                        rs.getInt("id"),
                        empresa,
                        endereco,
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
        String sql = "SELECT * FROM vagas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));

                Endereco endereco = new Endereco(rs.getInt("fk_endereco"), null, null);

                Curso curso = new Curso(rs.getLong("id_curso"));

                return new Vaga(
                        rs.getInt("id"),
                        empresa,
                        endereco,
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
