<%@ page import="com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cadastro de candidato </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>

<h2 class="mb-4">Cadastro de Candidato</h2>
<%
    List<ErroCampo> erros =
            (List<com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo>) request.getAttribute("errosValidacao");

    if (erros != null && !erros.isEmpty()) {
%>
<div class="alert alert-danger">
    <ul>
        <% for (com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo erro : erros) { %>
        <li><strong><%= erro.getNomeCampo() %>:</strong> <%= erro.getMensagemErro() %></li>
        <% } %>
    </ul>
</div>
<%
    }
%>


<form action="${pageContext.request.contextPath}/candidato" method="post">
    <h4>Dados gerais</h4>
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" name="nome" required>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Senha</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div class="mb-3">
        <label for="telefone" class="form-label">Telefone</label>
        <input type="text" class="form-control" id="telefone" name="telefone" required>
    </div>
    <h4>Endereço</h4>
    <div class="mb-3">
        <label for="rua" class="form-label">Rua</label>
        <input type="text" class="form-control" id="rua" name="rua" required>
    </div>
    <div class="mb-3">
        <label for="bairro" class="form-label">Bairro</label>
        <input type="text" name="bairro" class="form-control" id="bairro" required>
    </div>
    <div class="mb-3">
        <label for="municipio" class="form-label">Município</label>
        <input type="text" name="municipio" class="form-control" id="muni" required>
    </div>
    <div class="mb-3">
        <label for="estado" class="form-label">Estado</label>
        <input type="text" name="estado" class="form-control" id="estado"  required>
    </div>
    <div class="mb-3">
        <label for="cep" class="form-label">CEP</label>
        <input type="text" name="cep" class="form-control" id="cep"  required>
    </div>
    <h4>Dados especificos</h4>
    <div class="mb-3">
        <label for="cpf" class="form-label">CPF</label>
        <input type="text" name="cpf" class="form-control" id="cpf"  required>
    </div>
    <div class="mb-3">
        <label for="genero" class="form-label" id="genero" name="genero" >Gênero</label>
        <select name="genero" class="form-select" required>
            <option value="">Selecione</option>
            <option value="MASCULINO">Masculino</option>
            <option value="FEMININO">Feminino</option>
            <option value="OUTRO">Outro</option>
        </select>
    </div>
    <div class="mb-3">
        <label for="Data" class="form-label" id="data" name="data" >Data de Nascimento</label>
        <input type="date" name="nascimento" class="form-control" required>
    </div>
    <h4 class="mt-4">Curso</h4>
    <div class="mb-3">
        <label for="NomeC" class="form-label">Nome do Curso</label>
        <input type="text" name="curso_nome" class="form-control" id="nomeCurso">
    </div>
    <div class="mb-3">
        <label for="instituicao" class="form-label">Instituição</label>
        <input type="text" name="curso_instituicao" class="form-control" id="instituicao">
    </div>
    <div class="mb-3">
        <label for="Desc" class="form-label">Descrição</label>
        <textarea name="curso_descricao" class="form-control" id="Desc"></textarea>
    </div>
    <div class="mb-3">
        <label for="DataIn" class="form-label">Data de Início</label>
        <input type="date" name="curso_inicio" class="form-control" id="dataIn">
    </div>
    <div class="mb-3">
        <label for="DataTe" class="form-label">Data de Término</label>
        <input type="date" name="curso_fim" class="form-control" id="dataTe">
    </div>

    <h4>Deficiência</h4>
    <div class="mb-3">
        <label for="def_nome" class="form-label">Nome da Deficiência</label>
        <input type="text" name="def_nome" class="form-control" id="defNome" required>
    </div>
    <div class="mb-3">
        <label for="def_descricao" class="form-label">Descrição</label>
        <textarea name="def_descricao" class="form-control" id="defDesc" required></textarea>
    </div>
    <div class="mb-3">
        <label for="def_tipo" class="form-label">Tipo de Deficiência</label>
        <select name="def_tipo" class="form-select" id="defTipo" required>
            <option value="">Selecione</option>
            <option value="FISICA">Física</option>
            <option value="VISUAL">Visual</option>
            <option value="AUDITIVA">Auditiva</option>
            <option value="INTELECTUAL">Intelectual</option>
            <option value="MENTAL">Mental</option>
            <option value="SENSORIAL">Sensorial</option>
            <option value="MULTIPLA">Múltipla</option>
            <option value="OUTRA">Outra</option>
        </select>
    </div>
    <div class="mb-3">
        <label for="def_apoio" class="form-label">Tipo de Apoio Necessário</label>
        <textarea name="def_apoio" class="form-control" id="defApoi" required></textarea>
    </div>
    <div class="form-check mb-4">
        <input class="form-check-input" type="checkbox" id="termos" name="aceitaTermos" required>
        <label class="form-check-label" for="termos">
            Aceito os <a href="#">termos de uso</a> do site(e o uso de todos os dados fornecidos(juntamente com o uso do meu cartão).
        </label>
    </div>

    <button type="submit" class="btn btn-success">Cadastrar</button>
</form>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/validacoes.js"></script>
</body>
</html>
