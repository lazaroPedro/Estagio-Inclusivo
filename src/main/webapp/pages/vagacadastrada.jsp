<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Estagio Inclusivo</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
    <%@ include file="/assets/components/header.jsp" %>

    <div class="container-xl mt-5 pt-5">
        <c:if test="${usuarioLogado.tipoUsuario == TipoUsuario.CANDIDATO}">
            <h3 class="modal-title">Vagas em que se candidatou:</h3>

            <c:choose>
            <c:when test="${vagas != null  && !vagas.isEmpty()}">
                <c:forEach var="vaga" items="${vagas}">

                    <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/vaga?id=${vaga.id}">
                        <div class="card mb-3 ">
                            <div class="row g-0">
                                <div class="col-4 card-body">
                                    <p class="card-text ">${vaga.empresa.razaoSocial}</p>
                                    <p class="card-text ">${vaga.titulo}</p>

                                </div>
                                <div class="col-4">

                                    <div class="card-body">
                                        <h5 class="card-title">${vaga.descricao} </h5>
                                        <p class="card-text ">${vaga.requisitos}</p>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="card-body">
                                        <p class="card-text ">${vaga.beneficios}</p>
                                        <p class="card-text ">${vaga.qtdVagas}</p>
                                        <p class="card-text ">${vaga.status}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>


                </c:forEach>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/" role="button">Buscar Vagas</a>

            </c:when>
            <c:otherwise>
                <div class="card mb-3 ">
                    <div class="row g-0">
                        <div class="card-body ">
                            <h5 class="card-title">Você não se candidatou a nenhuma vaga.</h5>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/" role="button">Buscar Vagas</a>


                        </div>
                    </div>
                </div>
            </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${usuarioLogado.tipoUsuario == TipoUsuario.EMPRESA}">
            <h3 class="modal-title">Vagas cadastradas:</h3>


            <c:choose>
            <c:when test="${vagas != null  && !vagas.isEmpty()}">
                <c:forEach var="vaga" items="${vagas}">

                    <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/home/empresa/vaga?id=${vaga.id}">
                        <div class="card mb-3 ">
                            <div class="row g-0">
                                <div class="col-4 card-body">
                                    <p class="card-text ">${vaga.empresa.razaoSocial}</p>
                                    <p class="card-text ">${vaga.titulo}</p>

                                </div>
                                <div class="col-4">

                                    <div class="card-body">
                                        <h5 class="card-title">${vaga.descricao} </h5>
                                        <p class="card-text ">${vaga.requisitos}</p>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="card-body">
                                        <p class="card-text ">${vaga.beneficios}</p>
                                        <p class="card-text ">${vaga.qtdVagas}</p>
                                        <p class="card-text ">${vaga.status}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>


                </c:forEach>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/" role="button">Buscar Vagas</a>

            </c:when>
            <c:otherwise>
                <div class="card mb-3 ">
                    <div class="row g-0">
                        <div class="card-body">
                            <h5 class="card-title">Você não cadastrou nenhuma vaga. </h5>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/pages/cadastrovagas.jsp" role="button">Cadastrar Vaga</a>
                        </div>
                    </div>
                </div>
            </c:otherwise>
            </c:choose>
        </c:if>






    </div>


    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>

</body>
</html>
