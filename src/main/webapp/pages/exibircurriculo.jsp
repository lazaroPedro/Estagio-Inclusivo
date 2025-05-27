<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currículo do Candidato</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>

<h2 class="mt-5 pt-5">Currículo do Candidato</h2>

<div class="card shadow p-4 mb-3">
    <h3>${candidato.nome}</h3>
    <p><strong>Email:</strong> ${candidato.email}</p>
    <p><strong>Telefone:</strong> ${candidato.telefone}</p>
    <p><strong>Endereço:</strong> ${endereco.rua}, ${endereco.bairro}, ${endereco.municipio}, ${endereco.estado} - ${endereco.cep}</p>
    <hr>
    <h4>Objetivo</h4>
    <p>${curriculo.objetivo}</p>

    <h4>Habilidades</h4>
    <p>${curriculo.habilidades}</p>

    <h4>Experiência</h4>
    <p>${curriculo.experiencia}</p>

    <h4>Cursos</h4>
    <ul>
        <c:forEach var="curso" items="${cursos}">
            <li>${curso.nomeCurso} - ${curso.instituicao} (${curso.dataInicio} a ${curso.dataFim})</li>
            <li>${curso.descricao}</li>
        </c:forEach>
    </ul>

    <h4>Deficiências</h4>
    <ul>
        <c:forEach var="def" items="${deficiencias}">
            <li>${def.nome} - ${def.tipo} - ${def.descricao} (${def.tipoApoio})</li>
        </c:forEach>
    </ul>
</div>
<!--
<div class="text-end mt-3">
    <a href="${pageContext.request.contextPath}/curriculo/exportarPDF" class="btn btn-success">Exportar como PDF</a>
</div>-->
</body>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>

</html>
