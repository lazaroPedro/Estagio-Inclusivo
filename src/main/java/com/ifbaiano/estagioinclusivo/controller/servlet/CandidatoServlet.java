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
/**
 *
 *
 *
 * /home/* restringe login
 * /home/candidato/ getall
 * /home/candidato/id/[perfil]
 * /home/candidato/vaga
 * /home/candidato/insert
 * /home/candidato/update/id/
 * /home/candidato/delete/id/
 */

@WebServlet("/candidato/*")
public class CandidatoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String h = request.getParameter("method");
        if (h != null) {
                if(h.equalsIgnoreCase("put_")) {
            doPut(request, response);}

        } else{



        if (request.getParameter("aceitaTermos") == null) {
            request.setAttribute("erro", "É necessário aceitar os termos de uso.");
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            return;
        }


        try (DAOFactory factory = new DAOFactory()) {
            factory.openTransaction();
            try {

                DAOEndereco daoEndereco = factory.buildDAOEndereco();
                DAOCandidato daoCandidato = factory.buildDAOCandidato();
                DAOCurso daoCurso = factory.buildDAOCurso();
                DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();

        String cep = request.getParameter("cep").replaceAll("[^\\d]", "");
        String telefone = request.getParameter("telefone").replaceAll("[^\\d]", "");
        String cpf = request.getParameter("cpf").replaceAll("[^\\d]", "");


        Endereco endereco = new Endereco();
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setMunicipio(request.getParameter("municipio"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCep(cep);

        Validator.validate(endereco);

        Integer idEndereco = daoEndereco.insert(endereco).orElseThrow(()->new RuntimeException("Não foi possivel cadastrar o endereço"));

        endereco.setId(idEndereco);

        String salt = SenhaUtils.gerarSalt();
        String hash = SenhaUtils.gerarHashSenha(request.getParameter("password"), salt);

        Candidato candidato = new Candidato();
        candidato.setNome(request.getParameter("nome"));
        candidato.setEmail(request.getParameter("email"));
        candidato.setHashSenha(hash);
        candidato.setSalt(salt);
        candidato.setEndereco(endereco);
        candidato.setTelefone(telefone);
        candidato.setPapel(TipoUsuario.CANDIDATO);
        candidato.setCpf(cpf);
        candidato.setGenero(Genero.valueOf(request.getParameter("genero")));
        candidato.setDataNascimento(LocalDate.parse(request.getParameter("nascimento")));
                Validator.validate(candidato);

                Integer idCandidato = daoCandidato.insert(candidato).orElseThrow(() -> new RuntimeException("Erro ao inserir candidato."));
                candidato.setId(idCandidato);

                TipoDeficiencia tipoDeficiencia = new TipoDeficiencia();
                tipoDeficiencia.setNome(request.getParameter("def_nome"));
                tipoDeficiencia.setCandidato(candidato);
                tipoDeficiencia.setTipo(TipoDeficienciaEnum.valueOf(request.getParameter("def_tipo")));
                tipoDeficiencia.setDescricao(request.getParameter("def_descricao"));
                tipoDeficiencia.setTipoApoio(request.getParameter("def_apoio"));

                Validator.validate(tipoDeficiencia);

                daoTipoDeficiencia.insert(tipoDeficiencia).orElseThrow(() -> new RuntimeException("Erro ao cadastrar deficiência"));

                String nomeCurso = request.getParameter("curso_nome");
                if (nomeCurso != null && !nomeCurso.trim().isEmpty()) {
                    Curso curso = new Curso();
                    curso.setNomeCurso(nomeCurso);
                    curso.setInstituicao(request.getParameter("curso_instituicao"));
                    curso.setDescricao(request.getParameter("curso_descricao"));
                    curso.setDataInicio(LocalDate.parse(request.getParameter("curso_inicio")));
                    curso.setDataFim(LocalDate.parse(request.getParameter("curso_fim")));
                    curso.setCandidato(candidato);

                    Validator.validate(curso);

                    daoCurso.insert(curso);
                }
                factory.closeTransaction();
                response.sendRedirect("pages/login.jsp?sucesso=1");
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
    }}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(DAOFactory daoFactory = new DAOFactory()){
            DAOCandidato dV = daoFactory.buildDAOCandidato();
            SessionDTO d = (SessionDTO) req.getSession().getAttribute("user");
            dV.findById(d.getId()).ifPresent(candidato -> {
                DAOEndereco dE  = daoFactory.buildDAOEndereco();
                dE.findById(candidato.getEndereco().getId()).ifPresent(candidato::setEndereco);
                req.setAttribute("candidato", candidato);
            });

    }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOCandidato dV = daoFactory.buildDAOCandidato();
            SessionDTO d = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
            Candidato candidato = new Candidato();

            candidato.setPapel(TipoUsuario.CANDIDATO);


            dV.findById(d.getId()).ifPresent(candid -> {
                candidato.setId(candid.getId());
                candidato.setSalt(candid.getSalt());
                candidato.setHashSenha(candid.getHashSenha());
                candidato.setEndereco(candid.getEndereco());
                candidato.setEmail(candid.getEmail());

                Optional.ofNullable(req.getParameter("nome")).ifPresentOrElse(candidato::setNome, () -> candidato.setNome(candid.getNome()));
                Optional.ofNullable(req.getParameter("cpf")).ifPresentOrElse(candidato::setCpf, () -> candidato.setCpf(candid.getCpf()));
                Optional.ofNullable(req.getParameter("genero")).ifPresentOrElse(can -> candidato.setGenero(Genero.valueOf(can)), () -> candidato.setGenero(candid.getGenero()));
                Optional.ofNullable(req.getParameter("nascimento")).ifPresentOrElse(can -> candidato.setDataNascimento(LocalDate.parse(can)), () -> candidato.setDataNascimento(candid.getDataNascimento()));
                Optional.ofNullable(req.getParameter("telefone")).ifPresentOrElse(candidato::setTelefone, () -> candidato.setTelefone(candid.getTelefone()));


                try {
                    Validator.validate(candidato);
                } catch (ValidationException e) {
                    req.setAttribute("errosValidacao", e.getErrors());
                }
                dV.update(candidato);
                req.setAttribute("alterado", true);


            });
            req.getRequestDispatcher("/pages/perfil.jsp").forward(req, resp);

        }
    }
}



