$(document).ready(function () {
    console.log('js carregado');
    $('.linha-projeto').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=soshome.model.command.LoadPedidoServico&id=' + id;
        window.location.href = url;
    });
}); 