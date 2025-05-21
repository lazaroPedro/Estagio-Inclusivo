<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Currículo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
    <script>
        function removerCurso(id, btn) {
            if (confirm("Deseja realmente remover este curso?")) {
                fetch(`${pageContext.request.contextPath}/home/curriculo/removerCurso?id=` + id, {
                    method: 'DELETE'
                }).then(res => {
                    if (res.ok) {
                        btn.closest('.curso-item').remove();
                    }
                });
            }
        }

        function removerDeficiencia(id, btn) {
            if (confirm("Tem certeza que deseja remover esta deficiência?")) {
                fetch('${pageContext.request.contextPath}/home/curriculo/removerDeficiencia?id=' + id, {
                    method: 'DELETE'
                }).then(res =>{
                    if (res.ok) {
                        btn.closest('.deficiencia-item').remove();
                    }
                });
            }
        }

        function adicionarCurso() {
            const container = document.getElementById("cursos-lista");
            const novoCurso = `
                <div class="mb-3 border rounded p-3 novo-curso">
                    <input type="text" name="curso_nome[]" class="form-control mb-1" placeholder="Nome do Curso" required>
                    <input type="text" name="curso_instituicao[]" class="form-control mb-1" placeholder="Instituição" required>
                    <textarea name="curso_descricao[]" rows="2" class="form-control mb-1" placeholder="Descrição" required></textarea>
                    <input type="date" name="curso_inicio[]" class="form-control mb-1" required>
                    <input type="date" name="curso_fim[]" class="form-control mb-1" required>
                    <button type="button" class="btn btn-danger mt-2" onclick="this.closest('.novo-curso').remove()">Remover</button>
                </div>`;
            container.insertAdjacentHTML("beforeend", novoCurso);
        }

        function adicionarDeficiencia() {
            const container = document.getElementById("deficiencias-lista");
            const novaDeficiencia = `
                <div class="mb-3 border rounded p-3 nova-deficiencia">
                    <input type="text" name="def_nome[]" class="form-control mb-1" placeholder="Nome da Deficiência" required>
                    <textarea name="def_descricao[]" rows="2" class="form-control mb-1" placeholder="Descrição" required></textarea>
                    <select name="def_tipo[]" class="form-select mb-1" required>
                        <option value="FISICA">Física</option>
                        <option value="VISUAL">Visual</option>
                        <option value="AUDITIVA">Auditiva</option>
                        <option value="INTELECTUAL">Intelectual</option>
                        <option value="MENTAL">Mental</option>
                        <option value="SENSORIAL">Sensorial</option>
                        <option value="MULTIPLA">Múltipla</option>
                        <option value="OUTRA">Outra</option>
                    </select>
                    <input type="text" name="def_apoio[]" class="form-control mb-1" placeholder="Tipo de Apoio" required>
                    <button type="button" class="btn btn-danger mt-2" onclick="this.closest('.nova-deficiencia').remove()">Remover</button>
                </div>`;
            container.insertAdjacentHTML("beforeend", novaDeficiencia);
        }
    </script>
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>

<h2>Cadastro de Currículo</h2>
<form action="${pageContext.request.contextPath}/home/curriculo/insert" method="post">
    <h3>Informações Pessoais</h3>
    <div class="mb-3">
        <label><strong>Nome:</strong></label>
        <p class="form-control-static">${candidato.nome}</p>
    </div>
    <div class="mb-3">
        <label><strong>Email:</strong></label>
        <p class="form-control-static">${candidato.email}</p>
    </div>
    <div class="mb-3">
        <label><strong>Cpf:</strong></label>
        <p class="form-control-static">${candidato.cpf}</p>
    </div>
    <div class="mb-3">
        <label><strong>Telefone:</strong></label>
        <p class="form-control-static">${candidato.telefone}</p>
    </div>
    <div class="mb-3">
        <label><strong>Endereço:</strong></label>
        <p class="form-control-static">
            ${endereco.rua}, ${endereco.bairro},
            ${endereco.municipio}, ${endereco.estado} - ${endereco.cep}
        </p>
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


    <h3>Cursos Cadastrados</h3>
    <div id="cursosExistentes">
        <c:forEach var="curso" items="${cursos}">
            <div class="curso-item mb-3 border rounded p-3">
                <p><strong>Nome do Curso: ${curso.nomeCurso}</strong>
                <p><strong>Instituição: ${curso.instituicao}</strong>
                <p><strong>Descrição: ${curso.descricao}</strong>
                <p><strong>Periodo: </strong> ${curso.dataInicio} a ${curso.dataFim}</p>
                <button type="button" class="btn btn-danger" onclick="removerCurso(${curso.id}, this)">Remover</button>
            </div>
        </c:forEach>
    </div>

    <h3>Adicionar Novos Cursos</h3>
    <div id="cursos-lista"></div>
    <button type="button" class="btn btn-primary mt-2" onclick="adicionarCurso()">Adicionar Curso</button>

    <h3>Deficiências Cadastradas</h3>
    <div id="deficienciasExistentes">
        <c:forEach var="def" items="${deficiencias}">
            <div class="deficiencia-item mb-3 border rounded p-3">
                <p><strong>Nome da Deficiencia: ${def.nome}</strong>
                <p><strong>Tipo Da deficiencia: ${def.tipo}</strong>
                <p><strong>Descrição: ${def.descricao}</strong>
                <p><strong>Tipo de Apoio: ${def.tipoApoio}</strong></p>
                <button type="button" class="btn btn-danger" onclick="removerDeficiencia(${def.id}, this)">Remover</button>
            </div>
        </c:forEach>
    </div>

    <h3>Adicionar Novas Deficiências</h3>
    <div id="deficiencias-lista"></div>
    <button type="button" class="btn btn-primary mt-2" onclick="adicionarDeficiencia()">Adicionar Deficiência</button>

    <div class="mt-4 text-end">
        <button type="submit" class="btn btn-success">Salvar Currículo</button>
        <a href="${pageContext.request.contextPath}/home/curriculo/id?id=${curriculo.id}" class="btn btn-info">Exibir Currículo</a>
    </div>
</form>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
