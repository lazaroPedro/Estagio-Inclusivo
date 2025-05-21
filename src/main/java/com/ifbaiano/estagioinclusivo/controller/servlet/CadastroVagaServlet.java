package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/vaga/insert")
public class CadastroVagaServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
		int usuarioId = usuarioLogado.getId();

		try (DAOFactory factory = new DAOFactory()) {
			factory.openTransaction();
			try {
				
			DAOEmpresa daoEmpresa = factory.buildDAOEmpresa();
			DAOVaga daoVaga = factory.buildDAOVaga();
			DAOEndereco daoEndereco = factory.buildDAOEndereco();
			
			Optional<Empresa> empresaOpt = daoEmpresa.findById(usuarioId);
            if (empresaOpt.isEmpty()) {
                response.sendRedirect("/index");
                return;
            }
            
			String cep = request.getParameter("cep");
			
			
			
			Endereco endereco = new Endereco();
			endereco.setRua(request.getParameter("rua"));
	        endereco.setBairro(request.getParameter("bairro"));
	        endereco.setMunicipio(request.getParameter("municipio"));
	        endereco.setEstado(request.getParameter("estado"));
	        endereco.setCep(cep);

	        Validator.validate(endereco);

	        Integer idEndereco = daoEndereco.insert(endereco).orElseThrow(()->new RuntimeException("Não foi possivel cadastrar o endereço"));
	        endereco.setId(idEndereco);
	        

			String[] deficienciasSelecionadas = request.getParameterValues("tiposDeficiencia");
			String deficienciasPermitidas = "";
			if(deficienciasSelecionadas != null) {
				deficienciasPermitidas = String.join(",", deficienciasSelecionadas);
			}
			
			
			Vaga vaga = new Vaga();
			vaga.setDescricao(request.getParameter("descricao"));
			vaga.setRequisitos(request.getParameter("requisitos"));
			vaga.setBeneficios(request.getParameter("beneficios"));
			vaga.setStatus(TipoVaga.ATIVA);
			vaga.setEmpresa(new Empresa());
			vaga.getEmpresa().setId(usuarioId);
			vaga.setEndereco(endereco);
			vaga.setQtdVagas(Long.valueOf((request.getParameter("qtd_vagas"))));
			vaga.setTitulo(request.getParameter("titulo"));

			daoVaga.insert(vaga).ifPresent(vaga :: setId);
			
			factory.closeTransaction();
			response.sendRedirect(request.getContextPath() + "/vaga?id="+vaga.getId());


		} catch (ValidationException ve) {
            try {
                factory.rollbackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            request.setAttribute("errosValidacao", ve.getErrors());
            request.getRequestDispatcher("/pages/cadastrovagas.jsp").forward(request, response);
        } catch (SQLException e) {
            try {
                factory.rollbackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new ServletException("Erro ao cadastrar candidato", e);
        	}
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }
		}		
	 

	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/pages/cadastrovagas.jsp").forward(request, response);
	}
}


