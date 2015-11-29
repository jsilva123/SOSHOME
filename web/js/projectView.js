/* $( document ).ready(function() {
 console.log('js carregado');
 
 
 }); */

$(document).ready(function () {
    //Adiciona a função de aba na barra superior
    $('.aba a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    //Cada linha clicada na aba de teste irá abrir a página do teste
    $('.linha-teste').click(function (e) {
        e.preventDefault();
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadTest&id=' + id;
        window.location.href = url;
    });

    //Cada linha clicada na aba de teste irá abrir a página do teste
    $('.linha-vm').click(function (e) {
        e.preventDefault();
        id = $(this).attr('id');
        url = 'ServletController?cmd=platssa.model.command.LoadVmProjeto&idVm=' + id;
        window.location.href = url;
    });


    $('#openQns').click(function (e) {
        window.open('', 'OpenQNS');
        document.getElementById('QNSForm').submit();
    });


    $('#button-deletar').click(function () {
        $('#deletar-modal').modal();
    });


    //Graficos
    var options = {
        series: {
            pie: {
                show: true,
                radius: 1,
                label: {
                    show: true,
                    radius: 3 / 4,
                    formatter: function (label, series) {
                        return '<div style="border:1px solid grey;font-size:8pt;text-align:center;padding:5px;color:white;">' +
                                label + ' : ' +
                                Math.round(series.percent) +
                                '%</div>';
                    },
                    background: {
                        opacity: 0.5,
                        color: '#000'
                    }
                }
            }
        },
        legend: {
            show: false
        },
        grid: {
            hoverable: true
        }
    };

    /**
     * Relativo ao gráfico de status dos projetos
     */
    var riscoAlto = $('#riscoAlto').text();
    var riscoMedio = $('#riscoMedio').text();
    var riscoBaixo = $('#riscoBaixo').text();
    var data = [
        {label: "Alto", data: riscoAlto, color: "red"},
        {label: "M&eacute;dio.", data: riscoMedio, color: "yellow"},
        {label: "Baixo", data: riscoBaixo, color: "green"}
    ];
    console.log(data);
    var plot = $("#riscos").plot(data, options).data("plot");
});