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
			<div class="alert alert-success text-center" role="alert">Vaga cadastrada com sucesso!</div>
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
				<li class="list-group-item"><Strong>Razão Social:</Strong>
					${vaga.empresa.razaoSocial}</li>
				<li class="list-group-item"><Strong>Nome Fantasia:</Strong>
					${vaga.empresa.nome}</li>
				<li class="list-group-item"><Strong>CNPJ:</Strong>
					${vaga.empresa.cnpj}</li>
				<li class="list-group-item"><Strong>Email:</Strong>
					${vaga.empresa.email}</li>
				<li class="list-group-item"><Strong>Telefone:</Strong>
					${vaga.empresa.telefone}</li>
			</ul>

			<h6 class="text-primary">Localização</h6>
			<p>${vaga.endereco.rua},${vaga.endereco.bairro},
				${vaga.endereco.municipio} - ${vaga.endereco.estado}</p>

			<h6 class="text-primary">Quantidade de Vagas</h6>
			<span class="badge bg-primary-subtle text-dark fs-6">${vaga.qtdVagas}</span>

		</div>
	</div>
	</div>
	<c:if test="${usuarioLogado.tipoUsuario == 'CANDIDATO'}">
		<div class="text-center">
			<a href="${pageContext.request.contextPath}/candidatar?id=${vaga.id}"
				class="bnt bnt-success bnt-lg px-5">Candidatar-se</a>
		</div>
	</c:if>


</body>
</html>