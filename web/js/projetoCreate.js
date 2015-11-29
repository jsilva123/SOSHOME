$(document).ready(function () {
    console.log('projetoCreate.js carregado');

    //Seta as ferramentas de acordo com o tipo de teste.
    $('.choosen-test').click(function (e) {
        console.log('choosen-test clicado');
        var html = "";
        var tools = [];
        var selectedType = $(this);
        //Quando acaba de clicar, vai se tornar ativo mas ainda nao eh. por isso eh para caso nao esteja ativo
        if (!$(this).hasClass('active')) {
            console.log('ativando');
            var id = selectedType.attr("id");
            $('.type-tool' + id).each(function (e) {
                var newObject = {"id": $(this).attr("tool-id"), "name": $(this).attr("tool-nome")};
                tools.push(newObject);
            });
        } else {
            console.log('desativando');
            tools = [];
            $('tools-div').html(html);
        }
        $('.choosen-test.active').each(function () {
            console.log($(this));
            console.log($(selectedType));
            //pra cada um que esteja ativo que nao seja o proprio, uma vez que o propio so aparece como ativo aqui se estiver sendo desativado.
            if (selectedType.attr("id") !== $(this).attr("id")) {
                var id = $(this).attr("id");
                $('.type-tool' + id).each(function (e) {
                    var newObject = {"id": $(this).attr("tool-id"), "name": $(this).attr("tool-nome")};
                    tools.push(newObject);
                });
            } else {

            }
        });
        var printedToolsId = [];
        for (i = 0; i < tools.length; i++) {
            //Evita que a mesma ferramenta seja printada mais de uma vez.
            if ($.inArray(tools[i]["id"], printedToolsId) === -1) {
                line = "<label class=\"btn btn-default\"><input type=\"checkbox\" name=\"tool\" value=\"" + tools[i]["id"] + "\"> " + tools[i]["name"] + "</label>"
                html = html + line;
                printedToolsId.push(tools[i]["id"]);
            }
        }
        $('.tools-div').html(html);
    });

    $('.analyst-select').change(function () {
        var myOpt = [];
        $(".analyst-select").each(function () {
            myOpt.push($(this).val());
        });
        $(".analyst-select").each(function () {
            $(this).find("option").prop('disabled', false);
            var sel = $(this);
            $.each(myOpt, function (key, value) {
                if ((value != "") && (value != sel.val())) {
                    sel.find("option").filter('[value="' + value + '"]').prop('disabled', true);
                }
            });
        });
    });

    $('#form-vm').hide();
    $('#select-dinamico').change(function () {
        //$('#form-vm').slideDown('slow');
        if ($(this).prop('checked') === true) {
            $('#form-vm').slideDown('slow');
        } else {
            $('#form-vm').slideUp('slow');
        }
        console.log('clicou');
    });


});

function validateForm(event) {
    $('#input-projeto-nome-erro').html('');
    //$('#input-projeto-analista1-erro').html('');
    //$('#input-projeto-analista2-erro').html('');
    $('#input-projeto-prazo-erro').html('');
    console.log('validating form...');
    var projectName = $('#input-projeto-nome').val();
    var regex = /[\[]|[\]]/;
    var validated = true;
    if (regex.test(projectName)) {
        console.log('nope!');
        $('#input-projeto-nome-erro').text('O nome do projeto não deve conter os caracteres \'[\' ou \']\'.');
        $('#input-projeto-nome-erro').css('color', 'red');
        event.preventDefault();
        validated = false;
    }
    if (projectName.length >= 30) {
        console.log('nope!');
        $('#input-projeto-nome-erro').text('O nome do projeto deve ter no máximo 30 caracteres.');
        $('#input-projeto-nome-erro').css('color', 'red');
        event.preventDefault();
        validated = false;
    }
    /*var analista1 = $('#input-projeto-analista1').val();
     if (analista1 === 'Escolha um Analista'){
     console.log('nope!');
     $('#input-projeto-analista1-erro').text('Escolha um analista.');
     $('#input-projeto-analista1-erro').css('color', 'red');
     event.preventDefault();
     validated = false;
     }
     var analista2 = $('#input-projeto-auditor').val();
     if (analista2 === 'Escolha um Analista'){
     console.log('nope!');
     $('#input-projeto-analista2-erro').text('Escolha um auditor.');
     $('#input-projeto-analista2-erro').css('color', 'red');
     event.preventDefault();
     validated = false;
     }*/
    dataString = $('#input-projeto-prazo').val();
    console.log(dataString);
    data = moment(dataString, "YYYY-MM-DD");
    hoje = moment();
    if (data.isBefore(hoje, "day")) {
        console.log('nope!');
        $('#input-projeto-prazo-erro').text('Escolha uma data futura!');
        $('#input-projeto-prazo-erro').css('color', 'red');
        event.preventDefault();
        validated = false;
    }
    if (validated === false) {
        return false;
    }
    console.log('oh yeah!');
    document.form.submit();
    return true;
}