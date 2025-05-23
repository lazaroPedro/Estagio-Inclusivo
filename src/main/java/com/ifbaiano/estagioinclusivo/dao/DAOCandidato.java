package com.ifbaiano.estagioinclusivo.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;

public class DAOCandidato implements DAORepository<Candidato, Integer> {

    private Connection connection;
    private DAOUsuario daoUsuario;

    public DAOCandidato(Connection connection) {
        daoUsuario = new DAOUsuario(connection);
        this.connection = connection;
    }

    @Override
    public Optional<Integer> insert(Candidato entity) {
        String sql = "INSERT INTO candidatos (id_candidato, genero, nascimento, cpf) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try{
            daoUsuario.insert(entity).ifPresent(entity::setId);
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getGenero().name());
            stmt.setDate(3, Date.valueOf(entity.getDataNascimento()));
            stmt.setString(4, entity.getCpf());
            stmt.executeUpdate();

            return Optional.of(entity.getId());




        } catch (SQLException e) {
            throw new RuntimeException("Falha ao inserir candidato.",e);
        }finally {
            fechar(stmt);

        }
    }

    @Override
    public void update(Candidato entity) {
        PreparedStatement stmt = null;
         try {

             connection.setAutoCommit(false);
             daoUsuario.update(entity);
             String sql = "UPDATE candidatos SET cpf = ?, genero = ?, nascimento = ? WHERE id_candidato = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCpf());
            stmt.setInt(4, entity.getId());
            stmt.setString(2, entity.getGenero().name());
            stmt.setDate(3, Date.valueOf(entity.getDataNascimento()));
            stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e){
             try {
                 connection.rollback();
                 connection.setAutoCommit(true);
             } catch (SQLException ex) {
                 throw new RuntimeException(ex);
             }

            throw new RuntimeException("Erro ao atualizar os dados do candidato", e);
        }

    }

    @Override
    public void delete(Integer id) {


        String sql = "DELETE FROM candidatos WHERE id_usuario = ?";
        try {
            connection.setAutoCommit(false);
            daoUsuario.delete(id);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException("Erro ao deletar candidato", e);
        }
    }


   @Override
    public List<Candidato> findAll() {
        List<Candidato> candidatos = new ArrayList<>();
    String sql = "SELECT * FROM candidatos JOIN usuarios ON candidatos.id_candidato = usuarios.id_usuario";

        try (
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setId(rs.getInt("fk_endereco"));
                Candidato candidato = new Candidato(
                        rs.getInt("id_candidato"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        e,
                        rs.getString("salt"),
                        rs.getString("hash_senha"),
                        rs.getString("telefone"),
                        rs.getString("cpf"),
                        Genero.valueOf(rs.getString("genero")),
                        rs.getDate("nascimento").toLocalDate()
                );
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidato", e);
        }
        return candidatos;
    }

    @Override
    public Optional<Candidato> findById(Integer id) {
        String sql = "SELECT * FROM candidatos JOIN usuarios ON candidatos.id_candidato = usuarios.id_usuario WHERE candidatos.id_candidato = ?";
        ResultSet rs = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Candidato c = new Candidato();
                Endereco e = new Endereco();
                e.setId(rs.getInt("fk_endereco"));
                c.setEndereco(e);
                c.setId(rs.getInt("id_candidato"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setSalt(rs.getString("salt"));
                c.setHashSenha(rs.getString("hash_senha"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setGenero(Genero.valueOf(rs.getString("genero")));
                c.setDataNascimento(rs.getDate("nascimento").toLocalDate());

                return Optional.of(c);


            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por id ", e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    public boolean emailJaExiste(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar e-mail.", e);
        }
    }

    public boolean cpfJaExiste(String cpf) {
        String sql = "SELECT * FROM candidatos WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar CPF.", e);
        }
    }


    @Override
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

