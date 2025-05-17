package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOTipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.TipoDeficiencia;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/deficiencia/post")
public class DeficienciaPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOTipoDeficiencia dTD = daoFactory.buildDAOTipoDeficiencia();

            TipoDeficiencia deficiencia = new TipoDeficiencia();
            deficiencia.setNome(req.getParameter("nome"));
            deficiencia.setDescricao(req.getParameter("descricao"));
            deficiencia.setTipoApoio(req.getParameter("tipoApoio"));
            deficiencia.setCandidato(new Candidato());
            deficiencia.getCandidato().setId(user.getId());
            deficiencia.setTipo(TipoDeficienciaEnum.valueOf(req.getParameter("tipoDeficiencia")));

            try {
                Validator.validate(deficiencia);
                dTD.insert(deficiencia);
                req.setAttribute("sucesso", true);

            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }
            req.getRequestDispatcher("/pages/perfil.jsp").forward(req, resp);

        }

    }

}
