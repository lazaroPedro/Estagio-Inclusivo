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
                    <a class="nav-link" href="#item-4">Alterar Endereco</a>
                    <a class="nav-link" href="#item-5">Alterar Vagas</a>
                    <a class="nav-link" href="#item-6">Deletar Conta</a>



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
                        <p class="card-text">Nome: ${empresa.nome}</p>
                        <p class="card-text">Telefone: ${empresa.telefone}</p>
                        <p class="card-text">CNPJ: ${empresa.cnpj}</p>
                        <p class="card-text">Razão Social: ${empresa.razaoSocial} </p>
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template')">Editar</button>
                        </div>

                <form action="${pageContext.request.contextPath}/home/empresa/put" method="post" id="form-template" style="display: none;" class="mt-3">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${empresa.nome}" >
                    </div>
                    <div class="mb-3">
                        <label for="razao" class="form-label">Razão Social</label>
                        <input type="text" class="form-control" id="razao" name="razaoSocial" value="${empresa.razaoSocial}" >
                    </div>
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" value="${empresa.telefone}">
                    </div>
                    <div class="mb-3">
                        <label for="cnpj" class="form-label">CNPJ</label>
                        <input type="text" name="cnpj" class="form-control" id="cnpj" value="${empresa.cnpj}">
                    </div>

                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>

                <h4 id="item-3">Alterar Dados de Acesso</h4>
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Email: ${empresa.email} </p>
                        <p class="card-text">Senha: </p>

                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template-')">Editar</button>
                        </div>
                <form action="${pageContext.request.contextPath}/home/usuario/put" method="post" id="form-template-" style="display: none;" class="mt-3">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" class="form-control" id="email" name="email" value="${empresa.email}">
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



                <h5 id="item-4">Alterar Endereco</h5>
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Rua: ${empresa.endereco.rua}</p>
                        <p class="card-text">Bairro: ${empresa.endereco.bairro}</p>
                        <p class="card-text">Municipio: ${empresa.endereco.municipio} </p>
                        <p class="card-text">Estado: ${empresa.endereco.estado} </p>
                        <p class="card-text">CEP: ${empresa.endereco.cep}</p>

                        <div class="d-flex justify-content-between">
                            <button class="btn btn-primary" onclick="toggleForm('form-template-e')">Editar</button>
                        </div>

                <form action="${pageContext.request.contextPath}/home/endereco/put" method="post" id="form-template-e" style="display: none;" class="mt-3">
                    <div class="mb-3">
                        <label for="rua" class="form-label">Rua</label>
                        <input type="text" class="form-control" id="rua" name="rua" value="${empresa.endereco.rua}">
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" name="bairro" class="form-control" id="bairro" value="${empresa.endereco.bairro}">
                    </div>
                    <div class="mb-3">
                        <label for="municipio" class="form-label">Município</label>
                        <input type="text" name="municipio" class="form-control" id="municipio"  value="${empresa.endereco.municipio}">
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" name="estado" class="form-control" id="estado" value="${empresa.endereco.estado}">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" name="cep" class="form-control" id="cep" value="${empresa.endereco.cep}">
                    </div>
                    <button type="submit" class="btn btn-success">Atualizar</button>
                </form>
                    </div>
                </div>
                <h5 id="item-5">Alterar Vagas</h5>
 <div class="card mb-4 p-2" id="card-template">

                <c:forEach var="vaga" items="${vagas}">
                <div class="card mb-4" id="card-template">
                    <div class="card-body">
                        <p class="card-text">Titulo: ${vaga.titulo}</p>
                        <p class="card-text">Descricao: ${vaga.descricao} </p>
                        <p class="card-text">Requisitos: ${vaga.requisitos}</p>
                        <p class="card-text">Beneficios: ${vaga.beneficios} </p>
                        <p class="card-text">Quantidade de Vagas: ${vaga.qtdVagas}</p>

                        <div class="d-flex justify-content-between">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/home/vaga/put?id=${vaga.id}" >Editar</a>
                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/home/vaga/delete?id=${vaga.id}">Excluir</a>

                        </div>

                    </div>
                </div>
                </c:forEach>
                <div class="card mb-4 p-3" id="card-template">

                <div class="d-flex justify-content-center ">
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/home/vaga/insert">Inserir Novo</a>
                </div>




                </div>
                                </div>
                <h4 id="item-6">Deletar Conta</h4>
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
                            <a href="${pageContext.request.contextPath}/home/curso/delete" class="btn btn-danger">Confirmar</a>
                        </div>
                    </div>
                </div>
            </div>

</div>




</body>
</html>
