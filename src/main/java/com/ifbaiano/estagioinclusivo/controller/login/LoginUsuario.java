package com.ifbaiano.estagioinclusivo.controller.login;

import com.ifbaiano.estagioinclusivo.config.DBConfig;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import  com.ifbaiano.estagioinclusivo.model.Usuario;
import com.ifbaiano.estagioinclusivo.model.dto.SessionDTO;
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

        DAOUsuario dao = new DAOUsuario(DBConfig.criarConexao());
        Usuario u;
                SessionDTO s = new SessionDTO();
                req.getSession().setAttribute("user", s);
                resp.sendRedirect(req.getContextPath() + "/home");




    }
}
