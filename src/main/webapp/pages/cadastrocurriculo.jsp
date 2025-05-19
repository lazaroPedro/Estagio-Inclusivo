<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Currículo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2>Cadastro de Currículo</h2>
<form action="${pageContext.request.contextPath}/home/curriculo/insert" method="post">
    <h3>Informações Pessoais</h3>
    <div class="mb-3">
        <label>Nome:</label>
        <input type="text" name="nome" class="form-control" value="${candidato.nome}" readonly>
    </div>
    <div class="mb-3">
        <label>Email:</label>
        <input type="email" name="email" class="form-control" value="${candidato.email}" readonly>
    </div>
    <div class="mb-3">
        <label>Telefone:</label>
        <input type="text" name="telefone" class="form-control" value="${candidato.telefone}" readonly>
    </div>
    <div class="mb-3">
        <label>Endereço:</label>
        <input type="text" class="form-control"
               value="${candidato.endereco.rua}, ${candidato.endereco.bairro}, ${candidato.endereco.municipio}, ${candidato.endereco.estado} - ${candidato.endereco.cep}"
               readonly>
    </div>

    <h3>Objetivo Profissional</h3>
    <div class="mb-3">
        <textarea name="objetivo" rows="4" class="form-control">${curriculo.objetivo}</textarea>
    </div>

    <h3>Habilidades</h3>
    <div class="mb-3">
        <textarea name="habilidades" rows="4" class="form-control">${curriculo.habilidades}</textarea>
    </div>

    <h3>Experiência Profissional</h3>
    <div class="mb-3">
        <textarea name="experiencia" rows="4" class="form-control">${curriculo.experiencia}</textarea>
    </div>

    <h3>Cursos</h3>
    <div id="cursos-lista">
        <c:forEach var="curso" items="${cursos}">
            <div class="mb-3 border rounded p-3">
                <input type="text" name="curso_nome[]" class="form-control mb-1" value="${curso.nomeCurso}" placeholder="Nome do Curso">
                <input type="text" name="curso_instituicao[]" class="form-control mb-1" value="${curso.instituicao}" placeholder="Instituição">
                <textarea name="curso_descricao[]" rows="2" class="form-control mb-1">${curso.descricao}</textarea>
                <input type="date" name="curso_inicio[]" class="form-control mb-1" value="${curso.dataInicio}">
                <input type="date" name="curso_fim[]" class="form-control mb-1" value="${curso.dataFim}">
            </div>
        </c:forEach>
    </div>
    <button type="button" class="btn btn-primary mt-2" onclick="adicionarCurso()">Adicionar Curso</button>

    <h3>Deficiências</h3>
    <div id="deficiencias-lista">
        <c:forEach var="def" items="${deficiencias}">
            <div class="mb-3 border rounded p-3">
                <input type="text" name="def_nome[]" class="form-control mb-1" value="${def.nome}" placeholder="Nome da Deficiência">
                <textarea name="def_descricao[]" rows="2" class="form-control mb-1">${def.descricao}</textarea>
                <input type="text" name="def_tipo[]" class="form-control mb-1" value="${def.tipo}" placeholder="Tipo da Deficiência">
                <input type="text" name="def_apoio[]" class="form-control mb-1" value="${def.tipoApoio}" placeholder="Tipo de Apoio">
            </div>
        </c:forEach>
    </div>
    <button type="button" class="btn btn-primary mt-2" onclick="adicionarDeficiencia()">Adicionar Deficiência</button>

    <div class="mt-4 text-end">
        <button type="submit" class="btn btn-success">Salvar Currículo</button>
        <a href="${pageContext.request.contextPath}/curriculo/exibir" class="btn btn-info">Exibir Currículo</a>
    </div>
</form>

<script>
    function adicionarCurso() {
        const container = document.getElementById("cursos-lista");
        const novoCurso = `
            <div class="mb-3 border rounded p-3">
                <input type="text" name="curso_nome[]" class="form-control mb-1" placeholder="Nome do Curso">
                <input type="text" name="curso_instituicao[]" class="form-control mb-1" placeholder="Instituição">
                <textarea name="curso_descricao[]" rows="2" class="form-control mb-1" placeholder="Descrição"></textarea>
                <input type="date" name="curso_inicio[]" class="form-control mb-1">
                <input type="date" name="curso_fim[]" class="form-control mb-1">
            </div>`;
        container.insertAdjacentHTML("beforeend", novoCurso);
    }

    function adicionarDeficiencia() {
        const container = document.getElementById("deficiencias-lista");
        const novaDeficiencia = `
            <div class="mb-3 border rounded p-3">
                <input type="text" name="def_nome[]" class="form-control mb-1" placeholder="Nome da Deficiência">
                <textarea name="def_descricao[]" rows="2" class="form-control mb-1" placeholder="Descrição"></textarea>
                <input type="text" name="def_tipo[]" class="form-control mb-1" placeholder="Tipo da Deficiência">
                <input type="text" name="def_apoio[]" class="form-control mb-1" placeholder="Tipo de Apoio">
            </div>`;
        container.insertAdjacentHTML("beforeend", novaDeficiencia);
    }
</script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
