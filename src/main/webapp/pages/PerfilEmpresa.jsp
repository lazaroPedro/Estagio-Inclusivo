<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil da Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f8f9fa;
        }

        h1, h2 {
            color: #2c3e50;
        }

        .perfil, .vagas {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .perfil p, .vagas p {
            font-size: 1.1rem;
            margin-bottom: 10px;
        }

        .perfil p strong, .vagas p strong {
            color: #34495e;
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
            font-size: 1rem;
        }

        .btn-logout:hover {
            background-color: #c0392b;
        }

        .alert-warning {
            background-color: #f39c12;
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <header class="bg-primary text-white text-center py-4">
        <h1>Bem-vinda, <%= empresa.getNome() %>!</h1>
        <a href="logout" class="btn btn-danger mt-2">Sair</a>
    </header>

    <div class="container mt-5">
        <section class="perfil mb-5">
            <h2 class="text-primary">Dados da Empresa</h2>
            <p><strong>ID:</strong> <%= empresa.getId() %></p>
            <p><strong>Nome:</strong> <%= empresa.getNome() %></p>
            <p><strong>Razão social:</strong> <%= empresa.getRazaoSocial() %></p>
             <p><strong>Endereço:</strong> <%= empresa.getCnpj() %></p>
            <p><strong>CNPJ:</strong> <%= empresa.getCnpj() %></p>
            <p><strong>Email:</strong> <%= empresa.getEmail() %></p>
            <p><strong>Telefone:</strong> <%= empresa.getTelefone() %></p>
        </section>

        <section class="vagas">
            <h2 class="text-primary">Vagas Publicadas</h2>

            <% if (vagasPublicadas == null || vagasPublicadas.isEmpty()) { %>
                <div class="alert alert-warning" role="alert">
                    Nenhuma vaga foi publicada até o momento.
                </div>
            <% } else { %>
                <% for (Vaga vaga : vagasPublicadas) { %>
                    <div class="vaga">
                        <p><strong>Endereço:</strong> <%= vaga.getEndereco() %></p>
                        <p><strong>Status:</strong> <%= vaga.getStatus() %></p>
                        <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                        <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                        <p><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
                        <p><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                    </div>
                <% } %>
            <% } %>
        </section>
    </div>
</body>
</html>
