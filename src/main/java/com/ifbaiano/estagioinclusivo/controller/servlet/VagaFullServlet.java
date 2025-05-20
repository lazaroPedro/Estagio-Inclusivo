package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.CandidatoVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/home/empresa/vaga")
public class VagaFullServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(DAOFactory daoFactory = new DAOFactory()){
            DAOVaga dV = daoFactory.buildDAOVaga();
            DAOCandidatoVaga dCV = daoFactory.buildDAOCandidatoVaga();
            DAOEmpresa dE = daoFactory.buildDAOEmpresa();
            DAOCandidato dC = daoFactory.buildDAOCandidato();
            SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
            Optional<Vaga>  oV= dV.findByIdEmpresa(user.getId()).stream().filter(vaga -> {
                return vaga.getId() == Integer.parseInt(req.getParameter("id"));
            }).findFirst();
            if(oV.isEmpty()) {
                resp.sendRedirect("/index");
                return;
            }
            Vaga vaga = oV.get();
            List<Candidato> candidatos = dCV.findByVaga(vaga.getId()).stream().map(cand ->{
                return  dC.findById(cand.getCandidato().getId()).get();
            }).toList();

            req.setAttribute("candidatos", candidatos);
            req.setAttribute("vaga", vaga);
            req.getRequestDispatcher("/pages/editarvaga.jsp").forward(req, resp);

        }
    }
}
