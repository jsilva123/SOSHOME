$(document).ready(function () {
    console.log('main carregado');

    /* off-canvas sidebar toggle */
    $('[data-toggle=offcanvas]').click(function () {
        $(this).toggleClass('visible-xs text-center');
        $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
        $('.row-offcanvas').toggleClass('active');
        $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
        $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
        $('#btnShow').toggle();
    });

    /*
     $('.side-menu-title').click(function (){
     $('.side-menu-panel').each( function (){
     $(this).collapse('hide'); 
     });
     console.log($(this).next());
     $(this).next().collapse('show');
     }); */

    $(".dropdown-menu li a").click(function () {

        $(this).parent().parent().parent().find('button').html($(this).text() + " <span class='caret'></span>");
        $(this).parent().parent().parent().find('button').val($(this).text());

    });

    $('#sidebar-seta').click(function () {
        if ($(this).hasClass('glyphicon-chevron-right')) {
            $(this).removeClass('glyphicon-chevron-right');
            $(this).addClass('glyphicon-chevron-left');
        } else {
            $(this).removeClass('glyphicon-chevron-left');
            $(this).addClass('glyphicon-chevron-right');
        }
    });


    //Carrega o modal de erro, se houver.
    $('#msgModal').modal();
});

