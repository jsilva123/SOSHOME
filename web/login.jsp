<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>SOSHOME - Login</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            body {
                padding-top: 50px;
                padding-bottom: 20px;
            }
        </style>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/login.css">

        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

        <link rel="icon" 
              type="image/png" 
              href="favicon.ico">
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->


        <div class="container">

            <form class="form-signin" role="form" name="login" action="ServletController" method="POST">
                <input type="hidden" name="cmd" value="soshome.model.command.UserLogin" />
                <h2 class="form-signin-heading">Por favor, entre com as suas credenciais</h2>
                <input type="text" class="form-control" placeholder="Usu&aacute;rio(nome)" name="login" required autofocus>
                <input type="password" class="form-control" placeholder="Senha(CPF)" name="password" required>
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login"/>
            </form>
            <form class="form-signin" role="form" name="create" action="CreateCliente.jsp" method="POST" >
                <input type="hidden" name="cmd" value=""  />
                <input class="btn btn-lg btn-info btn-block" type="submit" value="Criar novo Cliente"/>
            </form>
        </div> <!-- /container -->

        <div class="footer">
            <div class="container">

            </div>
        </div>

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

        <footer>



        </footer>

        <script>
            window.jQuery || document.write('<script src="js/vendor/jquery-1.11.0.min.js"><\/script>')
        </script>
        <script src="js/vendor/bootstrap.min.js"></script> <script src="js/main.js"></script>
    </body>
</html>
