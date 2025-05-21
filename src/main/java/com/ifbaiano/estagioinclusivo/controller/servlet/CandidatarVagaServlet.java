package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidatoVaga;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.CandidatoVaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/candidatovaga")
public class CandidatarVagaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(DAOFactory daoFactory = new DAOFactory()) {
            SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
            int idVaga = Integer.parseInt(req.getParameter("id"));
            DAOVaga dV = daoFactory.buildDAOVaga();
            Candidato candidato = new Candidato();
            candidato.setId(user.getId());
            DAOCandidatoVaga dcv = daoFactory.buildDAOCandidatoVaga();

			if(dcv.findById(user.getId(), idVaga).isEmpty() && dV.findById(idVaga).isPresent()){

            dV.findById(idVaga).ifPresent(vg -> {
                CandidatoVaga candidatoVaga = new CandidatoVaga();

                candidatoVaga.setCandidato(candidato);
                candidatoVaga.setVaga(vg);
                dcv.insert(candidatoVaga);


            });}
            resp.sendRedirect(req.getContextPath() + "/index");

        }
    }

}
