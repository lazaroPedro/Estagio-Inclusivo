<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Empresa" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>

<%
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Vaga> vagasAtivas = (List<Vaga>) request.getAttribute("vagasAtivas");

    String cnpj = empresa.getCnpj();
    String cnpjFormatado = cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");

    String telefone = empresa.getTelefone();
    String telefoneFormatado = "";
    if (telefone != null) {
        if (telefone.length() == 11) {
            telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else if (telefone.length() == 10) {
            telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else {
            telefoneFormatado = telefone;
        }
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil da Empresa</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .header-curvo {
            background-color: #805DFF;
            color: white;
            padding: 60px 0;
            border-bottom-left-radius: 50% 10%;
            border-bottom-right-radius: 50% 10%;
            text-align: center;
        }

        .section-title {
            color: #805DFF;
            font-weight: 600;
            border-bottom: 2px solid #805DFF;
            padding-bottom: 5px;
            margin-bottom: 20px;
            display: inline-block;
        }

        .card-info {
            border: 2px solid #805DFF;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
            margin-bottom: 20px;
        }

        @media (max-width: 768px) {
            .col-md-6 {
                margin-bottom: 40px;
            }
        }
    </style>
</head>
<body>

<%@ include file="/assets/components/header.jsp" %>

<div class="header-curvo">
    <h1>Perfil da Empresa</h1>
</div>

<div class="container mt-5">
    <div class="row">
        <div class="">
            <h2 class="section-title">Informações da Empresa</h2>
            <div class="card-info">
                <p><strong>Nome Fantasia:</strong> <%= empresa.getNome() %></p>
                <p><strong>Razão Social:</strong> <%= empresa.getRazaoSocial() %></p>
                <p><strong>CNPJ:</strong> <%= cnpjFormatado %></p>
                <p><strong>Email:</strong> <%= empresa.getEmail() %></p>
                <p><strong>Telefone:</strong> <%= telefoneFormatado %></p>
            </div>
        </div>

        <div class="">
            <h2 class="section-title">Endereço</h2>
            <div class="card-info">
                <p><strong>Rua:</strong> ${empresa.endereco.rua}</p>
                <p><strong>Bairro:</strong> ${empresa.endereco.bairro} </p>
                <p><strong>Município:</strong> ${empresa.endereco.municipio}</p>
                <p><strong>Estado:</strong> ${empresa.endereco.estado}</p>
                <p><strong>CEP:</strong> ${empresa.endereco.cep}</p>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <h2 class="section-title">Vagas Ativas</h2>
            <%
                if (vagasAtivas == null || vagasAtivas.isEmpty()) {
            %>
            <div class="alert alert-secondary">Nenhuma vaga ativa cadastrada.</div>
            <%
            } else {
                for (Vaga vaga : vagasAtivas) {
            %>
            <div class="card-info">
                <p><strong>Status:</strong> <%= vaga.getStatus() %></p>
                <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                <p><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>