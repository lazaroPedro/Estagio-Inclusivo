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

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar candidato", e);

		}

	}

	@Override
	public void delete(Integer id) {

		try {

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
						rs.getString("telefone"));
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
							rs.getString("telefone"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar candidato por ID", e);
		}
		return null;
	}

	
	
	public void fecharConexao() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
