$(document).ready(function () {
    console.log('parameterCreate.js carregadoo');

    /**
     * Exibe o ID VM somente quando necessario.
     */
    $('#dinamico-form').hide();
    $('#select-dinamico').change(function () {
        //$('#form-vm').slideDown('slow');
        if ($(this).prop('checked') === true) {
            $('#dinamico-form').slideDown('slow');
            $('#exe-input').prop('required', true);
        } else {
            $('#dinamico-form').slideUp('slow');
            $('#exe-input').prop('required', false);
        }
        console.log('clicou');
    });

    /**
     * Exibe o BD dump somente quando necessario.
     */
    $('#com-bd-form').hide();
    $('.com-bd').click(function () {
        $('#com-bd-form').slideDown('slow');
        $('#dump-input').prop('required', true);

    });
    $('#no-bd').change(function () {
        $('#com-bd-form').slideUp('slow');
        $('#dump-input').prop('required', false);
    });


    /**
     * 
     */


    //Seta as ferramentas de acordo com o tipo de teste.
    $('#label-estatico').hide();
    $('#com-estatico-form').hide();
    $('.linguagem-label').click(function (e) {
        console.log(e);
        var id = $(this).children(':first').attr('value');
        console.log(id);
        //se existe
        if ($('#estatica-' + id).length) {
            console.log('existe!');
            $('#label-estatico').slideDown();
        } else {
            $('#label-estatico').slideUp();
            $('#com-estatico-form').slideUp();
            $('#codigo-input').prop('required', false);
        }

    });

    //Seta o arquivo fonte como obrigatório se o tipo escolhido incluir estático.
    $('#label-estatico').click(function (e) {
        var active = $(this).hasClass('active');
        console.log(active);
        //Tem que ser o contrário pois ele roda isso antes de alterar o valor de active.
        if (active !== true) {
            console.log('existe!');
            $('#codigo-input').prop('required', true);
            $('#com-estatico-form').slideDown();
        } else {
            $('#com-estatico-form').slideUp();
            $('#codigo-input').prop('required', false);
        }

    });

    $('form').submit(function (e) {
        $('#escopo-error').text('');
        $('#bd-error').text('');
        $('#linguagem-error').text('');
        if ($('.escopo-label').find(':checked').length < 1) {
            $('#escopo-error').text('Escolha ao menos um tipo de escopo.');
            e.preventDefault();
        }
        if ($('.bd-label').find(':checked').length < 1) {
            $('#bd-error').text('Escolha ao menos um tipo de banco de dados.');
            e.preventDefault();
        }
        if ($('.linguagem-label').find(':checked').length < 1) {
            $('#linguagem-error').text('Escolha ao menos um tipo de linguagem.');
            e.preventDefault();
        }
    });
});