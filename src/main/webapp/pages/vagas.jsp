<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalhes da Vaga</title>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body class="bd-light">


	<div class=" container mt-5 mb-5" style="max-width: 800px;">
		<c:if test="${param.sucesso == '1'}">
			<div class="alert alert-success text-center" role="alert">Vaga
				cadastrada com sucesso!</div>
		</c:if>

		<div class="border rouded-3 p-3 mb-4 gb-white">
			<h4 class="mb-0">${vaga.titulo}</h4>
			<small class="text-muted">${vaga.empresa.razaoSocial} .
				${vaga.endereco.municipio}, ${vaga.endereco.estado}</small>
		</div>


		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Descrição</h6>
			<p class="mb-0">${vaga.descricao}</p>
		</div>
		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Requisitos</h6>
			<p class="mb-0">${vaga.requisitos}</p>
		</div>
		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Benefícios</h6>
			<p class="mb-0">${vaga.beneficios}</p>
		</div>

		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Informações da Empresa</h6>
			<ul class="list-unstyled mb-0">
				<li><Strong>Razão Social:</Strong> ${vaga.empresa.razaoSocial}</li>
				<li><Strong>Nome Fantasia:</Strong> ${vaga.empresa.nome}</li>
				<li><Strong>CNPJ:</Strong> ${vaga.empresa.cnpj}</li>
				<li><Strong>Email:</Strong> ${vaga.empresa.email}</li>
				<li><Strong>Telefone:</Strong> ${vaga.empresa.telefone}</li>
			</ul>
		</div>
		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Localização</h6>
			<p class="mb-0">${vaga.endereco.rua},${vaga.endereco.bairro},
				${vaga.endereco.municipio} - ${vaga.endereco.estado}</p>
		</div>

		<div class="border rounded-3 p-3 mb-3 bg-white">
			<h6 class="text-secondary">Quantidade de Vagas</h6>
			<span class="fw-bold">${vaga.qtdVagas}</span>
		</div>

		<c:if test="${usuarioLogado.tipoUsuario == 'CANDIDATO'}">
			<c:choose>
				<c:when test="${candidatado == 1}">
					<div class="text-center">
						<form method="POST" action="${pageContext.request.contextPath}/home/candidatovaga/delete?id=${vaga.id}">

							<button class="btn btn-danger">Descandidatar-se</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="text-center">
						<form method="POST" action="${pageContext.request.contextPath}/home/candidatovaga?id=${vaga.id}">

							<button class="btn btn-success">Candidatar-se</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>


		</c:if>
	</div>
</body>
</html>