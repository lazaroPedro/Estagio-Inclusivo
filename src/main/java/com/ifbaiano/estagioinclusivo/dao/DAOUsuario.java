package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOUsuario implements DAORepository<Usuario, Integer> {
    private Connection connection;

    public DAOUsuario(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Integer> insert(Usuario entity) {
        String sql = "INSERT INTO usuarios (nome, email, hash_senha, salt, fk_endereco, papel, telefone) VALUES (?, ?, ?, ?, ? , ?, ?)";
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.setInt(5, entity.getEndereco().getId());
            stmt.setString(6, entity.getPapel().name());
            stmt.setString(7, entity.getTelefone());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário ", e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(Usuario entity) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, hash_senha = ?, salt = ?, fk_endereco = ? , papel = ? , telefone = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getHashSenha());
            stmt.setString(4, entity.getSalt());
            stmt.setInt(5, entity.getEndereco().getId());
            stmt.setString(6, entity.getPapel().name());
            stmt.setString(7, entity.getTelefone());
            stmt.setInt(7, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuario ", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
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
            usuario.setHashSenha(rs.getString("hash_senha"));
            usuario.setSalt(rs.getString("salt"));
            usuario.setPapel(TipoUsuario.valueOf(rs.getString("papel")));
            usuario.setTelefone(rs.getString("telefone"));
            Endereco endereco = new Endereco();
            endereco.setId(rs.getInt("fk_endereco"));

            usuario.setEndereco(endereco);
            usuarios.add(usuario);
        }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuario ", e);
        }
        return usuarios;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        ResultSet rs = null;
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setInt(1, id);
           rs = stmt.executeQuery();
           if (rs.next()) {
               Endereco endereco = new Endereco();
               endereco.setId(rs.getInt("fk_endereco"));

               Usuario usuario = new Usuario();
               usuario.setId(rs.getInt("id_usuario"));
               usuario.setNome(rs.getString("nome"));
               usuario.setEmail(rs.getString("email"));
               usuario.setHashSenha(rs.getString("hash_senha"));
               usuario.setSalt(rs.getString("salt"));
               usuario.setEndereco(endereco);
               usuario.setPapel(TipoUsuario.valueOf(rs.getString("papel")));
               usuario.setTelefone(rs.getString("telefone"));

               return Optional.of(usuario);
           }
       } catch (SQLException e) {
           throw new RuntimeException("Erro ao buscar usuario por id ", e);
       } finally {
           fechar(rs);
       }

        return Optional.empty();
    }

    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        ResultSet rs = null;
        try{
                PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("fk_endereco"));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setHashSenha(rs.getString("hash_senha"));
                usuario.setSalt(rs.getString("salt"));
                usuario.setEndereco(endereco);
                usuario.setPapel(TipoUsuario.valueOf(rs.getString("papel")));
                usuario.setTelefone(rs.getString("telefone"));

                return Optional.of(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por email ", e);
        }finally {
            fechar(rs);
        }
        return Optional.empty();
    }
    public void fechar(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
