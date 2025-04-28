package com.ifbaiano.estagioinclusivo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;

public class DAOCandidato implements DAORepository<Candidato, Integer> {

	private Connection connection;

	public DAOCandidato(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Candidato entity) {
		String sql = "INSERTE INTO candidato (id, nome, email, hashSenha, salt, cpf, curso, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getEmail());
			stmt.setString(3, entity.getHashSenha());
			stmt.setString(4, entity.getSalt());
			stmt.setString(5, entity.getCpf());
			stmt.setString(6, entity.getCurso());
			stmt.setString(7, entity.getTelefone());
			stmt.executeUpdate();

			inserirDeficiencias(entity.getId(), entity.getDeficiencias());

		} catch (Exception e) {
			throw new RuntimeException("Erro ao inserir candidato", e);
		}
	}

	@Override
	public void update(Candidato entity) {
		String sql = "UPDATE candidato SET nome = ?, email = ?, hashSenha = ?, salt = ?, cpf = ?, curso = ?, telefone = ? WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getEmail());
			stmt.setString(3, entity.getHashSenha());
			stmt.setString(4, entity.getSalt());
			stmt.setString(5, entity.getCpf());
			stmt.setString(6, entity.getCurso());
			stmt.setString(7, entity.getTelefone());
			stmt.setInt(8, entity.getId());
			stmt.executeUpdate();

			deletarDeficiencias(entity.getId());
			inserirDeficiencias(entity.getId(), entity.getDeficiencias());

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar candidato", e);

		}

	}

	@Override
	public void delete(Integer id) {

		try {
			deletarDeficiencias(id);

			String sql = "DELETE FROM candidsto WHERE id = ?";
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
		String sql = "SELECT * FROM candidato";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Candidato candidato = new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("hashSenha"), rs.getString("salt"), rs.getString("cpf"), rs.getString("curso"),
						rs.getString("telefone"), buscarDeficiencias(rs.getInt("id")));
				candidatos.add(candidato);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar candidato", e);
		}
		return candidatos;
	}

	public Candidato findById(Integer id) {
		String sql = "SELECT * from candidato WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
							rs.getString("hashSenha"), rs.getString("salt"), rs.getString("cpf"), rs.getString("curso"),
							rs.getString("telefone"), buscarDeficiencias(id));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar candidato por ID", e);
		}
		return null;
	}

	private void inserirDeficiencias(int candidatoId, List<TipoDeficiencia> deficiencias) throws SQLException {
		String sql = "INSERT INTO candidatoDeficiencia (candidatoId, deficienciaId) VALUES (?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			for (TipoDeficiencia def : deficiencias) {
				stmt.setInt(1, candidatoId);
				stmt.setLong(2, def.getId());
				stmt.addBatch();
			}

			stmt.executeBatch();
		}
	}

	private List<TipoDeficiencia> buscarDeficiencias(int candidatoId) throws SQLException {
		String sql = "SELECT d.id, d.nome FROM tipoDeficiencia d"
				+ "JOIN cadidatoDeficiencia cd ON d.id = cd.deficienciaId " + "WHERE cd.candidatoId = ?";
		List<TipoDeficiencia> deficiencias = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, candidatoId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TipoDeficiencia def = new TipoDeficiencia();
					def.setId(rs.getLong("id"));
					def.setNome(rs.getString("nome"));
					deficiencias.add(def);
				}
			}

		}
		return deficiencias;
	}

	private void deletarDeficiencias(int candidatoId) throws SQLException {
		String sql = "DELETE FROM candidatoDeficiencia WHERE candidatoId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, candidatoId);
			stmt.executeUpdate();
		}

	}

	public void fecharConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
