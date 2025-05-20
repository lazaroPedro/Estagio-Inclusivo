<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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

</head>

<body class="bd-light">
	<%@ include file="/assets/components/header.jsp"%>

	<div class=" container mt-5">
		<c:if test="${param.sucesso == '1'}">
			<div class="alert alert-success" role="alert">Vaga cadastrada
				com sucesso!</div>
		</c:if>

		<h2>Vagas Cadastradas</h2>

		<table class="table table-striped table-bordered mt-3">
			<thead class="table-dark">
				<tr>
					<th>Descrição</th>
					<th>Requisitos</th>
					<th>Benefícios</th>
					<th>Quantidade de Vagas</th>
					<th>Empresa</th>
					<th>Local</th>
					<th>Deficiências Aceitas</th>
				</tr>
			<tbody>
				<c:ForEach var="vaga" items="${listaVagas}">
					<tr>
						<td>${vaga.descricao}</td>
						<td>${vaga.requisitos}</td>
						<td>${vaga.beneficios}</td>
						<td>${vaga.qtdVagas}</td>
						<td>${vaga.empresa.nome}</td>
						<td>${vaga.endereco.rua}, ${vaga.endereco.bairro},
							${vaga.endereco.municipio} - ${vaga.endereco.estado}</td>
						<td><c:forEach var="tipo" items="${vaga.tipoDeficiencia}">
								<span class="badge bg-secondary me-1">${tipo}</span>
							</c:forEach></td>
					</tr>
				</c:ForEach>
			</tbody>


		</table>
	</div>

</body>
</html>