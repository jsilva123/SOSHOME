$(document).ready(function () {
    console.log('js carregado');
    $('.linha-ferramenta').click(function () {
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadTool&id=' + id;
        window.location.href = url;
    });
});



/*dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }*/