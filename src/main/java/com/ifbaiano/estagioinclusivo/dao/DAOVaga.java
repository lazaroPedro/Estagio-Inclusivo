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
        String sql = "INSERT INTO vagas (fk_empresa, fk_endereco, descricao, requisitos, beneficios, status, qtd_vagas, titulo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getEmpresa().getId());
            stmt.setInt(2, entity.getEndereco().getId());
            stmt.setString(3, entity.getDescricao());
            stmt.setString(4, entity.getRequisitos());
            stmt.setString(5, entity.getBeneficios());
            stmt.setString(6, entity.getStatus().name());
            stmt.setLong(7, entity.getQtdVagas());
            stmt.setString(8, entity.getTitulo());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vaga", e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(Vaga entity) {
    String sql = "UPDATE vagas SET fk_empresa = ?, fk_endereco = ?, descricao = ?, requisitos = ?, beneficios = ?, status = ?, qtd_vagas = ?, titulo = ? WHERE id_vaga = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getEmpresa().getId());
            stmt.setInt(2, entity.getEndereco().getId());
            stmt.setString(3, entity.getDescricao());
            stmt.setString(4, entity.getRequisitos());
            stmt.setString(5, entity.getBeneficios());
            stmt.setString(6, entity.getStatus().name());
            stmt.setLong(7, entity.getQtdVagas());
            stmt.setString(8, entity.getTitulo());
            stmt.setInt(9, entity.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM vagas WHERE id_vaga = ?";
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
            vagas.add(buildVaga(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas", e);
        }
        return vagas;
    }

    @Override
    public Optional<Vaga> findById(Integer id) {
        String sql = "SELECT * FROM vagas WHERE id_vaga = ?";
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {

                return Optional.of(buildVaga(rs));
            }
 
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por ID", e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }
    public List<Vaga> findByIdCandidato(int candidatoId) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT v.* FROM vagas v " +
                     "JOIN candidato_vaga cv ON v.id_vaga = cv.fk_vaga " +
                     "WHERE cv.fk_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidatoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vagas.add(buildVaga(rs));
            }
            } catch(SQLException e){
                throw new RuntimeException("Erro ao buscar vagas do candidato", e);
            }
            return vagas;
        }

        public List<Vaga> findByIdEmpresa(int empresaId) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas WHERE fk_empresa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vagas.add(buildVaga(rs));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vagas por empresa", e);
        }
        return vagas;
    }


    private Vaga buildVaga(ResultSet rs) throws SQLException {

            Empresa empresa = new Empresa();
            empresa.setId(rs.getInt("fk_empresa"));

            Endereco endereco = new Endereco();
            endereco.setId(rs.getInt("fk_endereco"));

            return new Vaga(
                rs.getInt("id_vaga"),
                empresa,
                endereco,
                rs.getString("descricao"),
                rs.getString("requisitos"),
                rs.getString("beneficios"),
                rs.getLong("qtd_vagas"),
                TipoVaga.valueOf(rs.getString("status")),
                    rs.getString("titulo"));

    }

    public List<Vaga> findByTituloContaining(String titulo) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas WHERE titulo LIKE ? ORDER BY qtd_vagas DESC";
        try(PreparedStatement pp = connection.prepareStatement(sql)){
            pp.setString(1, "%"+titulo+"%");

            ResultSet rs = pp.executeQuery();
            while (rs.next()) {
                vagas.add(buildVaga(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vagas;
    }



    @Override
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
