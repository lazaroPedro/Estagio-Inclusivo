<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario" %>
<link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<script>
    function collapseBar() {
        let perfil = document.getElementById("noshow-perfil");
        let header = document.getElementById("collapse-header");

        if (perfil) {
            perfil.classList.add("d-none");
        }

        if (header) {
            header.classList.toggle("collapse-header");
        }
    }

    function showPerfil() {
        let header = document.getElementById("collapse-header");
        let perfil = document.getElementById("noshow-perfil");

        if (header) {
            header.classList.remove("collapse-header");
        }

        if (perfil) {
            perfil.classList.toggle("d-none");
        }
    }

</script>
<style>
        :root {
            --primary-color: #815DF2;
            --background-accent: rgba(129, 93, 242, 0.2);
            --dark-grey: #343A40;
            --medium-grey: #495057;
            --light-grey: #6C757D;
            --border-light: #E9ECEF;
            --white: #FFFFFF;
            --darker-accent: #6C4ECB;
            --shadow-light: rgba(0,0,0,.08);
        }


        .main-sidebar-header {
            background-color: var(--white);
            border-right: 1px solid var(--border-light);
            box-shadow: 2px 0 5px var(--shadow-light);
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            z-index: 1000;
            overflow-y: auto;
        }
        .nav-item .nav-link {
            font-weight: 500;
            color: var(--medium-grey);
            padding: 12px 15px;
            display: flex;
            align-items: center;
            gap: 1rem;
            border-radius: 5px;
            margin-bottom: 5px;
            text-align: center;
            transition: background-color 0.2s ease, color 0.2s ease;
        }
        .sidebarnav {
            padding: 10px 15px;
        }


        .sidebarnav .navbar-nav .nav-item .nav-link-header {
            background: var(--darker-accent);
            color: var(--border-light) !important;
            font-weight: bold;
            font-size: 1.2rem;


        }
        .sidebarnav .navbar-nav .nav-item .nav-link i {
            font-size: 1.5em;
            text-align: center;
        }

        .sidebarnav .navbar-nav .nav-item .nav-link:hover,
        .sidebarnav .navbar-nav .nav-item .nav-link.active {
            background-color: var(--background-accent);
            color: var(--primary-color);
        }

        .sidebarnav .navbar-nav .nav-item .nav-link-header:hover,
        .sidebarnav .navbar-nav .nav-item .nav-link-header.active {
            background: var(--darker-accent);
            color: var(--border-light);
        }
        .collapse-header span{
            display: none;
        }

        .dropdown-perfil{
            background: var(--background-accent);
            border-radius:  0.2rem 0.2rem 1.5rem  1.5rem;

            list-style: none;
        }
        .dropdown-perfil .nav-link{
            color: var(--primary-color);
        }

</style>





<header class="main-sidebar-header d-flex flex-column" id="collapse-header" >

<nav class="sidebarnav flex-grow-1">
<ul class="navbar-nav flex-column">
<li class="nav-item" onclick="collapseBar()">
    <a class="nav-link nav-link-header"  href="#">

        <i class="fas fa-briefcase logo-icon"></i>
        <span class="logo-text" >Estagio Inclusivo</span>
    </a>

</li>
<li class="nav-item">
    <a href="${pageContext.request.contextPath}/" class="nav-link" aria-current="page">
        <i class="fas fa-home"></i>
        <span > Inicio</span>
    </a>
</li>

    <li class="nav-item">
        <a href="${pageContext.request.contextPath}/index" class="nav-link active" aria-current="page">
            <i class="fas fa-search"></i>
            <span > Buscar Vagas</span>
        </a>
    </li>

<c:choose>
    <c:when test="${sessionScope.usuarioLogado == null}">

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/login"  class="nav-link">
                <i class="fas fa-sign-in-alt "></i>
                <span >
                                Login
                                </span>
            </a>
        </li>


        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/login" class="nav-link">
                <i class="fas fa-user-plus "></i>
                <span >
                                    Cadastrar
                                </span>
            </a>
        </li>
    </c:when>
    <c:otherwise>
        <li class="nav-item">
        <a href="${pageContext.request.contextPath}/home/vaga/all" class="nav-link">
        <i class="fas fa-folder-open "></i>
        <span >
        Minhas Vagas
        </span>
        </a>
        </li>
        <li class="nav-item">
        <a class="nav-link " href="#" onclick="showPerfil()">
        <i class="fas fa-user "></i>
        <span>
        Olá ${sessionScope.usuarioLogado.nome} ▼
        </span>
        </a>
        <ul class="dropdown-perfil" id="noshow-perfil">
        <c:if test="${sessionScope.usuarioLogado.tipoUsuario == TipoUsuario.CANDIDATO}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home/candidato/id">
                    Perfil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home/curriculo/insert">
                    Curriculo</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/home/candidato/full" class="nav-link">
                    Configurações
                </a>
            </li>

        </c:if>
        <c:if test="${sessionScope.usuarioLogado.tipoUsuario == TipoUsuario.EMPRESA}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home/empresa/id">
                    Perfil</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/home/empresa/full" class="nav-link">
                    Configurações
                </a>
            </li>
        </c:if>
        <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home/logout">
        Sair</a>
        </li>
        </ul>

        <li>
        </c:otherwise>
    </c:choose>

        </ul>
        </nav>
        </header>
