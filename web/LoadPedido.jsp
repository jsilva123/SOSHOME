<%@page import="soshome.model.bean.Cliente"%>
<%@page import="soshome.util.Constants" %>
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
    <script src="moment.js"></script>
    <body>
        <div class="wrapper box row row-offcanvas row-offcanvas-left">
            <%@include file='sidebar.jsp'%>


            <ul class="nav navbar-nav">
                <li id="aba-info" class="aba active">
                    <a href="#" role="tab" data-toggle="tab">Detalhes do Pedido</a>
                </li>
            </ul>

            <%@include file='upperbar-right.jsp'%>        



        </div><!-- /top nav -->
        <!--Pedidos Criados, Cliente -->
        <c:if test="${pedido.getStatus().equals('CRIADO') && pageContext.session.getAttribute('isCliente').equals('true')}">
            <div class="padding">
                <form name="edit" action="ServletController" method="POST">
                    <div class="full col-sm-9">
                        <!-- content -->

                        <div class="row panel panel-primary">
                            <!-- Default panel contents -->


                            <div class="panel-heading">
                                <h3 class="panel-title">Detalhes do Pedido</h3>
                            </div>

                            <table class="table table-bordered table-striped table-hover">
                                <tr class="">
                                    <td class="">Descri&ccedil;&atilde;o</td>
                                    <td class="">
                                        <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                            <c:out value="${pedido.getDescricao()}" />
                                        </textarea>
                                    </td>                                   
                                </tr>
                                <tr class="">
                                    <td class="">Tipo de Servi&ccedil;o</td>
                                    <td class="">
                                        <select name="selectTipo">
                                            <c:choose>
                                                <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                                    <option value="1" selected>El&eacute;trico</option>
                                                    <option value="2" >Hidr&aacute;ulico</option>
                                                </c:when>
                                                <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                                    <option value="1" >El&eacute;trico</option>
                                                    <option value="2" selected>Hidr&aacute;ulico</option>
                                                </c:when>
                                                <c:otherwise>

                                                    <option value="1" >El&eacute;trico</option>
                                                    <option value="2" >Hidr&aacute;ulico</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </td>                                   
                                </tr>
                                <tr class="">
                                    <td class="">Emerg&ecirc;ncia?</td>
                                    <td>
                                        <input type="checkbox" name="emergenciaSelect" value="ON"  ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                                    </td>
                                </tr>
                                <tr class="">
                                    <td class="">Faixa de Hor&aacute;rio</td>
                                    <td class="">
                                        <select name="selectTurno">
                                            <option value="manha">Manh&atilde;</option>
                                            <option value="tarde">Tarde</option>
                                            <option value="noite">Noite</option>
                                            <option value="integral">Integral</option>
                                        </select>
                                    </td>                                   
                                </tr>

                            </table>

                        </div><!--/row-->
                        <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
                        <input type="hidden" name="cmd" value="soshome.model.command.EditPedidoServico" />
                        <input type="submit" value="Editar" class="btn btn-primary btn pull-right"/>
                </form>
                <form name="edit" action="ServletController" method="POST">
                    <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
                    <input type="hidden" name="cmd" value="soshome.model.command.DeletePedidoServico" />
                    <input type="submit" value="Excluir" class="btn btn-danger btn pull-right"/>
                </form>
                <hr>
            </div></form></div>
</c:if><!-- /col-9 --> 

<!--Pedidos Respondidos, Cliente -->
<c:if test="${pedido.getStatus().equals('RESPONDIDO') && pageContext.session.getAttribute('isCliente').equals('true')}">    <!--Pedidos Respondidos, Cliente -->
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" name="deadline" disabled="" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Profissional</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getNome()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Telefone</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getTelefone()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Valor Base</td>
                            <td class="">
                                <input id="input-projeto-valor" type="text" name="telefone" readonly="" disabled="" value="${servico.getValorbase()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Classifica&ccedil;&atilde;o M&eacute;dia</td>
                            <td class="">
                                <c:if test="media > 0">
                                    <input id="input-projeto-media" type="text" name="media" readonly="" disabled="" value="${media}"/>
                                </c:if>
                                <c:if test="media = 0">
                                    <input id="input-projeto-media" type="text" name="media" readonly="" disabled="" value="Sem Classifica&ccedil;&atilde;o"/>
                                </c:if>

                            </td>
                        </tr>
                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.ConfirmPedidoServico" />

            <input type="submit" value="Confirmar Serviço" class="btn btn-primary btn pull-right"/>


            <!--
            
            -->
            <br/>
        </form><!-- /col-9 -->
        <form name="edit" action="ServletController" method="POST">
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="cmd" value="soshome.model.command.RefutePedidoServico" />
            <input type="submit" value="Recusar" class="btn btn-danger btn pull-right"/>
        </form>
        <br/>
    </form><!-- /col-9 -->
    <form name="edit" action="ServletController" method="POST">
        <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
        <input type="hidden" name="cmd" value="soshome.model.command.DeletePedidoServico" />
        <input type="submit" value="Excluir" class="btn btn-danger btn pull-right"/>
    </form> </div>
