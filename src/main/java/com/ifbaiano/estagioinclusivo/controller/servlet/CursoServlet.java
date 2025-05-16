package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCurso;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**/
@WebServlet("/home/curso")
public class CursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOCurso dC = daoFactory.buildDAOCurso();
            List<Curso> cursos = dC.findAllByCandidato(user.getId());
            req.setAttribute("cursos", cursos);
            resp.sendRedirect(req.getContextPath() + "/pages/perfil.jsp");
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
            dao.insert(curso);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOCurso dao = new DAOCurso(DBConfig.criarConexao());

        resp.sendRedirect(req.getContextPath() + "/home");

    }
}
