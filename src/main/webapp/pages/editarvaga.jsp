<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page import="java.time.format.DateTimeFormatter" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalhes da Vaga</title>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css"
	rel="stylesheet">
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>

</head>
<style>


	/* Seção Principal da Página (Base do Job Portal, mas com container centralizado) */
	.job-portal-main-section {
		background-color: var(--background-accent); /* Fundo roxo com opacidade */
		min-height: 100vh;
		padding-top: 50px;
		padding-bottom: 50px;
	}

	.job-portal-main-section .content-container {
		max-width: 1000px; /* Largura máxima para o conteúdo centralizado */
	}

	/* Card Principal de Detalhes da Vaga (NOVO ESTILO - VOCÊ GOSTOU DESTE!) */
	.job-detail-main-card {
		background-color: var(--white);
		border: none;
		border-radius: 12px;
		box-shadow: 0 8px 30px rgba(0, 0, 0, 0.08);
		padding: 0; /* Padding será dentro do card-body */
	}

	.job-detail-main-card .card-body {
		padding: 2rem; /* Padding interno */
	}

	.job-detail-main-card .job-title-main {
		font-size: 2.2em;
		font-weight: 700;
		color: var(--dark-grey);
	}

	.job-detail-main-card .job-info-row {
		margin-bottom: 1.5rem;
	}

	.job-detail-main-card .company-brand-wrapper {
		width: 60px; /* Tamanho do logo */
		height: 60px;
		background-color: var(--background-accent);
		border-radius: 8px; /* Mais arredondado */
		display: flex;
		align-items: center;
		justify-content: center;
		flex-shrink: 0;
	}

	.job-detail-main-card .company-brand-wrapper img {
		max-width: 80%;
		max-height: 80%;
		object-fit: contain;
	}

	.job-detail-main-card .company-name-detail {
		font-size: 1.1em;
		font-weight: 600;
		color: var(--dark-grey);
	}

	.job-detail-main-card .job-meta-list {
		margin-top: 1.5rem;
		padding-left: 0;
		list-style: none;
	}

	.job-detail-main-card .job-meta-list li {
		font-size: 0.95em;
		color: var(--medium-grey);
		margin-bottom: 8px;
		display: flex;
		align-items: center;
	}

	.job-detail-main-card .job-meta-list li i {
		color: var(--primary-color);
		width: 25px; /* Ajuste para ícones */
		text-align: center;
		flex-shrink: 0;
	}

	.job-detail-main-card hr {
		border-top: 1px solid var(--border-light);
		margin-top: 2rem;
		margin-bottom: 2rem;
	}

	.job-detail-main-card .detail-label {
		font-size: 1em;
		font-weight: 600;
		color: var(--dark-grey); /* Labels em cinza escuro */
		margin-bottom: 0.5rem;
		display: flex;
		align-items: center;
	}

	.job-detail-main-card .detail-label i {
		color: var(--primary-color); /* Ícones dos labels em roxo */
		width: 25px;
		text-align: center;
		flex-shrink: 0;
	}

	.job-detail-main-card .detail-text {
		font-size: 1em;
		color: var(--medium-grey);
		padding-left: 30px; /* Indenta o texto */
		line-height: 1.6;
	}

	.job-detail-main-card .card-footer {
		background-color: var(--white);
		border-top: 1px solid var(--border-light);
		padding: 1rem 1.5rem;
		border-bottom-left-radius: 12px;
		border-bottom-right-radius: 12px;
	}

	.job-detail-main-card .btn-primary {
		background-color: var(--primary-color);
		border-color: var(--primary-color);
		color: var(--white);
		font-weight: 500;
		padding: 0.6rem 1.2rem;
		border-radius: 8px;
	}

	.job-detail-main-card .btn-primary:hover {
		background-color: var(--darker-accent);
		border-color: var(--darker-accent);
	}

	.job-detail-main-card .btn-outline-danger {
		border-color: #dc3545;
		color: #dc3545;
		font-weight: 500;
		padding: 0.6rem 1.2rem;
		border-radius: 8px;
	}

	.job-detail-main-card .btn-outline-danger:hover {
		background-color: #dc3545;
		color: var(--white);
	}


	/* Seção de Candidatos (Container) */
	.candidates-section-card {
		background-color: var(--white);
		border: none;
		border-radius: 12px;
		box-shadow: 0 8px 30px rgba(0, 0, 0, 0.08);
	}

	.candidates-section-card .card-header {
		background-color: var(--white);
		border-bottom: 1px solid var(--border-light);
		border-top-left-radius: 12px;
		border-top-right-radius: 12px;
		padding: 1.25rem 1.5rem;
	}

	.candidates-section-card .card-header-title {
		font-size: 1.4em;
		font-weight: 600;
		color: var(--dark-grey);
	}

	.candidates-section-card .card-body {
		padding: 1.5rem;
	}

	/* CARD INDIVIDUAL DO CANDIDATO (LISTA SIMPLES) */
	.candidate-list-item {
		border: 1px solid var(--border-light);
		border-radius: 8px;
		background-color: var(--white);
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.03); /* Sombra mais leve que o card principal */
		transition: transform 0.2s ease, box-shadow 0.2s ease;
	}

	.candidate-list-item:hover {
		transform: translateY(-3px);
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
	}

	.candidate-list-item:not(:last-child) {
		border-bottom: none; /* Remove borda inferior entre itens se estiver usando grid, mas mantém a sombra*/
		margin-bottom: 1rem; /* Espaçamento entre os cards */
	}

	.candidate-item-info {
		flex-grow: 1;
	}

	.candidate-item-name {
		font-size: 1.25em;
		font-weight: 600;
		color: var(--dark-grey);
		margin-bottom: 0.5rem;
	}

	.candidate-item-details {
		padding-left: 0;
		list-style: none;
	}

	.candidate-item-details li {
		font-size: 0.9em;
		color: var(--medium-grey);
		margin-bottom: 0.4rem;
		display: flex;
		align-items: center;
	}

	.candidate-item-details li i {
		color: var(--primary-color); /* Ícones dos detalhes em roxo */
		width: 20px;
		text-align: center;
		flex-shrink: 0;
	}

	.candidate-item-actions {
		flex-shrink: 0;
	}

	.candidate-item-actions .access-cv-button {
		background-color: var(--primary-color);
		color: var(--white);
		border: none;
		padding: 0.6rem 1.2rem;
		font-size: 0.9em;
		border-radius: 5px;
		transition: background-color 0.2s ease;
	}

	.candidate-item-actions .access-cv-button:hover {
		background-color: var(--darker-accent);
	}

	/* Alerta de nenhum candidato (Mantido o mesmo) */
	.alert-info {
		background-color: var(--background-accent);
		color: var(--primary-color);
		border-color: transparent;
		font-weight: 500;
	}

	/* Media Queries para Responsividade */
	@media (max-width: 991.98px) {
		.job-portal-main-section .content-container {
			padding: 0 15px; /* Adiciona padding nas laterais em mobile */
		}
		.job-detail-main-card .job-title-main {
			font-size: 1.8em;
		}
		.job-detail-main-card .detail-text,
		.job-detail-main-card .job-meta-list li {
			font-size: 0.9em;
		}
		.job-detail-main-card .d-flex.justify-content-end {
			flex-direction: column;
			align-items: stretch;
		}
		.job-detail-main-card .btn {
			width: 100%;
			margin-bottom: 10px;
			margin-right: 0 !important;
		}
		.job-detail-main-card .btn:last-child {
			margin-bottom: 0;
		}

		/* Candidato em Lista Simples */
		.candidate-list-item {
			flex-direction: column; /* Empilha info e botão */
			align-items: flex-start !important;
			padding: 1rem !important;
		}
		.candidate-item-info {
			width: 100%;
			margin-bottom: 10px !important;
		}
		.candidate-item-details {
			flex-direction: column; /* Detalhes da lista empilhados */
		}
		.candidate-item-details li {
			margin-right: 0 !important;
			margin-bottom: 0.4rem;
			width: 100%;
		}
		.candidate-item-actions {
			width: 100%;
		}
		.candidate-item-actions .access-cv-button {
			width: 100%;
		}
	}


