package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.Curriculo;
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
            DAOEndereco dEEndereco = daoFactory.buildDAOEndereco();
            DAOCurriculo dC = daoFactory.buildDAOCurriculo();
            DAOCandidato dCa = daoFactory.buildDAOCandidato();

            String search = request.getParameter("pesquisa");
            String filtro = request.getParameter("filtro");

            switch (filtro){
                case "0":
                    List<Vaga> lV = dV.findByTituloContaining(search).stream()
                            .filter(vaga -> vaga.getStatus() == TipoVaga.ATIVA)
                            .map(vaga -> {
                                dE.findById(vaga.getEmpresa().getId()).ifPresent(vaga::setEmpresa);
                                return vaga;
                            }).collect(Collectors.toList());
                    request.setAttribute("vagas", lV);
                    break;
                case "1":
                    List<Empresa> lE = dE.findByNomeContaining(search).stream().map(empresa -> {
                        dEEndereco.findById(empresa.getEndereco().getId()).ifPresent(empresa::setEndereco);
                        return empresa;
                    }).toList();
                    request.setAttribute("empresas", lE);
                    break;

                case "2":
                    List<Curriculo> lC = dC.findContaining(search).stream().map(curriculo -> {
                        dCa.findById(curriculo.getCandidato().getId()).ifPresent(curriculo::setCandidato);
                        return curriculo;

                    }).toList();
                    request.setAttribute("curriculos", lC);
                    break;

                default:
                    response.sendRedirect(request.getContextPath() +"/");
                    return;
            }


            request.getRequestDispatcher("/pages/resultpesquisa.jsp").forward(request, response);

        }}

    }

