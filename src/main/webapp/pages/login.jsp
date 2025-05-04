<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<%
  String erro = request.getParameter("erro");
  if ("1".equals(erro)) {
%>
<div id="erroAlerta" class="alert alert-danger d-flex align-items-center" role="alert">
  <i class="bi bi-exclamation-triangle-fill me-2"></i>
  E-mail ou senha inválidos. Tente novamente.
</div>
<%
  }
%>
    <form action="${pageContext.request.contextPath}/login" method="post">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Endereço de email</label>
    <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Seu email">
    <small id="emailHelp" class="form-text text-muted">Nunca vamos compartilhar seu email, com ninguém. </small>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Senha</label>
    <input name="senha" type="password" class="form-control" id="exampleInputPassword1" placeholder="Senha">
  </div>
  <button type="submit" class="btn btn-primary">Fazer login</button>
    </form>
<div class="mt-5">

    <a class="btn btn-primary" href="#" role="button">Cadastrar Empresa</a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/pages/cadastrocandidato.jsp" role="button">Cadastrar Candidato</a>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
<script>
  window.addEventListener("DOMContentLoaded", function () {
    const alerta = document.getElementById("erroAlerta");
    if (alerta) {
      setTimeout(() => {
        alerta.classList.add("fade");
        alerta.classList.remove("show");
        alerta.style.opacity = 0;
        setTimeout(() => alerta.remove(), 500);
      }, 5000);
    }
  });
</script>
</body>

</html>
