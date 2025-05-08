<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>
<%@ page import="java.util.List" %><%--
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

    <div class="container-xl mt-5 pt-5">
        <%
            List<Vaga> lista = (List<Vaga>) request.getAttribute("lista_vagas");

        if(lista == null ||  lista.isEmpty()){
            %>
             <div class="card mb-3 ">
                 <div class="row g-0">
                     <div class="card-body text-bg-danger">
                        <h5 class="card-title">NÃO TEMOS VAGAS PRA VOCÊ!</h5>
                     </div>
                 </div>
             </div>
        <%
        } else {
            for (Vaga u : lista) {
                %>
                <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/vaga?id=<%=u.getId()%>">
                <div class="card mb-3 ">
                    <div class="row g-0">
                        <div class="col-4 card-body">
                            <p class="card-text "><%=u.getEmpresa().getRazaoSocial()%></p>
                            <p class="card-text ">Vagas: <%=u.getQtdVagas()%></p>

                        </div>
                        <div class="col-4">

                            <div class="card-body">
                                <h5 class="card-title"><%=u.getTitulo()%> </h5>
                                <p class="card-text "><%=u.getDescricao()%></p>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="card-body">
                                <p class="card-text "><%=u.getRequisitos()%></p>
                                <p class="card-text "><%=u.getBeneficios()%></p>

                            </div>
                        </div>
                    </div>
                </div>
                </a>
        <%

        }


    }

        %>

    </div>


    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js" ></script>

</body>
</html>
