/* $( document ).ready(function() {
 console.log('js carregado');
 $('.linha-ferramenta-expiracao').each(function(){
 dataString = $(this).text();
 data =  moment(dataString, "DD/MM/YYYY");
 hoje = moment();
 if (data.isBefore(hoje, "day")){
 $(this).parent().addClass('danger');
 }
 });
 
 }); */

$(document).ready(function () {
    //Bloqueia a escolha do mesmo analista do 1 na 2
    $('.dropdown-option-analista1').click(function (e) {
        var analista1 = $(this).text();
        console.log(analista1);
        $('.dropdown-option-analista2').each(function (e) {
            if (analista1 == $(this).text()) {
                $(this).parent().addClass('disabled');
                $(this).parent().click(e.preventDefault());
            } else {
                $(this).parent().removeClass('disabled');
            }
        });
    });

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
            if (html === "") {
                html = "Escolha um dos testes para acessar as ferramentas dispon&iacute;veis.";
            }
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


});