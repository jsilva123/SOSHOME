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

            <ul class="nav navbar-nav" role="tablist">
                <li class="aba active" id="aba-ativo">
                    <a href="#pane-ativo" role="tab" data-toggle="tab">Ativos</a>
                </li>
            </ul>

            <%@include file='upperbar-right.jsp'%> 
        </nav>
    </div><!-- /top nav -->

    <div class="padding">
        <div class="full col-sm-9">
            <!-- content -->

            <div class="row tab-content">

                <!-- tabela do ativo -->
                <div class="tab-pane active"  id="pane-ativo">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Listagem de Usu&aacuterios Ativos</h3>
                        </div>

                        <table class="table table-bordered table-striped table-hover">
                            <tr>
                                <th class="col-sm-10">Nome</th>
                                <th class="col-sm-2">CPF</th>
                            </tr>

                            <c:forEach var="cliente" items="${clientes}">
                                <tr class="linha-user" id='${cliente.getIdcliente()}'>
                                    <td>${cliente.getNome()}</td>
                                    <td>${cliente.getCpf()}</td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>


            </div>
            <!--/row-->
            <hr>

        </div><!-- /col-9 -->
    </div><!-- /padding -->
    <c:if test="${msg}">
        <!-- Modal -->
        <div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Aten&ccedil;&atilde;o</h4>
                    </div>
                    <div class="modal-body">
                        <div class="panel-body">
                            ${msg_txt}
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>  
    </c:if>
</div><!-- /main -->
</div><!--post modal-->g


</body>
</html>
