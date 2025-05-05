package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/perfil-candidato")
public class PerfilCandidatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("pages/login.jsp");
            return;
        }

        SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidato daoCandidato = daoFactory.buildDAOCandidato();
            DAOVaga daoVaga = daoFactory.buildDAOVaga();

            Optional<Candidato> candidatoOpt = daoCandidato.findById(sessionDTO.getId());
            if (!candidatoOpt.isPresent()) {
                req.setAttribute("erro", "Candidato não encontrado.");
                req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
                return;
            }

            Candidato candidatoAtualizado = candidatoOpt.get();
            List<Vaga> vagasInscritas = daoVaga.findByIdCandidato(sessionDTO.getId());

            req.setAttribute("candidato", candidatoAtualizado);
            req.setAttribute("vagasInscritas", vagasInscritas);

            req.getRequestDispatcher("/pages/perfil-candidato.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao carregar perfil do candidato.");
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
    }
}
