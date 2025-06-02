<%@ page import="com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo" %>
<%@ page import="com.ifbaiano.estagioinclusivo.utils.validation.ListErrors" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String erro2 = (String) request.getAttribute("erro");
    if (erro2 != null) {
%>
<div class="alert alert-danger">
    <strong>Erro:</strong> <%= erro2 %>
</div>
<%
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title >Cadastro de candidato </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
    <style>
        :root {
            --primary-color: #7c3aed;
            --primary-hover: #6d28d9;
            --background-accent: #f3f0ff;
            --dark-grey: #1f2937;
            --medium-grey: #6b7280;
            --white: #ffffff;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", sans-serif;
            background-color: var(--background-accent);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-wrapper {
            display: flex;
            background-color: var(--white);
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
            overflow: hidden;
            max-width: 1000px;
            width: 100%;
            height: auto;
        }

        .image-side {
            flex: 1;
            background: url('${pageContext.request.contextPath}/assets/images/Cadastro.png') no-repeat center center;
            background-size: cover;
            min-height: 100%;
        }

        .form-side {
            flex: 1;
            padding: 40px;
        }

        .form-title {
            font-weight: 700;
            font-size: 1.8rem;
            color: var(--primary-color);
        }

        .form-subtitle {
            font-size: 1rem;
            color: var(--medium-grey);
            margin-bottom: 25px;
        }

        label {
            font-weight: 500;
            color: var(--dark-grey);
        }

        .btn-primary {
            background-color: var(--primary-color) !important;
            border: none !important;
        }

        .btn-primary:hover {
            background-color: var(--primary-hover) !important;
        }

        .text-link {
            color: var(--primary-color);
            text-decoration: underline;
        }

        @media (max-width: 991.98px) {
            .form-wrapper {
                flex-direction: column;
                max-width: 90%;
            }

            .image-side {
                height: 220px;
                background-size: contain;
                background-repeat: no-repeat;
            }

            .form-side {
                padding: 25px;
            }
        }
    </style>
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>
<div class="form-wrapper">
    <div class="image-side"></div>

    <div class="form-side">
        <h2 class="form-title text-center mb-2">Cadastro de Candidato</h2>
        <p class="form-subtitle text-center">Preencha seus dados para criar sua conta</p>

                <% String erro = (String) request.getAttribute("erro");
                    if (erro != null) { %>
                <div class="alert alert-danger"><%= erro %></div>
                <% } %>

                <% ListErrors erros = (ListErrors) request.getAttribute("errosValidacao");
                    if (erros != null && !erros.isEmpty()) { %>
                <div class="alert alert-danger">
                    <ul class="mb-0">
                        <% for (ErroCampo e : erros.getErroCampos()) { %>
                        <li><strong><%= e.getNomeCampo() %>:</strong> <%= e.getMensagemErro() %></li>
                        <% } %>
                    </ul>
                </div>
                <% } %>

                    <form action="${pageContext.request.contextPath}/candidato/insert" method="post">
                        <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nome" class="form-label">Nome Completo</label>
                            <input type="text" class="form-control" id="nome" name="nome" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="password" class="form-label">Senha</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" class="form-control" id="telefone" name="telefone" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" class="form-control" id="cpf"  required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="genero" class="form-label">Gênero</label>
                            <select name="genero" class="form-select" required>
                                <option value="">Selecione</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMININO">Feminino</option>
                                <option value="OUTRO">Outro</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="Data" class="form-label" id="data" name="data" >Data de Nascimento</label>
                            <input type="date" name="nascimento" class="form-control" required>
                        </div>
                            </div>
                        <div class="form-check mb-4">
                            <input class="form-check-input" type="checkbox" id="termos" name="aceitaTermos" required>
                            <label class="form-check-label" for="termos">
                                Aceito os <a href="#" class="text-link">termos de uso</a> do site(e o uso de todos os dados fornecidos(juntamente com o uso do meu cartão).
                            </label>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
                    </form>
            </div>
        </div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/validacoes.js" type="text/javascript"></script>
</body>
</html>