</div><!-- /padding --></c:if>

<!--Pedidos Aceitos, Cliente -->
<c:if test="${pedido.getStatus().equals('ACEITO') && pageContext.session.getAttribute('isCliente').equals('true')}">    <!--Pedidos Respondidos, Cliente -->
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" name="deadline" disabled="" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Profissional</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getNome()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Telefone</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getTelefone()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Valor Base</td>
                            <td class="">
                                <input id="input-projeto-valor" type="text" name="telefone" readonly="" disabled="" value="${servico.getValorbase()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Classificação Média</td>
                            <td class="">
                                <c:if test="media > 0">
                                    <input id="input-projeto-media" type="text" name="media" readonly="" disabled="" value="${media}"/>
                                </c:if>
                                <c:if test="media > 0">
                                    <input id="input-projeto-media" type="text" name="media" readonly="" disabled="" value="Sem Classifica&ccedil;&atilde;o"/>
                                </c:if>

                            </td>
                        </tr>
                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="id" value="${pedido.getIdcliente().getIdcliente()}" />

            <input type="hidden" name="cmd" value="soshome.model.command.LoadPedidosCliente" />

            <input type="submit" value="Voltar" class="btn btn-primary btn pull-right"/>

            <!--
            
            -->


        </form><!-- /col-9 -->
        <form name="edit" action="ServletController" method="POST">
            <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="cmd" value="soshome.model.command.DeletePedidoServico" />
            <input type="submit" value="Excluir" class="btn btn-danger btn pull-right"/>
        </form> </div>
</div><!-- /padding --></c:if>


<!--Pedidos Agendados, Cliente -->
<c:if test="${pedido.getStatus().equals('CONFIRMADO') && pageContext.session.getAttribute('isCliente').equals('true')}">    <!--Pedidos Respondidos, Cliente -->
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" name="deadline" readonly=""  disabled="" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Profissional</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getNome()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Telefone</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getTelefone()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Nota</td>
                            <td class="">
                                <input id="input-projeto-media" type="text" name="nota" value=""/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.FinalizePedidoServico" />

            <input type="submit" value="Finalizar Pedido" class="btn btn-primary btn pull-right"/>
    </div>

    <!--
    
    -->



</form><!-- /col-9 -->
</div><!-- /padding --></c:if>

<!--Pedidos Finalizados, Cliente -->
<c:if test="${pedido.getStatus().equals('FINALIZADO') && pageContext.session.getAttribute('isCliente').equals('true')}">    <!--Pedidos Respondidos, Cliente -->
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" name="deadline" disabled="" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Profissional</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getNome()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Telefone</td>
                            <td class="">
                                <input id="input-projeto-telefone" type="text" name="telefone" readonly="" disabled="" value="${pedido.getIdprofissional().getTelefone()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Valor Base</td>
                            <td class="">
                                <input id="input-projeto-valor" type="text" name="telefone" readonly="" disabled="" value="${servico.getValorbase()}"/>
                            </td>
                        </tr>
                        </tr>
                        <tr class="">
                            <td class="">Nota</td>
                            <td class="">

                                <input id="input-projeto-media" type="text" disabled="" readonly=" "name="nota" value="${pedido.getAvaliacao()}"/>


                            </td>
                        </tr>
                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="id" value="${pedido.getIdcliente().getIdcliente()}" />

            <input type="hidden" name="cmd" value="soshome.model.command.LoadPedidosCliente" />

            <input type="submit" value="Voltar" class="btn btn-primary btn pull-right"/>


            <!--
            
            -->

        </form>
        <!-- /col-9 -->
    </div>
</div><!-- /padding --></c:if>




<!--Pedidos Criados, Profissional -->
<c:if test="${pedido.getStatus().equals('CRIADO') && pageContext.session.getAttribute('isProfissional').equals('true')}"> 
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Valor Base</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="" name="vBase" value=""/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" name="deadline" value=""/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.ClaimPedidoServico" />

            <input type="submit" value="Oferecer Serviço" class="btn btn-primary btn pull-right"/>
    </div>

    <!--
    
    -->



</form><!-- /col-9 -->
</div><!-- /padding -->
</c:if>

<!--Pedidos Respondidos, Profissional -->
<c:if test="${pedido.getStatus().equals('RESPONDIDO') && pageContext.session.getAttribute('isProfissional').equals('true')}">
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" disabled="" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" disabled=""  readonly="" name="deadline" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Valor Base</td>
                            <td class="">
                                <input id="input-projeto-valor" type="text" name="vBase" readonly="" disabled="" value="${servico.getValorbase()}"/>
                            </td>
                        </tr>
                        </tr>


                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.ConfirmPedidoServicoProfissional" />

            <input type="submit" value="Agendar" class="btn btn-primary btn pull-right"/>
    </div>
</form>

<form name="edit" action="ServletController" method="POST">
    <input type="hidden" name="id" value="${pedido.getIdpedido()}" />
    <input type="hidden" name="cmd" value="soshome.model.command.RefutePedidoServico" />
    <input type="submit" value="Recusar" class="btn btn-danger btn pull-right"/>
