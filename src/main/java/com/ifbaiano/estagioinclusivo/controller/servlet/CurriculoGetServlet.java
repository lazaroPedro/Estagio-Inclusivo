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

@WebServlet("/home/curriculo/id")
public class CurriculoGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");


        try (DAOFactory factory = new DAOFactory()) {
            DAOCandidato daoCandidato = factory.buildDAOCandidato();
            DAOCurso daoCurso = factory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();
            DAOEndereco daoEndereco = factory.buildDAOEndereco();
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Curriculo> curriculoOpt = daoCurriculo.findByCandidatoId(id);
            Optional<Candidato> candidatoOpt = daoCandidato.findById(id);
            if (candidatoOpt.isEmpty() || curriculoOpt.isEmpty()) {
                response.sendRedirect("/index");
                return;
            }


            Endereco endereco = daoEndereco.findById(candidatoOpt.get().getEndereco().getId()).orElse(new Endereco());

            List<Curso> cursos = daoCurso.findAllByCandidato(id);
            List<TipoDeficiencia> deficiencias = daoTipoDeficiencia.findAllByCandidato(id);

            request.setAttribute("candidato", candidatoOpt.get());
            request.setAttribute("endereco", endereco);
            request.setAttribute("cursos", cursos);
            request.setAttribute("deficiencias", deficiencias);
            request.setAttribute("curriculo", curriculoOpt.get());
            request.getRequestDispatcher("/pages/exibircurriculo.jsp").forward(request, response);

        }

    }


}
