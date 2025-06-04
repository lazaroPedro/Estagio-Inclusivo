<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 04/05/2025
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Configurações da Empresa</title>

    <script>
        function toggleForm(formId) {
            const form = document.getElementById(formId);
            form.classList.toggle('d-none'); // Usar classe do Bootstrap para display
            // Opcional: rolar para o formulário se ele abrir
            if (!form.classList.contains('d-none')) {
                form.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        }

        function formatarCNPJ(cnpj) {
            if (!cnpj) return '';
            // Adiciona pontos e barra
            // Remove tudo que não é dígito e garante 14 caracteres para formatação
            cnpj = cnpj.replace(/\D/g, '');
            if (cnpj.length === 14) {
                return cnpj.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, "$1.$2.$3/$4-$5");
            }
            return cnpj; // Retorna sem formatar se o tamanho for inesperado
        }

        function formatarTelefone(tel) {
            if (!tel) return '';
            // Ajusta para formatar (XX) XXXXX-XXXX ou (XX) XXXX-XXXX
            // Remove tudo que não é dígito
            tel = tel.replace(/\D/g, '');
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
            const displayCnpj = document.getElementById('displayCnpj');
            if(displayCnpj && displayCnpj.textContent) {
                displayCnpj.textContent = formatarCNPJ(displayCnpj.textContent.trim());
            }

            const displayTelefone = document.getElementById('displayTelefone');
            if(displayTelefone && displayTelefone.textContent) {
                displayTelefone.textContent = formatarTelefone(displayTelefone.textContent.trim());
            }

            // Adiciona máscaras de input para os campos de formulário (melhora UX)
            const inputCnpj = document.querySelector('input[name="cnpj"]');
            if (inputCnpj) {
                inputCnpj.addEventListener('input', function (e) {
                    let value = e.target.value.replace(/\D/g, ''); // Remove tudo que não é dígito
                    if (value.length > 14) value = value.slice(0, 14);
                    e.target.value = formatarCNPJ(value);
                });
                // Garante que o valor inicial já formatado
                if (inputCnpj.value) {
                    inputCnpj.value = formatarCNPJ(inputCnpj.value);
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

        :root {
            --primary-color: #815DF2; /* Roxo principal */
            --background-accent: rgba(129, 93, 242, 0.1); /* Roxo bem suave para background */
            --dark-grey: #343A40;
            --medium-grey: #495057;
            --light-grey: #adb5bd; /* Um cinza mais claro para textos secundários */
            --border-light: #E9ECEF; /* Cinza bem claro para bordas e linhas */
            --white: #FFFFFF;
            --darker-accent: #6C4ECB; /* Roxo mais escuro para hover/ativo */
            --shadow-light: rgba(0,0,0,.08);
            --success-color: #28a745;
            --danger-color: #dc3545;
        }

        body {
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            background-color: var(--border-light); /* Fundo suave para a página toda */
            color: var(--dark-grey);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            margin: 0; /* Garante que não há margem extra do body */
            padding-top: 56px; /* Para o header fixo do Bootstrap */
        }

        /* --- Layout Principal (Instagram-like) --- */
        .settings-container {
            display: flex;
            flex: 1; /* Ocupa o espaço restante */
            max-width: 1000px; /* Largura máxima, como no Instagram */
            margin: 2rem auto; /* Centraliza o container na página */
            background-color: var(--white);
            border-radius: 8px; /* Cantos arredondados para o container principal */
            box-shadow: 0 4px 12px var(--shadow-light);
            overflow: hidden; /* Garante que os filhos respeitem o border-radius */
            border: 1px solid var(--border-light); /* Borda sutil */
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

        .btn-secondary { /* Mantido para o modal */
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

        @media (max-width: 575px) {
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
                <i class="fa-solid fa-building"></i>
                <span>Perfil da Empresa</span>
            </a>
            <a class="nav-link" href="#item-3">
                <i class="fa-solid fa-lock"></i>
                <span>Dados de Acesso</span>
            </a>
            <a class="nav-link" href="#item-4">
                <i class="fa-solid fa-briefcase"></i>
                <span>Vagas</span>
            </a>
            <a class="nav-link" href="#item-5">
                <i class="fa-solid fa-map-location-dot"></i>
                <span>Endereço</span>
            </a>
            <a class="nav-link" href="#item-6">
                <i class="fa-solid fa-trash-can"></i>
                <span>Deletar Conta</span>
            </a>
        </nav>
    </aside>

    <main class="settings-content" data-bs-spy="scroll" data-bs-target=".settings-sidebar .sidebar-nav" data-bs-offset="0" tabindex="0">
        <section id="item-1" class="settings-section">
            <h4 class="section-title">Bem-vindo(a) às configurações da sua empresa!</h4>
            <p class="section-description">Use o menu ao lado para navegar e gerenciar as informações da sua empresa, dados de acesso, vagas e endereço. Mantenha seus dados atualizados para atrair os melhores talentos.</p>
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
            <h4 class="section-title">Alterar Perfil da Empresa</h4>
            <p class="section-description">Mantenha as informações da sua empresa atualizadas.</p>
            <div class="setting-card">
                <div class="info-display">
                    <p><strong>Nome da Empresa:</strong> ${empresa.nome}</p>
                    <p><strong>Razão Social:</strong> ${empresa.razaoSocial}</p>
                    <p><strong>Telefone:</strong> <span id="displayTelefone">${empresa.telefone}</span></p>
                    <p><strong>CNPJ:</strong> <span id="displayCnpj">${empresa.cnpj}</span></p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-perfil-empresa')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/empresa/put" method="post" id="form-perfil-empresa" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome da Empresa</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${empresa.nome}" >
                    </div>
                    <div class="mb-3">
                        <label for="razaoSocial" class="form-label">Razão Social</label>
                        <input type="text" class="form-control" id="razaoSocial" name="razaoSocial" value="${empresa.razaoSocial}" >
                    </div>
                    <div class="mb-3">
                        <label for="telefone" class="form-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name="telefone" value="${empresa.telefone}" placeholder="(XX) XXXXX-XXXX">
                    </div>
                    <div class="mb-3">
                        <label for="cnpj" class="form-label">CNPJ</label>
                        <input type="text" name="cnpj" class="form-control" id="cnpj" value="${empresa.cnpj}" placeholder="XX.XXX.XXX/XXXX-XX">
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
                    <p><strong>Email:</strong> ${empresa.email}</p>
                    <p><strong>Senha:</strong> ************ </p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-acesso')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/usuario/put" method="post" id="form-acesso" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${empresa.email}">
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
            <h4 class="section-title">Gerenciar Vagas</h4>
            <p class="section-description">Adicione, edite ou remova as vagas de estágio/emprego da sua empresa.</p>
            <div class="setting-group">
                <c:choose>
                    <c:when test="${not empty vagas}">
                        <c:forEach var="vaga" items="${vagas}">
                            <div class="setting-card item-card">
                                <div class="info-display">
                                    <p><strong>Título:</strong> ${vaga.titulo}</p>
                                    <p><strong>Requisitos:</strong> ${vaga.requisitos}</p>
                                    <p><strong>Benefícios:</strong> ${vaga.beneficios}</p>
                                    <p><strong>Descrição:</strong> ${vaga.descricao}</p>
                                    <p><strong>Quantidade de Vagas:</strong> ${vaga.qtdVagas}</p>
                                </div>
                                <div class="d-flex justify-content-end mt-3 button-group">
                                    <button class="btn btn-outline-primary me-2" onclick="toggleForm('form-vaga-${vaga.id}')">Editar</button>
                                    <form action="${pageContext.request.contextPath}/home/vaga/delete?id=${vaga.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir esta vaga?');">
                                        <button class="btn btn-outline-danger">Excluir</button>
                                    </form>
                                </div>
                                <form action="${pageContext.request.contextPath}/home/vaga/put?id=${vaga.id}" method="post" id="form-vaga-${vaga.id}" class="mt-4 d-none">
                                    <div class="mb-3">
                                        <label for="titulo-vaga-${vaga.id}" class="form-label">Título da Vaga</label>
                                        <input type="text" name="titulo" class="form-control" id="titulo-vaga-${vaga.id}" value="${vaga.titulo}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="requisitos-vaga-${vaga.id}" class="form-label">Requisitos</label>
                                        <textarea name="requisitos" class="form-control" id="requisitos-vaga-${vaga.id}">${vaga.requisitos}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="beneficios-vaga-${vaga.id}" class="form-label">Benefícios</label>
                                        <textarea name="beneficios" class="form-control" id="beneficios-vaga-${vaga.id}">${vaga.beneficios}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="descricao-vaga-${vaga.id}" class="form-label">Descrição</label>
                                        <textarea name="descricao" class="form-control" id="descricao-vaga-${vaga.id}">${vaga.descricao}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="qtdVagas-vaga-${vaga.id}" class="form-label">Quantidade de Vagas</label>
                                        <input type="number" name="qtdVagas" class="form-control" id="qtdVagas-vaga-${vaga.id}" value="${vaga.qtdVagas}">
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
                            <p class="text-muted text-center">Nenhuma vaga cadastrada ainda.</p>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="setting-card add-new-card">
                    <a class="btn btn-lg btn-outline-primary" href="${pageContext.request.contextPath}/home/vaga/insert">
                        <i class="fa-solid fa-plus me-2"></i> Adicionar Nova Vaga
                    </a>
                </div>
            </div>
        </section>

        <section id="item-5" class="settings-section">
            <h4 class="section-title">Alterar Endereço</h4>
            <p class="section-description">Mantenha o endereço da sua empresa atualizado.</p>
            <div class="setting-card">
                <div class="info-display">
                    <p><strong>Rua:</strong> ${empresa.endereco.rua}</p>
                    <p><strong>Bairro:</strong> ${empresa.endereco.bairro}</p>
                    <p><strong>Município:</strong> ${empresa.endereco.municipio} </p>
                    <p><strong>Estado:</strong> ${empresa.endereco.estado} </p>
                    <p><strong>CEP:</strong> ${empresa.endereco.cep}</p>
                </div>
                <button class="btn btn-outline-primary mt-3" onclick="toggleForm('form-endereco')">Editar</button>

                <form action="${pageContext.request.contextPath}/home/endereco/put" method="post" id="form-endereco" class="mt-4 d-none">
                    <div class="mb-3">
                        <label for="rua" class="form-label">Rua</label>
                        <input type="text" class="form-control" id="rua" name="rua" value="${empresa.endereco.rua}">
                    </div>
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" name="bairro" class="form-control" id="bairro" value="${empresa.endereco.bairro}">
                    </div>
                    <div class="mb-3">
                        <label for="municipio" class="form-label">Município</label>
                        <input type="text" name="municipio" class="form-control" id="municipio"  value="${empresa.endereco.municipio}">
                    </div>
                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado</label>
                        <input type="text" name="estado" class="form-control" id="estado" value="${empresa.endereco.estado}">
                    </div>
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" name="cep" class="form-control" id="cep" value="${empresa.endereco.cep}">
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            </div>
        </section>

        <section id="item-6" class="settings-section">
            <h4 class="section-title text-danger">Deletar Conta</h4>
            <p class="section-description">Esta ação é irreversível e excluirá a conta da sua empresa permanentemente.</p>
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
                Você tem certeza absoluta que deseja excluir a conta da sua empresa? Esta ação não pode ser desfeita e todos os seus dados serão permanentemente removidos.
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