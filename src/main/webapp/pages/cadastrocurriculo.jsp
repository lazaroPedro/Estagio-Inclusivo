<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.*" %>
<%@ page import="java.util.List" %>
<html> <head> <title>Cadastro de Currículo</title> <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css"> <style> body { margin: 0; padding: 0; font-family: 'Segoe UI', sans-serif; background-color: #f5f7fa; }

.curriculo-wrapper {
    max-width: 900px;
    margin: 60px auto;
    background-color: white;
    border-radius: 12px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
    padding: 40px;
}

h2, h3 {
    color: #6e44ff;
    font-weight: bold;
}

.section-title {
    border-bottom: 2px solid #b892ff !important;
    padding-bottom: 5px;
    margin-bottom: 20px;
}

.form-control-static {
    background-color: #f8f9fa;
    padding: 8px 12px;
    border-radius: 6px;
    border: 1px solid #dee2e6;
    margin-bottom: 15px;
}

.btn-success, .btn-info {
    border-radius: 30px;
    padding: 10px 25px;
    font-weight: bold;
}

.btn-success {
    background-color: #6e44ff !important;
    border: none;
}

.btn-success:hover {
    background-color: #5a37cc !important;
}

.btn-info {
    background-color: #b892ff !important;
    border: none;
    color: white;
}

.btn-info:hover {
    background-color: #a27ee4 !important;
}

.info-section {
    margin-bottom: 30px;
}

.item-box {
    background-color: #fafafa;
    border: 1px solid #e2e6ea;
    border-radius: 8px;
    padding: 15px;
    margin-bottom: 15px;
}

.btn-group-custom {
    display: flex;
    gap: 10px;
    justify-content: end;
    margin-top: 30px;
}
</style>

</head> <body> <%@ include file="/assets/components/header.jsp" %> <div class="curriculo-wrapper"> <h2 class="mb-4 text-center">Cadastro de Currículo</h2> <form action="${pageContext.request.contextPath}/home/curriculo/insert" method="post"> <div class="info-section"> <h3 class="section-title">Informações Pessoais</h3> <div class="form-control-static"><strong>Nome:</strong> ${candidato.nome}</div> <div class="form-control-static"><strong>Email:</strong> ${candidato.email}</div> <div class="form-control-static"><strong>CPF:</strong> ${candidato.cpf}</div> <div class="form-control-static"><strong>Telefone:</strong> ${candidato.telefone}</div> <div class="form-control-static"><strong>Endereço:</strong> ${endereco.rua}, ${endereco.bairro}, ${endereco.municipio}, ${endereco.estado} - ${endereco.cep} </div> </div>

    <div class="info-section">
        <h3 class="section-title">Objetivo Profissional</h3>
        <textarea name="objetivo" rows="4" class="form-control" style="height: 100px; resize: none;">${curriculo.objetivo}</textarea>
    </div>

    <div class="info-section">
        <h3 class="section-title">Habilidades</h3>
        <textarea name="habilidades" rows="4" class="form-control" style="height: 100px; resize: none;">${curriculo.habilidades}</textarea>
    </div>

    <div class="info-section">
        <h3 class="section-title">Experiência Profissional</h3>
        <textarea name="experiencia" rows="4" class="form-control" style="height: 100px; resize: none;">${curriculo.experiencia}</textarea>
    </div>

    <div class="info-section">
        <h3 class="section-title">Cursos Cadastrados</h3>
        <c:forEach var="curso" items="${cursos}">
            <div class="item-box">
                <p><strong>Nome do Curso:</strong> ${curso.nomeCurso}</p>
                <p><strong>Instituição:</strong> ${curso.instituicao}</p>
                <p><strong>Descrição:</strong> ${curso.descricao}</p>
                <p><strong>Período:</strong> ${curso.dataInicio} a ${curso.dataFim}</p>
            </div>
        </c:forEach>
    </div>

    <div class="info-section">
        <h3 class="section-title">Deficiências Cadastradas</h3>
        <c:forEach var="def" items="${deficiencias}">
            <div class="item-box">
                <p><strong>Nome da Deficiência:</strong> ${def.nome}</p>
                <p><strong>Tipo da Deficiência:</strong> ${def.tipo}</p>
                <p><strong>Descrição:</strong> ${def.descricao}</p>
                <p><strong>Tipo de Apoio:</strong> ${def.tipoApoio}</p>
            </div>
        </c:forEach>
    </div>

    <div class="btn-group-custom">
        <button type="submit" class="btn btn-success">Salvar Currículo</button>
        <a href="${pageContext.request.contextPath}/curriculo?id=${candidato.id}" class="btn btn-info">Exibir Currículo</a>
    </div>
</form>

</div> <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script> </body> </html>