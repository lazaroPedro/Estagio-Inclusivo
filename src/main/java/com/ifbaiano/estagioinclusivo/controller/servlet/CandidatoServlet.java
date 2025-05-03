package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.services.CandidatoService;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/candidato")
public class CandidatoServlet extends HttpServlet {
    private CandidatoService candidatoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCandidato = Integer.parseInt(req.getParameter("id"));


        req.setAttribute("nome", c.getNome());
        req.setAttribute("email", c.getEmail());
        req.setAttribute("telefone", c.getTelefone());
        req.setAttribute("cpf", c.getCpf());




    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Candidato candidato = new Candidato();

        candidato.setNome(request.getParameter("nome"));
        candidato.setEmail(request.getParameter("email"));
        candidato.setTelefone(request.getParameter("telefone"));
        candidato.setCpf(request.getParameter("cpf"));
        try {
            candidato.validar();
        }catch (ValidationException e) {
            request.setAttribute("erros", e.getErroCampos());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));

        DAOCandidato daoCandidato = null;
        try {
            daoCandidato = new DAOCandidato(DBConfig.criarConexao());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        daoCandidato.delete(id);
        daoCandidato.fecharConexao();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
