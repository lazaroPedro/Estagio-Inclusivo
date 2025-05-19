package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOCandidato;
import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Empresa;
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
import java.util.Optional;

@WebServlet("/home/empresa/put")
public class EmpresaPutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOEmpresa dE = daoFactory.buildDAOEmpresa();
            Empresa empresa = new Empresa();
            empresa.setPapel(TipoUsuario.EMPRESA);
            empresa.setNome(req.getParameter("nome"));
            empresa.setRazaoSocial(req.getParameter("razaoSocial"));
            empresa.setTelefone(req.getParameter("telefone"));
            empresa.setCnpj(req.getParameter("cnpj"));

            dE.findById(user.getId()).ifPresent(candid -> {
                empresa.setId(candid.getId());
                empresa.setSalt(candid.getSalt());
                empresa.setHashSenha(candid.getHashSenha());
                empresa.setEndereco(candid.getEndereco());
                empresa.setEmail(candid.getEmail());
            });
                try {
                    Validator.validate(empresa);
                    dE.update(empresa);
                    req.setAttribute("sucesso", true);
                } catch (ValidationException e) {
                    req.setAttribute("erros", e.getErrors());
                }





            req.getRequestDispatcher("/home/empresa/full").forward(req, resp);

        }


    }

}