</style>
<body class="bd-light">
	<%@ include file="/assets/components/header.jsp"%>



	<main class="job-portal-main-section py-5">
		<div class="container-xl content-container"> <div class="row justify-content-center"> <div class="card job-detail-main-card mb-5">
			<div class="card-body p-4">
				<h2 class="job-title-main mb-4">${vaga.titulo}</h2>

				<div class="job-info-row d-flex align-items-center mb-3">

					<div>
						<h5 class="company-name-detail mb-0">${usuarioLogado.nome}</h5>
					</div>
				</div>

				<ul class="job-meta-list list-unstyled">
					<li><i class="fas fa-map-marker-alt me-2"></i> ${vaga.endereco.rua} - ${vaga.endereco.bairro}, ${vaga.endereco.municipio} - ${vaga.endereco.estado}</li>
				<!--	<li><i class="fas fa-dollar-sign me-2"></i> R$  </li>-->
				</ul>

				<hr class="my-4">

				<h6 class="detail-label mb-2"><i class="fas fa-file-alt me-2"></i> Descrição:</h6>
				<p class="detail-text mb-4">${vaga.descricao}</p>

				<h6 class="detail-label mb-2"><i class="fas fa-list-check me-2"></i> Requisitos:</h6>
				<p class="detail-text mb-4">${vaga.requisitos}</p>

				<h6 class="detail-label mb-2"><i class="fas fa-gift me-2"></i> Benefícios:</h6>
				<p class="detail-text mb-4">${vaga.beneficios}</p>

				<h6 class="detail-label mb-2"><i class="fas fa-users me-2"></i> Quantidade de Vagas:</h6>
				<p class="detail-text mb-4">${vaga.qtdVagas}</p>

				<div class="d-flex justify-content-end mt-4">
					<a class="btn btn-primary me-2" href="${pageContext.request.contextPath}/home/empresa/full"><i class="fas fa-edit me-2"></i> Editar Vaga</a>
				</div>
			</div>
		</div>

			<div class="card candidates-section-card mb-5">
				<div class="card-header">
					<h4 class="card-header-title mb-0">Candidatos Cadastrados</h4>
				</div>
				<div class="card-body">

					<c:forEach var="candidato" items="${candidatos}">

					<div class="candidate-list-item d-flex flex-column flex-md-row align-items-md-center justify-content-between p-3 mb-3">
						<div class="candidate-item-info flex-grow-1 mb-2 mb-md-0">
							<h5 class="candidate-item-name mb-1"> ${candidato.nome}</h5>
							<ul class="candidate-item-details list-unstyled d-flex flex-wrap mb-0">
								<li class="me-3"><i class="fas fa-envelope me-2"></i> <span class="text-muted">${candidato.email}</span></li>
								<li class="me-3"><i class="fas fa-phone me-2"></i> <span class="text-muted">${candidato.telefone}</span></li>
								<li class="me-3"><i class="fas fa-id-card me-2"></i> <span class="text-muted">${candidato.cpf}</span></li>

							</ul>
						</div>
						<div class="candidate-item-actions flex-shrink-0">
							<a href="${pageContext.request.contextPath}/curriculo?id=${candidato.id}" class="btn btn-primary btn-sm access-cv-button">
								<i class="fas fa-file-pdf me-2"></i> Acessar Currículo
							</a>
						</div>
					</div>

					</c:forEach>

				</div>
			</div>

		</div>
		</div>
	</main>






</body>
</html>