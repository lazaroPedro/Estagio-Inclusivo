<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Empresa" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>
<%
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Vaga> vagasPublicadas = (List<Vaga>) request.getAttribute("vagasPublicadas");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil da Empresa</title>
     <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>
    <div class="container-xl mt-5 pt-5">
    <div class="card mb-4">
        <div class="card-body bg-primary text-white">
            <h1 class="card-title">Bem-vinda, <%= empresa.getNome() %>!</h1>
            <a href="logout" class="btn btn-light mt-3">Sair</a>
        </div>
    </div>
    <div class="card mb-4">
        <div class="card-header bg-secondary text-white">
            <h2 >Dados da Empresa</h2>
            </div>
        <div class="card-body">
            <p><strong>ID:</strong> <%= empresa.getId() %></p>
            <p><strong>Nome:</strong> <%= empresa.getNome() %></p>
            <p><strong>Razão social:</strong> <%= empresa.getRazaoSocial() %></p>
             <p><strong>Endereço:</strong> <%= empresa.getCnpj() %></p>
            <p><strong>CNPJ:</strong> <%= empresa.getCnpj() %></p>
            <p><strong>Email:</strong> <%= empresa.getEmail() %></p>
            <p><strong>Telefone:</strong> <%= empresa.getTelefone() %></p>
         </div>
    </div>
<div class="card mb-4">
        <div class="card-header bg-secondary text-white">
            <h2 >Vagas Publicadas</h2>

            <% if (vagasPublicadas == null || vagasPublicadas.isEmpty()) { %>
                <div class="alert alert-danger" role="alert">
                    Nenhuma vaga foi publicada até o momento.
                </div>
            <% } else { 
                for (Vaga vaga : vagasPublicadas) { %>
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-4 card-body">
                        <p class="card-text"><strong>Endereço:</strong> <%= vaga.getEndereco() %></p>
                        <p class="card-text"><strong>Status:</strong> <%= vaga.getStatus() %></p>
                        </div>
                            <div class="col-4 card-body">
                        <p class="card-text"><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                        <p class="card-text"><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                         </div>
                            <div class="col-4 card-body">
                        <p class="card-text"><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
                        <p class="card-text"><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                    </div>
                    </div>
                    </div>
                <% } 
            } %>
     
  </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
