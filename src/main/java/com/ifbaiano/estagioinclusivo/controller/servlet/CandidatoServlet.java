package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOCurso;
import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
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

      try(Connection conexao = DBConfig.criarConexao()){
          conexao.setAutoCommit(false);
        Endereco endereco = new Endereco();
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setMunicipio(request.getParameter("municipio"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCep(request.getParameter("cep"));

        DAOEndereco daoEndereco = new DAOEndereco(conexao);
        Optional<Integer> idEndereco = daoEndereco.insert(endereco);

        if (idEndereco.isEmpty()) {
            throw new RuntimeException("Não foi possível cadastrar o endereço.");
        }

        endereco.setId(idEndereco.get());

        String salt = SenhaUtils.gerarSalt();
        String hash = SenhaUtils.gerarHashSenha(request.getParameter("password"), salt);

        Candidato candidato = new Candidato();
        candidato.setNome(request.getParameter("nome"));
        candidato.setEmail(request.getParameter("email"));
        candidato.setHashSenha(hash);
        candidato.setSalt(salt);
        candidato.setEndereco(endereco);
        candidato.setTelefone(request.getParameter("telefone"));
        candidato.setPapel(TipoUsuario.CANDIDATO);
        candidato.setCpf(request.getParameter("cpf"));
        candidato.setGenero(Genero.valueOf(request.getParameter("genero")));
        candidato.setDataNascimento(LocalDate.parse(request.getParameter("nascimento")));

        DAOCandidato daoCandidato = new DAOCandidato(conexao);
        Optional<Integer> idCandidato = daoCandidato.insert(candidato);

        if (idCandidato.isPresent()) {
            candidato.setId(idCandidato.get());
        } else {
            throw new RuntimeException("Erro ao cadastrar o candidato.");
        }

        String nomeCurso = request.getParameter("curso_nome");
        if (nomeCurso != null && !nomeCurso.trim().isEmpty()) {
            Curso curso = new Curso();
            curso.setNomeCurso(nomeCurso);
            curso.setInstituicao(request.getParameter("curso_instituicao"));
            curso.setDescricao(request.getParameter("curso_descricao"));
            curso.setDataInicio(LocalDate.parse(request.getParameter("curso_inicio")));
            curso.setDataFim(LocalDate.parse(request.getParameter("curso_fim")));
            curso.setCandidato(candidato);

            DAOCurso daoCurso = new DAOCurso(conexao);
            daoCurso.insert(curso);
        }
          conexao.commit();
        response.sendRedirect("pages/login.jsp?sucesso=1");

    } catch(Exception e){
        e.printStackTrace();
        try {
            DBConfig.criarConexao().rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("erro", "Erro ao realizar o cadastro: " + e.getMessage());
        request.getRequestDispatcher("/pages/cadastrocandidato.jsp").forward(request, response);
    }
}

}

