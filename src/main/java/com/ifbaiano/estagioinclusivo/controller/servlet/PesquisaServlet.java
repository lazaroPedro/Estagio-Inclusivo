package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
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
import java.util.stream.Collectors;


@WebServlet("/search")
public class PesquisaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (DAOFactory daoFactory = new DAOFactory()){
            DAOVaga dV = daoFactory.buildDAOVaga();
            DAOEmpresa dE = daoFactory.buildDAOEmpresa();

            String search = request.getParameter("pesquisa");
            if (search == null || search.isEmpty()) {
                response.sendRedirect(request.getContextPath() +"/home");

            }else {
            List<Vaga> lV = dV.findByTituloContaining(search).stream()
                    .filter(vaga -> vaga.getStatus() == TipoVaga.ATIVA)
                    .map(vaga -> {
              dE.findById(vaga.getEmpresa().getId()).ifPresent(vaga::setEmpresa);
              return vaga;
            }).collect(Collectors.toList());
            List<Empresa> lE = dE.findByNomeContaining(search);
            request.setAttribute("vagas", lV);
            request.setAttribute("empresas", lE);

            request.getRequestDispatcher("/pages/resultpesquisa.jsp").forward(request, response);

        }}

    }
}
