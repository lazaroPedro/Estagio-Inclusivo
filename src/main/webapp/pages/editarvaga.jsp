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

<body class="bd-light">
	<%@ include file="/assets/components/header.jsp"%>

	<div class="container-x1 mt-5 pt-5">


				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Detalhes da Vaga </h4>
					</div>

					<div class="card-body">
						<h3 class="card-title">${vaga.titulo}</h3>
						<h5 class="card-title">Descrição:</h5>
						<p class="card-text">${vaga.descricao}</p>
						<h5 class="card-title">Requisitos</h5>
						<p class="card-text">${vaga.requisitos}</p>

						<h5 class="card-title">Benefícios</h5>
						<p class="card-text">${vaga.beneficios}</p>

						<h5 class="card-title">Quantidade de Vagas</h5>
						<p class="card-text">${vaga.qtdVagas}</p>


						<div class="card-header">Endereco</div>
						<div class="card-body"></div>
					</div>
				</div>
		<div class="card bg-light " >
			<div class="card-header bg-primary text-white">
				<h4 class="mb-0">Candidatos: </h4>
			</div>
			<div class="card-body p-3">

			<c:forEach var="candidato" items="${candidatos}">
					<a class="text-decoration-none text-reset">
		<div class="card mb-4" id="card-template">
			<div class="card-body ">
				<div class="col-8">
				<p class="card-text">Nome: ${candidato.nome}</p>
				<p class="card-text">Email: ${candidato.email}</p>
				<p class="card-text">Telefone: ${candidato.telefone}</p>
				<p class="card-text">CPF: ${candidato.cpf}</p>
				<p class="card-text">Genero: ${candidato.genero.name()} </p>
				<p class="card-text">Data de Nascimento: ${candidato.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>
				</div>

			<div class=" col-4">
				<a class="card-link btn btn-success" href="/">Acessar Curriculo</a>
			</div>
			</div>

		</div>
					</a>
			</c:forEach>
			</div></div>



	</div>
</body>
</html>