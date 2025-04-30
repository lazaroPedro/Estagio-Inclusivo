package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOVaga implements DAORepository<Vaga, Integer> {

    private Connection connection;

    public DAOVaga(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Integer> insert(Vaga entity) {
        String sql = "INSERT INTO vagas (id_empresa, fk_endereco, descricao, requisitos, beneficios, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getEmpresa().getId());
            stmt.setInt(2, entity.getEndereco().getId());
            stmt.setString(4, entity.getDescricao());
            stmt.setString(5, entity.getRequisitos());
            stmt.setString(6, entity.getBeneficios());
            stmt.setString(7, entity.getStatus().name());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
                return Optional.empty();
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
            stmt.setString(7, entity.getStatus().name());
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

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("fk_endereco"));


                Curso curso = new Curso(rs.getLong("id_curso"));

                Vaga vaga = new Vaga(
                        rs.getInt("id"),
                        empresa,
                        endereco,
                        rs.getString("descricao"),
                        rs.getString("requisitos"),
                        rs.getString("beneficios"),
                        TipoVaga.valueOf(rs.getString("status"))
                );
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas", e);
        }
        return vagas;
    }

    @Override
    public Optional<Vaga> findById(Integer id) {
        String sql = "SELECT * FROM vagas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id_empresa"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("fk_endereco"));

                return Optional.of(new Vaga(
                        rs.getInt("id"),
                        empresa,
                        endereco,
                        rs.getString("descricao"),
                        rs.getString("requisitos"),
                        rs.getString("beneficios"),
                        TipoVaga.valueOf(rs.getString("status"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por ID", e);
        }

    }
}
