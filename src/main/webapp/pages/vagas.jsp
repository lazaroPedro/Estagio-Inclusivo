<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<div class="container mt-5">
		<c:choose>
			<c:when test="${not empty vaga}"> 

		<div class="card shadow-sm">
					<div class-"card-header bg-primary text-white">
						<h3>Detalhes da Vaga</h3>
						>
					</div>

					<div class="card-body">

							<strong>Empresa:</strong>
							${vaga.empresa.nome}
						<p>
							<strong>Endereço (ID):</strong>
							${vaga.endereco.id}</p>
						<p>
							<strong>Descrição:</strong>
							${vaga.descricao}</p>
						<p>
							<strong>Requisitos (ID):</strong>
							${vaga.requisitos}</p>
						<p>
							<strong>Benefícios:</strong>
							${vaga.beneficios}</p>
						<p>
							<strong>Quantidade de Vagas:</strong>
							${vaga.qtdVagas}</p>
						<p>
							<strong>Status:</strong>
							${vaga.status}</p>
					</div>
				</div>

			</c:when>
			<c:otherwise>
				<p class="text-danger">${erro}</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>