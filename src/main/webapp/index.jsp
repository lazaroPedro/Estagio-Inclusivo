<%@ page import="com.ifbaiano.estagioinclusivo.model.Vaga" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Estagio Inclusivo</title>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.5/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
    <%@ include file="/assets/components/header.jsp" %>

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
                <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/vaga/insert?id=<%=u.getId()%>">
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
