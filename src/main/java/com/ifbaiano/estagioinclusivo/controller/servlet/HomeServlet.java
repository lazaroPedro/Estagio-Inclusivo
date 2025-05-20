package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 *
 *
 *
 * /home/* restringe login
 * /home/candidato/all
 * /home/candidato/id
 * /home/candidato/vaga
 * /home/candidato/insert
 * /home/candidato/update
 * /home/candidato/delete
 */
@WebServlet("/") /* /home/*  */
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOVaga vDao = daoFactory.buildDAOVaga();
            DAOEmpresa eDao = daoFactory.buildDAOEmpresa();
            List<Vaga> vList = vDao.findAll().stream()
                    .filter(vaga -> vaga.getStatus() == TipoVaga.ATIVA)
                    .map(vaga -> {
                        Optional<Empresa> e = eDao.findById(vaga.getEmpresa().getId());
                        e.ifPresent(vaga::setEmpresa);
                        return vaga;
                    }).collect(Collectors.toList());

            req.setAttribute("lista_vagas", vList);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }
}
