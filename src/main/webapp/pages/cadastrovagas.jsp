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
</head>

<body>
	<%@ include file="/assets/components/header.jsp"%>

	<div class="conatiner mt-5 mb-5">
		<div class="card shadow">
			<div class="card-header bg-secondary text-white">
				<h4 class="mb-0">Cadastro de Vaga de Estágio</h4>
			</div>
			<div class="card-body">

				<form action="${pageContext.request.contextPath}/vaga/insert"
					method="post">
					
					<div class="mb-3">
						<label for="titulo" class="form-label">Titulo da Vaga</label>
						<input type="text" name="titulo" class="form-control" required>
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
						<label>Quantidade de Vagas</label> 
						<input type="number" name="qtd_vagas" class="form-control" min="1" required>
					</div>

					<hr>

					<h5>Local do Estágio</h5>
					<div class="form-row">
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


					<hr>

					<h5>Tipos de Deficiência Aceitas</h5>

					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="tiposDeficiencia" value="Auditiva"> <label
							class="form-check-label">Auditiva</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="tiposDeficiencia" value="visual"> <label
							class="form-check-label">Visual</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="tiposDeficiencia" value="Fisica"> <label
							class="form-check-label">Física</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="tiposDeficiencia" value="Intelectual"> <label
							class="form-check-label">Intelectual</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="tiposDeficiencia" value="outro"> <label
							class="form-check-label">Outro</label>
					</div>
					<br>

					<div class="text-end">
						<button type="submit" class="btn btn-secondary">Cadastrar</button>
					</div>

				</form>
				
	
</body>
</html>