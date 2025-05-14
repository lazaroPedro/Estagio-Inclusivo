<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<nav class="fixed-top navbar navbar-dark bg-dark navbar-expand-lg">
  <div class="container-lg d-flex">
        <a class="navbar-brand me-5" href="#">Estagio Inclusivo</a>

        <form action="${pageContext.request.contextPath}/search" method="post" class="d-flex justify-content-center">
        <div class="input-group ">
            <input type="text" class="form-control" name="pesquisa" placeholder="Buscar" required/>
            <button type="submit" class="btn btn-success">Q</button>
        </div>
        </form>
        <button class="navbar-toggler" data-bs-toggle="collapse"
            data-bs-target="#menu-links">
            <span class="navbar-toggler-icon"></span>
        </button>
      <div class="collapse navbar-collapse" id="menu-links">
            <ul class="navbar-nav ms-auto">

            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/home" class="nav-link">Home</a>
            </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/pages/cadastrovagas.jsp" class="nav-link ">Vagas</a>
        </li>
        <li class="nav-item">
   <a href="#" class="nav-link ">Mensagens</a>
   </li>
        <c:choose>
        <c:when test="${sessionScope.usuarioLogado == null}">

        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/login" class="nav-link ">Login</a>
        </li>
        </c:when>
          <c:otherwise>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
              Olá ${sessionScope.usuarioLogado.nome}
            </a>
            <ul class="dropdown-menu">

            <li>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/perfil-candidato">
                Perfil</a>

            </li>
            <li>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/pages/perfil.jsp">
                Configurações</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">
                Sobre o sistema</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">
                Suporte</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">
                Sair</a>
            </li>
          </ul>

        </li> </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
