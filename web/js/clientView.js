$(document).ready(function () {
    console.log('js carregado');
    $('.linha-projeto').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadProject&id=' + id;
        window.location.href = url;
    });
}); 