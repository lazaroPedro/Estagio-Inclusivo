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



				<form action="${pageContext.request.contextPath}/vaga" method="post">
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

					<div class="mb-3">

						<label for="qtd_vagas" class="form-label">Quantidade de
							Vagas</label> <input type="number" class"form-control" name="qtd_vagas"
							min="1" value="${param.qtd_vagas}" required>
					</div>



			<div class="text-end">
                        <button type="submit" class="btn btn-secondary">Cadastrar</button>

				</button>
			</div>

			</form>
		</div>

	</div>


</body>
</html>