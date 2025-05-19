<%@ page import="com.ifbaiano.estagioinclusivo.utils.validation.ListErrors" %>
<%@ page import="com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cadastro de Empresa</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>

<h2 class="mb-4">Cadastro de Empresa</h2>

<%
    String erroEmail = (String) request.getAttribute("erro");
    if (erroEmail != null && !erroEmail.isEmpty()) {
%>
    <div class="alert alert-danger">
        <%= erroEmail %>
    </div>
<%
    }

    ListErrors erros =
            (ListErrors) request.getAttribute("errosValidacao");

    if (erros != null && !erros.isEmpty()) {
%>
<div class="alert alert-danger">
    <ul>
        <% for (ErroCampo erro : erros.getErroCampos()) { %>
            <li><strong><%= erro.getNomeCampo() %>:</strong> <%= erro.getMensagemErro() %></li>
        <% } %>
    </ul>
</div>
<%
    }
%>

<form action="${pageContext.request.contextPath}/empresa/insert" method="post">
    <h4>Dados da Empresa</h4>
    <div class="mb-3">
        <label for="nome" class="form-label">Nome Fantasia</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
    </div>
    <div class="mb-3">
        <label for="razaoSocial" class="form-label">Razão Social</label>
        <input type="text" class="form-control" id="razaoSocial" name="razaoSocial" required>
    </div>
    <div class="mb-3">
        <label for="cnpj" class="form-label">CNPJ</label>
        <input type="text" class="form-control" id="cnpj" name="cnpj" required>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Senha</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div class="mb-3">
        <label for="telefone" class="form-label">Telefone</label>
        <input type="text" class="form-control" id="telefone" name="telefone" required>
    </div>

    <h4>Endereço</h4>
    <div class="mb-3">
        <label for="rua" class="form-label">Rua</label>
        <input type="text" class="form-control" id="rua" name="rua" required>
    </div>
    <div class="mb-3">
        <label for="bairro" class="form-label">Bairro</label>
        <input type="text" class="form-control" id="bairro" name="bairro" required>
    </div>
    <div class="mb-3">
        <label for="municipio" class="form-label">Município</label>
        <input type="text" class="form-control" id="municipio" name="municipio" required>
    </div>
    <div class="mb-3">
        <label for="estado" class="form-label">Estado</label>
        <input type="text" class="form-control" id="estado" name="estado" required>
    </div>
    <div class="mb-3">
        <label for="cep" class="form-label">CEP</label>
        <input type="text" class="form-control" id="cep" name="cep" required>
    </div>

    <div class="form-check mb-4">
        <input class="form-check-input" type="checkbox" id="termos" name="aceitaTermos" required>
        <label class="form-check-label" for="termos">
            Aceito os <a href="#">termos de uso</a> do site (incluindo o uso dos dados fornecidos).
        </label>
    </div>

    <button type="submit" class="btn btn-primary">Cadastrar Empresa</button>
</form>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
