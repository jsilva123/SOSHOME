$(document).ready(function () {
    console.log('js carregado');
    $('.validado-radio').change(function () {
        var index = $(this).attr("index");
        console.log('clicked ' + index + '.');
        if ($(this).hasClass('validado-radio-sim')) {
            console.log('going down...');
            $('#vulnerability-poc-form-' + index).slideDown();
        } else {
            console.log('going up...');
            $('#vulnerability-poc-form-' + index).slideUp();
        }
    });

    $('.validada-analista').each(function (e) {
        var resp = $(this).text().trim();
        console.log(resp);
        var index = $(this).attr('index');
        if (resp === "Sim") {
            console.log(index + " sim");
            $('#label-validado-sim-' + index).addClass('active');
            $('#input-validado-sim-' + index).attr('checked', 'checked');
        } else {
            console.log(index + " nao");
            $('#label-validado-nao-' + index).addClass('active');
            $('#input-validado-nao-' + index).attr('checked', 'checked');
        }
    });
}); 