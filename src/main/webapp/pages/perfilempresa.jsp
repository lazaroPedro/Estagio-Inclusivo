<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Empresa" %>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>

<%
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Vaga> vagasPublicadas = (List<Vaga>) request.getAttribute("vagasPublicadas");
    
    String cnpj = empresa.getCnpj();
    String cnpjFormatado = cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");

   
    String telefone = empresa.getTelefone();
    String telefoneFormatado = "";
    if (telefone != null) {
        if (telefone.length() == 11) {
            telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else if (telefone.length() == 10) {
            telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else {
            telefoneFormatado = telefone;
        }
    }
    
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil da Empresa</title>
     <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="/assets/components/header.jsp" %>
    <div class="container-xl mt-5 pt-5">
    <div class="card mb-4">
        <div class="card-body bg-light text-dark border">
            <h1 class="card-title">Dados da Empresa  <%= empresa.getNome() %></h1>
        </div>
    </div>
    <div class="card mb-4">
        <div class="card-body bg-secondary text-white">
            <h2 >Dados da Empresa</h2>
            </div>
        <div class="card-body">
            <p><strong>Nome:</strong> <%= empresa.getNome() %></p>
            <p><strong>Razão social:</strong> <%= empresa.getRazaoSocial() %></p>
             <p><strong>Cidade/Estado:</strong> <%= empresa.getEndereco().getMunicipio() %>/<%= empresa.getEndereco().getEstado() %></p>
            <p><strong>CNPJ:</strong> <%= cnpjFormatado %></p>
            <p><strong>Email:</strong> <%= empresa.getEmail() %></p>
            <p><strong>Telefone:</strong> <%= telefoneFormatado %></p>
         </div>
    </div>

<div class="container">
    <div class="row">
        <!-- VAGAS ATIVAS -->
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-header bg-secondary text-white">
                    <h2>Vagas Ativas</h2>
                </div>
                <div class="card-body">
                    <%
                        List<Vaga> vagasAtivas = (List<Vaga>) request.getAttribute("vagasAtivas");
                        if (vagasAtivas == null || vagasAtivas.isEmpty()) {
                    %>
                        <div class="alert alert-secondary">Nenhuma vaga Ativa.</div>
                    <%
                        } else {
                            for (Vaga vaga : vagasAtivas) {
                    %>
                        <div class="card mb-3 border-secondary">
                            <div class="row g-0">
                                <div class="col-4 card-body">
                                    <p><strong>Endereço:</strong> <%= vaga.getEndereco() %></p>
                                    <p><strong>Status:</strong> <%= vaga.getStatus() %></p>
                                </div>
                                <div class="col-4 card-body">
                                    <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                                    <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                                </div>
                                <div class="col-4 card-body">
                                    <p><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
                                    <p><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                                </div>
                            </div>
                        </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-header bg-secondary text-white">
                    <h2>Vagas Finalizadas</h2>
                </div>
                <div class="card-body">
                    <%
                        List<Vaga> vagasFinalizadas = (List<Vaga>) request.getAttribute("vagasFinalizadas");
                        if (vagasFinalizadas == null || vagasFinalizadas.isEmpty()) {
                    %>
                        <div class="alert alert-secondary">Nenhuma vaga finalizada.</div>
                    <%
                        } else {
                            for (Vaga vaga : vagasFinalizadas) {
                    %>
                        <div class="card mb-3 border-secondary">
                            <div class="row g-0">
                                <div class="col-4 card-body">
                                    <p><strong>Endereço:</strong> <%= vaga.getEndereco() %></p>
                                    <p><strong>Status:</strong> <%= vaga.getStatus() %></p>
                                </div>
                                <div class="col-4 card-body">
                                    <p><strong>Descrição:</strong> <%= vaga.getDescricao() %></p>
                                    <p><strong>Benefícios:</strong> <%= vaga.getBeneficios() %></p>
                                </div>
                                <div class="col-4 card-body">
                                    <p><strong>Quantidade de Vagas:</strong> <%= vaga.getQtdVagas() %></p>
                                    <p><strong>Localização:</strong> <%= vaga.getEndereco().getMunicipio() + " - " + vaga.getEndereco().getEstado() %></p>
                                </div>
                            </div>
                        </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
