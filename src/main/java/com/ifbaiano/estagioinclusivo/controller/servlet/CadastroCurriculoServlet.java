package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.*;
import com.ifbaiano.estagioinclusivo.model.*;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {
        "/home/curriculo/insert",
        "/home/curriculo/removerCurso",
        "/home/curriculo/removerDeficiencia"
})
public class CadastroCurriculoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");

        int usuarioId = usuarioLogado.getId();

        try (DAOFactory factory = new DAOFactory()) {
            DAOCandidato daoCandidato = factory.buildDAOCandidato();
            DAOCurso daoCurso = factory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();
            DAOEndereco daoEndereco = factory.buildDAOEndereco();

            Optional<Candidato> candidatoOpt = daoCandidato.findById(usuarioId);
            if (candidatoOpt.isEmpty()) {
                response.sendRedirect("/index");
                return;
            }


            Endereco endereco = daoEndereco.findById(candidatoOpt.get().getEndereco().getId()).orElse(new Endereco());

            List<Curso> cursos = daoCurso.findAllByCandidato(usuarioId);
            List<TipoDeficiencia> deficiencias = daoTipoDeficiencia.findAllByCandidato(usuarioId);
            Curriculo curriculoOpt = daoCurriculo.findByCandidatoId(usuarioId).orElse(new Curriculo());

            request.setAttribute("candidato", candidatoOpt.get());
            request.setAttribute("endereco", endereco);
            request.setAttribute("cursos", cursos);
            request.setAttribute("deficiencias", deficiencias);
            request.setAttribute("curriculo", curriculoOpt);
            request.getRequestDispatcher("/pages/cadastrocurriculo.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO usuarioLogado = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int usuarioId = usuarioLogado.getId();

        try (DAOFactory factory = new DAOFactory()) {
            factory.openTransaction();
            DAOCurriculo daoCurriculo = factory.buildDAOCurriculo();
            DAOCurso daoCurso = factory.buildDAOCurso();
            DAOTipoDeficiencia daoTipoDeficiencia = factory.buildDAOTipoDeficiencia();
            DAOCandidato daoCandidato = factory.buildDAOCandidato();

            String objetivo = request.getParameter("objetivo");
            String habilidades = request.getParameter("habilidades");
            String experiencia = request.getParameter("experiencia");

            Curriculo curriculo = daoCurriculo.findByCandidatoId(usuarioId).orElse(new Curriculo());
            curriculo.setCandidato( new Candidato());
            curriculo.getCandidato().setId(usuarioId);
            curriculo.setObjetivo(objetivo);
            curriculo.setHabilidades(habilidades);
            curriculo.setExperiencia(experiencia);

            if (curriculo.getId() > 0) {
                daoCurriculo.update(curriculo);
            } else {
                daoCurriculo.insert(curriculo);
            }

            String[] cursoNomes = request.getParameterValues("curso_nome[]");
            String[] cursoInstituicoes = request.getParameterValues("curso_instituicao[]");
            String[] cursoDescricoes = request.getParameterValues("curso_descricao[]");
            String[] cursoInicios = request.getParameterValues("curso_inicio[]");
            String[] cursoFins = request.getParameterValues("curso_fim[]");


            if (cursoNomes != null) {
                for (int i = 0; i < cursoNomes.length; i++) {
                    Curso curso = new Curso();
                    curso.setNomeCurso(cursoNomes[i]);
                    curso.setInstituicao(cursoInstituicoes[i]);
                    curso.setDescricao(cursoDescricoes[i]);
                    curso.setDataInicio(java.sql.Date.valueOf(cursoInicios[i]).toLocalDate());
                    curso.setDataFim(java.sql.Date.valueOf(cursoFins[i]).toLocalDate());
                    curso.setCandidato(new Candidato());
                    curso.getCandidato().setId(usuarioId);
                    daoCurso.insert(curso);
                }
            }
            String[] defIds = request.getParameterValues("def_id[]");
            String[] defNome = request.getParameterValues("def_nome[]");
            String[] defDescricao = request.getParameterValues("def_descricao[]");
            String[] defTipo = request.getParameterValues("def_tipo[]");
            String[] defApoio = request.getParameterValues("def_apoio[]");

            if (defNome != null) {
                for (int i = 0; i < defNome.length; i++) {
                    TipoDeficiencia def = new TipoDeficiencia();
                    if (defIds != null && !defIds[i].isEmpty()) {
                        def.setId(Integer.parseInt(defIds[i]));
                    }
                    def.setNome(defNome[i]);
                    def.setDescricao(defDescricao[i]);
                    def.setTipo(TipoDeficienciaEnum.valueOf(defTipo[i]));
                    def.setTipoApoio(defApoio[i]);
                    def.setCandidato(new Candidato());
                    def.getCandidato().setId(usuarioId);

                    if (def.getId() > 0) {
                        daoTipoDeficiencia.update(def);
                    } else {
                        daoTipoDeficiencia.insert(def);
                    }
                }
            }

            factory.closeTransaction();
            response.sendRedirect(request.getContextPath() + "/home/curriculo/insert");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/home/curriculo/insert?erro=1");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory factory = new DAOFactory()) {
            String servletPath = request.getServletPath();

            if (servletPath.endsWith("/removerCurso")) {
                int id = Integer.parseInt(request.getParameter("id"));
                factory.buildDAOCurso().delete(id);
            } else if (servletPath.endsWith("/removerDeficiencia")) {
                int id = Integer.parseInt(request.getParameter("id"));
                factory.buildDAOTipoDeficiencia().delete(id);
            }

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
