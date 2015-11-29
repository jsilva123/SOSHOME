$(document).ready(function () {
    console.log('js carregado');
    $('.linha-cliente').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadClient&id=' + id;
        window.location.href = url;
    });
}); 