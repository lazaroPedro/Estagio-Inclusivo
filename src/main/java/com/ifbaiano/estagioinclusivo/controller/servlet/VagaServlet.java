package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.*;
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

@WebServlet("/vaga")
public class VagaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		SessionDTO user = (SessionDTO) request.getSession().getAttribute("usuarioLogado");


		try (DAOFactory daoFactory = new DAOFactory()) {
			DAOVaga vagaDao = daoFactory.buildDAOVaga();
			DAOEndereco dE = daoFactory.buildDAOEndereco();
			DAOCandidatoVaga dcv = daoFactory.buildDAOCandidatoVaga();
			DAOEmpresa empresaDao = daoFactory.buildDAOEmpresa();
			int idVaga = Integer.parseInt(idParam);
			dcv.findById(user.getId(), idVaga).ifPresent(vaga -> {
				request.setAttribute("candidatado", 1);
			});

			Optional<Vaga> vagaOpt = vagaDao.findById(idVaga);

			if (vagaOpt.isPresent()) {
				Vaga vaga = vagaOpt.get();
				dE.findById(vaga.getEndereco().getId()).ifPresent(vaga :: setEndereco);
				Optional<Empresa> empresaOpt = empresaDao.findById(vaga.getEmpresa().getId());
				if (empresaOpt.isPresent()) {
					Empresa empresa = empresaOpt.get();
					vaga.setEmpresa(empresa);

					request.setAttribute("vaga", vaga);
					request.setAttribute("empresaLogada", empresa);

					request.getRequestDispatcher("/pages/vagas.jsp").forward(request, response);
				} else {
					request.setAttribute("erro", "Empresa da vaga não encontrada");
					request.getRequestDispatcher("/index.jsp").forward(request, response);

				}

			} else {
				request.setAttribute("erro", "Vaga não encontrada.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
}
	}
}