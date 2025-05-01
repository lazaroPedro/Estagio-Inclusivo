package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCurso;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

/*Pode receber um idCandidato ou um idCurso*/

public class CursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCandidato = Integer.parseInt(req.getParameter("idCandidato"));
        Integer idCurso = Long.parseLong(req.getParameter("idCurso"));
        DAOCurso dao = new DAOCurso(DBConfig.criarConexao());

        if(idCandidato != null) {

            req.setAttribute("listaCursos", dao.findByFkCandidato(idCandidato));
        } else if(idCurso != null) {
            Curso  curso = dao.findById(idCurso);

            req.setAttribute("nomeCurso", curso.getNomeCurso());
            req.setAttribute("descricaoCurso", curso.getDescricao());
            req.setAttribute("instituicao", curso.getInstituicao());
            req.setAttribute("dataInicio", curso.getDataInicio());
            req.setAttribute("dataFim", curso.getDataFim());



        }





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCandidato = Integer.parseInt(req.getParameter("idCandidato"));

        DAOCurso dao = new DAOCurso(DBConfig.criarConexao());

        Curso curso = new Curso();
        curso.setNomeCurso(req.getParameter("nomeCurso"));
        curso.setDescricao(req.getParameter("descricao"));
        curso.setInstituicao(req.getParameter("instituicao"));
        curso.setDataInicio(LocalDate.parse(req.getParameter("dataInicio")));
        curso.setDataFim(LocalDate.parse(req.getParameter("dataFim")));

        Candidato c = new Candidato();
        c.setId(idCandidato);
        curso.setCandidato(c);
        try {
            curso.validar();
            dao.insert(curso);
        }catch(ValidationException e) {
            req.setAttribute("erro", e.getErroCampos());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCurso = Long.parseLong(req.getParameter("idCurso"));

        DAOCurso dao = new DAOCurso(DBConfig.criarConexao());

        dao.delete(idCurso);
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
