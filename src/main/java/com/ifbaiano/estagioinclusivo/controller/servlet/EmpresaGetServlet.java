package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/empresa")
public class EmpresaGetServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		try (DAOFactory daoFactory = new DAOFactory()) {
			DAOEmpresa daoEmpresa = daoFactory.buildDAOEmpresa();
			DAOVaga daoVaga = daoFactory.buildDAOVaga();

			Optional<Empresa> empresaOpt = daoEmpresa.findById(id);
			if (empresaOpt.isEmpty()) {
				req.setAttribute("erro", "Empresa não encontrada.");
				return;
			}

			Empresa empresa = empresaOpt.get();
			DAOEndereco daoEndereco = daoFactory.buildDAOEndereco();
			Optional<Endereco> enderecoOpt = daoEndereco.findById(empresa.getEndereco().getId());
			empresa.setEndereco(enderecoOpt.orElse(null));
			List<Vaga> vagasPublicadas = daoVaga.findByIdEmpresa(empresa.getId()).stream().map(vg ->{
				 daoEndereco.findById(vg.getEndereco().getId()).ifPresent(vg::setEndereco);
				 return vg;
			}).toList();

			List<Vaga> vagasAtivas = new java.util.ArrayList<>();
			List<Vaga> vagasFinalizadas = new java.util.ArrayList<>();
			

			if (vagasPublicadas != null) {
			    for (Vaga vaga : vagasPublicadas) {
			        TipoVaga status = vaga.getStatus();
			        if (status == TipoVaga.ATIVA) {
			            vagasAtivas.add(vaga);
			        } else if (status == TipoVaga.FINALIZADA) {
			            vagasFinalizadas.add(vaga);
			        }
			    }
			}

			

			req.setAttribute("empresa", empresa);
			req.setAttribute("vagasPublicadas", vagasPublicadas);
			req.setAttribute("vagasAtivas", vagasAtivas);
			req.setAttribute("vagasFinalizadas", vagasFinalizadas);


			req.getRequestDispatcher("/pages/empresa.jsp").forward(req, resp);

		} catch (Exception e) {
			req.setAttribute("erro", "Erro ao carregar perfil da empresa.");
		}
	}
}
