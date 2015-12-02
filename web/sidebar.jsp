<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="column col-sm-2 col-xs-1 sidebar-offcanvas hidden-print" id="sidebar">
    <ul class="nav">
        <li>
            <a class=
               "visible-xs text-center glyphicon glyphicon-chevron-right"
               data-toggle="offcanvas" href="#" style=
               "font-style: italic" id="sidebar-seta"></a>
        </li>
    </ul>

    <ul class="nav hidden-xs" id="lg-menu">

        <c:if test="${pageContext.session.getAttribute('isCliente').equals('true')}">
            <li>
                <a class="side-menu-title" href="CreatePedido.jsp"><i class="glyphicon glyphicons-plus"></i> Criar Pedido</a>
            </li>
            <li>
                <a class="side-menu-title" href="ServletController?cmd=soshome.model.command.LoadPedidosCliente&id='${pageContext.session.getAttribute('id')}'"><i class="glyphicon glyphicon-stats"></i> Pedidos</a>
            </li>
            <li>
                <a class="side-menu-title" href="ServletController?cmd=soshome.model.command.GenerateReport"><i class="glyphicon glyphicon-stats"></i> Gerar Relatório</a>
            </li>

        </c:if>
        <c:if test="${pageContext.session.getAttribute('nomeCliente').equals('Admin')}">
            <li>
                <a class="side-menu-title" href="CreateProfissional.jsp"><i class="glyphicon glyphicon-stats"></i> Criar Profissional</a>
            </li>
        </c:if>

        <c:if test="${pageContext.session.getAttribute('isProfissional').equals('true')}">
            <li>
                <a class="side-menu-title" href="ServletController?cmd=soshome.model.command.LoadPedidosProfissional&id='${pageContext.session.getAttribute('id')}'"><i class="glyphicon glyphicon-stats"></i> Pedidos</a>
            </li>
        </c:if>




    </ul>

    <ul class="nav visible-xs" id="xs-menu">
        <li>
            <a class="text-center glyphicon glyphicon-stats" href=
               "#ferramentas" style="font-style: italic"></a>
        </li>

        <li>
            <a class="text-center glyphicon glyphicon-home" href=
               "#" style="font-style: italic"></a>
        </li>

        <li>
            <a class="text-center glyphicon glyphicon-user" href=
               "#" style="font-style: italic"></a>
        </li>

        <li>
            <a class="text-center glyphicon glyphicon-wrench" href="#"
               style="font-style: italic"></a>
        </li>
    </ul>
</div><!-- /sidebar -->


<!-- main right col -->

<div class="column col-sm-10 col-xs-11" id="main">
    <!-- top nav -->

    <div class="navbar navbar-blue navbar-static-top">
        <div class="navbar-header">
            <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
                <span class="sr-only">Toggle</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">S.O.S HOME</a>
        </div>

        <nav class="collapse navbar-collapse">