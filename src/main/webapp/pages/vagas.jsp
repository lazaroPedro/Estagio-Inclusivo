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
	<%@ include file="/assets/components/header.jsp"%>

	<div class="container-x1 mt-5 pt-5">
		<c:choose>
			<c:when test="${not empty vaga}">

				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Detalhes da Vaga</h4>
					</div>

					<div class="card-body">
						<h5 class="card-title">Requisitos</h5>
						<p class="card-text">${vaga.requisitos}</p>

						<h5 class="card-title">Benefícios</h5>
						<p class="card-text">${vaga.beneficios}</p>

						<h5 class="card-title">Quantidade de Vagas</h5>
						<p class="card-text">${vaga.qtdVagas}</p>

						<hr>

						<c:choose>
							<c:when test="${empty empresaLogada}">
								<div class="card mb-4">
									<div class="card-header">Informações da Empresa</div>
									<div class="card-body">
										<p>
											<strong>Razão Social:</strong> ${empresaLogada.razaoSocial}
										</p>
										<p>
											<strong></strong> ${empresaLogada.cnpj}
										</p>
										<p>
											<strong></strong> ${empresaLogada.email}
										</p>
										<p>
											<strong></strong> ${empresaLogada.telefone}
										</p>
									</div>
								</div>

								<div class="alert alert-danger">Nenhuma empresa logada.</div>
							</c:when>
						</c:choose>
			</c:when>
			<c:otherwise>
				<p class="text-danger">Vaga não encontrada.</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>