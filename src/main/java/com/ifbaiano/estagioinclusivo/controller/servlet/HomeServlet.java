package com.ifbaiano.estagioinclusivo.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute ("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        }else {

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
