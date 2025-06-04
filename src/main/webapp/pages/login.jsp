<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
  <style>
    :root {
      --primary-color: #7c3aed;
      --primary-hover: #6d28d9;
      --background-accent: #f3f0ff;
      --dark-grey: #1f2937;
      --medium-grey: #6b7280;
      --white: #ffffff;
    }

    body {
      margin: 0;
      padding: 0;
      font-family: "Segoe UI", sans-serif;
      background-color: var(--background-accent);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }


    .form-wrapper {
      display: flex;
      background-color: var(--white);
      border-radius: 12px;
      box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
      overflow: hidden;
      max-width: 1000px;
      width: 100%;
      height: auto;
    }

    .image-side {
      flex: 1;
      background: url('${pageContext.request.contextPath}/assets/images/Login.png') no-repeat center center;
      background-size: cover;
      min-height: 100%;
    }

    .form-side {
      flex: 1;
      padding: 40px;
      min-height: 400px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }


    .form-title {
      font-weight: 700;
      font-size: 1.8rem;
      color: var(--primary-color);
    }

    .form-subtitle {
      font-size: 1rem;
      color: var(--medium-grey);
      margin-bottom: 25px;
    }

    label {
      font-weight: 500;
      color: var(--dark-grey);
    }

    .btn-primary {
      background-color: var(--primary-color) !important;
      border: none !important;
    }

    .btn-primary:hover {
      background-color: var(--primary-hover) !important;
    }

    .text-link {
      color: var(--primary-color) !important;
      text-decoration: underline;
    }

    @media (max-width: 991.98px) {
      .form-wrapper {
        flex-direction: column;
        max-width: 90%;
      }

      .image-side {
        height: 220px;
        background-size: contain;
        background-repeat: no-repeat;
      }

      .form-side {
        padding: 25px;
      }
    }
  </style>
</head>
<body class="container mt-5">
<%@ include file="/assets/components/header.jsp" %>

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
<div class="form-wrapper">
  <div class="image-side"></div>
    <div class="form-side">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="row">
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
        </div>

      </form>
  </div>
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
