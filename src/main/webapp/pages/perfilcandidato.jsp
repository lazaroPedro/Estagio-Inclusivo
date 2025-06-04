<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Candidato"%>
<%@ page import="com.ifbaiano.estagioinclusivo.model.Curso"%>
<%@ page import="com.ifbaiano.estagioinclusivo.model.TipoDeficiencia"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDate"%>


<%
Candidato candidato = (Candidato) request.getAttribute("candidato");
List<TipoDeficiencia> deficiencias = (List<TipoDeficiencia>) request.getAttribute("deficiencias");
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");

LocalDate dataNascimento = candidato.getDataNascimento();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String dataFormatada = dataNascimento.format(formatter);
String cpf = candidato.getCpf();
String cpfFormatado = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

String telefone = candidato.getTelefone();
String telefoneFormatado = "";
if (telefone.length() == 11) {
	telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
} else if (telefone.length() == 10) {
	telefoneFormatado = telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
} else {
	telefoneFormatado = telefone;
}
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil do Candidato</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .header-curvo {
            background-color: #805DFF;
            color: white;
            padding: 60px 0;
            border-bottom-left-radius: 50% 10%;
            border-bottom-right-radius: 50% 10%;
            text-align: center;
        }

        .breadcrumb-custom {
            background: none;
            color: white;
        }

        .breadcrumb-custom a {
            color: white;
            text-decoration: underline;
        }

        .section-title {
            color: #805DFF;
            font-weight: 600;
            border-bottom: 2px solid #805DFF;
            padding-bottom: 5px;
            margin-bottom: 20px;
            display: inline-block;
        }

        .card-info {
            border: 2px solid #805DFF;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
            margin-bottom: 20px;
        }

        @media (max-width: 768px) {
            .col-md-6 {
                margin-bottom: 40px;
            }
        }
    </style>
</head>
<body>

<%@ include file="/assets/components/header.jsp"%>

<div class="header-curvo">
    <h1>Meu perfil</h1>
</div>

<div class="container mt-5">
    <div class="row">
      
        <div class="">
            <h2 class="section-title">Dados Pessoais</h2>
            <div class="card-info">
                <p><strong>Nome:</strong> <%= candidato.getNome() %></p>
                <p><strong>Gênero:</strong> <%= candidato.getGenero() %></p>
                <p><strong>Data de Nascimento:</strong> <%= dataFormatada %></p>
                <p><strong>Cidade:</strong> <%= candidato.getEndereco().getMunicipio() %></p>
                <p><strong>Email:</strong> <%= candidato.getEmail() %></p>
                <p><strong>CPF:</strong> <%= cpfFormatado %></p>
                <p><strong>Telefone:</strong> <%= telefoneFormatado %></p>
            </div>
        </div>

      
        <div class="">
            <h2 class="section-title">Deficiências</h2>
            <%
                if (deficiencias == null || deficiencias.isEmpty()) {
            %>
                <div class="alert alert-secondary">Nenhuma deficiência registrada para este candidato.</div>
            <%
                } else {
                    for (TipoDeficiencia def : deficiencias) {
            %>
                <div class="card-info">
                    <p><strong>Deficiência:</strong> <%= def.getNome() %></p>
                    <p><strong>Tipo:</strong> <%= def.getTipo() %></p>
                    <p><strong>Descrição:</strong> <%= def.getDescricao() %></p>
                    <p><strong>Tipo de Apoio:</strong> <%= def.getTipoApoio() %></p>
                </div>
            <%
                    }
                }
            %>

            <h2 class="section-title">Cursos Inscritos</h2>
            <%
                if (cursos == null || cursos.isEmpty()) {
            %>
                <div class="alert alert-secondary">Você ainda não se inscreveu em nenhum curso.</div>
            <%
                } else {
                    DateTimeFormatter formatterCurso = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    for (Curso curso : cursos) {
            %>
                <div class="card-info">
                    <p><strong>Instituição:</strong> <%= curso.getInstituicao() %></p>
                    <p><strong>Curso:</strong> <%= curso.getNomeCurso() %></p>
                    <p><strong>Descrição:</strong> <%= curso.getDescricao() %></p>
                    <p><strong>Início:</strong> <%= curso.getDataInicio().format(formatterCurso) %></p>
                    <p><strong>Fim:</strong> <%= curso.getDataFim().format(formatterCurso) %></p>
                </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
