<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 04/05/2025
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.enums.Genero" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
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
    <script>
        function toggleForm(formId) {
            const form = document.getElementById(formId);
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }
        function formatDate(form){
            const [year, month, day] = form.split("-");
            return `${day}/${month}/${year}`;
        }

        function formatarCPF(cpf) {
            return cpf.slice(0,3) + "." + cpf.slice(3,6) + "." + cpf.slice(6,9) + "-" + cpf.slice(9);
        }

        function formatarTelefone(tel) {
            return "(" + tel.slice(0,2) + ") " + tel.slice(2,7) + "-" + tel.slice(7);
        }
        document.addEventListener("DOMContentLoaded", function() {
            const myModal = new bootstrap.Modal(document.getElementById('successModal'));
            myModal.show();
        });
    </script>
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>


<div class=" row mt-5 pt-5">
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
                        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" >
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="successModalLabel">Sucesso</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                Alterado com sucesso!
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                              </div>
                            </div>
                          </div>
                        </div>
                    <% } else if (request.getAttribute("erros") != null){%>
                <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="successModalLabel">Erros nos campos</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                ${erros.getMessage()}
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                              </div>
                            </div>
                          </div>
                        </div>


                <% } else if (request.getAttribute("erro") != null){%>
                <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="successModalLabel">Erros nos campos</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ${erro}
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>


                <%}



                %>

                <h5 id="item-2">Alterar Perfil</h5>
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Nome: ${candidato.nome}</p>
                        <p class="card-text">
                            Telefone: ${candidato.telefone}

                        </p>                        <p class="card-text">
                            CPF:
                            ${candidato.cpf}

                        </p>                        <p class="card-text">Genero: ${candidato.genero.name()} </p>
                        <p class="card-text">
                            Data de Nascimento:
                            ${candidato.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}

                        </p>
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template')">Editar</button>
                        </div>

                <form action="${pageContext.request.contextPath}/home/candidato/put" method="post" id="form-template" style="display: none;" class="mt-3">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${candidato.nome}" >
                    </div>
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" value="${candidato.telefone}">
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control" id="cpf" value="${candidato.cpf}">
                    </div>
                    <div class="mb-3">
                        <label for="genero" class="form-label"  >Gênero</label>
                            <select name="genero" class="form-select" id="genero"  >
                                <option value=""  >Selecione</option>
                                <option value="MASCULINO" ${candidato.genero == Genero.MASCULINO ? 'selected' : ''} >Masculino</option>
                                <option value="FEMININO" ${candidato.genero == Genero.FEMININO ? 'selected' : ''}>Feminino</option>
                                <option value="OUTRO" ${candidato.genero == Genero.OUTRO ? 'selected' : ''}>Outro</option>
                                <option value="NAO_INFORMAR" ${candidato.genero == Genero.NAO_INFORMAR ? 'selected' : ''}>Não desejo informar</option>
                            </select>

                    </div>
                    <div class="mb-3">
                        <label for="data" class="form-label" id="data" name="data" >Data de Nascimento</label>
                        <input type="date" name="nascimento" class="form-control" id="data" value="${candidato.dataNascimento}">
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>

                <h4 id="item-3">Alterar Dados de Acesso</h4>
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Email: ${candidato.email} </p>
                        <p class="card-text">Senha: </p>

                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template-')">Editar</button>
                        </div>
                <form action="${pageContext.request.contextPath}/home/usuario/put" method="post" id="form-template-" style="display: none;" class="mt-3">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control" id="email" name="email" value="${candidato.email}">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Antiga Senha</label>
                            <input type="text" class="form-control" id="senha" name="senha" required>
                        </div>
                    <div class="mb-3">
                        <label for="novaSenha" class="form-label">Nova Senha</label>
                        <input type="text" class="form-control" id="novaSenha" name="novaSenha" required>
                    </div>
                        <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>

                <h4 id="item-4">Alterar Curso</h4>
                                <div class="card mb-4 p-2" id="card-template">

                <c:forEach var="curso" items="${cursos}">
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Nome: ${curso.nomeCurso}</p>
                        <p class="card-text">Instituição: ${curso.instituicao}</p>
                        <p class="card-text">Descricao: ${curso.descricao} </p>
                        <p class="card-text">Data Inicio: ${curso.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} </p>
                        <p class="card-text">Data Fim: ${curso.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>

                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template-c#${curso.id}')" >Editar</button>
                            <form action="${pageContext.request.contextPath}/home/curso/delete/id?id=${curso.id}" method="post">
                                <button class="btn btn-danger" >Excluir</button>
                            </form>
                        </div>
                <form action="${pageContext.request.contextPath}/home/curso/put/id?id=${curso.id}" method="post" id="form-template-c#${curso.id}" style="display: none;" class="mt-3">


                <div class="mb-3">
                    <label for="nome" class="form-label">Nome do Curso</label>
                    <input type="text" name="nome" class="form-control" id="nome" value="${curso.nomeCurso}">
                </div>
                <div class="mb-3">
                    <label for="instituicao" class="form-label">Instituição</label>
                    <input type="text" name="instituicao" class="form-control" id="instituicao" value="${curso.instituicao}">
                </div>
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <textarea name="descricao" class="form-control" id="descricao" >${curso.descricao}</textarea>
                </div>
                <div class="mb-3">
                    <label for="inicio" class="form-label">Data de Início</label>
                    <input type="date" name="dataInicio" class="form-control" id="inicio" value="${curso.dataInicio}">
                </div>
                <div class="mb-3">
                    <label for="termino" class="form-label">Data de Término</label>
                    <input type="date" name="dataFim" class="form-control" id="termino" value="${curso.dataFim}">
                </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>
                </c:forEach>
                <div class="card mb-4 p-3" id="card-template">

                <div class="d-flex justify-content-center ">
                    <button class="btn btn-success" onclick="toggleForm('form-template-c-post')">Inserir Novo</button>
                </div>



                <form action="${pageContext.request.contextPath}/home/curso/post" method="post" id="form-template-c-post" style="display: none;" class="mt-3">


                <div class="mb-3">
                    <label for="nome" class="form-label">Nome do Curso</label>
                    <input type="text" name="nome" class="form-control" id="nome" >
                </div>
                <div class="mb-3">
                    <label for="instituicao" class="form-label">Instituição</label>
                    <input type="text" name="instituicao" class="form-control" id="instituicao" >
                </div>
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição</label>
                    <textarea name="descricao" class="form-control" id="descricao" ></textarea>
                </div>
                <div class="mb-3">
                    <label for="inicio" class="form-label">Data de Início</label>
                    <input type="date" name="dataInicio" class="form-control" id="inicio" >
                </div>
                <div class="mb-3">
                    <label for="termino" class="form-label">Data de Término</label>
                    <input type="date" name="dataFim" class="form-control" id="termino" >
                </div>
                    <button type="submit" class="btn btn-success">Adcionar</button>
                </form>
                </div>
                                </div>
                <h5 id="item-5">Alterar Endereco</h5>
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Rua: ${candidato.endereco.rua}</p>
                        <p class="card-text">Bairro: ${candidato.endereco.bairro}</p>
                        <p class="card-text">Municipio: ${candidato.endereco.municipio} </p>
                        <p class="card-text">Estado: ${candidato.endereco.estado} </p>
                        <p class="card-text">CEP: ${candidato.endereco.cep}</p>

                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template-e')">Editar</button>
                        </div>

                <form action="${pageContext.request.contextPath}/home/endereco/put" method="post" id="form-template-e" style="display: none;" class="mt-3">
                    <div class="mb-3">
                        <label for="rua" class="form-label">Rua</label>
                        <input type="text" class="form-control" id="rua" name="rua" value="${candidato.endereco.rua}">
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" name="bairro" class="form-control" id="bairro" value="${candidato.endereco.bairro}">
                    </div>
                    <div class="mb-3">
                        <label for="municipio" class="form-label">Município</label>
                        <input type="text" name="municipio" class="form-control" id="municipio"  value="${candidato.endereco.municipio}">
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" name="estado" class="form-control" id="estado" value="${candidato.endereco.estado}">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" name="cep" class="form-control" id="cep" value="${candidato.endereco.cep}">
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>

                <h5 id="item-6">Alterar Deficiencia</h5>
                                <div class="card mb-4 p-2" id="card-template">

                <c:forEach var="def" items="${deficiencias}">
                    <div class="card mb-4" id="card-template">
                    <div class="card-body">
                    <p class="card-text">Nome: ${def.nome}</p>
                    <p class="card-text">Descricao: ${def.descricao} </p>
                    <p class="card-text">Tipo de Apoio: ${def.tipoApoio} </p>
                    <p class="card-text">Tipo: ${def.tipo.name()}</p>

                    <div class="d-flex justify-content-between">
                    <button class="btn btn-primary" onclick="toggleForm('form-template-d#${def.id}')">Editar</button>
                        <form action="${pageContext.request.contextPath}/home/deficiencia/delete/id?id=${def.id}" method="post">
                            <button class="btn btn-danger" >Excluir</button>
                        </form>


                    </div>


                <form action="${pageContext.request.contextPath}/home/deficiencia/put/id?id=${def.id}" method="post" id="form-template-d#${def.id}" style="display: none;" class="mt-3">

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome da Deficiência</label>
                        <input type="text" name="nome" class="form-control" id="nome" value="${def.nome}">
                    </div>
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <textarea name="descricao" class="form-control" id="descricao">${def.descricao}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="tipo" class="form-label">Tipo de Deficiência</label>
                        <select name="tipo" class="form-select" id="tipo">
                            <option value="FISICA" ${def.tipo == TipoDeficienciaEnum.FISICA ? 'selected' : ''} >Física</option>
                            <option value="VISUAL" ${def.tipo == TipoDeficienciaEnum.VISUAL ? 'selected' : ''}>Visual</option>
                            <option value="AUDITIVA" ${def.tipo == TipoDeficienciaEnum.AUDITIVA ? 'selected' : ''}>Auditiva</option>
                            <option value="INTELECTUAL" ${def.tipo == TipoDeficienciaEnum.INTELECTUAL ? 'selected' : ''}>Intelectual</option>
                            <option value="MENTAL" ${def.tipo == TipoDeficienciaEnum.MENTAL ? 'selected' : ''}>Mental</option>
                            <option value="SENSORIAL" ${def.tipo == TipoDeficienciaEnum.SENSORIAL ? 'selected' : ''}>Sensorial</option>
                            <option value="MULTIPLA" ${def.tipo == TipoDeficienciaEnum.MULTIPLA ? 'selected' : ''}>Múltipla</option>
                            <option value="OUTRA" ${def.tipo == TipoDeficienciaEnum.OUTRA ? 'selected' : ''}>Outra</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="apoio" class="form-label">Tipo de Apoio Necessário</label>
                        <textarea name="tipoApoio" class="form-control" id="apoio">${def.tipoApoio}</textarea>
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
    </div></div>
    </c:forEach>
<div class="card mb-4 p-3" id="card-template">

                <div class="d-flex justify-content-center ">
                    <button class="btn btn-success" onclick="toggleForm('form-template-d-post')">Inserir Novo</button>
                </div>
    <form action="${pageContext.request.contextPath}/home/deficiencia/post" method="post" id="form-template-d-post" style="display: none;" class="mt-3">

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
                        <textarea name="tipoApoio" class="form-control" id="apoio"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success">Adcionar</button>
                </form>
</div></div>

                <h4 id="item-7">Deletar Conta</h4>
                                <div class="d-flex justify-content-center p-5">
                                    <button class="btn btn-danger"  data-bs-toggle="modal" data-bs-target="#modalExcluir">Deletar Conta</button>

                                </div>
            </div></div>
            <div class="modal fade" id="modalExcluir" tabindex="-1" aria-labelledby="modalExcluirLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalExcluirLabel">Confirmar Exclusão</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Deseja excluir sua conta para sempre? (É muito tempo)
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <a href="${pageContext.request.contextPath}/home/curso/delee" class="btn btn-danger">Confirmar</a>
                        </div>
                    </div>
                </div>
            </div>

</div>




</body>
</html>
