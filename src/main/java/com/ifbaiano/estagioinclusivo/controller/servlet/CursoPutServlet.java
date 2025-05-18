package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOCurso;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

/**/
@WebServlet("/home/curso/put")
public class CursoPutServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");

        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOCurso dC = daoFactory.buildDAOCurso();

            Curso curso = new Curso();
            curso.setId(Integer.parseInt(req.getParameter("id")));
            curso.setNomeCurso(req.getParameter("nome"));
            curso.setDescricao(req.getParameter("descricao"));
            curso.setInstituicao(req.getParameter("instituicao"));
            curso.setDataInicio(LocalDate.parse(req.getParameter("dataInicio")));
            curso.setDataFim(LocalDate.parse(req.getParameter("dataFim")));
            curso.setCandidato(new Candidato());
            curso.getCandidato().setId(user.getId());



            try {
                Validator.validate(curso);
                dC.update(curso);
                req.setAttribute("sucesso", true);

            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }
            req.getRequestDispatcher("/home/candidato/full").forward(req, resp);

        }
    }


}
