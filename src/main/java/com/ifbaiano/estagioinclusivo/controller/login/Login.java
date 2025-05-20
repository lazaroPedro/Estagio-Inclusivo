package com.ifbaiano.estagioinclusivo.controller.login;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import  com.ifbaiano.estagioinclusivo.model.Usuario;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;


@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senhaDigitada = req.getParameter("senha");

        LoginDTO login = new LoginDTO();
        login.setEmail(email);
        login.setSenha(senhaDigitada);
        try(DAOFactory factory = new DAOFactory()) {
            Validator.validate(login);

            DAOUsuario dao = factory.buildDAOUsuario();
            Optional<Usuario> optionalUsuario = dao.findByEmail(email);
            if (optionalUsuario.isPresent()) {
                Usuario u = optionalUsuario.get();
                boolean senhaValida = SenhaUtils.verificarSenha(
                        senhaDigitada,
                        u.getSalt(),
                        u.getHashSenha()
                );
                if (senhaValida) {

                    SessionDTO sessionDTO = new SessionDTO(
                            u.getId(),
                            u.getNome(),
                            u.getPapel()
                    );

                    HttpSession session = req.getSession();
                    session.setAttribute("usuarioLogado", sessionDTO);
                    resp.sendRedirect(req.getContextPath() +"/home");
                    return;
                }
            }
            req.setAttribute("erro", "Email ou senha inválidos.");
            resp.sendRedirect("pages/login.jsp?erro=1");
            /*req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);*/

        } catch (ValidationException ve) {
            req.setAttribute("errosValidacao", ve.getErrors());
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("erro", "Erro interno no login: " + e.getMessage());
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }



}
