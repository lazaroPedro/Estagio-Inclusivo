/*package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.utils.PDFUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@WebServlet("/curriculo/exportarPDF")
public class ExportarCurriculoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer usuarioId = (Integer) request.getSession().getAttribute("usuarioLogado");
        if (usuarioId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (DAOFactory factory = new DAOFactory()) {
            DAOCandidato daoCandidato = factory.buildDAOCandidato();
            DAOCurso daoCurso = factory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();

            Optional<Candidato> candidatoOpt = daoCandidato.findById(usuarioId);
            if (candidatoOpt.isEmpty()) {
                response.sendRedirect("index.jsp");
                return;
            }

            Candidato candidato = candidatoOpt.get();
            List<Curso> cursos = daoCurso.findAllByCandidato(usuarioId);
            List<TipoDeficiencia> deficiencias = daoTipoDeficiencia.findAllByCandidato(usuarioId);
            Optional<Curriculo> curriculoOpt = daoCurriculo.findByCandidatoId(usuarioId);

            if (curriculoOpt.isEmpty()) {
                response.sendRedirect("curriculo?erro=1");
                return;
            }

            Curriculo curriculo = curriculoOpt.get();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=curriculo.pdf");

            try (OutputStream out = response.getOutputStream()) {
                PDFUtils.generateCurriculoPDF(out, candidato, curriculo, cursos, deficiencias);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?erro=1");
        }
    }
}*/
