package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/home") /* /home/*  */
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* if (req.getSession().getAttribute ("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        }else {

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
*/

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOVaga vDao = daoFactory.buildDAOVaga();
            DAOEmpresa eDao = daoFactory.buildDAOEmpresa();
            List<Vaga> vList = vDao.findAll();
            for (Vaga v : vList) {
                Optional<Empresa> e = eDao.findById(v.getEmpresa().getId());
            e.ifPresent(v::setEmpresa);
            }
            req.setAttribute("lista_vagas", vList);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }
}
