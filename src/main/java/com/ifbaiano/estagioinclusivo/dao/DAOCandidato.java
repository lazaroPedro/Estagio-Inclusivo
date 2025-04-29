package com.ifbaiano.estagioinclusivo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;

public class DAOCandidato implements DAORepository<Candidato, Integer> {

    private Connection connection;
    private DAOUsuario daoUsuario;

    public DAOCandidato(Connection connection) {
        this.connection = connection;
        this.daoUsuario = new DAOUsuario(connection);
    }

    @Override
    public void insert(Candidato entity) {
        String sql = "INSERT INTO candidatos (id_candidato, telefone, cpf, Regiao) VALUES (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            int id_candidato = daoUsuario.insertAndReturnid(entity);
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id_candidato);
                stmt.setString(2, entity.getTelefone());
                stmt.setString(3, entity.getCpf());
                stmt.setInt(4, entity.getRegiao().getId());
                stmt.executeUpdate();
            }
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2){
                throw new RuntimeException("Erro no rollback", e);
            }
            throw new RuntimeException("Erro ao inserir candidato", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Erro no reset dos commits", e);
            }
        }
    }

    @Override
    public void update(Candidato entity) {
        String sql = "UPDATE candidatos SET telefone = ?, cpf = ?, Regiao = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getTelefone());
            stmt.setString(2, entity.getCpf());
            stmt.setObject(3, entity.getRegiao());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException("Erro ao da update", e);
        }

    }

    @Override
    public void delete(Integer id) {

        try {
            String sql = "DELETE FROM candidatos WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidato", e);
        }
    }

    @Override
    public List<Candidato> findAll() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidatos";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Candidato candidato = new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
                        rs.getString("hashSenha"), rs.getString("salt"), rs.getString("cpf"), rs.getString("curso"),
                        rs.getString("telefone"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidato", e);
        }
        return candidatos;
    }

    @Override
    public Candidato findById(Integer id) {
        String sql = "SELECT * from candidatos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
                            rs.getString("hashSenha"), rs.getString("salt"), rs.getString("cpf"), rs.getString("curso"),
                            rs.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidato por ID", e);
        }
        return null;
    }



<<<<<<< HEAD
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
							rs.getString("hashSenha"), rs.getString("salt"), rs.getString("cpf"), rs.getString("curso"),
							rs.getString("telefone"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar candidato por ID", e);
		}
		return null;
	}
=======
    public void fecharConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 70f6b1e46e9025819cb9e953ca34da0a85c07fff
}
