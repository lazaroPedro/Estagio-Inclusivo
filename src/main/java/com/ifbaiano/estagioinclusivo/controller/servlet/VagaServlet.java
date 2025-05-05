package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/vaga")
public class VagaServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		Vaga vaga = null;
		String erro = null;

		HttpSession session = request.getSession(false);
		SessionDTO usuariologado = null;
		if (session != null) {
			usuariologado = (SessionDTO) session.getAttribute("usuarioLogado");
		}

		if (usuariologado == null) {
			erro = "Usuário não está logado.";
		} else {
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
		}

		request.setAttribute("erro", erro);
		request.setAttribute("vaga", vaga);
		request.getRequestDispatcher("/vaga.jsp").forward(request, response);

		
		
	}
}
