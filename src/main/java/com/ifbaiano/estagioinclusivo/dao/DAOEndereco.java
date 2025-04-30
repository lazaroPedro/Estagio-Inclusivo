package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOEndereco implements DAORepository<Endereco, Integer> {

    private Connection connection;

    public DAOEndereco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Integer> insert(Endereco entity) {
        String sql = "INSERT INTO enderecos (rua, bairro, municipio, estado, cep) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getRua());
            stmt.setString(2, entity.getBairro());
            stmt.setString(3, entity.getMunicipio());
            stmt.setString(4, entity.getEstado());
            stmt.setString(5, entity.getCep());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir endereço", e);
        }
    }

    @Override
    public void update(Endereco entity) {
        String sql = "UPDATE enderecos SET rua = ?, bairro = ?, municipio = ?, estado = ?, cep = ? WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getRua());
            stmt.setString(2, entity.getBairro());
            stmt.setString(3, entity.getMunicipio());
            stmt.setString(4, entity.getEstado());
            stmt.setString(5, entity.getCep());
            stmt.setInt(6, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar endereço", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM enderecos WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar endereço", e);
        }
    }

    @Override
    public List<Endereco> findAll() {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM enderecos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("rua"),
                        rs.getString("bairro"),
                        rs.getString("municipio"),
                        rs.getString("estado"),
                        rs.getString("cep")
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereços", e);
        }
        return enderecos;
    }

    @Override
    public Optional<Endereco> findById(Integer id) {
        String sql = "SELECT * FROM enderecos WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Endereco(
                            rs.getInt("id_endereco"),
                            rs.getString("rua"),
                            rs.getString("bairro"),
                            rs.getString("municipio"),
                            rs.getString("estado"),
                            rs.getString("cep")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereço por ID", e);
        }
        return null;
    }
}
