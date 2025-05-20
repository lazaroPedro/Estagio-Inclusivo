package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOTipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**/
@WebServlet("/home/deficiencia/delete/id")
public class DeficienciaDelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");

        try(DAOFactory daoFactory = new DAOFactory()) {
            int id = Integer.parseInt(req.getParameter("id"));

            DAOTipoDeficiencia dC = daoFactory.buildDAOTipoDeficiencia();
            dC.findAllByCandidato(user.getId()).forEach(candidato -> {
                if(candidato.getId() == id) {
                    dC.delete(id);
                    req.setAttribute("sucesso", true);
                }
            });
            req.getRequestDispatcher("/home/candidato/full").forward(req, resp);

        }

    }
}
