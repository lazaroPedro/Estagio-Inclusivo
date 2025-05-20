package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Curriculo;
import com.ifbaiano.estagioinclusivo.model.Candidato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCurriculo implements DAORepository<Curriculo, Integer> {
    private Connection connection;

    public DAOCurriculo(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<Integer> insert(Curriculo entity) {
        String sql = "INSERT INTO curriculos (objetivo, habilidades, experiencia, fk_candidato) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getObjetivo());
            stmt.setString(2, entity.getHabilidades());
            stmt.setString(3, entity.getExperiencia());
            stmt.setInt(4, entity.getCandidato().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                entity.setId(idGerado);
                return Optional.of(idGerado);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir currículo.", e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Curriculo entity) {
        String sql = "UPDATE curriculos SET objetivo = ?, habilidades = ?, experiencia = ? WHERE id_curriculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getObjetivo());
            stmt.setString(2, entity.getHabilidades());
            stmt.setString(3, entity.getExperiencia());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar currículo.", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM curriculos WHERE id_curriculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar currículo.", e);
        }
    }

    @Override
    public List<Curriculo> findAll() {
        List<Curriculo> curriculos = new ArrayList<>();
        String sql = "SELECT * FROM curriculos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getInt("id_curriculo"));
                curriculo.setObjetivo(rs.getString("objetivo"));
                curriculo.setHabilidades(rs.getString("habilidades"));
                curriculo.setExperiencia(rs.getString("experiencia"));

                Candidato candidato = new Candidato();
                candidato.setId(rs.getInt("fk_candidato"));
                curriculo.setCandidato(candidato);

                curriculos.add(curriculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar currículos.", e);
        }
        return curriculos;
    }

    @Override
    public Optional<Curriculo> findById(Integer id) {
        String sql = "SELECT * FROM curriculos WHERE id_curriculo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getInt("id_curriculo"));
                curriculo.setObjetivo(rs.getString("objetivo"));
                curriculo.setHabilidades(rs.getString("habilidades"));
                curriculo.setExperiencia(rs.getString("experiencia"));

                Candidato candidato = new Candidato();
                candidato.setId(rs.getInt("fk_candidato"));
                curriculo.setCandidato(candidato);

                return Optional.of(curriculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar currículo por ID.", e);
        }
        return Optional.empty();
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


    public Optional<Curriculo> findByCandidatoId(int idCandidato) {
        String sql = "SELECT * FROM curriculos WHERE fk_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getInt("id_curriculo"));
                curriculo.setObjetivo(rs.getString("objetivo"));
                curriculo.setHabilidades(rs.getString("habilidades"));
                curriculo.setExperiencia(rs.getString("experiencia"));

                Candidato candidato = new Candidato();
                candidato.setId(idCandidato);
                curriculo.setCandidato(candidato);
                return Optional.of(curriculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar currículo.", e);
        }
        return Optional.empty();
    }
}
