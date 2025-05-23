package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
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

@WebServlet("/home/candidato/put")
public class CandidatoPutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidato dV = daoFactory.buildDAOCandidato();
            Candidato candidato = new Candidato();

            candidato.setPapel(TipoUsuario.CANDIDATO);
            candidato.setNome(req.getParameter("nome"));
            candidato.setCpf(req.getParameter("cpf").replaceAll("\\D", ""));
            candidato.setGenero(Genero.valueOf(req.getParameter("genero").toUpperCase()));
            candidato.setTelefone(req.getParameter("telefone").replaceAll("\\D", ""));
            candidato.setDataNascimento(LocalDate.parse(req.getParameter("nascimento")));
            dV.findById(user.getId()).ifPresent(candid -> {
                candidato.setId(candid.getId());
                candidato.setSalt(candid.getSalt());
                candidato.setHashSenha(candid.getHashSenha());
                candidato.setEndereco(candid.getEndereco());
                candidato.setEmail(candid.getEmail());

                Optional.ofNullable(req.getParameter("nome")).ifPresentOrElse(candidato::setNome, () -> candidato.setNome(candid.getNome()));
                Optional.of(req.getParameter("cpf").replaceAll("\\D", "")).ifPresentOrElse(candidato::setCpf, () -> candidato.setCpf(candid.getCpf()));
                Optional.ofNullable(req.getParameter("genero")).ifPresentOrElse(can -> candidato.setGenero(Genero.valueOf(can)), () -> candidato.setGenero(candid.getGenero()));
                Optional.ofNullable(req.getParameter("nascimento")).ifPresentOrElse(can -> candidato.setDataNascimento(LocalDate.parse(can)), () -> candidato.setDataNascimento(candid.getDataNascimento()));
                Optional.of(req.getParameter("telefone").replaceAll("\\D", "")).ifPresentOrElse(candidato::setTelefone, () -> candidato.setTelefone(candid.getTelefone()));


                try {
                    Validator.validate(candidato);
                    dV.update(candidato);
                    req.setAttribute("sucesso", true);
                } catch (ValidationException e) {
                    req.setAttribute("erros", e.getErrors());
                }



            });
            req.getRequestDispatcher("/home/candidato/full").forward(req, resp);

        }


    }

}
