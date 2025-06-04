

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Estagio Inclusivo</title>
    <meta charset="UTF-8">
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


    .job-filter-section {
        margin-top: -80px;
        position: relative;
        z-index: 3;
    }

    .filter-bar {
        background-color: var(--white);
        border: 1px solid var(--border-light);
        box-shadow: 0 5px 20px var(--shadow-light);
    }

    .filter-bar .input-group-text {
        background-color: var(--white);
        border-right: none;
        border-color: var(--border-light);
        color: var(--light-grey);
    }

    .filter-bar .form-control,
    .filter-bar .form-select {
        border-color: var(--border-light);
        box-shadow: none;
        padding: 0.75rem 1rem;
        color: var(--medium-grey);
    }

    .filter-bar .form-control::placeholder {
        color: var(--light-grey);
    }

    .filter-bar .filter-button {
        background-color: var(--primary-color);
        color: var(--white);
        font-weight: 600;
        padding: 0.75rem 1.5rem;
        border: none;
        transition: background-color 0.3s ease;
    }

    .filter-bar .filter-button:hover {
        background-color: var(--darker-accent);
    }

    .job-list-section {
        background-color: var(--white);
        padding-top: 40px;
    }

    .job-list-section .section-subtitle {
        font-size: 1.5em;
        font-weight: 600;
        color: var(--dark-grey);
        margin-left: 15px;
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
        transition: background-color 0.3s ease;
    }

    .apply-now-button:hover {
        background-color: var(--darker-accent);
    }




    @media (max-width: 800px) {

        .page-title-section::after {
            height: 60px;
        }
        .job-filter-section {
            margin-top: -50px;
        }
        .filter-bar {
            flex-direction: column;
        }
        .filter-bar .input-group,
        .filter-bar .filter-button {
            width: 100%;
            margin-right: 0 !important;
            margin-bottom: 10px;
        }
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



<section class="page-title-section text-center position-relative">

        </section>

    <section class="job-filter-section py-4">
        <div class="container">
            <form action="${pageContext.request.contextPath}/search" method="post" class="filter-bar p-3 rounded-3 shadow-sm d-flex flex-wrap align-items-center justify-content-center">

    <div class="input-group flex-grow-1 me-2 mb-2 mb-md-0">
        <span class="input-group-text"><i class="fas fa-briefcase"></i></span>
        <input type="text" class="form-control" name="pesquisa" placeholder="Buscar cargo, empresa, currículo..." required>
    </div>

    <div class="input-group flex-grow-1 me-2 mb-2 mb-md-0">
        <span class="input-group-text"><i class="fas fa-filter"></i></span> <select class="form-select" name="filtro" required>
            <option selected disabled value="">Selecione tipo de busca...</option>
            <option value="0">Vagas</option>
            <option value="1">Empresas</option>
            <option value="2">Currículos</option>
        </select>
    </div>

    <button class="btn btn-primary filter-button" type="submit">
        <i class="fas fa-search me-2"></i> Buscar
    </button>
</form>
        </div>
    </section>




<section class="job-list-section py-5">
        <div class="container">
            <h4 class="section-subtitle mb-4">Popular</h4>
    <c:forEach var="vaga" items="${lista_vagas}">
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


        </div>

    </section>



    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>

</body>
</html>
