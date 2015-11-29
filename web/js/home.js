/**
 * 
 * Relativo a todos os plots desta pagina.
 */
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
var criados = $('#criados').text();
var emConfig = $('#emConfig').text();
var configurado = $('#configurado').text();
var emTeste = $('#emTeste').text();
var emAnalise = $('#emAnalise').text();
var analisado = $('#analisado').text();
var validadoAuditor = $('#validadoAuditor').text();
var finalizado = $('#finalizado').text();
var data = [
    {label: "Criado", data: criados, color: "#005CDE"},
    {label: "Em config.", data: emConfig, color: "#00A36A"},
    {label: "Configurado", data: configurado, color: "#7D0096"},
    {label: "Em Teste", data: emTeste, color: "#992B00"},
    {label: "Em Análise", data: emAnalise, color: "#DE000F"},
    {label: "Analisado", data: analisado, color: "#ED7B00"},
    {label: "Validado", data: validadoAuditor, color: "#ED7B00"},
    {label: "Finalizado", data: finalizado, color: "#ED7B00"}
];

var plot = $("#projetosTotais").plot(data, options).data("plot");


/**
 * Relativo ao gráfico de quantidade de projetos por usuário
 */
var label = [];
$('.usuario').each(function () {
    label.push({label: $(this).attr('nome'), data: parseInt($(this).attr('projetos'))});
});


var plot = $("#projetosPorUsuarios").plot(label, options).data("plot");


/**
 * Relativo ao gráfico de projetos rodando ou nao
 */
var naoFinalizado = $('#naoFinalizado').text();
var naoRodando = $('#naoRodando').text();
var dataRodando = [
    {label: "Rodando", data: naoFinalizado, color: "green"},
    {label: "Finalizados e Deletados", data: naoRodando, color: "red"}
];

var plot = $("#projetosFinalizados").plot(dataRodando, options).data("plot");