$(document).ready(function () {
    console.log('js carregado');
    $('.linha-vm').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadVmProjeto&idVm=' + id;
        window.location.href = url;
    });
});



/*dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }*/