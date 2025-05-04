package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;

import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/CadastroVagaServlet")
public class CadastroVagaServlet extends HttpServlet {

	private Object enderecoId;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UFT-8");
		
		try {
			HttpServlet session = (HttpServlet) request.getSession(false);
			SessionDTO usuariologado = (session != null) ? (SessionDTO) ((ServletRequest) session).getAttribute("usuarioLogado") : null;
		
			if (usuarioLogado == null || usuarioLogado.getTipoUsuario() == null) {
				request.setRedirect("login.jsp");
				return;
			} 

		String descricao = request.getParameter("descricao");
		String requisitos = request.getParameter("requisitos");
		String beneficios = request.getParameter("beneficios");
		int qtdVagas = Integer.parseInt(request.getParameter("qtd_vagas"));
		TipoVaga status = request.getParameter("status");
		int Empresa.id - Integer.parseInt(request.getParameter("empresa_id"));
		int idEndereco - Integer.parseInt(request.getParameter("endereco_id"));

		Vaga vaga = new Vaga();
		vaga.setDescricao(descricao);
		vaga.setRequisitos(requisitos);
		vaga.setBeneficios(beneficios);
		vaga.setQtdVagas(qtdVagas);
		vaga.setStatus(status);
		vaga.setId(idVaga);
		
		DAOVaga vagaDAO = new DAOVaga();
		vagaDAO.inserir(vaga);
		
		request.setAttribute("mensagem", "Vaga cadastrada com sucesso!");
		request.getRequestDispatcher("CadastroVagas.jsp").forward(request, response);
		
	}catch(Exception e){
		e.printStackTrace();
		request.setAttribute("erro", "Erro ao cadastraar vaga: " + e.getMessage());
		request.getRequestDispatcher("CadastroVagas.jsp").forward(request, response);

	}
}

