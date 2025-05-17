package com.ifbaiano.estagioinclusivo.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter("/home/*")
public class AuthFilter implements Filter {
    protected FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute ("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);


        }


    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
