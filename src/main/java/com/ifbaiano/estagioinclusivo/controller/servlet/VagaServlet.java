package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/vaga/insert")
public class VagaServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		Vaga vaga = null;
		String erro = null;

			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);

					try (DAOFactory daoFactory = new DAOFactory()) {
						vaga = daoFactory.buildDAOVaga().findById(id).orElse(null);
					}

					if (vaga == null) {
						erro = "Vaga não encontrada.";
					}
				} catch (Exception e) {
					erro = "Erro ao carregar vaga: " + e.getMessage();
				}
			} else {
				erro = "ID da vaga não informado.";
			}


		request.setAttribute("erro", erro);
		request.setAttribute("vaga", vaga);
		request.getRequestDispatcher("/pages/vagas.jsp").forward(request, response);

		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		try (Connection connection = DBConfig.criarConexao()) {
			connection.setAutoCommit(false);

			String descricao = request.getParameter("descricao");
			String requisitos = request.getParameter("requisitos");
			String beneficios = request.getParameter("beneficios");
			int qtdVagas = Integer.parseInt(request.getParameter("qtd_vagas"));
			String statusParam = request.getParameter("status");
			String empresaNome = request.getParameter("empresa_nome");

			if (descricao == null || descricao.trim().isEmpty() || requisitos == null || requisitos.trim().isEmpty()
					|| beneficios == null || beneficios.trim().isEmpty() || statusParam == null
					|| statusParam.trim().isEmpty() || empresaNome == null || empresaNome.trim().isEmpty()) {

				request.setAttribute("erro", "todos os campos são obrigatórios.");
				request.getRequestDispatcher("cadastrovagas.jsp").forward(request, response);
				return;
			}

			TipoVaga status;
			try {
				status = TipoVaga.valueOf(statusParam.toUpperCase());
			} catch (IllegalArgumentException e) {
				request.setAttribute("erro", "Status inválido.");
				request.getRequestDispatcher("cadastrovagas.jsp").forward(request, response);
				return;
			}
			Vaga vaga = new Vaga();
			vaga.setDescricao(descricao);
			vaga.setRequisitos(requisitos);
			vaga.setBeneficios(beneficios);
			vaga.setQtdVagas(Long.valueOf(request.getParameter("qtd_vagas")));
			vaga.setStatus(status);

			DAOVaga vagaDAO = new DAOVaga(connection);
			vagaDAO.insert(vaga);

			connection.commit();
			response.sendRedirect("cadastrovagas.jsp?sucesso=1");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastraar vaga: " + e.getMessage());
			request.getRequestDispatcher("cadastrovagas.jsp").forward(request, response);

		}
	}
}
