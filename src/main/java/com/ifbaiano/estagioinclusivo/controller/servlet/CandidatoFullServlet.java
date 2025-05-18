package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.LoginDTO;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/home/candidato/full")
public class CandidatoFullServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) request.getSession().getAttribute("usuarioLogado");

        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidato dCand = daoFactory.buildDAOCandidato();
            DAOCurso dCurso = daoFactory.buildDAOCurso();
            DAOTipoDeficiencia dDef = daoFactory.buildDAOTipoDeficiencia();
            DAOEndereco dEnd = daoFactory.buildDAOEndereco();
            List<Curso> lC = dCurso.findAllByCandidato(user.getId());
            List<TipoDeficiencia> lT = dDef.findAllByCandidato(user.getId());
            Optional<Candidato> c = dCand.findById(user.getId());

            c.ifPresent(cand ->{
                cand.setSalt(null);
                cand.setHashSenha(null);
                 dEnd.findById(cand.getEndereco().getId()).ifPresent(cand :: setEndereco);
                 request.setAttribute("candidato", cand);
             });
            request.setAttribute("cursos", lC);
            request.setAttribute("deficiencias", lT);


            request.getRequestDispatcher("/pages/perfil.jsp").forward(request, response);

        }
    }



}
