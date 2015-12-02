<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->

<html class="no-js">
    <!--<![endif]-->

    <%@include file='head.html'%>

    <body>
        <div class="wrapper box row row-offcanvas row-offcanvas-left">
            <%@include file='sidebar.jsp'%>
            <c:if test="${pageContext.session.getAttribute('isCliente').equals('true')}">
                <ul class="nav navbar-nav">
                    <li id="aba-criado" class="aba active">
                        <a href="#pane-criado" role="tab" data-toggle="tab">Pedidos Esperando Resposta</a>
                    </li>

                    <li id="aba-respondida" class="aba">
                        <a href="#pane-respondida" role="tab" data-toggle="tab">Pedidos Respondidos
                        </a>
                    </li>

                    <li id="aba-aceito" class="aba">
                        <a href="#pane-aceito" role="tab" data-toggle="tab">Pedidos Esperando Confirma&ccedil;&atilde;o
                        </a>
                    </li>

                    <li id="aba-confirmado" class="aba">
                        <a href="#pane-confirmado" role="tab" data-toggle="tab">Pedidos Confirmados
                        </a>
                    </li>


                    <li id="aba-finalizado" class="aba">
                        <a href="#pane-finalizado" role="tab" data-toggle="tab">Pedidos Finalizados
                        </a>
                    </li>
                </ul>

                <%@include file='upperbar-right.jsp'%> 
            </c:if>
            <c:if test="${pageContext.session.getAttribute('isProfissional').equals('true')}">
                <ul class="nav navbar-nav">
                    <li id="aba-criado" class="aba active">
                        <a href="#pane-criado" role="tab" data-toggle="tab">Pedidos Abertos</a>
                    </li>

                    <li id="aba-respondida" class="aba">
                        <a href="#pane-respondida" role="tab" data-toggle="tab">Pedidos Respondidos
                        </a>
                    </li>

                    <li id="aba-aceito" class="aba">
                        <a href="#pane-aceito" role="tab" data-toggle="tab">Pedidos Aceitos
                        </a>
                    </li>

                    <li id="aba-confirmado" class="aba">
                        <a href="#pane-confirmado" role="tab" data-toggle="tab">Pedidos Confirmados
                        </a>
                    </li>

                    <li id="aba-finalizado" class="aba">
                        <a href="#pane-finalizado" role="tab" data-toggle="tab">Pedidos Finalizados
                        </a>
                    </li>
                </ul>

                <%@include file='upperbar-right.jsp'%> 
            </c:if>

        </nav>
    </div><!-- /top nav -->
    <c:if test="${pageContext.session.getAttribute('isCliente').equals('true')}">
        <div class="padding">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row tab-content">

                    <!-- tabela de todos os analise -->
                    <div class="tab-pane active"  id="pane-criado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Criadas
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Status</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosCriados}">
                                    <tr class="linha-projeto" id="${pedido.idpedido}">
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <!-- tabela de todos os confirmados -->
                    <div class="tab-pane"  id="pane-respondida">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Respondidas
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosRespondidos}">
                                    <tr class="linha-projeto-respondidos" id="${pedido.idpedido}">
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane"  id="pane-confirmado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Confirmados
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosConfirmados}">
                                    <tr class="linha-projeto-respondidos" id="${pedido.idpedido}">
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane"  id="pane-aceito">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Confirmados
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosAceitos}">
                                    <tr class="linha-projeto-respondidos" id="${pedido.idpedido}">
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane"  id="pane-finalizado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Confirmados
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosFinalizados}">
                                    <tr class="linha-projeto-respondidos" id="${pedido.idpedido}">
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </div><!--/row-->
                <hr>

            </div><!-- /col-9 -->
        </div><!-- /padding -->
    </c:if>

    <c:if test="${pageContext.session.getAttribute('isProfissional').equals('true')}">
        <div class="padding">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row tab-content">

                    <!-- tabela de todos os analise -->
                    <div class="tab-pane active"  id="pane-criado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Criadas
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Status</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosCriados}">
                                    <tr class="linha-projeto-profissional" id='${pedido.idpedido}'>
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <!-- tabela de todos os analisados -->
                    <div class="tab-pane"  id="pane-respondida">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Respondidas
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosRespondidos}">
                                    <tr class="linha-projeto-profissional-respondidos" id='${pedido.idpedido}'>
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane"  id="pane-confirmado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Respondidas
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosConfirmados}">
                                    <tr class="linha-projeto-profissional-respondidos" id='${pedido.idpedido}'>
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane"  id="pane-aceito">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Aceitos
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosAceitos}">
                                    <tr class="linha-projeto-profissional-respondidos" id='${pedido.idpedido}'>
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane"  id="pane-finalizado">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                Finalizados
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr>
                                    <th class="col-sm-8">Nome do Projeto</th>
                                    <th class="col-sm-2">Data de In&iacute;cio</th>
                                    <th class="col-sm-2">Prazo</th>
                                </tr>

                                <c:forEach var="pedido" items="${pedidosFinalizados}">
                                    <tr class="linha-projeto-profissional-respondidos" id='${pedido.idpedido}'>
                                        <td class="linha-projeto-nome">${pedido.descricao}</td>
                                        <td class="linha-projeto-data">${pedido.datasolicitacao}</td>
                                        <td class="linha-projeto-prazo">${pedido.flagemergencia }</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>


                </div><!--/row-->
                <hr>

            </div><!-- /col-9 -->
        </div><!-- /padding -->
    </c:if>
</div><!-- /main -->
</div><!--post modal-->

<script>
    window.jQuery || document.write('<script src="js/vendor/jquery-1.11.0.min.js"><\/script>')
</script> 
<script src="js/vendor/bootstrap.min.js"></script> 
<script src="js/vendor/moment.min.js"></script> 
<script src="js/main.js"></script> 
<script src="js/pedidoList.js"></script>
</body>
</html>