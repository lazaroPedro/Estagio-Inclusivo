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
</head>

<body class="bd-light">
	<%@ include file="/assets/components/header.jsp" %>

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
<<<<<<< HEAD
							${vaga.endereco.rua} - ${vaga.endereco.bairro} - ${vaga.endereco.municipio} - ${vaga.endereco.estado} 
							
							<span class="text-muted" style="font-family: monospace;">CEP: ${vaga.endereco.cep}</span>
							</p>
=======
							${vaga.endereco.id}</p>
>>>>>>> 8612f13cad86dc85da5f5bc5258235e33f2117cb
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
<<<<<<< HEAD
							<strong>Status:</strong><span class="text-success"> Ativa</span></p>
							
						<p> 
							<strong>Publicada em:</strong> <fmt:formatDate value="${vaga.dataPublicacao}" pattern="dd/MM/yyyy" /></p>
=======
							<strong>Status:</strong>
							${vaga.status}</p>
>>>>>>> 8612f13cad86dc85da5f5bc5258235e33f2117cb
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