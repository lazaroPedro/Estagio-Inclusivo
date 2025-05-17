package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOTipoDeficiencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**/
@WebServlet("/home/deficiencia/delete")
public class DeficienciaDelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOTipoDeficiencia dC = daoFactory.buildDAOTipoDeficiencia();
            dC.delete(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("sucesso", true);
            req.getRequestDispatcher("/pages/perfil.jsp").forward(req, resp);

        }

    }
}
