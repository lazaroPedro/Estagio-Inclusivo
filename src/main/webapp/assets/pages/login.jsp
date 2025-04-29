<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 28/04/2025
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Title</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css">
</head>
<body>
    <form>
  <div class="form-group">
    <label for="exampleInputEmail1">Endereço de email</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Seu email">
    <small id="emailHelp" class="form-text text-muted">Nunca vamos compartilhar seu email, com ninguém. </small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Senha</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Senha">
  </div>
  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Eu aceito os termos de uso e a venda total dos meus dados, assim como a clonagem do meu cartão.</label>
  </div>
  <button type="submit" class="btn btn-primary">Fazer login</button>
    </form>


    <a class="btn btn-primary" href="#" role="button">Cadastrar Empresa</a>
    <a class="btn btn-primary" href="#" role="button">Cadastrar Usuario</a>
</body>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>

</html>
