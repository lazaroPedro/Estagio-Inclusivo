package com.ifbaiano.estagioinclusivo.controller.servlet;

import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.LoginDTO;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/home/usuario/put")
public class UsuarioPutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDTO user = (SessionDTO) req.getSession().getAttribute("usuarioLogado");

        try (DAOFactory daoFactory = new DAOFactory()) {
            DAOUsuario dU = daoFactory.buildDAOUsuario();

            LoginDTO login = new LoginDTO();

            login.setEmail(req.getParameter("email"));
            String antigaSenha = req.getParameter("senha");
            String novaSenha = req.getParameter("novaSenha");

            try {
                login.setSenha(antigaSenha);
                Validator.validate(login);
                login.setSenha(novaSenha);
                Validator.validate(login);


            } catch (ValidationException e) {
                req.setAttribute("erros", e.getErrors());
            }
            Optional<Usuario> u = dU.findById(user.getId());
            if (!SenhaUtils.verificarSenha(antigaSenha, u.get().getSalt(), u.get().getHashSenha())) {
                req.setAttribute("erro", "Senha digitada esta incorreta");
                req.getRequestDispatcher("/pages/perfil.jsp").forward(req, resp);
            }
            u.get().setEmail(login.getEmail());
            u.get().setSalt(SenhaUtils.gerarSalt());
            u.get().setHashSenha(SenhaUtils.gerarHashSenha(novaSenha, u.get().getSalt()));
            dU.update(u.get());
            req.setAttribute("sucesso", true);
            req.getRequestDispatcher("/pages/perfil.jsp").forward(req, resp);
        }


    }
}
