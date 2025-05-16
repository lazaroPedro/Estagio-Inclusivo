package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import java.sql.Connection; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/vaga/insert")
public class VagaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

<<<<<<< HEAD
		if (idParam == null || idParam.isEmpty()) {
			response.sendRedirect("index.jsp");
			return;
		}

		try (DAOFactory daoFactory = new DAOFactory()) {
			DAOVaga vagaDao = daoFactory.buildDAOVaga();
			DAOEmpresa empresaDao = daoFactory.buildDAOEmpresa();
=======
			if (idParam != null) {
				try {
					int id = Integer.parseInt(idParam);
>>>>>>> 92763d5d6306b28fa330ce7cd61a23c6b1b215b7

			int idVaga;

			try {
				idVaga = Integer.parseInt(idParam);
			} catch (NumberFormatException e) {
				request.setAttribute("erro", "ID da vaga inválido.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}
			Optional<Vaga> vagaOpt = vagaDao.findById(idVaga);

			if (vagaOpt.isPresent()) {
				Vaga vaga = vagaOpt.get();

				Optional<Empresa> empresaOpt = empresaDao.findById(vaga.getEmpresa().getId());
				empresaOpt.ifPresent(vaga::setEmpresa);

				request.setAttribute("vaga", vaga);
				request.getRequestDispatcher("/page/Vaga.jsp").forward(request, response);
			} else {
				request.setAttribute("erro", "Vaga não encontrada");
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro a carregar os dados da vaga");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}


	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
<<<<<<< HEAD
		Connection conexao = (Connection) DBConfig.criarConexao();
		HttpSession session = request.getSession(false);
		SessionDTO usuariologado = (session != null) ? (SessionDTO) session.getAttribute("usuarioLogado") : null;
=======
>>>>>>> 92763d5d6306b28fa330ce7cd61a23c6b1b215b7


		try (Connection connection = DBConfig.criarConexao()) {
			connection.setAutoCommit(false);

			String descricao = request.getParameter("descricao");
			String requisitos = request.getParameter("requisitos");
			String beneficios = request.getParameter("beneficios");
			String empresaNome = request.getParameter("empresa_nome");
			
			String qtdVagasStr = request.getParameter("qtd_vagas");
			int qtdVagas;
			
			try {
				qtdVagas = Integer.parseInt(qtdVagasStr);
			} catch (NumberFormatException e) {
				request.setAttribute("erro", "Quantidade de vagas inválida.");
				request.getRequestDispatcher("cadastrovagas.jsp").forward(request, response);				return;
			}

			if (descricao == null || descricao.trim().isEmpty() 
					|| requisitos == null || requisitos.trim().isEmpty()
					|| beneficios == null || beneficios.trim().isEmpty() 
					|| empresaNome == null || empresaNome.trim().isEmpty()) {

				request.setAttribute("erro", "todos os campos são obrigatórios.");
				request.getRequestDispatcher("cadastrovagas.jsp").forward(request, response);
				return;
			}

		
			Vaga vaga = new Vaga();
			vaga.setDescricao(descricao);
			vaga.setRequisitos(requisitos);
			vaga.setBeneficios(beneficios);
<<<<<<< HEAD
			vaga.setQtdVagas(Integer.parseInt(request.getParameter("qtd_vagas")));
			vaga.setStatus(TipoVaga.ATIVA);
			
=======
			vaga.setQtdVagas(Long.valueOf(request.getParameter("qtd_vagas")));
			vaga.setStatus(status);

>>>>>>> 92763d5d6306b28fa330ce7cd61a23c6b1b215b7
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
