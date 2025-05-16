package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home/vaga/insert")
public class CadastroVagaServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (Connection connection = DBConfig.criarConexao()) {
			connection.setAutoCommit(false);

			
			String descricao = request.getParameter("descricao");
			String requisitos = request.getParameter("requisitos");
			String beneficios = request.getParameter("beneficios");
			long qtdVagas = Integer.parseInt(request.getParameter("qtd_vagas"));

			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");			
			String municipio = request.getParameter("municipio");
			String estado = request.getParameter("estado");
			String cep = request.getParameter("cep");
		
		
			
			Endereco endereco = new Endereco();
			endereco.setRua(rua);
			endereco.setBairro(bairro);
			endereco.setMunicipio(municipio);
			endereco.setEstado(estado);
			endereco.setCep(cep);
		
			
			DAOEndereco daoEndereco = new DAOEndereco(connection);
			Integer idEndereco = daoEndereco.insert(endereco).orElseThrow(() -> new RuntimeException("Erro ao salvar endereço"));
			endereco.setId(idEndereco);
			
			DAOEmpresa daoEmpresa = new DAOEmpresa(connection);
			int idEmpresa = usuariologado.getId();
			Empresa empresa = daoEmpresa.findById(idEmpresa).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

			String[] deficienciasSelecionadas = request.getParameterValues("tiposDeficiencia");
			String deficienciasPermitidas = "";
			if(deficienciasSelecionadas != null) {
				deficienciasPermitidas = String.join(",", deficienciasSelecionadas);
			}
			
			
			Vaga vaga = new Vaga();
			vaga.setDescricao(descricao);
			vaga.setRequisitos(requisitos);
			vaga.setBeneficios(beneficios);
<<<<<<< HEAD
			vaga.setQtdVagas(qtdVagas);
			vaga.setStatus(TipoVaga.ATIVA);
			vaga.setEmpresa(empresa);
			vaga.setEndereco(endereco);
			
=======
			vaga.setQtdVagas(Long.valueOf((request.getParameter("qtd_vagas"))));
			vaga.setStatus(status);

>>>>>>> 92763d5d6306b28fa330ce7cd61a23c6b1b215b7
			DAOVaga vagaDAO = new DAOVaga(connection);
			vagaDAO.insert(vaga);
			
			
			connection.commit();
			response.sendRedirect("CadastroVagas.jsp?sucesso=1");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastraar vaga: " + e.getMessage());
			request.getRequestDispatcher("CadastroVagas.jsp").forward(request, response);

		}

	}
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/pages/CadastroVagas.jsp").forward(request, response);
	}

}
