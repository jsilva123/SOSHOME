$(document).ready(function () {
    console.log('parameterCreate.js carregado');



    /**
     * Exibe o ID VM somente quando necessario.
     */
    $('#dinamico-form').hide();
    atualizaDinamicoForm();
    $('#select-dinamico').change(function () {
        atualizaDinamicoForm();
    });

    /**
     * Exibe o BD dump somente quando necessario.
     */
    $('#com-bd-form').hide();
    carregaComDbForm();
    $('.com-bd').click(function () {
        atualizaComDbForm();
    });
    $('#no-bd').change(function () {
        $('#com-bd-form').slideUp('slow');
        $('#dump-input').prop('required', false);
    });

    //Seta as ferramentas de acordo com o tipo de teste.
    $('#label-estatico').hide();
    $('#com-estatico-form').hide();
    $('.linguagem-label').each(function () {
        var id = $(this).children(':first').attr('value');
        //se existe
        if (!($('#estatica-' + id).length)) {
            $('#label-estatico').slideDown();
        }
    });
    $('.linguagem-label').click(function (e) {
        var id = $(this).children(':first').attr('value');
        //se existe
        if ($('#estatica-' + id).length) {
            $('#label-estatico').slideDown();
        } else {
            $('#label-estatico').slideUp();
            $('#com-estatico-form').slideUp();
            $('#codigo-input').prop('required', false);
        }

    });

    //mostra o painel com-estatico-form quando carrega a tela, se estatico vier marcado
    if ($('#label-estatico').hasClass('active')) {
        if ($('#codigo-input').attr('setado') === "false")
            $('#codigo-input').prop('required', true);
        $('#com-estatico-form').slideDown();
    }

    //Seta o arquivo fonte como obrigatório se o tipo escolhido incluir estático.
    $('#label-estatico').click(function (e) {
        var active = $(this).hasClass('active');
        //Tem que ser o contrário pois ele roda isso antes de alterar o valor de active.
        if (active !== true) {
            if ($('#codigo-input').attr('setado') === "false")
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

function atualizaDinamicoForm() {
    console.log($('#select-dinamico').prop('checked'));
    if ($('#select-dinamico').prop('checked') === true) {
        $('#dinamico-form').slideDown('slow');
        if ($('#exe-input').attr('setado') === "false")
            $('#exe-input').prop('required', true);
    } else {
        $('#dinamico-form').slideUp('slow');
        $('#exe-input').prop('required', false);
    }

}

function atualizaComDbForm() {
    if ($(this).hasClass('active') === false) {
        $('#com-bd-form').slideDown('slow');
        if ($('#dump-input').attr('setado') === "false")
            $('#dump-input').prop('required', true);
    }
}

function carregaComDbForm() {
    $('.com-bd').each(function () {
        if ($(this).hasClass('active') === false) {
            $('#com-bd-form').slideDown('slow');
            if ($('#dump-input').attr('setado') === "false")
                $('#dump-input').prop('required', true);
        }
    });
}