package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOCurso;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOTipoDeficiencia;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Perfil-candidato")
public class PerfilCandidatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        

  
      
        
        SessionDTO sessionDTO = (SessionDTO) session.getAttribute("usuarioLogado");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidato daoCandidato = daoFactory.buildDAOCandidato();
            DAOCurso daoCurso=daoFactory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = daoFactory.buildDAOTipoDeficiencia();

            Optional<Candidato> candidatoOpt = daoCandidato.findById(sessionDTO.getId());
            if (!candidatoOpt.isPresent()) {
                req.setAttribute("erro", "Candidato não encontrado.");
                req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
                return;
            }

            Candidato candidatoAtualizado = candidatoOpt.get();
            
            List<Curso> cursosInscritos = daoCurso. findAllByCandidato(sessionDTO.getId());
            List<TipoDeficiencia> todasDeficiencias = daoTipoDeficiencia.findAll();
            List<TipoDeficiencia> deficienciasDoCandidato = todasDeficiencias.stream()
                .filter(d -> d.getCandidato().getId() == sessionDTO.getId())
                .toList();
            
            DAOEndereco daoEndereco = daoFactory.buildDAOEndereco();

            int idEndereco = candidatoAtualizado.getEndereco().getId();

            Optional<Endereco> enderecoOpt = daoEndereco.findById(idEndereco);

            enderecoOpt.ifPresent(candidatoAtualizado::setEndereco);

            req.setAttribute("candidato", candidatoAtualizado);
            req.setAttribute("cursos", cursosInscritos);
            req.setAttribute("deficiencias", deficienciasDoCandidato);
            req.getRequestDispatcher("/pages/perfilcandidato.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao carregar perfil do candidato.");
 
        }
        
    }
    }

