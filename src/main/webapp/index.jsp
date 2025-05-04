<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 28/04/2025
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estagio Inclusivo</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
    <nav class="fixed-top navbar navbar-dark bg-dark navbar-expand-lg">
        <div class="container-xl">
            <a class="navbar-brand me-5" href="#">Estagio Inclusivo</a>
            <button class="navbar-toggler" data-bs-toggle="collapse"
            data-bs-target="#menu-links">
            <span class="navbar-toggler-icon"></span>
            </button>
            <!-- menu de links -->
            <div class="collapse navbar-collapse" id="menu-links">
            <!-- logo e link alinhados à esquerda -->
            <ul class="navbar-nav me-auto">
            <!-- Link home -->
            <li class="nav-item">
            <a href="#" class="nav-link">Home</a>
            </li>
            <!-- Link desabilitado -->
            <li class="nav-item">
            <a href="#" class="nav-link disabled">Contato</a>
            </li>
            <!-- Menu suspenso -->
            <li class="nav-item dropdown">
            <!-- botão do menu suspenso -->
            <a class="nav-link dropdown-toggle" href="#"
            role="button" data-bs-toggle="dropdown">
            Ajuda
            </a>
            <!-- links ocultos -->
            <ul class="dropdown-menu">
            <li>
            <a class="dropdown-item" href="#">
            Sobre o sistema</a>
            </li>
            <li>
            <a class="dropdown-item" href="#">
            Especialistas</a>

            </li>
            </ul>
            </li>
            </ul>
            </div>
        </div>
    </nav>


    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>

</body>
</html>
