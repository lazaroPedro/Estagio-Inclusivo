<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Candidato" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>
<%
    Candidato candidato = (Candidato) request.getAttribute("candidato");
    List<Vaga> vagasInscritas = (List<Vaga>) request.getAttribute("vagasInscritas");
    
    if (candidato == null) {
        out.println("<h3>Erro: Não foi possível carregar o perfil do candidato.</h3>");
    } else {
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil do Candidato</title>
    <!-- Inclusão do Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        h1, h2 {
            color: #2c3e50;
        }

        .perfil, .vagas {
            background-color: #ecf0f1;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }

        .vaga {
            border-bottom: 1px solid #bdc3c7;
            padding: 10px 0;
        }

        .btn-logout {
            background-color: #e74c3c;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn-logout:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

    <!-- Cabeçalho -->
    <header class="bg-primary text-white text-center py-4">
        <h1>Bem-vindo, <%= candidato.getNome() %>!</h1>
        <a href="logout" class="btn btn-danger mt-2">Sair</a>
    </header>

    <div class="container mt-5">
        <!-- Perfil do Candidato -->
        <section class="perfil mb-5">
            <h2 class="text-primary">Seus dados</h2>
            <p><strong>Nome:</strong> <%= candidato.getNome() %></p>
            <p><strong>Email:</strong> <%= candidato.getEmail() %></p>
            <p><strong>CPF:</strong> <%= candidato.getCpf() %></p>
            <p><strong>Telefone:</strong> <%= candidato.getTelefone() %></p>
            <!-- Adicione mais campos conforme existirem no seu modelo Candidato -->
        </section>

        <!-- Vagas Inscritas -->
        <section class="vagas">
            <h2 class="text-primary">Vagas em que você está inscrito</h2>

            <% if (vagasInscritas == null || vagasInscritas.isEmpty()) { %>
                <div class="alert alert-warning" role="alert">
                    Você ainda não se inscreveu em nenhuma vaga.
                </div>
            <% } else { %>
                <% for (Vaga vaga : vagasInscritas) { %>
                    <div class="vaga">
                        <p><strong>Empresa:</strong> <%= vaga.getEmpresa().getNome() %></p>
                        <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                        <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                        <p><strong>Quantidade:</strong> <%= vaga.getQtdVagas() %></p>
                        <p><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                    </div>
                <% } %>
            <% } %>
        </section>

        <a href="logout" class="btn btn-danger mt-4">Sair</a>
    </div>

</body>
</html>

<%
    }
%>
