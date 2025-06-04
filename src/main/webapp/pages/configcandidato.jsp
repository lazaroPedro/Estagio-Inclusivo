<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 04/05/2025
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.enums.Genero" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Configurações do Perfil</title>

    <script>
        function toggleForm(formId) {
            const form = document.getElementById(formId);
            form.classList.toggle('d-none'); // Usar classe do Bootstrap para display
            // Opcional: rolar para o formulário se ele abrir
            if (!form.classList.contains('d-none')) {
                form.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        }

        function formatarCPF(cpf) {
            if (!cpf) return '';
            return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
        }

        function formatarTelefone(tel) {
            if (!tel) return '';
            // Ajusta para formatar (XX) XXXXX-XXXX ou (XX) XXXX-XXXX
            if (tel.length === 11) {
                return tel.replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
            } else if (tel.length === 10) {
                return tel.replace(/(\d{2})(\d{4})(\d{4})/, "($1) $2-$3");
            }
            return tel; // Retorna sem formatar se o tamanho for inesperado
        }

        document.addEventListener("DOMContentLoaded", function() {
            const myModalElement = document.getElementById('successModal');
            if (myModalElement) {
                const myModal = new bootstrap.Modal(myModalElement);
                <% if (request.getAttribute("sucesso") != null || request.getAttribute("erros") != null || request.getAttribute("erro") != null) { %>
                myModal.show();
                <% } %>
            }

            // Aplica máscaras de formatação se os campos existirem na visualização
            const displayCpf = document.getElementById('displayCpf');
            if(displayCpf && displayCpf.textContent) {
                displayCpf.textContent = formatarCPF(displayCpf.textContent.trim());
            }

            const displayTelefone = document.getElementById('displayTelefone');
            if(displayTelefone && displayTelefone.textContent) {
                displayTelefone.textContent = formatarTelefone(displayTelefone.textContent.trim());
            }

            // Adiciona máscaras de input para os campos de formulário (opcional, mas melhora UX)
            const inputCpf = document.querySelector('input[name="cpf"]');
            if (inputCpf) {
                inputCpf.addEventListener('input', function (e) {
                    let value = e.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito
                    if (value.length > 11) value = value.slice(0, 11);
                    e.target.value = formatarCPF(value);
                });
                // Garante que o valor inicial já formatado
                if (inputCpf.value) {
                    inputCpf.value = formatarCPF(inputCpf.value);
                }
            }

            const inputTelefone = document.querySelector('input[name="telefone"]');
            if (inputTelefone) {
                inputTelefone.addEventListener('input', function (e) {
                    let value = e.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito
                    if (value.length > 11) value = value.slice(0, 11);
                    e.target.value = formatarTelefone(value);
                });
                // Garante que o valor inicial já formatado
                if (inputTelefone.value) {
                    inputTelefone.value = formatarTelefone(inputTelefone.value);
                }
            }
        });
    </script>
    <style>
        /* style.css */

        :root {
            --primary-color: #815DF2;
            --background-accent: rgba(129, 93, 242, 0.1);
            --dark-grey: #343A40;
            --medium-grey: #495057;
            --light-grey: #adb5bd;
            --border-light: #E9ECEF;
            --white: #FFFFFF;
            --darker-accent: #6C4ECB;
            --shadow-light: rgba(0,0,0,.08);
            --success-color: #28a745;
            --danger-color: #dc3545;
        }

        body {
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            background-color: var(--border-light);
            color: var(--dark-grey);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0;
            padding-top: 56px; /
        }

        .settings-container {
            display: flex;
            flex: 1;
            max-width: 1000px;
            margin: 2rem auto;
            background-color: var(--white);
            border-radius: 8px;
            box-shadow: 0 4px 12px var(--shadow-light);
            overflow: hidden;
            border: 1px solid var(--border-light);
        }

        .settings-sidebar {
            width: 280px;
            flex-shrink: 0;
            padding: 2rem 0;
            border-right: 1px solid var(--border-light);
            background-color: var(--white);
        }

        .sidebar-title {
            font-size: 1.5rem;
            font-weight: 600;
            color: var(--primary-color);
            padding: 0 1.5rem 1.5rem;
            border-bottom: 1px solid var(--border-light);
            margin: 0 0 1.5rem;
        }

        .sidebar-nav {
            display: flex;
            flex-direction: column;
            padding: 0 0;
        }

        .sidebar-nav .nav-link {
            display: flex;
            align-items: center;
            padding: 0.8rem 1.5rem;
            color: var(--medium-grey);
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.2s ease, color 0.2s ease;
            border-left: 3px solid transparent;
        }

        .sidebar-nav .nav-link i {
            margin-right: 1rem;
            font-size: 1.1rem;
            color: var(--light-grey);
        }

        .sidebar-nav .nav-link:hover {
            background-color: var(--background-accent);
            color: var(--dark-grey);
        }

        .sidebar-nav .nav-link.active {
            background-color: var(--background-accent);
            color: var(--primary-color);
            font-weight: 600;
            border-left-color: var(--primary-color);
        }

        .sidebar-nav .nav-link.active i {
            color: var(--primary-color);
        }

        .settings-content {
            flex-grow: 1;
            padding: 2rem 2.5rem;
            overflow-y: auto;
            max-height: calc(100vh - 56px - 4rem);
        }

        .settings-section {
            margin-bottom: 3rem;
        }

        .section-title {
            font-size: 1.8rem;
            font-weight: 600;
            color: var(--dark-grey);
            margin-bottom: 0.5rem;
            border-bottom: none;
            padding-bottom: 0;
            display: block;
        }

        .section-description {
            color: var(--light-grey);
            font-size: 0.95rem;
            margin-bottom: 1.5rem;
        }

        .setting-card {
            background-color: var(--white);
            border: 1px solid var(--border-light);
            border-radius: 8px;
            padding: 1.5rem;
            box-shadow: 0 2px 8px rgba(0,0,0,.05);
            transition: box-shadow 0.2s ease;
        }

        .setting-card:hover {
            box-shadow: 0 4px 12px rgba(0,0,0,.08);
        }

        .info-display p {
            margin-bottom: 0.5rem;
            font-size: 1rem;
            color: var(--dark-grey);
        }

        .info-display p:last-child {
            margin-bottom: 0;
        }

        .info-display p strong {
            color: var(--dark-grey);
            min-width: 150px;
            display: inline-block;
            font-weight: 600;
        }

        form.d-none {
            display: none !important;
        }


        .setting-group .item-card {
            margin-bottom: 1rem;
        }


        .form-label {
            font-weight: 500;
            color: var(--medium-grey);
            margin-bottom: 0.4rem;
            font-size: 0.95rem;
        }

        .form-control, .form-select {
            border: 1px solid var(--border-light);
            border-radius: 5px;
            padding: 0.75rem 1rem;
            color: var(--dark-grey);
            transition: border-color 0.2s ease, box-shadow 0.2s ease;
            background-color: var(--white);
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.25rem var(--background-accent);
            outline: none;
            background-color: var(--white);
        }

        textarea.form-control {
            min-height: 100px;
            resize: vertical;
        }


        .btn {
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
            font-weight: 600;
            transition: all 0.2s ease;
            cursor: pointer;
            text-decoration: none;
            font-size: 0.95rem;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }

        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
            color: var(--white);
        }

        .btn-primary:hover {
            background-color: var(--darker-accent);
            border-color: var(--darker-accent);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(0,0,0,.1);
        }


        .btn-outline-primary {
            color: var(--primary-color);
            border-color: var(--primary-color);
            background-color: transparent;
        }
        .btn-outline-primary:hover {
            background-color: var(--primary-color);
            color: var(--white);
            border-color: var(--primary-color);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(0,0,0,.1);
        }

        .btn-outline-danger {
            color: var(--danger-color);
            border-color: var(--danger-color);
            background-color: transparent;
        }
        .btn-outline-danger:hover {
            background-color: var(--danger-color);
            color: var(--white);
            border-color: var(--danger-color);
            transform: translateY(-1px);
            box-shadow: 0 2px 6px rgba(0,0,0,.1);
        }

        .btn-secondary {
            background-color: var(--light-grey);
            border-color: var(--light-grey);
            color: var(--white);
        }
        .btn-secondary:hover {
            background-color: var(--medium-grey);
            border-color: var(--medium-grey);
        }


        .add-new-card {
            border: 2px dashed var(--primary-color);
            background-color: var(--background-accent);
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 120px;
            padding: 1.5rem;
        }
        .add-new-card .btn {
            margin-bottom: 0;
        }
        .add-new-card .btn-lg {
            padding: 0.9rem 2rem;
            font-size: 1.1rem;
        }


        .danger-zone {
            background-color: #ffebeb;
            border-color: #ffb3b3;
            text-align: center;
            padding: 2rem;
        }
        .danger-zone .btn-danger {
            padding: 1rem 2rem;
            font-size: 1.2rem;
        }


        .modal-content {
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0,0,0,.2);
            border: none;
        }

        .modal-header {
            background-color: var(--primary-color);
            color: var(--white);
            border-top-left-radius: 12px;
            border-top-right-radius: 12px;
            border-bottom: none;
            padding: 1.2rem 1.5rem;
        }

        .modal-title {
            font-weight: 600;
            font-size: 1.2rem;
        }

        .modal-body {
            color: var(--dark-grey);
            padding: 1.5rem;
            line-height: 1.6;
        }

        .modal-footer {
            border-top: 1px solid var(--border-light);
            padding: 1rem 1.5rem;
        }

        .btn-close {
            filter: invert(1) grayscale(100%) brightness(200%);
            font-size: 0.8rem;
        }


        @media (max-width: 990px) {
            body {
                padding-top: 0;
            }

            .settings-container {
                flex-direction: column;
                margin: 1rem;
                box-shadow: none;
                border: none;
                max-width: none;
            }

            .settings-sidebar {
                width: 100%;
                border-right: none;
                border-bottom: 1px solid var(--border-light);
                padding: 1rem 0;
                background-color: var(--white);
            }

            .sidebar-title {
                text-align: center;
                padding: 0 1rem 1rem;
                margin-bottom: 1rem;
            }

            .sidebar-nav {
                flex-direction: row;
                flex-wrap: wrap;
                justify-content: center;
                padding: 0 0.5rem;
            }

            .sidebar-nav .nav-link {
                padding: 0.6rem 0.8rem;
                margin: 0.2rem;
                border-left: none !important;
                border-bottom: 3px solid transparent;
                flex-direction: column;
                font-size: 0.85rem;
                text-align: center;
            }

            .sidebar-nav .nav-link i {
                margin-right: 0;
                margin-bottom: 0.3rem;
                font-size: 1rem;
            }

            .sidebar-nav .nav-link.active {
                border-bottom-color: var(--primary-color) !important;
                border-left: none !important;
            }

            .settings-content {
                padding: 1.5rem 1rem;
                max-height: none;
                overflow-y: visible;
            }

            .section-title {
                font-size: 1.6rem;
                margin-top: 1.5rem;
                margin-bottom: 0.5rem;
            }

            .setting-card {
                padding: 1rem;
            }

            .info-display p strong {
                display: block;
                min-width: unset;
                margin-bottom: 0.2rem;
            }

            .button-group {
                flex-direction: column;
                align-items: stretch;
            }
            .button-group .btn {
                width: 100%;
                margin-left: 0;
                margin-bottom: 0.5rem;
            }
            .button-group .btn:last-child {
                margin-bottom: 0;
            }

            .add-new-card .btn {
                padding: 0.8rem 1.5rem;
                font-size: 1rem;
            }
        }

        @media (max-width: 570px) {
            .settings-container {
                margin: 0.5rem;
            }
            .sidebar-nav .nav-link {
                padding: 0.5rem 0.5rem;
                font-size: 0.8rem;
            }
            .section-title {
                font-size: 1.4rem;
            }
            .settings-content {
                padding: 1rem 0.8rem;
            }
        }
    </style>
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>

