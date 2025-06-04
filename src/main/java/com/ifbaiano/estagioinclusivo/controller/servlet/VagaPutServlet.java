package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOVaga;
import com.ifbaiano.estagioinclusivo.model.Vaga;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/vaga/put")
public class VagaPutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()) {
            DAOVaga dV = daoFactory.buildDAOVaga();
            Vaga vaga = new Vaga();
            vaga.setId(Integer.parseInt(req.getParameter("id")));
            vaga.setTitulo(req.getParameter("titulo"));
            vaga.setDescricao(req.getParameter("descricao"));
            vaga.setRequisitos(req.getParameter("requisitos"));
            vaga.setBeneficios(req.getParameter("beneficios"));
            vaga.setQtdVagas(Long.valueOf(req.getParameter("qtdVagas")));
            dV.findByIdEmpresa(user.getId()).forEach(vg ->{
                if(vg.getEmpresa().getId() == user.getId()) {
                    vaga.setEmpresa(vg.getEmpresa());
                    vaga.setEndereco(vg.getEndereco());
                    vaga.setStatus(vg.getStatus());
                }

            });
            try {
                Validator.validate(vaga);
                dV.update(vaga);
                req.setAttribute("sucesso", true);
            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }
            req.getRequestDispatcher("/home/empresa/full").forward(req, resp);
        }
    }


}
