package com.ifbaiano.estagioinclusivo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Endereco;

public class DAOCandidato implements DAORepository<Candidato, Integer> {

    private Connection connection;

    public DAOCandidato(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Integer> insert(Candidato entity) {
        String sql = "INSERT INTO candidatos (id_candidato, telefone, cpf) VALUES (?, ?, ?)";
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getTelefone());
            stmt.setString(3, entity.getCpf());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException("Falha ao inserir candidato.",e);
        }finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(Candidato entity) {
        String sql = "UPDATE candidatos SET telefone = ?, cpf = ?, WHERE id_usuario = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getTelefone());
            stmt.setString(2, entity.getCpf());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException("Erro ao atualizar os dados do candidato", e);
        }

    }

    @Override
    public void delete(Integer id) {


        String sql = "DELETE FROM candidatos WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidato", e);
        }
    }


   @Override
    public List<Candidato> findAll() {
        List<Candidato> candidatos = new ArrayList<>();
    String sql = "SELECT * FROM candidatos JOIN usuarios ON candidatos.id_candidato = usuarios.id_candidato";

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
                        rs.getString("hashSenha"),
                        rs.getString("cpf"),
                        rs.getString("telefone"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidato", e);
        }
        return candidatos;
    }

    @Override
    public Optional<Candidato> findById(Integer id) {
        String sql = "SELECT * FROM candidatos JOIN usuarios ON candidatos.id_candidato = usuarios.id_candidato WHERE candidatos.id_candidato = ?";
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
                c.setHashSenha(rs.getString("hashSenha"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));

                return Optional.of(c);


            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por id ", e);
        } finally {
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
            throw new RuntimeException("Erro ao fechar conexão",e);
        }
    }
    public void fechar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar conexão",e);
        }
    }


}

