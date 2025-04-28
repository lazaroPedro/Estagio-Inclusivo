package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Empresa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEmpresa implements DAORepository<Empresa, Integer> {
    private Connection connection;

    public DAOEmpresa(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Empresa entity) {
        String sql = "INSERT INTO empresa (nome, email, hashSenha, salt, cnpj, razaoSocial) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.setString(5, entity.getCnpj());
            stmt.setString(6, entity.getRazaoSocial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir empresa", e);
        }
    }

    @Override
    public void update(Empresa entity) {
        String sql = "UPDATE empresa SET nome = ?, email = ?, hashSenha = ?, salt = ?, cnpj = ?, razaoSocial = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.setString(5, entity.getCnpj());
            stmt.setString(6, entity.getRazaoSocial());
            stmt.setInt(7, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empresa", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM empresa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar empresa", e);
        }
    }

    @Override
    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEmail(rs.getString("email"));
                empresa.setHashSenha(rs.getString("hashSenha"));
                empresa.setSalt(rs.getString("salt"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazaoSocial(rs.getString("razaoSocial"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empresas", e);
        }
        return empresas;
    }

    @Override
    public Empresa findById(Integer id) {
        String sql = "SELECT * FROM empresa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Empresa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("hashSenha"),
                            rs.getString("salt"),
                            rs.getString("cnpj"),
                            rs.getString("razaoSocial")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empresa por ID", e);
        }
        return null;
    }

    public Empresa findByCnpj(String cnpj) {
        String sql = "SELECT * FROM empresa WHERE cnpj = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Empresa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("hashSenha"),
                            rs.getString("salt"),
                            rs.getString("cnpj"),
                            rs.getString("razaoSocial")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empresa por CNPJ", e);
        }
        return null;
    }
}
