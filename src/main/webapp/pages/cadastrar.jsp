<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #6e44ff, #b892ff, #ffc2e2);
            font-family: 'Segoe UI', sans-serif;
            animation: gradient 20s ease infinite;
            background-size: 400% 400%;
        }

        @keyframes gradient {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .glow-title {
            font-size: 3rem;
            text-align: center;
            color: white;
            text-shadow: 0 0 10px #fff, 0 0 20px #f0f, 0 0 30px #f0f, 0 0 40px #0ff;
            margin-top: 60px;
        }

        .card-container {
            display: flex;
            justify-content: center;
            gap: 60px;
            margin-top: 60px;
        }

        .fancy-card {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(20px);
            padding: 30px;
            border-radius: 20px;
            border: 2px solid white;
            box-shadow: 0 0 30px rgba(255, 255, 255, 0.3);
            transition: all 0.3s ease-in-out;
            text-align: center;
            width: 300px;
        }

        .fancy-card:hover {
            transform: scale(1.05) rotate(1deg);
            box-shadow: 0 0 40px rgba(255, 255, 255, 0.6);
        }

        .fancy-card i {
            font-size: 3rem;
            color: white;
            margin-bottom: 20px;
            text-shadow: 0 0 15px #000;
        }

        .fancy-card a {
            margin-top: 10px;
            display: inline-block;
            background-color: #ffffff33;
            border: 2px solid white;
            padding: 10px 25px;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 10px;
            transition: all 0.3s ease-in-out;
        }

        .fancy-card a:hover {
            background-color: white;
            color: #6e44ff;
            box-shadow: 0 0 20px #fff;
        }
    </style>
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>

<div class="glow-title">✨ Portal de Cadastro Estonteante ✨</div>

<div class="card-container">
    <div class="fancy-card">
        <i class="bi bi-building-check"></i>
        <h4>Cadastro de Empresa</h4>
        <a href="${pageContext.request.contextPath}/pages/cadastroempresa.jsp">Cadastrar Empresa</a>
    </div>

    <div class="fancy-card">
        <i class="bi bi-person-plus-fill"></i>
        <h4>Cadastro de Candidato</h4>
        <a href="${pageContext.request.contextPath}/pages/cadastrocandidato.jsp">Cadastrar Candidato</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
