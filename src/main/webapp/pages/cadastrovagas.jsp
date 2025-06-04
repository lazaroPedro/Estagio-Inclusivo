<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Vaga</title>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css"
	rel="stylesheet">

<style>
:root {
	--roxo: #805DFF;
}

.header-curvo {
	background-color: #805DFF;
	color: white;
	padding: 60px 0;
	border-bottom-left-radius: 50% 10%;
	border-bottom-right-radius: 50% 10%;
	text-align: center;
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

.form-control:focus {
	border-color: var(--roxo);
	box-shadow: 0 0 0 0.2rem rgba(128, 93, 255, 0.25);
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
	padding-top: 20px;
}
</style>
<%@ include file="/assets/components/header.jsp"%>
</head>

<body class="bg-light">

	<div class="header-curvo">
		<div class="bg-roxo text-white text-center py-4">
			<h2 class="mb-0">Cadastro de Vaga de Estágio</h2>
		</div>
	</div>

	<div class="container my-5">
		<div class="card border-roxo mx-auto" style="max-width: 700px;">
			<div class="card-body">
				<h2 class="card-title text-center text-roxo mb-4">Preencha os
					dados da vaga</h2>


				<form action="${pageContext.request.contextPath}/home/vaga/insert"
					method="post">

					<div class="mb-3">
						<label for="titulo" class="form-label">Titulo da Vaga</label> <input
							type="text" name="titulo" class="form-control" required>
					</div>

					<div class="mb-3">
						<label for="descricao" class="form-label">Descrição</label>
						<textarea class="form-control" name="descricao" rows="3" required>${param.descricao}</textarea>
					</div>

					<div class="mb-3">
						<label for="requisitos" class="form-label">Requisitos</label>
						<textarea class="form-control" name="requisitos" rows="2" required>${param.requisitos}</textarea>
					</div>

					<div class="mb-3">
						<label for="beneficios" class="form-label">Benefícios</label>
						<textarea class="form-control" name="beneficios" rows="2" required>${param.beneficios}</textarea>
					</div>

					<div class="form-group">
						<label>Quantidade de Vagas</label> <input type="number"
							name="qtd_vagas" class="form-control" min="1" required>
					</div>

					<hr>

					<h5>Local do Estágio</h5>
					<div class="row g-3">
						<div class="form-group col-md-6">
							<label>CEP</label> <input type="text" name="cep"
								class="form-control" required>
						</div>
						<div class="form-group col-md-6">
							<label>Rua</label> <input type="text" name="rua"
								class="form-control" required>
						</div>




						<div class="form-group col-md-6">
							<label>Bairro</label> <input type="text" name="bairro"
								class="form-control" required>
						</div>

						<div class="form-group col-md-6">
							<label>Municício</label> <input type="text" name="municipio"
								class="form-control" required>
						</div>




						<div class="form-group col-md-6">
							<label>Estado</label> <input type="text" name="estado"
								class="form-control" required>
						</div>
					</div>




					<div class="text-end">
						<button type="submit" class="btn btn-roxo">Cadastrar</button>
					</div>

			</form>

			</div>
		</div>
	</div>
</body>
</html>