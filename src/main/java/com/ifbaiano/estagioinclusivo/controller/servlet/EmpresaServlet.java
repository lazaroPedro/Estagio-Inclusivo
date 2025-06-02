package com.ifbaiano.estagioinclusivo.controller.servlet;
import com.ifbaiano.estagioinclusivo.dao.DAOEndereco;
import com.ifbaiano.estagioinclusivo.dao.DAOEmpresa;
import com.ifbaiano.estagioinclusivo.dao.DAOFactory;
import com.ifbaiano.estagioinclusivo.dao.DAOUsuario;
import com.ifbaiano.estagioinclusivo.model.Empresa;
import com.ifbaiano.estagioinclusivo.model.Endereco;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/empresa/insert")
public class EmpresaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("aceitaTermos") == null) {
            request.setAttribute("erro", "É necessário aceitar os termos de uso.");
            request.getRequestDispatcher("/pages/cadastroempresa.jsp").forward(request, response);
            return;
        }

        try (DAOFactory factory = new DAOFactory()) {
            try {
            factory.openTransaction();
                DAOEmpresa daoEmpresa = factory.buildDAOEmpresa();

                String telefone = request.getParameter("telefone").replaceAll("[^\\d]", "");
                String cnpj = request.getParameter("cnpj").replaceAll("[^\\d]", "");
                String email = request.getParameter("email");

                if (daoEmpresa.emailJaExiste(email)) {
                    request.setAttribute("erro", "O e-mail já está cadastrado.");
                    request.getRequestDispatcher("/pages/cadastroempresa.jsp").forward(request, response);
                    return;
                }

                if (daoEmpresa.cnpjJaExiste(cnpj)) {
                    request.setAttribute("erro", "O CNPJ já está cadastrado.");
                    request.getRequestDispatcher("/pages/cadastroempresa.jsp").forward(request, response);
                    return;
                }


                String salt = SenhaUtils.gerarSalt();
                String hash = SenhaUtils.gerarHashSenha(request.getParameter("password"), salt);

                Empresa empresa = new Empresa();
                empresa.setNome(request.getParameter("nome"));
                empresa.setRazaoSocial(request.getParameter("razaoSocial"));
                empresa.setCnpj(cnpj);
                empresa.setEmail(email);
                empresa.setHashSenha(hash);
                empresa.setSalt(salt);
                empresa.setTelefone(telefone);
                empresa.setEndereco(null);
                empresa.setPapel(TipoUsuario.EMPRESA);

                Validator.validate(empresa);
                Integer idEmpresa = daoEmpresa.insert(empresa).orElseThrow(() -> new RuntimeException("Erro ao inserir empresa."));
                empresa.setId(idEmpresa);

                factory.closeTransaction();
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp");

            } catch (ValidationException ve) {
                try {
                    factory.rollbackTransaction();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                request.setAttribute("errosValidacao", ve.getErrors());
                request.getRequestDispatcher("/pages/cadastroempresa.jsp").forward(request, response);
            } catch (Exception e) {
                try {
                    factory.rollbackTransaction();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                throw new ServletException("Erro ao cadastrar empresa", e);
            }
        }
    }
}
