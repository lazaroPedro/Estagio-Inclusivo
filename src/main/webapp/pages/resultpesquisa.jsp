<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Resultados</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>
</head>
<body>
    <%@ include file="/assets/components/header.jsp" %>
    <div class="container-xl mt-5 pt-5">

        <c:choose>
            <c:when test="${vagas != null && !vagas.isEmpty()}">
                <h5 class="card-title">Vagas:</h5>

            <c:forEach var="vaga" items="${vagas}">

                <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/vaga?id=${vaga.id}">
                    <div class="card mb-3 ">
                        <div class="row g-0">
                            <div class="col-4 card-body">
                                <p class="card-text ">${vaga.empresa.razaoSocial}</p>
                                <p class="card-text ">Vagas: ${vaga.qtdVagas}</p>

                            </div>
                            <div class="col-4">

                                <div class="card-body">
                                    <h5 class="card-title"> ${vaga.titulo} </h5>
                                    <p class="card-text "> ${vaga.descricao}</p>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="card-body">
                                    <p class="card-text ">${vaga.requisitos}</p>
                                    <p class="card-text "> ${vaga.beneficios}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
            </c:when>
                        <c:otherwise>
                <div class="card mb-3 ">
                 <div class="row g-0">
                     <div class="card-body text-bg-danger">
                        <h5 class="card-title">Vaga não encontrada</h5>
                     </div>
                 </div>
             </div>
            </c:otherwise>

        </c:choose>
            <c:choose>

                <c:when test="${empresas != null && !empresas.isEmpty()}">
                    <h5 class="card-title">Empresas:</h5>


                    <c:forEach var="empresa" items="${empresas}">
                        <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/empresa?id=${empresa.id}">
                            <div class="card mb-3 ">
                                <div class="row g-0">
                                    <div class="col-4 card-body">
                                        <p class="card-text ">${empresa.razaoSocial}</p>

                                    </div>
                                    <div class="col-4">

                                        <div class="card-body">
                                            <h5 class="card-title"> Telefone: ${empresa.telefone} </h5>
                                            <p class="card-text "> Email: ${empresa.email}</p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:when>
            <c:otherwise>
                <div class="card mb-3 ">
                 <div class="row g-0">
                     <div class="card-body text-bg-danger">
                        <h5 class="card-title">Empresa não encontrada</h5>
                     </div>
                 </div>
             </div>
            </c:otherwise>

        </c:choose>

        <c:choose>

            <c:when test="${curriculos != null && !curriculos.isEmpty()}">
                <h5 class="card-title">Curriculos:</h5>


                <c:forEach var="curriculo" items="${curriculos}">
                    <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/home/curriculo/id?id=${curriculo.candidato.id}">
                        <div class="card mb-3 ">
                            <div class="row g-0">
                                <div class="col-4 card-body">
                                    <p class="card-text ">${curriculo.candidato.nome}</p>
                                    <p class="card-text ">${curriculo.candidato.email}</p>

                                </div>
                                <div class="col-4">

                                    <div class="card-body">
                                        <h5 class="card-title"> ${curriculo.habilidades} </h5>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="card mb-3 ">
                    <div class="row g-0">
                        <div class="card-body text-bg-danger">
                            <h5 class="card-title">Curriculo não encontrado</h5>
                        </div>
                    </div>
                </div>
            </c:otherwise>

        </c:choose>
    </div>

</body>
</html>
