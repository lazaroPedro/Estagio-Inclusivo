package com.ifbaiano.estagioinclusivo.dao;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class DAOEmpresa implements DAORepository<Empresa, Integer> {
    private Connection connection;
    private DAOUsuario daoUsuario;


    public DAOEmpresa(Connection connection) {
        this.connection = connection;
        this.daoUsuario = new DAOUsuario(connection);
    }

    @Override
    public Optional<Integer> insert(Empresa entity) {

        String sql = "INSERT INTO empresas (id_empresa, cnpj, razaoSocial) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
            try {
                connection.setAutoCommit(false);
                daoUsuario.insert(entity).ifPresent(entity::setId);
                stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, entity.getId());
                stmt.setString(2, entity.getCnpj());
                stmt.setString(3, entity.getRazaoSocial());
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                connection.commit();
                connection.setAutoCommit(true);
                if (rs.next()) {
                    return Optional.of(rs.getInt(1));
                }

            } catch (SQLException e) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                throw new RuntimeException("Erro ao inserir empresa.", e);
            } finally {
                fechar(rs);
                fechar(stmt);
            }
            return Optional.empty();
    }


    @Override
    public void update(Empresa entity) {
        String sql = "UPDATE empresas SET cnpj = ?, razaoSocial = ? WHERE id_empresa = ?";
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false);
            daoUsuario.update(entity);
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCnpj());
            stmt.setString(2, entity.getRazaoSocial());
            stmt.setInt(3, entity.getId());
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

            throw new RuntimeException("Erro ao atualizar empresa", e);
        } finally {
            fechar(stmt);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM empresas WHERE id_empresa = ?";
        PreparedStatement stmt = null;
        try {
            connection.setAutoCommit(false);
            daoUsuario.delete(id);
            stmt = connection.prepareStatement(sql);
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

            throw new RuntimeException("Erro ao deletar empresa", e);
        } finally {
            fechar(stmt);
        }
    }

    @Override
    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresas JOIN usuario ON empresas.id_empresa = usuario.id_usuario";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empresa empresa = new Empresa();
                Endereco e = new Endereco();
                e.setId(rs.getInt("fk_endereco"));
                empresa.setId(rs.getInt("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEmail(rs.getString("email"));
                empresa.setHashSenha(rs.getString("hashSenha"));
                empresa.setSalt(rs.getString("salt"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazaoSocial(rs.getString("razaoSocial"));
                empresa.setEndereco(e);
                empresa.setTelefone("telefone");
                empresa.setPapel(TipoUsuario.valueOf("papel"));

                empresas.add(empresa);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empresas", e);
        }
        return empresas;
    }

    @Override
    public Optional<Empresa> findById(Integer id) {
        String sql = "SELECT * FROM empresas JOIN usuarios ON empresas.id_empresa = usuarios.id_usuario WHERE id_empresa = ?";
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Empresa empresa = new Empresa();
                Endereco e = new Endereco();
                e.setId(rs.getInt("fk_endereco"));
                empresa.setId( rs.getInt("id_empresa"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEmail(rs.getString("email"));
                empresa.setHashSenha(rs.getString("hash_senha"));
                empresa.setSalt(rs.getString("salt"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazaoSocial(rs.getString("razao_social"));
                empresa.setEndereco(e);
                empresa.setPapel(TipoUsuario.valueOf(rs.getString("papel")));
                empresa.setTelefone("telefone");

                return Optional.of(empresa);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empresa por ID", e);
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
