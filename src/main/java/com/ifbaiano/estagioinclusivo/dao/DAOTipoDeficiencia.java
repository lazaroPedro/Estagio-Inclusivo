package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DAOTipoDeficiencia implements DAORepository<TipoDeficiencia, Integer> {
    private Connection conexao;

    public DAOTipoDeficiencia(Connection conexao) {
        this.conexao = conexao;
    }
    @Override
    public Optional<Integer> insert(TipoDeficiencia entity) {
        String sql = "INSERT INTO deficiencias (nome, descricao, tipo_deficiencia, tipo_apoio, fk_candidato) VALUES (?,?,?,?,?)";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pp.setString(1, entity.getNome());
            pp.setString(2, entity.getDescricao());
            pp.setString(3, entity.getTipo().name());
            pp.setString(4, entity.getTipoApoio());
            pp.setInt(5, entity.getCandidato().getId());
            pp.executeUpdate();
            rs = pp.getGeneratedKeys();
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir deficiencia", e);
        } finally {
            fechar(rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(TipoDeficiencia entity) {
        String sql = "UPDATE deficiencias SET nome = ?, descricao = ?, tipo_deficiencia = ?, tipo_apoio = ?, fk_candidato = ? WHERE id_deficiencia = ?";

        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setString(1, entity.getNome());
            pp.setString(2, entity.getDescricao());
            pp.setString(3, entity.getTipo().name());
            pp.setString(4, entity.getTipoApoio());
            pp.setInt(5, entity.getCandidato().getId());
            pp.setInt(6, entity.getId());
            pp.execute();

        } catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar deficiencias", e);
        }


    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM deficiencias WHERE id_deficiencia = ?";

        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, id);
            pp.execute();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao deletar deficiencia", e);
        }

    }

    @Override
    public List<TipoDeficiencia> findAll() {
        String sql = "SELECT * FROM deficiencias";
        List<TipoDeficiencia> lista = new ArrayList<>();
        try (PreparedStatement pp = conexao.prepareStatement(sql);
            ResultSet rs = pp.executeQuery()){
            while (rs.next()) {
                TipoDeficiencia tipoDeficiencia = new TipoDeficiencia();
                tipoDeficiencia.setId(rs.getInt("id_deficiencia"));
                tipoDeficiencia.setNome(rs.getString("nome"));
                tipoDeficiencia.setDescricao(rs.getString("descricao"));
                tipoDeficiencia.setTipoApoio(rs.getString("tipo_apoio"));
                tipoDeficiencia.setTipo(TipoDeficienciaEnum.valueOf(rs.getString("tipo_deficiencia")));
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                tipoDeficiencia.setCandidato(c);
            }
            pp.execute();


        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar deficiencia", e);
        }
        return lista;
    }

    @Override
    public Optional<TipoDeficiencia> findById(Integer id) {
        String sql = "SELECT * FROM deficiencias WHERE id_deficiencia = ?";
        ResultSet rs = null;
        try (PreparedStatement pp = conexao.prepareStatement(sql)){
            pp.setInt(1, id);
            rs = pp.executeQuery();


            if (rs.next()) {
                TipoDeficiencia tipoDeficiencia = new TipoDeficiencia();
                tipoDeficiencia.setId(rs.getInt("id_deficiencia"));
                tipoDeficiencia.setNome(rs.getString("nome"));
                tipoDeficiencia.setDescricao(rs.getString("descricao"));
                tipoDeficiencia.setTipoApoio(rs.getString("tipo_apoio"));
                tipoDeficiencia.setTipo(TipoDeficienciaEnum.valueOf(rs.getString("tipo_deficiencia")));
                Candidato c = new Candidato();
                c.setId(rs.getInt("fk_candidato"));
                tipoDeficiencia.setCandidato(c);
                return Optional.of(tipoDeficiencia);
            }





        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar deficiencias", e);
        } finally {
            fechar(rs);
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

}
