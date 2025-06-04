
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Estagio Inclusivo</title>
    <meta charset="UTF-8">
</head>

    <style>

        .page-title-section {
            background-color: var(--primary-color);
            padding-top: 80px;
            padding-bottom: 120px;
            position: relative;
            overflow: hidden;
        }

        .page-title-section::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 100px;
            background: var(--white);
            border-top-left-radius: 50% 100px;
            border-top-right-radius: 50% 100px;
            transform: translateY(50%);
            z-index: 1;
        }

        .section-subtitle {
            color: var(--white);
            padding-top: 40px;
        }



        .job-card-link {
            display: block;
        }

        .job-card {
            border: 1px solid var(--border-light);
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            cursor: pointer;
        }

        .job-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .job-card .card-body {
            padding: 1.5rem;
        }

        .star-badge {
            background-color: var(--background-accent);
            width: 35px;
            height: 35px;
            border-radius: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .star-badge i {
            color: var(--primary-color);
            font-size: 1.1em;
        }

        .job-title {
            font-size: 1.25em;
            font-weight: 600;
            color: var(--dark-grey);
        }

        .company-name {
            font-size: 0.95em;
            color: var(--medium-grey);
        }

        .job-details {
            text-align: right;
        }

        .job-salary {
            font-size: 1.1em;
            color: #28a745;
            font-weight: 500;
        }

        .apply-now-button {
            background-color: var(--primary-color);
            color: var(--white);
            border: none;
            padding: 0.6rem 1.2rem;
            font-size: 0.9em;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .apply-now-button:hover {
            background-color: var(--darker-accent);
        }




        @media (max-width: 800px) {


            .job-card .card-body {
                flex-direction: column;
                align-items: flex-start !important;
            }
            .job-details {
                text-align: left !important;
                margin-top: 15px;
                width: 100%;
            }
            .apply-now-button {
                width: 100%;
            }
        }
    </style>

<body>

    <%@ include file="/assets/components/header.jsp" %>


        <c:if test="${usuarioLogado.tipoUsuario == TipoUsuario.CANDIDATO}">

    <section class="page-title-section text-center position-relative">
                <h1 class="section-subtitle">Vagas em que se candidatou:</h1>
    </section>


    <div class="container-xl">

    <c:forEach var="vaga" items="${vagas}">
        <a href="${pageContext.request.contextPath}/vaga?id=${vaga.id}" class="job-card-link text-decoration-none text-reset">
            <div class="job-card card mb-3">
                <div class="card-body d-flex flex-column flex-md-row align-items-md-center justify-content-between">
                    <div class="d-flex align-items-center mb-3 mb-md-0">
                        <div class="star-badge position-relative me-3">
                            <i class="fas fa-briefcase logo-icon"></i>
                        </div>

                        <div>
                            <h5 class="job-title mb-1">${vaga.titulo}</h5>
                            <p class="company-name text-muted mb-0">${vaga.empresa.nome}</p>
                            <small class="text-muted"><i class="far fa-user me-1"></i> ${vaga.qtdVagas} vagas</small>
                        </div>
                    </div>
                    <div class="job-details text-md-end">
                        <button class="apply-now-button">
                            Ver Detalhes <i class="fas fa-arrow-right ms-2"></i>
                        </button>
                    </div>
                </div>
            </div>
        </a>

    </c:forEach>



    <div class="d-flex justify-content-around pt-3">
        <a class="apply-now-button" href="${pageContext.request.contextPath}/index" role="button">Buscar Vagas</a>
        </div>
            </div>
                </c:if>

                <c:if test="${usuarioLogado.tipoUsuario == TipoUsuario.EMPRESA}">
        <section class="page-title-section text-center position-relative">
                        <h1 class="section-subtitle">Vagas cadastradas:</h1>
        </section>
    <div class="container-xl">




        <c:forEach var="vaga" items="${vagas}">
        <a href="${pageContext.request.contextPath}/home/empresa/vaga?id=${vaga.id}" class="job-card-link text-decoration-none text-reset">
            <div class="job-card card mb-3">
                <div class="card-body d-flex flex-column flex-md-row align-items-md-center justify-content-between">
                    <div class="d-flex align-items-center mb-3 mb-md-0">
                        <div class="star-badge position-relative me-3">
                            <i class="fas fa-briefcase logo-icon"></i>
                        </div>

                        <div>
                            <h5 class="job-title mb-1">${vaga.titulo}</h5>
                            <p class="company-name text-muted mb-0">${vaga.empresa.nome}</p>
                            <small class="text-muted"><i class="far fa-user me-1"></i> ${vaga.qtdVagas} vagas</small>
                        </div>
                    </div>
                    <div class="job-details text-md-end">
                        <button class="apply-now-button">
                            Ver Detalhes <i class="fas fa-arrow-right ms-2"></i>
                        </button>
                    </div>
                </div>
            </div>
        </a>
        </c:forEach>
        <div class="d-flex justify-content-around pt-3">
            <a class="apply-now-button" href="${pageContext.request.contextPath}/index" role="button">Buscar Vagas</a>

                <a class="apply-now-button" href="${pageContext.request.contextPath}/pages/cadastrovagas.jsp" role="button">Cadastrar Vaga</a>
        </div>
    </div>
    </c:if>















</body>
</html>
