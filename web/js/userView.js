$(document).ready(function () {
    console.log('js carregado');
    $('.linha-usuario-projeto').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoServico&id=' + id;
        window.location.href = url;
    });
});



/*dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }*/