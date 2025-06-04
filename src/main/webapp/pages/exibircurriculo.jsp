
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currículo do Candidato</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9fbfd;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container-curriculo {
            max-width: 900px;
            margin: 60px auto;
            background-color: white;
            padding: 40px 50px;
            border-radius: 16px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.1);
            border: 1px solid #e3e6f0;
        }

        .header-curriculo {
            text-align: center;
            margin-bottom: 30px;
        }

        .header-curriculo h1 {
            font-size: 2.2rem;
            font-weight: bold;
            color: #2e3a59;
        }

        .header-curriculo p {
            margin: 0;
            color: #6c757d;
        }

        .section {
            margin-bottom: 30px;
        }

        .section h4 {
            font-size: 1.3rem;
            color: #4e5d6c;
            border-bottom: 2px solid #dee2e6;
            padding-bottom: 8px;
            margin-bottom: 15px;
        }

        .section p, .section li {
            font-size: 1rem;
            line-height: 1.6;
            color: #495057;
        }

        ul {
            padding-left: 20px;
        }

        .badge-info {
            font-size: 0.9rem;
            background-color: #e8f0fe;
            color: #1a73e8;
            padding: 0.4em 0.8em;
            border-radius: 6px;
        }
    </style>
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>

<div class="container-curriculo">
    <div class="header-curriculo">
        <h1>${candidato.nome}</h1>
        <p><i class="bi bi-envelope"></i> ${candidato.email} | <i class="bi bi-telephone"></i> ${candidato.telefone}</p>
        <p><i class="bi bi-geo-alt"></i> ${endereco.rua}, ${endereco.bairro}, ${endereco.municipio} - ${endereco.estado}, ${endereco.cep}</p>
    </div>

    <div class="section">
        <h4><i class="bi bi-bullseye"></i> Objetivo</h4>
        <p>${curriculo.objetivo}</p>
    </div>

    <div class="section">
        <h4><i class="bi bi-lightbulb"></i> Habilidades</h4>
        <p>${curriculo.habilidades}</p>
    </div>

    <div class="section">
        <h4><i class="bi bi-briefcase"></i> Experiência</h4>
        <p>${curriculo.experiencia}</p>
    </div>

    <div class="section">
        <h4><i class="bi bi-book"></i> Cursos</h4>
        <ul>
            <c:forEach var="curso" items="${cursos}">
                <li>
                    <span class="badge-info">${curso.nomeCurso}</span> - ${curso.instituicao}<br>
                    <small>${curso.dataInicio} a ${curso.dataFim}</small><br>
                        ${curso.descricao}
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="section">
        <h4><i class="bi bi-universal-access"></i> Deficiências</h4>
        <ul>
            <c:forEach var="def" items="${deficiencias}">
                <li>
                    <strong>${def.nome}</strong> (${def.tipo})<br>
                        ${def.descricao}<br>
                    <em>Tipo de apoio: ${def.tipoApoio}</em>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
