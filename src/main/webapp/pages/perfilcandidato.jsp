<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Candidato" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.TipoDeficiencia" %>
<%
    Candidato candidato = (Candidato) request.getAttribute("candidato");
    List<Vaga> vagasInscritas = (List<Vaga>) request.getAttribute("vagasInscritas");
    List<TipoDeficiencia> deficiencias = (List<TipoDeficiencia>) request.getAttribute("deficiencias");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil do Candidato</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
       
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>

   <div class="container-xl mt-5 pt-5">
    <div class="card mb-4">
        <div class="card-body">
            <h1 class="card-title text-primary">Bem-vindo, <%= candidato.getNome() %>!</h1>
            <a href="logout" class="btn btn-danger mt-3">Sair</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-body">
            <h2 class="text-primary">Seus Dados</h2>
              <p><strong>ID:</strong> <%= candidato.getId() %></p>
            <p><strong>Nome:</strong> <%= candidato.getNome() %></p>
            <p><strong>Gênero:</strong> <%= candidato.getGenero() %></p>
            <p><strong>Data de Nascimento:</strong> <%= candidato.getDataNascimento() %></p>
            <p><strong>Endereço:</strong> <%= candidato.getEndereco() %></p>
            <p><strong>Email:</strong> <%= candidato.getEmail() %></p>
            <p><strong>CPF:</strong> <%= candidato.getCpf() %></p>
            <p><strong>Telefone:</strong> <%= candidato.getTelefone() %></p>
         </div>
    </div>
     <div class="card mb-4">
        <div class="card-body">
            <h2 class="text-primary">Deficiências</h2>

            <% if (deficiencias == null || deficiencias.isEmpty()) { %>
                <div class="alert alert-danger mt-3">
                    Nenhuma deficiência registrada para este candidato.
                </div>
            <% } else { %>
                <% for (TipoDeficiencia deficiencia : deficiencias) { %>
                    <div class="alert alert-info mt-3">
                        <p><strong>Deficiência:</strong> <%= deficiencia.getNome() %></p>
                        <p><strong>Tipo:</strong> <%= deficiencia.getTipo() %></p>
                        <p><strong>Descrição:</strong> <%= deficiencia.getDescricao() %></p>
                        <p><strong>Tipo de Apoio:</strong> <%= deficiencia.getTipoApoio() %></p>
                    </div>
                <% } %>
            <% } %>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-body">
            <h2 class="text-primary">Vagas em que você está inscrito</h2>

            <% if (vagasInscritas == null || vagasInscritas.isEmpty()) { %>
                <div class="alert alert-danger mt-3">
                    Você ainda não se inscreveu em nenhuma vaga.
                </div>
            <% } else { %>
                <% for (Vaga vaga : vagasInscritas) { %>
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-md-4 card-body">
                        <p><strong>Empresa:</strong> <%= vaga.getEmpresa().getNome() %></p>
                        <p><strong>Endereço:</strong> <%= vaga.getEndereco() %></p>
                         </div>
                            <div class="col-md-4 card-body">
                        <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                        <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                         </div>
                            <div class="col-md-4 card-body">
                        <p><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
                        <p><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                </div>
                        </div>
                    </div>
               <% } %> 
            <% } %> 
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>