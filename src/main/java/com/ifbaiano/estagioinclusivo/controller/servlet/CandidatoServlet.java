package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/cadastrocandidato")
public class CandidatoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("aceitaTermos") == null) {
            request.setAttribute("erro", "É necessário aceitar os termos de uso.");
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
            return;
        }


      try(DAOFactory factory = new DAOFactory()){
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

        Validator.validar(endereco);

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

        Validator.validar(candidato);

        Integer idCandidato = daoCandidato.insert(candidato).orElseThrow(() -> new RuntimeException("Erro ao inserir candidato."));
          candidato.setId(idCandidato);

        TipoDeficiencia tipoDeficiencia = new TipoDeficiencia();
        tipoDeficiencia.setNome(request.getParameter("def_nome"));
        tipoDeficiencia.setCandidato(candidato);
        tipoDeficiencia.setTipo(TipoDeficienciaEnum.valueOf(request.getParameter("def_tipo")));
        tipoDeficiencia.setDescricao(request.getParameter("def_descricao"));
        tipoDeficiencia.setTipoApoio(request.getParameter("def_apoio"));

        Validator.validar(tipoDeficiencia);

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

            Validator.validar(curso);

            daoCurso.insert(curso);
        }
        factory.closeTransaction();
        response.sendRedirect("pages/login.jsp?sucesso=1");
        } catch(ValidationException ve){
            try {
            factory.rollbackTransaction();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            request.setAttribute("errosValidacao", ve.getErroCampos());
            request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
        } catch (Exception e) {
            try {
                factory.rollbackTransaction();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            throw new ServletException("Erro ao cadastrar candidato", e);
            }
        } catch (SQLException e) {
          throw new RuntimeException(e);
      }

    }
    }




