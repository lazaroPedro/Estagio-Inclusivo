<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalhes da Vaga</title>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>


<style>
:root {
	--roxo: #805DFF;
}

.bg-roxo {
	background-color: var(--roxo) !important;
}

.text-roxo {
	color: var(--roxo) !important;
}

.border-roxo {
	border: 2px solid var(--roxo) !important;
}

.btn-roxo {
	background-color: var(--roxo);
	color: white;
}

.btn-roxo:hover {
	background-color: #6841ff;
	color: white;
}

body {
	padding-top: 70px;
	background-color: #f8f9fa;
}
</style>

</head>

<body class="bd-light">
	<%@ include file="/assets/components/header.jsp"%>


	<div class=" container my-5" style="max-width: 800px;">
		<c:if test="${param.sucesso == '1'}">
			<div class="alert alert-success text-center" role="alert">Vaga
				cadastrada com sucesso!</div>
		</c:if>


		<div class="card border-roxo mb-4">
			<div class="card-body">
				<h2 class="card-title text-roxo">${vaga.titulo}</h2>
				<small class="text-muted">${vaga.empresa.razaoSocial} .
					${vaga.endereco.municipio}, ${vaga.endereco.estado}</small>
			</div>
		</div>

		<div class="card mb-3 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Descrição</h6>
				<p class="mb-0">${vaga.descricao}</p>
			</div>
		</div>
		<div class="card mb-3 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Requisitos</h6>
				<p class="mb-0">${vaga.requisitos}</p>
			</div>
		</div>

		<div class="card mb-3 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Benefícios</h6>
				<p class="mb-0">${vaga.beneficios}</p>
			</div>
		</div>


		<div class="card mb-3 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Informações da Empresa</h6>
				<ul class="list-unstyled mb-0">
					<li><Strong>Razão Social:</Strong> ${vaga.empresa.razaoSocial}</li>
					<li><Strong>Nome Fantasia:</Strong> ${vaga.empresa.nome}</li>
					<li><Strong>CNPJ:</Strong> ${vaga.empresa.cnpj}</li>
					<li><Strong>Email:</Strong> ${vaga.empresa.email}</li>
					<li><Strong>Telefone:</Strong> ${vaga.empresa.telefone}</li>
				</ul>
			</div>
		</div>

		<div class="card mb-3 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Localização</h6>
				<p class="mb-0">${vaga.endereco.rua},${vaga.endereco.bairro},
					${vaga.endereco.municipio} - ${vaga.endereco.estado}</p>
			</div>
		</div>

		<div class="card mb-4 border-roxo">
			<div class="card-body">
				<h6 class="text-roxo">Quantidade de Vagas</h6>
				<span class="fw-bold">${vaga.qtdVagas}</span>
			</div>
		</div>


		<c:if test="${usuarioLogado.tipoUsuario == 'CANDIDATO'}">
			<c:choose>
				<c:when test="${candidatado == 1}">
					<div class="text-center">
						<form method="POST"
							action="${pageContext.request.contextPath}/home/candidatovaga/delete?id=${vaga.id}">

							<button class="btn btn-danger">Descandidatar-se</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="text-center">
						<form method="POST"
							action="${pageContext.request.contextPath}/home/candidatovaga?id=${vaga.id}">

							<button class="btn btn-roxo">Candidatar-se</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>


		</c:if>
	</div>
</body>
</html>