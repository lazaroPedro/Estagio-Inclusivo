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
import java.time.format.DateTimeFormatter;
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
                 cand.getEndereco().setCep(cand.getEndereco().getCep().substring(0, 5) + "-" + cand.getEndereco().getCep().substring(5));

                cand.setCpf(
                        cand.getCpf().substring(0, 3) + "." +
                                cand.getCpf().substring(3, 6) + "." +
                                cand.getCpf().substring(6, 9) + "-" +
                                cand.getCpf().substring(9, 11)
                );
                if (cand.getTelefone() != null && cand.getTelefone().length() == 11) {
                    cand.setTelefone(
                            "(" + cand.getTelefone().substring(0, 2) + ") " +
                                    cand.getTelefone().substring(2, 7) + "-" +
                                    cand.getTelefone().substring(7, 11)
                    );
                }
                 request.setAttribute("candidato", cand);
             });
            request.setAttribute("cursos", lC);
            request.setAttribute("deficiencias", lT);


            request.getRequestDispatcher("/pages/configcandidato.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