<div class="container-xl settings-container">
    <aside class="settings-sidebar">
        <h2 class="sidebar-title">Configurações</h2>
        <nav class="sidebar-nav">
            <a class="nav-link active" href="#item-1">
                <i class="fa-solid fa-house-chimney"></i>
                <span>Início</span>
            </a>
            <a class="nav-link" href="#item-2">
                <i class="fa-solid fa-user"></i>
                <span>Perfil</span>
            </a>
            <a class="nav-link" href="#item-3">
                <i class="fa-solid fa-lock"></i>
                <span>Dados de Acesso</span>
            </a>
            <a class="nav-link" href="#item-4">
                <i class="fa-solid fa-graduation-cap"></i>
                <span>Curso</span>
            </a>
            <a class="nav-link" href="#item-5">
                <i class="fa-solid fa-map-location-dot"></i>
                <span>Endereço</span>
            </a>
            <a class="nav-link" href="#item-6">
                <i class="fa-solid fa-wheelchair"></i>
                <span>Deficiência</span>
            </a>
            <a class="nav-link" href="#item-7">
                <i class="fa-solid fa-trash-can"></i>
                <span>Deletar Conta</span>
            </a>
        </nav>
    </aside>

    <main class="settings-content" data-bs-spy="scroll" data-bs-target=".settings-sidebar .sidebar-nav" data-bs-offset="0" tabindex="0">
        <section id="item-1" class="settings-section">
            <h4 class="section-title">Bem-vindo(a) às suas configurações!</h4>
            <p class="section-description">Use o menu ao lado para navegar e gerenciar suas informações de perfil, acesso, cursos, endereço e deficiências. Mantenha seus dados atualizados para garantir a melhor experiência na plataforma.</p>
            <% if (request.getAttribute("sucesso") != null) { %>
            <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" >
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="successModalLabel">Sucesso</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Alterado com sucesso!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
            <% } else if (request.getAttribute("erros") != null){%>
            <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="successModalLabel">Erros nos campos</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            ${erros.getMessage()}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
            <% } else if (request.getAttribute("erro") != null){%>
            <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="successModalLabel">Erro</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            ${erro}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </section>

        <section id="item-2" class="settings-section">
            <h4 class="section-title">Alterar Perfil</h4>
            <p class="section-description">Mantenha suas informações pessoais atualizadas.</p>
            <div class="setting-card">
                <div class="info-display">
                    <p><strong>Nome:</strong> ${candidato.nome}</p>
                    <p><strong>Telefone:</strong> <span id="displayTelefone">${candidato.telefone}</span></p>
                    <p><strong>CPF:</strong> <span id="displayCpf">${candidato.cpf}</span></p>
                    <p><strong>Gênero:</strong> ${candidato.genero.name()} </p>
                    <p><strong>Data de Nascimento:</strong> ${candidato.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-perfil')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/candidato/put" method="post" id="form-perfil" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome Completo</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${candidato.nome}" >
                    </div>
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" value="${candidato.telefone}" placeholder="(XX) XXXXX-XXXX">
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control" id="cpf" value="${candidato.cpf}" placeholder="XXX.XXX.XXX-XX">
                    </div>
                    <div class="mb-3">
                        <label for="genero" class="form-label">Gênero</label>
                        <select name="genero" class="form-select" id="genero">
                            <option value="" >Selecione</option>
                            <option value="MASCULINO" ${candidato.genero == Genero.MASCULINO ? 'selected' : ''} >Masculino</option>
                            <option value="FEMININO" ${candidato.genero == Genero.FEMININO ? 'selected' : ''}>Feminino</option>
                            <option value="OUTRO" ${candidato.genero == Genero.OUTRO ? 'selected' : ''}>Outro</option>
                            <option value="NAO_INFORMAR" ${candidato.genero == Genero.NAO_INFORMAR ? 'selected' : ''}>Não desejo informar</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="nascimento" class="form-label">Data de Nascimento</label>
                        <input type="date" name="nascimento" class="form-control" id="nascimento" value="${candidato.dataNascimento}">
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            </div>
        </section>

        <section id="item-3" class="settings-section">
            <h4 class="section-title">Alterar Dados de Acesso</h4>
            <p class="section-description">Gerencie seu e-mail e senha para acesso seguro.</p>
            <div class="setting-card">
                <div class="info-display">
                    <p><strong>Email:</strong> ${candidato.email} </p>
                    <p><strong>Senha:</strong> ************ </p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-acesso')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/usuario/put" method="post" id="form-acesso" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${candidato.email}">
                    </div>
                    <div class="mb-3">
                        <label for="senha-antiga" class="form-label">Antiga Senha</label>
                        <input type="password" class="form-control" id="senha-antiga" name="senha" required>
                    </div>
                    <div class="mb-3">
                        <label for="novaSenha" class="form-label">Nova Senha</label>
                        <input type="password" class="form-control" id="novaSenha" name="novaSenha" required>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            </div>
        </section>

        <section id="item-4" class="settings-section">
            <h4 class="section-title">Gerenciar Cursos</h4>
            <p class="section-description">Adicione, edite ou remova seus cursos e formações.</p>
            <div class="setting-group">
                <c:choose>
                    <c:when test="${not empty cursos}">
                        <c:forEach var="curso" items="${cursos}">
                            <div class="setting-card item-card">
                                <div class="info-display">
                                    <p><strong>Nome:</strong> ${curso.nomeCurso}</p>
                                    <p><strong>Instituição:</strong> ${curso.instituicao}</p>
                                    <p><strong>Descrição:</strong> ${curso.descricao} </p>
                                    <p><strong>Data Início:</strong> ${curso.dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))} </p>
                                    <p><strong>Data Fim:</strong> ${curso.dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>
                                </div>
                                <div class="d-flex justify-content-end mt-3 button-group">
                                    <button class="btn btn-outline-primary me-2" onclick="toggleForm('form-curso-${curso.id}')">Editar</button>
                                    <form action="${pageContext.request.contextPath}/home/curso/delete/id?id=${curso.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este curso?');">
                                        <button class="btn btn-outline-danger">Excluir</button>
                                    </form>
                                </div>
                                <form action="${pageContext.request.contextPath}/home/curso/put/id?id=${curso.id}" method="post" id="form-curso-${curso.id}" class="mt-4 d-none">
                                    <div class="mb-3">
                                        <label for="nome-curso-${curso.id}" class="form-label">Nome do Curso</label>
                                        <input type="text" name="nome" class="form-control" id="nome-curso-${curso.id}" value="${curso.nomeCurso}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="instituicao-${curso.id}" class="form-label">Instituição</label>
                                        <input type="text" name="instituicao" class="form-control" id="instituicao-${curso.id}" value="${curso.instituicao}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="descricao-curso-${curso.id}" class="form-label">Descrição</label>
                                        <textarea name="descricao" class="form-control" id="descricao-curso-${curso.id}">${curso.descricao}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="inicio-curso-${curso.id}" class="form-label">Data de Início</label>
                                        <input type="date" name="dataInicio" class="form-control" id="inicio-curso-${curso.id}" value="${curso.dataInicio}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="termino-curso-${curso.id}" class="form-label">Data de Término</label>
                                        <input type="date" name="dataFim" class="form-control" id="termino-curso-${curso.id}" value="${curso.dataFim}">
                                    </div>
                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="setting-card">
                            <p class="text-muted text-center">Nenhum curso cadastrado ainda.</p>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="setting-card add-new-card">
                    <button class="btn btn-lg btn-outline-primary" onclick="toggleForm('form-curso-post')">
                        <i class="fa-solid fa-plus me-2"></i> Adicionar Novo Curso
                    </button>
                    <form action="${pageContext.request.contextPath}/home/curso/post" method="post" id="form-curso-post" class="mt-4 d-none">
                        <div class="mb-3">
                            <label for="novo-nome-curso" class="form-label">Nome do Curso</label>
                            <input type="text" name="nome" class="form-control" id="novo-nome-curso" required>
                        </div>
                        <div class="mb-3">
                            <label for="nova-instituicao" class="form-label">Instituição</label>
                            <input type="text" name="instituicao" class="form-control" id="nova-instituicao" required>
                        </div>
                        <div class="mb-3">
                            <label for="nova-descricao-curso" class="form-label">Descrição</label>
                            <textarea name="descricao" class="form-control" id="nova-descricao-curso"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="novo-inicio-curso" class="form-label">Data de Início</label>
                            <input type="date" name="dataInicio" class="form-control" id="novo-inicio-curso" required>
                        </div>
                        <div class="mb-3">
                            <label for="novo-termino-curso" class="form-label">Data de Término</label>
                            <input type="date" name="dataFim" class="form-control" id="novo-termino-curso">
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary">Salvar Novo Curso</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>

        <section id="item-5" class="settings-section">
            <h4 class="section-title">Alterar Endereço</h4>
            <p class="section-description">Mantenha seu endereço atualizado para comunicações importantes.</p>
            <div class="setting-card">
                <div class="info-display">
                    <p><strong>Rua:</strong> ${candidato.endereco.rua}</p>
                    <p><strong>Bairro:</strong> ${candidato.endereco.bairro}</p>
                    <p><strong>Município:</strong> ${candidato.endereco.municipio} </p>
                    <p><strong>Estado:</strong> ${candidato.endereco.estado} </p>
                    <p><strong>CEP:</strong> ${candidato.endereco.cep}</p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-endereco')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/endereco/put" method="post" id="form-endereco" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="rua" class="form-label">Rua</label>
                        <input type="text" class="form-control" id="rua" name="rua" value="${candidato.endereco.rua}">
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" name="bairro" class="form-control" id="bairro" value="${candidato.endereco.bairro}">
                    </div>
                    <div class="mb-3">
                        <label for="municipio" class="form-label">Município</label>
                        <input type="text" name="municipio" class="form-control" id="municipio"  value="${candidato.endereco.municipio}">
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" name="estado" class="form-control" id="estado" value="${candidato.endereco.estado}">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" name="cep" class="form-control" id="cep" value="${candidato.endereco.cep}">
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            </div>
        </section>

        <section id="item-6" class="settings-section">
            <h4 class="section-title">Gerenciar Deficiências</h4>
            <p class="section-description">Gerencie as informações sobre suas deficiências e apoios necessários.</p>
            <div class="setting-group">
                <c:choose>
                    <c:when test="${not empty deficiencias}">
                        <c:forEach var="def" items="${deficiencias}">
                            <div class="setting-card item-card">
                                <div class="info-display">
                                    <p><strong>Nome:</strong> ${def.nome}</p>
                                    <p><strong>Descrição:</strong> ${def.descricao} </p>
                                    <p><strong>Tipo de Apoio:</strong> ${def.tipoApoio} </p>
                                    <p><strong>Tipo:</strong> ${def.tipo.name()}</p>
                                </div>
                                <div class="d-flex justify-content-end mt-3 button-group">
                                    <button class="btn btn-outline-primary me-2" onclick="toggleForm('form-deficiencia-${def.id}')">Editar</button>
                                    <form action="${pageContext.request.contextPath}/home/deficiencia/delete/id?id=${def.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir esta deficiência?');">
                                        <button class="btn btn-outline-danger">Excluir</button>
                                    </form>
                                </div>
                                <form action="${pageContext.request.contextPath}/home/deficiencia/put/id?id=${def.id}" method="post" id="form-deficiencia-${def.id}" class="mt-4 d-none">
                                    <div class="mb-3">
                                        <label for="nome-deficiencia-${def.id}" class="form-label">Nome da Deficiência</label>
                                        <input type="text" name="nome" class="form-control" id="nome-deficiencia-${def.id}" value="${def.nome}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="descricao-deficiencia-${def.id}" class="form-label">Descrição</label>
                                        <textarea name="descricao" class="form-control" id="descricao-deficiencia-${def.id}">${def.descricao}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="tipo-deficiencia-${def.id}" class="form-label">Tipo de Deficiência</label>
                                        <select name="tipo" class="form-select" id="tipo-deficiencia-${def.id}">
                                            <option value="FISICA" ${def.tipo == TipoDeficienciaEnum.FISICA ? 'selected' : ''} >Física</option>
                                            <option value="VISUAL" ${def.tipo == TipoDeficienciaEnum.VISUAL ? 'selected' : ''}>Visual</option>
                                            <option value="AUDITIVA" ${def.tipo == TipoDeficienciaEnum.AUDITIVA ? 'selected' : ''}>Auditiva</option>
                                            <option value="INTELECTUAL" ${def.tipo == TipoDeficienciaEnum.INTELECTUAL ? 'selected' : ''}>Intelectual</option>
                                            <option value="MENTAL" ${def.tipo == TipoDeficienciaEnum.MENTAL ? 'selected' : ''}>Mental</option>
                                            <option value="SENSORIAL" ${def.tipo == TipoDeficienciaEnum.SENSORIAL ? 'selected' : ''}>Sensorial</option>
                                            <option value="MULTIPLA" ${def.tipo == TipoDeficienciaEnum.MULTIPLA ? 'selected' : ''}>Múltipla</option>
                                            <option value="OUTRA" ${def.tipo == TipoDeficienciaEnum.OUTRA ? 'selected' : ''}>Outra</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="apoio-deficiencia-${def.id}" class="form-label">Tipo de Apoio Necessário</label>
                                        <textarea name="tipoApoio" class="form-control" id="apoio-deficiencia-${def.id}">${def.tipoApoio}</textarea>
                                    </div>
                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="setting-card">
                            <p class="text-muted text-center">Nenhuma deficiência cadastrada ainda.</p>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="setting-card add-new-card">
                    <button class="btn btn-lg btn-outline-primary" onclick="toggleForm('form-deficiencia-post')">
                        <i class="fa-solid fa-plus me-2"></i> Adicionar Nova Deficiência
                    </button>
                    <form action="${pageContext.request.contextPath}/home/deficiencia/post" method="post" id="form-deficiencia-post" class="mt-4 d-none">
                        <div class="mb-3">
                            <label for="novo-nome-deficiencia" class="form-label">Nome da Deficiência</label>
                            <input type="text" name="nome" class="form-control" id="novo-nome-deficiencia" required>
                        </div>
                        <div class="mb-3">
                            <label for="nova-descricao-deficiencia" class="form-label">Descrição</label>
                            <textarea name="descricao" class="form-control" id="nova-descricao-deficiencia"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="novo-tipo-deficiencia" class="form-label">Tipo de Deficiência</label>
                            <select name="tipo" class="form-select" id="novo-tipo-deficiencia" required>
                                <option value="" disabled selected>Selecione</option>
                                <option value="FISICA">Física</option>
                                <option value="VISUAL">Visual</option>
                                <option value="AUDITIVA">Auditiva</option>
                                <option value="INTELECTUAL">Intelectual</option>
                                <option value="MENTAL">Mental</option>
                                <option value="SENSORIAL">Sensorial</option>
                                <option value="MULTIPLA">Múltipla</option>
                                <option value="OUTRA">Outra</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="novo-apoio-deficiencia" class="form-label">Tipo de Apoio Necessário</label>
                            <textarea name="tipoApoio" class="form-control" id="novo-apoio-deficiencia"></textarea>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary">Salvar Nova Deficiência</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>

        <section id="item-7" class="settings-section">
            <h4 class="section-title text-danger">Deletar Conta</h4>
            <p class="section-description">Esta ação é irreversível e excluirá sua conta permanentemente.</p>
            <div class="setting-card danger-zone text-center">
                <button class="btn btn-danger btn-lg" data-bs-toggle="modal" data-bs-target="#modalExcluir">
                    Deletar Minha Conta
                </button>
            </div>
        </section>
    </main>
</div>

<div class="modal fade" id="modalExcluir" tabindex="-1" aria-labelledby="modalExcluirLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalExcluirLabel">Confirmar Exclusão de Conta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Você tem certeza absoluta que deseja excluir sua conta? Esta ação não pode ser desfeita e todos os seus dados serão permanentemente removidos.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <a href="${pageContext.request.contextPath}/home/usuario/delete" class="btn btn-danger">Confirmar Exclusão</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>