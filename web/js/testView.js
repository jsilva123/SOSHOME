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

    $('#button-deletar').click(function () {
        $('#deletar-modal').modal();
    });
}); 