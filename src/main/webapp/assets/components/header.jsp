<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>



<nav class="fixed-top navbar navbar-dark bg-dark navbar-expand-lg">
  <div class="container-xl">
    <a class="navbar-brand me-5" href="#">Estagio Inclusivo</a>
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
        <!--           <li class="nav-item">
   <a href="#" class="nav-link ">Mensagens</a>
   </li> -->
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/login" class="nav-link ">Login</a>
        </li>

        <!-- Menu suspenso -->
        <li class="nav-item dropdown">
          <!-- botão do menu suspenso -->
          <a class="nav-link dropdown-toggle" href="#"
             role="button" data-bs-toggle="dropdown">
            Olá ${sessionScope.usuarioLogado.nome}
          </a>
          <!-- links ocultos -->
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

        </li>
      </ul>
    </div>
  </div>
</nav>
