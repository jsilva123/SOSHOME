

$(document).ready(
        function () {
            $("#header").load("header.html");
            //$("#footer").load("footer.html");

            $('#whiteboxbutton').click(function () {
                if ($('#whiteboxbutton').is(':checked')) {
                    $('#whiteboxdiv').fadeIn("slow");
                }
            });

            $('#blackboxbutton').click(function () {
                if ($('#blackboxbutton').is(':checked')) {
                    $('#whiteboxdiv').fadeOut();
                }
            });
            $('#meu_form').validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    url: {
                        required: true,
                    },
                    boxtype: {
                        //required: true
                    },
                    language: {
                        //required: "#whiteboxbutton:checked"
                    },
                    dataBase: {
                        //required: "#whiteboxbutton:checked"
                    },
                    testType: {
                        //required: "#whiteboxbutton:checked"
                    },
                    vulnerabilities: {
                        //required: "#whiteboxbutton:checked"
                    },
                    analysis: {
                        //required: "#whiteboxbutton:checked"
                    },
                    cores: {
                        //required: "#whiteboxbutton:checked"
                    },
                    ram: {
                        //required: "#whiteboxbutton:checked"
                    },
                    os: {
                        //required: "#whiteboxbutton:checked"
                    },
                },
                messages: {
                    name: {
                        required: "O campo nome é obrigatorio.",
                        minlength: "O campo nome deve conter no mínimo 3 caracteres."
                    },
                    url: {
                        required: "O campo é obrigatorio.",
                    },
//                    boxtype: {
//                        required: "O campo é obrigatorio.",
//                    }, 
//                    language: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    dataBase: {
//                       required: "O campo é obrigatorio.",
//                    },
//                    testType: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    vulnerabilities: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    analysis: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    cores: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    ram: {
//                        required: "O campo é obrigatorio.",
//                    },
//                    os: {
//                        required: "O campo é obrigatorio.",
//                    },
                },
//               errorPlacement: function(error, element) {
//			
//				if ( element.is(":radio") )
//				error.appendTo( element.parent().next().next() );
//			else if ( element.is(":checkbox") )
//				error.appendTo ( element.next() );
//			
//		}



            });
        });