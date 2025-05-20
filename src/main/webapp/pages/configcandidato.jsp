<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 04/05/2025
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Perfil</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>
<style>
    html {
      scroll-padding-top: 20%;
    }
  </style>
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>


<div class="row mt-5 pt-5">
        <!-- Coluna à esquerda -->
        <div class="col-4">
            <nav id="menu-rolagem" class="col-md-3 col-lg-2 d-md-block bg-light sidebar position-fixed vh-100 p-3">
                <!-- Título -->
                <a class="navbar-brand" href="#">Configuracões</a>

                <!-- Itens do menu -->
                <nav class="nav flex-column">
                    <a class="nav-link active" href="#item-1">Inicio</a>
                    <a class="nav-link" href="#item-2">Alterar Perfil</a>
                    <a class="nav-link" href="#item-3">Alterar Dados de Acesso</a>
                    <a class="nav-link" href="#item-4">Alterar Curso</a>
                    <a class="nav-link" href="#item-5">Alterar Endereco</a>
                    <a class="nav-link" href="#item-6">Alterar Deficiencia</a>
                    <a class="nav-link" href="#item-7">Deletar Conta</a>



                </nav>
            </nav>
        </div>

        <!-- Coluna à direita -->
        <div class="offset-md-3 offset-lg-2 col-md-9 col-lg-10 ms-auto ps-4">
            <div data-bs-spy="scroll" data-bs-target="#menu-rolagem" data-bs-offset="0" tabindex="0"
                 style="position: relative; height: 100vh; overflow: auto;">
                <h4 id="item-1">Inicio</h4>
                    <% if (request.getAttribute("sucesso") != null) { %>
                <div class="alert-success d-flex align-items-center" role="alert">
                                    Alterado com sucesso!
                </div>
                    <% } %>


                <h5 id="item-2">Alterar Perfil</h5>

                <form action="${pageContext.request.contextPath}/home/candidato/put" method="post">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" >
                    </div>
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" >
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control" id="cpf"  >
                    </div>
                    <div class="mb-3">
                        <label for="genero" class="form-label"  >Gênero</label>
                            <select name="genero" class="form-select" id="genero">
                                <option value="">Selecione</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMININO">Feminino</option>
                                <option value="OUTRO">Outro</option>
                                <option value="NAO_INFORMAR">Não desejo informar</option>
                            </select>

                    </div>
                    <div class="mb-3">
                        <label for="data" class="form-label" id="data" name="data" >Data de Nascimento</label>
                        <input type="date" name="nascimento" class="form-control" id="data" >
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>


                <h4 id="item-3">Alterar Dados de Acesso</h4>
                <form action="${pageContext.request.contextPath}/home/usuario/put" method="post">
                    <input type="hidden" name="method" value="put_" />
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control" id="email" name="email" >
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="text" class="form-control" id="senha" name="senha" >
                        </div>
                        <button type="submit" class="btn btn-success">Atualizar</button>
                </form>


                <h4 id="item-4">Alterar Curso</h4>
                <c:choose>
                    <c:when test="${cursos == null}">
                        <a href="#">Adcionar Cursos</a>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="curso" items="${cursos}">
                            <a class="text-decoration-none text-reset" href="#">
                                <div class="card mb-3 ">
                                    <div class="row g-0">
                                        <div class="col-4 card-body">
                                            <p class="card-text ">${curso.instituicao}</p>

                                        </div>
                                        <div class="col-4">

                                            <div class="card-body">
                                                <h5 class="card-title"> ${curso.nomeCurso} </h5>
                                                <p class="card-text ">${curso.descricao}</p>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="card-body">
                                                <p class="card-text ">${curso.dataInicio}</p>
                                                <p class="card-text ">${curso.dataFim}</p>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <form action="${pageContext.request.contextPath}/home/curso/put" method="post">
                <input type="hidden" name="method" value="put_" />


                <div class="mb-3">
                    <label for="nome" class="form-label">Nome do Curso</label>
                    <input type="text" name="nome" class="form-control" id="nome">
                </div>
                <div class="mb-3">
                    <label for="instituicao" class="form-label">Instituição</label>
                    <input type="text" name="instituicao" class="form-control" id="instituicao">
                </div>
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <textarea name="descricao" class="form-control" id="descricao"></textarea>
                </div>
                <div class="mb-3">
                    <label for="inicio" class="form-label">Data de Início</label>
                    <input type="date" name="inicio" class="form-control" id="inicio">
                </div>
                <div class="mb-3">
                    <label for="termino" class="form-label">Data de Término</label>
                    <input type="date" name="termino" class="form-control" id="termino">
                </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>



                <h5 id="item-5">Alterar Endereco</h5>
                <form action="${pageContext.request.contextPath}/home/endereco/put" method="post">
                <input type="hidden" name="method" value="put_" />
                    <div class="mb-3">
                        <label for="rua" class="form-label">Rua</label>
                        <input type="text" class="form-control" id="rua" name="rua" >
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" name="bairro" class="form-control" id="bairro" >
                    </div>
                    <div class="mb-3">
                        <label for="municipio" class="form-label">Município</label>
                        <input type="text" name="municipio" class="form-control" id="municipio" >
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" name="estado" class="form-control" id="estado">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" name="cep" class="form-control" id="cep">
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>


                <h5 id="item-6">Alterar Deficiencia</h5>
                <form action="${pageContext.request.contextPath}/home/deficiencia/put" method="post">
                    <input type="hidden" name="method" value="put_" />

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome da Deficiência</label>
                        <input type="text" name="nome" class="form-control" id="nome">
                    </div>
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <textarea name="descricao" class="form-control" id="descricao"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="tipo" class="form-label">Tipo de Deficiência</label>
                        <select name="tipo" class="form-select" id="tipo">
                            <option value="" disabled >Selecione</option>
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
                        <label for="apoio" class="form-label">Tipo de Apoio Necessário</label>
                        <textarea name="apoio" class="form-control" id="apoio"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>


                <h4 id="item-7">Deletar Conta</h4>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/home/candidato/delete" role="button">Deletar Conta</a>

</form>


</body>
</html>
