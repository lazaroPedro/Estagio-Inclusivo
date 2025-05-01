package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCurso implements DAORepository<Curso, Integer> {
    private Connection conexao;

    public DAOCurso(Connection connection) {
        this.conexao = conexao;
    }

    @Override
    public Optional<Integer> insert(Curso entity) {
        String sql = "INSERT INTO cursos (instituicao, nome, descricao, data_inicio, data_fim, fk_candidato) VALUES (?,?,?,?,?,?)";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pp.setString(1, entity.getInstituicao());
            pp.setString(2,entity.getNomeCurso());
            pp.setString(3, entity.getDescricao());
            pp.setDate(4, Date.valueOf(entity.getDataInicio()));
            pp.setDate(5, Date.valueOf(entity.getDataFim()));
            pp.setInt(6, entity.getCandidato().getId());
            pp.executeUpdate();
            rs = pp.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Curso",e);
        } finally {
            fechar(rs);
        }

    }


    @Override
    public void update(Curso entity) {
        String sql = "UPDATE cursos SET instituicao = ?, nome = ?, descricao = ?, data_inicio = ?, data_fim = ?, fk_candidato = ? WHERE id_curso = ?)";
            try (PreparedStatement pp = conexao.prepareStatement(sql)){
                pp.setString(1, entity.getInstituicao());
                pp.setString(2,entity.getNomeCurso());
                pp.setString(3, entity.getDescricao());
                pp.setDate(4, Date.valueOf(entity.getDataInicio()));
                pp.setDate(5, Date.valueOf(entity.getDataFim()));
                pp.setInt(6, entity.getCandidato().getId());
                pp.setInt(7, entity.getId());
                pp.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar Curso",e);
            }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM cursos WHERE id_curso = ?";

        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, id);
            pp.execute();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao deletar Curso",e);
        }

    }

    @Override
    public List<Curso> findAll() {
        String sql = "SELECT * FROM cursos";
        List<Curso> lista = new ArrayList<>();
        try (
                PreparedStatement pp = conexao.prepareStatement(sql);
                ResultSet rs = pp.executeQuery()
        ){

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id_curso"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);
                lista.add(curso);

            }

            } catch (SQLException e){
            throw new RuntimeException("Erro ao listar Curso",e);
        }
        return lista;
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        String sql = "SELECT * FROM cursos where id_curso = ?";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, id);
            rs = pp.executeQuery();
            Curso curso = new Curso();
            if (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id_curso"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);
                return Optional.of(curso);
            }

            }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar Curso",e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    public List<Curso> findByFkCandidato(Integer idCandidato) {

        String sql = "SELECT * FROM cursos WHERE fk_candidato = ?";
        ResultSet rs = null;
        List<Curso> lista = new ArrayList<>();
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, idCandidato);
            rs = pp.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id_curso"));
                curso.setInstituicao(rs.getString("instituicao"));
                curso.setNomeCurso(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                curso.setDataFim(rs.getDate("data_fim").toLocalDate());
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                curso.setCandidato(c);
                lista.add(curso);

            }

            } catch (SQLException e){
            throw new RuntimeException("Erro ao buscar Curso",e);
        } finally {
            fechar(rs);
        }
        return lista;


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
