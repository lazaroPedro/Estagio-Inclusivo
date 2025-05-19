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
                DAOEndereco daoEndereco = factory.buildDAOEndereco();
                DAOEmpresa daoEmpresa = factory.buildDAOEmpresa();
                DAOUsuario daoUsuario = factory.buildDAOUsuario();

                String cep = request.getParameter("cep").replaceAll("[^\\d]", "");
                String telefone = request.getParameter("telefone").replaceAll("[^\\d]", "");
                String cnpj = request.getParameter("cnpj").replaceAll("[^\\d]", "");

                Endereco endereco = new Endereco();
                endereco.setRua(request.getParameter("rua"));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setMunicipio(request.getParameter("municipio"));
                endereco.setEstado(request.getParameter("estado"));
                endereco.setCep(cep);

                Integer idEndereco = daoEndereco.insert(endereco)
                        .orElseThrow(() -> new RuntimeException("Não foi possível cadastrar o endereço"));

                endereco.setId(idEndereco);

                String email = request.getParameter("email");

       
                if (daoUsuario.findByEmail(email).isPresent()) {
                    request.setAttribute("erro", "Este e-mail já está cadastrado.");
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
                empresa.setEndereco(endereco);
                empresa.setPapel(TipoUsuario.EMPRESA);

                Validator.validate(empresa);

                daoEmpresa.insert(empresa);

                response.sendRedirect(request.getContextPath() +"/index");

            } catch (ValidationException ve) {
                request.setAttribute("errosValidacao", ve.getErrors());
                request.getRequestDispatcher("/pages/cadastroempresa.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Erro ao cadastrar empresa", e);
            }
        }
    }
}
