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
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/home/curriculo/insert")
public class CadastroCurriculoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Integer usuarioId = usuarioLogado.getId();

        try (DAOFactory factory = new DAOFactory()) {
            DAOCandidato daoCandidato = factory.buildDAOCandidato();
            DAOCurso daoCurso = factory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();
            DAOEndereco daoEndereco = factory.buildDAOEndereco();

            Optional<Candidato> candidatoOpt = daoCandidato.findById(usuarioId);
            if (candidatoOpt.isEmpty()) {
                response.sendRedirect("/index");
                return;
            }


            Candidato candidato = candidatoOpt.get();
            Endereco endereco = daoEndereco.findById(candidato.getEndereco().getId()).get();

            List<Curso> cursos = daoCurso.findAllByCandidato(usuarioId);
            List<TipoDeficiencia> deficiencias = daoTipoDeficiencia.findAllByCandidato(usuarioId);
            Optional<Curriculo> curriculoOpt = daoCurriculo.findByCandidatoId(usuarioId);

            request.setAttribute("candidato", candidato);
            request.setAttribute("endereco", endereco);
            request.setAttribute("cursos", cursos);
            request.setAttribute("deficiencias", deficiencias);
            request.setAttribute("curriculo", curriculoOpt.get());
            request.getRequestDispatcher("/pages/exibircurriculo.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Integer usuarioId = usuarioLogado.getId();

        try (DAOFactory factory = new DAOFactory()) {
            factory.openTransaction();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();

            String objetivo = request.getParameter("objetivo");
            String habilidades = request.getParameter("habilidades");
            String experiencia = request.getParameter("experiencia");

            Curriculo curriculo = new Curriculo();
            curriculo.setCandidato( new Candidato());
            curriculo.getCandidato().setId(usuarioId);
            curriculo.setObjetivo(objetivo);
            curriculo.setHabilidades(habilidades);
            curriculo.setExperiencia(experiencia);

            Optional<Curriculo> existingCurriculo = daoCurriculo.findByCandidatoId(usuarioId);
            if (existingCurriculo.isPresent()) {
                curriculo.setId(existingCurriculo.get().getId());
                daoCurriculo.update(curriculo);
            } else {
                daoCurriculo.insert(curriculo);
            }

            factory.closeTransaction();
            response.sendRedirect(request.getContextPath() + "/curriculo/exibir");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?erro=1");
        }
    }
}
