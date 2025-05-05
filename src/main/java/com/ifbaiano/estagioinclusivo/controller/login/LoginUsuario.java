package com.ifbaiano.estagioinclusivo.controller.login;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import  com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
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
public class LoginUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senhaDigitada = req.getParameter("senha");
        try(DAOFactory factory = new DAOFactory()) {

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
                    resp.sendRedirect("index.jsp");
                    return;
                }
            }

            resp.sendRedirect("pages/login.jsp?erro=1");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno no login: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

}