</form>
<!-- /col-9 -->
</div><!-- /padding -->
</c:if>

<!--Pedidos Confirmados, Profissional -->
<c:if test="${pedido.getStatus().equals('CONFIRMADO') && pageContext.session.getAttribute('isProfissional').equals('true')}">
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" disabled="" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" disabled=""  readonly="" name="deadline" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Endere&ccedil;o</td>
                            <td class="">
                                <input id="input-projeto-endereco" type="text" disabled=""  readonly="" name="endCli" value="${pedido.getIdcliente().getEndereco()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Telefone do Profissional</td>
                            <td class="">
                                <input id="input-projeto-endereco" type="text" disabled=""  readonly="" name="endCli" value="${pedido.getIdcliente().getTelefone()}"/>
                            </td>
                        </tr>

                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.ConfirmPedidoServicoProfissional" />

            <input type="submit" value="Agendar" class="btn btn-primary btn pull-right"/>
        </form>
        <form name="edit" action="ServletController" method="POST">
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="cmd" value="soshome.model.command.RefutePedidoServico" />
            <input type="submit" value="Recusar" class="btn btn-danger btn pull-right"/>
        </form>
    </div>

    <!--
    
    -->



    <!-- /col-9 -->
</div><!-- /padding -->
</c:if>

<!--Pedidos Aceitos, Profissional -->
<c:if test="${pedido.getStatus().equals('ACEITO') && pageContext.session.getAttribute('isProfissional').equals('true')}">
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" disabled="" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" disabled=""  readonly="" name="deadline" value="${pedido.getDataexecucao()}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Endere&cc&ccedil;o</td>
                            <td class="">
                                <input id="input-projeto-endereco" type="text" disabled=""  readonly="" name="endCli" value="${pedido.getIdcliente().getEndereco()}"/>
                            </td>
                        </tr>


                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="idPedido" value="${pedido.getIdpedido()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.ConfirmPedidoServicoProfissional" />

            <input type="submit" value="Agendar" class="btn btn-primary btn pull-right"/>


        </form>
    </div>

    <!--
    
    -->



</form><!-- /col-9 -->
</div><!-- /padding -->
</c:if>



<!--Pedidos Finalizados, Profissional -->
<c:if test="${pedido.getStatus().equals('FINALIZADO') && pageContext.session.getAttribute('isProfissional').equals('true')}">
    <div class="padding">
        <form name="edit" action="ServletController" method="POST">
            <div class="full col-sm-9">
                <!-- content -->

                <div class="row panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <h3 class="panel-title">Detalhes do Pedido</h3>
                    </div>

                    <table class="table table-bordered table-striped table-hover">
                        <tr class="">
                            <td class="">Descri&ccedil;&atilde;o</td>
                            <td class="">
                                <textarea name="descricaoTextArea"  rows="4" cols="20" disabled="" readonly="readonly">
                                    <c:out value="${pedido.getDescricao()}" />
                                </textarea>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Tipo de Servi&ccedil;o</td>
                            <td class="">
                                <select name="selectTipo" disabled="">
                                    <c:choose>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 1}">
                                            <option value="1" selected>El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:when test="${pedido.getIdtiposervico().getIdtiposervico() == 2}">
                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" selected>Hidr&aacute;ulico</option>
                                        </c:when>
                                        <c:otherwise>

                                            <option value="1" >El&eacute;trico</option>
                                            <option value="2" >Hidr&aacute;ulico</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Emerg&ecirc;ncia?</td>
                            <td>
                                <input type="checkbox" name="emergenciaSelect" value="ON"  disabled="" ${(pedido.getFlagemergencia()+"").equals('T') ? 'checked' : ''} />      
                            </td>


                        </tr>
                        <tr class="">
                            <td class="">Faixa de Hor&aacute;rio</td>
                            <td class="">
                                <select name="selectTurno" disabled="">
                                    <option value="manha">Manh&atilde;</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noite">Noite</option>
                                    <option value="integral">Integral</option>
                                </select>

                            </td>                                   
                        </tr>
                        <tr class="">
                            <td class="">Prazo</td>
                            <td class="">
                                <input id="input-projeto-prazo" type="date" disabled=""  readonly="" name="deadline" value=""/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="">Endere&cc&ccedil;o</td>
                            <td class="">
                                <input id="input-projeto-endereco" type="text" disabled=""  readonly="" name="endCli" value="${pedido.getIdcliente().getEndereco()}"/>
                            </td>
                        </tr>


                    </table>
                </div>
            </div><!--/row-->
            <input type="hidden" name="id" value="${pedido.getIdprofissional().getIdprofissional()}" />
            <input type="hidden" name="idProfissional" value="${pageContext.session.getAttribute('id')}" />

            <input type="hidden" name="cmd" value="soshome.model.command.LoadPedidosProfissional" />

            <input type="submit" value="Voltar" class="btn btn-primary btn pull-right"/>


        </form>
    </div>

    <!--
    
    -->



</form><!-- /col-9 -->
</div><!-- /padding -->
</c:if>









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