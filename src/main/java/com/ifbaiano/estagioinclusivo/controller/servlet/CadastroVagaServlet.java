package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/CadastroVagaServlet")

public class CadastroVagaServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		SessionDTO usuariologado = (session != null) ? (SessionDTO) session.getAttribute("usuarioLogado") : null;

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
				request.getRequestDispatcher("CadastroVagas.jsp").forward(request, response);
				return;
			}

			TipoVaga status;
			try {
				status = TipoVaga.valueOf(statusParam.toUpperCase());
			} catch (IllegalArgumentException e) {
				request.setAttribute("erro", "Status inválido.");
				request.getRequestDispatcher("CadastroVagas.jsp").forward(request, response);
				return;
			}
			Vaga vaga = new Vaga();
			vaga.setDescricao(descricao);
			vaga.setRequisitos(requisitos);
			vaga.setBeneficios(beneficios);
			vaga.setQtdVagas(Integer.parseInt(request.getParameter("qtd_vagas")));
			vaga.setStatus(status);

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
