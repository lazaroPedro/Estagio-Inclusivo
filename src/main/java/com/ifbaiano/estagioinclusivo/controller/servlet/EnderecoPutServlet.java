package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/home/endereco/put")
public class EnderecoPutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");
        try(DAOFactory daoFactory = new DAOFactory()){
            DAOEndereco dE = daoFactory.buildDAOEndereco();
            DAOUsuario dU = daoFactory.buildDAOUsuario();
            Endereco endereco = new Endereco();
            endereco.setRua(req.getParameter("rua"));
            endereco.setBairro(req.getParameter("bairro"));
            endereco.setEstado(req.getParameter("estado"));
            endereco.setMunicipio(req.getParameter("municipio"));
            endereco.setCep(req.getParameter("cep").replaceAll("\\D", ""));
            Optional<Usuario> u = dU.findById(user.getId());
                if(u.isEmpty()){
                    req.setAttribute("erros", "Falha no cadastro");
                    if (user.getTipoUsuario() == TipoUsuario.CANDIDATO) {
                        req.getRequestDispatcher("/home/candidato/full").forward(req, resp);
                    } else if (user.getTipoUsuario() == TipoUsuario.EMPRESA){
                        req.getRequestDispatcher("/home/empresa/full").forward(req, resp);
                    }

                } else if (u.get().getEndereco() == null){

                    try {
                Validator.validate(endereco);

                dE.insert(endereco);
                req.setAttribute("sucesso", true);
            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }


                } else {


            endereco.setId(u.get().getEndereco().getId());



            try {
                Validator.validate(endereco);

                dE.update(endereco);
                req.setAttribute("sucesso", true);
            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }}
            if (user.getTipoUsuario() == TipoUsuario.CANDIDATO) {
                req.getRequestDispatcher("/home/candidato/full").forward(req, resp);
            } else if (user.getTipoUsuario() == TipoUsuario.EMPRESA){
                req.getRequestDispatcher("/home/empresa/full").forward(req, resp);
            }
        }
    }
}
