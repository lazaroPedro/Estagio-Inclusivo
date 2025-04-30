package com.ifbaiano.estagioinclusivo.controller.login;

import com.ifbaiano.estagioinclusivo.config.DAOConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import  com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        DAOUsuario dao = new DAOUsuario(DAOConfig.criarConexao());
        Usuario u;
        try {
            u = dao.findByEmail(email);
            if(u.getHashSenha().equals(senha)){
                SessionDTO s = new SessionDTO();
                s.setId(u.getId());
                req.getSession().setAttribute("user", s);
                resp.sendRedirect(req.getContextPath() + "/");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
