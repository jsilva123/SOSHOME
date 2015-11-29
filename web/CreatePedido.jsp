<%@page import="soshome.model.bean.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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


            <ul class="nav navbar-nav">
                <li id="aba-info" class="aba active">
                    <a href="#" role="tab" data-toggle="tab">Criar Cliente</a>
                </li>
            </ul>

            <%@include file='upperbar-right.jsp'%>        


        </nav>
    </div><!-- /top nav -->

    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->


                    <div class="panel-heading">
                        <h3 class="panel-title">Criar Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">


                        <tr class="">
                            <td class="">Descrição</td>
                            <td class="">

                                <textarea name="descricaoTextArea"  rows="4" cols="20">
                                </textarea>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Serviço</td>
                            <td class="">


                                <select name="selectTipo">

                                    <option value="1">Elétrico</option>
                                    <option value="2">Hidráulico</option>
                                </select>



                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emergência?</td>
                            <td class="">

                                <input type="checkbox" name="emergenciaSelect" value="ON" />           
                            </td>                                   
                        </tr>

                        <tr class="">
                            <td class="">Faixa de Horário</td>
                            <td class="">
                                <select name="selectTurno">
                                    <option value="manha">Manhã</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>

                    </table>

                </div><!--/row-->
                <input type="hidden" name="id" value="${pageContext.session.getAttribute('id')}" />
                <input type="hidden" name="cmd" value="soshome.model.command.CreatePedidoServico" />
                <input type="submit" value="Criar Pedido de Serviço" class="btn btn-primary btn pull-right"/>
                <!--
               
                -->

                <hr>

            </div></form><!-- /col-9 -->
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
</div><!--post modal-->

<!--<script>
    window.jQuery || document.write('<script src="js/vendor/jquery-1.11.0.min.js"><\/script>')
</script> <script src="js/vendor/bootstrap.min.js"></script> <script src="js/vendor/moment.min.js"></script> <script src="js/main.js"></script> <script src="js/tools - view.js"></script>
-->
</body>
</html>