package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario implements DAORepository<Usuario, Integer> {
    private Connection connection;

    public DAOUsuario(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Usuario entity) {
        String sql = "INSERT INTO usuarios (nome, email, hashSenha, salt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário ", e);
        }
    }

    public int insertAndReturnid(Usuario entity) {
        String sql = "INSERT INTO usuarios (nome, email, hashSenha, salt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Erro ao obter o ID do usuário.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário ", e);
        }
    }

    @Override
    public void update(Usuario entity) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, hashSenha = ?, salt = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.setInt(5, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao da o update no usuario ", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuario ", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setHashSenha(rs.getString("hashSenha"));
            usuario.setSalt(rs.getString("salt"));
            usuarios.add(usuario);
        }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuario ", e);
        }
        return usuarios;
    }

    @Override
    public Usuario findById(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
       try(PreparedStatement stmt = connection.prepareStatement(sql);) {
           stmt.setInt(1, id);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               return new Usuario(
                       rs.getInt("id_usuario"),
                       rs.getString("nome"),
                       rs.getString("email"),
                       rs.getString("hashSenha"),
                       rs.getString("salt")
               );
           }
       } catch (SQLException e) {
           throw new RuntimeException("Erro ao buscar usuario por id ", e);
       }

        return null;
    }

    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("hashSenha"),
                            rs.getString("salt")
                    );
                }
            }
        }
        return null;
    }

}
