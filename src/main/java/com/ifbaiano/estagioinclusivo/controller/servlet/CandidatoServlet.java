package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;


import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@WebServlet("/candidato/insert")
public class CandidatoServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (request.getParameter("aceitaTermos") == null) {
            request.setAttribute("erro", "É necessário aceitar os termos de uso.");
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            return;
        }


        try (DAOFactory factory = new DAOFactory()) {
            factory.openTransaction();
            try {
                DAOCandidato daoCandidato = factory.buildDAOCandidato();

        String telefone = request.getParameter("telefone").replaceAll("[^\\d]", "");
        String cpf = request.getParameter("cpf").replaceAll("[^\\d]", "");
        String email = request.getParameter("email");

        if (daoCandidato.emailJaExiste(email)) {
            request.setAttribute("erro", "O e-mail já está cadastrado.");
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            return;
        }

        if (daoCandidato.cpfJaExiste(cpf)) {
            request.setAttribute("erro", "O CPF já está cadastrado.");
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            return;
        }

        String salt = SenhaUtils.gerarSalt();
        String hash = SenhaUtils.gerarHashSenha(request.getParameter("password"), salt);

        Candidato candidato = new Candidato();
        candidato.setNome(request.getParameter("nome"));
        candidato.setEmail(email);
        candidato.setHashSenha(hash);
        candidato.setSalt(salt);
        candidato.setEndereco(null);
        candidato.setTelefone(telefone);
        candidato.setPapel(TipoUsuario.CANDIDATO);
        candidato.setCpf(cpf);
        candidato.setGenero(Genero.valueOf(request.getParameter("genero")));
        candidato.setDataNascimento(LocalDate.parse(request.getParameter("nascimento")));
        Validator.validate(candidato);

        Integer idCandidato = daoCandidato.insert(candidato).orElseThrow(() -> new RuntimeException("Erro ao inserir candidato."));
        candidato.setId(idCandidato);

        factory.closeTransaction();
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            } catch (ValidationException ve) {
                try {
                    factory.rollbackTransaction();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("errosValidacao", ve.getErrors());
                request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            } catch (Exception e) {
                try {
                    factory.rollbackTransaction();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                throw new ServletException("Erro ao cadastrar candidato", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



