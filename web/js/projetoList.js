$(document).ready(function () {
    $('.linha-projeto').click(function () {
        console.log('clicado');
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadProject&id=' + id;
        window.location.href = url;
    });

    //Setando vermelho se estiver perto do prazo.
    $('.linha-projeto-prazo').each(function () {
        dataString = $(this).text();
        dataLimite = moment(dataString, "DD/MM/YYYY");
        hoje = moment();
        antecedencia = 5;
        dataAviso = dataLimite.subtract(antecedencia, 'days');
//        if (data.isBefore(hoje, "day")){
//                $(this).parent().addClass('danger');
//        }
        if (hoje.isAfter(dataAviso)) {
            console.log('danger!');
            $(this).parent().addClass('danger');
        }
    });
});



/*dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }*/