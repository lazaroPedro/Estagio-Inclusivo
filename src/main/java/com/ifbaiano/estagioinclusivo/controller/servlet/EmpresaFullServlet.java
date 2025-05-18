package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.*;
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

@WebServlet("/home/empresa/full")
public class EmpresaFullServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) request.getSession().getAttribute("usuarioLogado");

        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOEmpresa dE = daoFactory.buildDAOEmpresa();
            DAOEndereco dEnd = daoFactory.buildDAOEndereco();
            DAOVaga dVaga = daoFactory.buildDAOVaga();
            Optional<Empresa> e = dE.findById(user.getId());
            List<Vaga> vagas = dVaga.findByIdEmpresa(user.getId()).stream().map(vg -> {
                vg.setEmpresa(e.get());
                dEnd.findById(vg.getEndereco().getId()).ifPresent(vg :: setEndereco);
                return vg;
            }).collect(Collectors.toList());
            e.ifPresent(emp ->{
                emp.setSalt(null);
                emp.setHashSenha(null);
                 dEnd.findById(emp.getEndereco().getId()).ifPresent(emp :: setEndereco);
                 request.setAttribute("empresa", emp);
             });
            request.setAttribute("vagas", vagas);


            request.getRequestDispatcher("/pages/perfil.jsp").forward(request, response);

        }
    }



}
