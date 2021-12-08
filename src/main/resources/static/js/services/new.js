import { DOMAIN } from "../configurations/domain.js";
import { OrderRegistrationDTO } from "../models/OrderRegistrationDTO.js";

$(document).ready(function(){

    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
    var sessionToken = window.sessionStorage.getItem("token");
    
    if(sessionToken){

        $('#btn_new').click(function(){

            $('#container_new').toggle();
            console.log("acionado !")
        });
    
         /* Check values in form */
        $('#form_new_order input').keyup(function(){
    
            var val = $('#input_value').val();
            var email = $('#input_email_buyer').val();
            var doc = $('#input_document').val();
            if( val > 0 && email.length > 1 && doc.length > 1) {
                $("#btn_new_order").removeClass("disabled");
            }else{
                $("#btn_new_order").addClass("disabled");
            }
        });

        /* Clear Form new order */
        $('#btn_retunr_two').click(function(){
            $('#container_new').toggle();

            $('#form_new_order :input').val('');
            $("#btn_new_order").addClass("disabled");
        });

        /* Access form Register */
        $("#btn_new_order").click(function () {

            $( ".alert" ).remove();

            var val = $('#input_value').val();
            var email = $('#input_email_buyer').val();
            var doc = $('#input_document').val();

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            myHeaders.append("Authorization", sessionBasicToken);

            const newOrder = new OrderRegistrationDTO(val, email, doc);

            var myInit = { method: 'POST',
                    headers: myHeaders,
                    mode: 'cors',
                    cache: 'default',
                    body: JSON.stringify(newOrder)
                };

            fetch(DOMAIN.PRO + "/api/v1/order/" + sessionToken, myInit).then(function(response) {
                if(response.status === 201) {
                    response.json().then(data => {
                        console.log("Registrado");
                        console.log(data);
                        $('#container_new').toggle();
                        location.reload();
                    });
                } else {
                response.json().then(data => {
                    $('#alert_new_order').append('<div class="alert byellow tbc">Value above one</div>');
                    $('#alert_new_order').append('<div class="alert byellow tbc">Pass a valid email format</div>');
                    $('#alert_new_order').append('<div class="alert byellow tbc">Pass a valid CPF format</div>');
                    $('#alert_new_order').css("display", "flex");
                    console.log("Erro: " + data);
                    
                });
                }
            })
            .catch(function(error) {
                console.log('There has been a problem with your fetch operation: ' + error.message);
            });

        });

    } else {
        $('#form_search').css("display", "flex");
        $('#form_login').css("display", "flex");
        $('#form_register').css("display", "none");
        $('#qr_code').css("display", "flex");
        $('#profile_user').css("display", "none");
    }

});