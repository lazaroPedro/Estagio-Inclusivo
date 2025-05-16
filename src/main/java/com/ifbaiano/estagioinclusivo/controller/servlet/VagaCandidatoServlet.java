package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidatoVaga;
import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home/vaga/all")
public class VagaCandidatoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidatoVaga dCV = daoFactory.buildDAOCandidatoVaga();
            DAOVaga dV = daoFactory.buildDAOVaga();
            DAOEmpresa dE = daoFactory.buildDAOEmpresa();
            if(user.getTipoUsuario() == TipoUsuario.EMPRESA) {

                List<Vaga> lv = dV.findByIdEmpresa(user.getId()).stream().map(vg -> {
                    dE.findById(vg.getEmpresa().getId()).ifPresent(vg::setEmpresa);
                    return vg;
                }).collect(Collectors.toList());
                req.setAttribute("vagas", lv);
            } else if(user.getTipoUsuario() == TipoUsuario.CANDIDATO){
                List<Vaga> lv = dCV.findByCandidato(user.getId()).stream()
                        .map( lcv -> {
                           return dV.findById(lcv.getVaga().getId()).get();

                        }).collect(Collectors.toList());
                req.setAttribute("vagas", lv);
        }
            req.getRequestDispatcher("/pages/vagacadastrada.jsp").forward(req, resp);

        }
    }
}
