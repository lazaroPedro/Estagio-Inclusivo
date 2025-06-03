package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/vaga/delete")
public class VagaDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOVaga dV = daoFactory.buildDAOVaga();
            int id = Integer.parseInt(req.getParameter("id"));
            dV.findByIdEmpresa(user.getId()).forEach(vg ->{
                if(vg.getEmpresa().getId() == user.getId()) {
;                   dV.delete(id);
                    req.setAttribute("sucesso", true);

                }
        });
            req.getRequestDispatcher("/home/empresa/full").forward(req, resp);

        }
    }
}
