$(document).ready(function () {
    $('.linha-projeto').click(function () {
        console.log('clicado');
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoCliente&id=' + id;
        window.location.href = url;
    });

});
$(document).ready(function () {
    $('.linha-projeto-respondidos').click(function () {
        console.log('clicado');
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoCliente&id=' + id;
        window.location.href = url;
    });

});
$(document).ready(function () {
    $('.linha-projeto-profissional').click(function () {
        console.log('clicado');
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoProfissional&id=' + id;
        window.location.href = url;
    });

});
$(document).ready(function () {
    $('.linha-projeto-profissional-respondidos').click(function () {
        console.log('clicado');
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoProfissional&id=' + id;
        window.location.href = url;
    });

});


/*dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }*/