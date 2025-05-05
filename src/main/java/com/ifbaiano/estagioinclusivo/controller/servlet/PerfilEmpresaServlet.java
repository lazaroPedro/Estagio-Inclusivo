package com.ifbaiano.estagioinclusivo.controller.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/perfil-empresa")
public class PerfilEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("pages/login.jsp");
            return;
        }

        SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOEmpresa daoEmpresa = daoFactory.buildDAOEmpresa();
            DAOVaga daoVaga = daoFactory.buildDAOVaga();

            Optional<Empresa> empresaOpt = daoEmpresa.findById(sessionDTO.getId());
            if (!empresaOpt.isPresent()) {
                req.setAttribute("erro", "Empresa não encontrada.");
                req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
                return;
            }

            Empresa empresa = empresaOpt.get();
            List<Vaga> vagasPublicadas = daoVaga.findByIdEmpresa(empresa.getId());

            req.setAttribute("empresa", empresa);
            req.setAttribute("vagasPublicadas", vagasPublicadas);

            req.getRequestDispatcher("/pages/perfil-empresa.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao carregar perfil da empresa.");
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
    }
}
