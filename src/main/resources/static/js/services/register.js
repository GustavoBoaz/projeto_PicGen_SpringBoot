import { DOMAIN } from "../configurations/domain.js";
import { UserResgistrationDTO } from "../models/UserRegistrationDTO.js";

$(document).ready(function(){

    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
    var sessionToken = window.sessionStorage.getItem("token");
    
    if(!sessionToken){

        /* Check values in form */
        $('#form_register input').keyup(function(){

            var name = $('#input_name').val();
            var email = $('#input_email').val();
            var pass = $('#input_pass').val();
            if( name.length > 1 && email.length > 1 && pass.length > 1) {
                $("#btn_create").removeClass("disabled");
            }else{
                $("#btn_create").addClass("disabled");
            }
        });

        /* Clear Form register */
        $('#btn_retunr_one').click(function(){
            $("#form_register").css("display", "none");
            $("#form_login").css("display", "flex");
            $("#form_search").css("display", "flex");
            $('#alert_register').css("display", "none");
            $( ".alert" ).remove();

            $('#form_register :input').val('');
            $("#btn_create").addClass("disabled");
            $("#btn_login").addClass("disabled");
        });

        /* Access form Register */
        $("#btn_create").click(function () {

            $( ".alert" ).remove();

            var name = $('#input_name').val();
            var email = $('#input_email').val();
            var pass = $('#input_pass').val();

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            const newUser = new UserResgistrationDTO(name, email, pass);

            var myInit = { method: 'POST',
                    headers: myHeaders,
                    mode: 'cors',
                    cache: 'default',
                    body: JSON.stringify(newUser)
                };

            fetch(DOMAIN.PRO + "/api/v1/user/save", myInit).then(function(response) {
                if(response.status === 201) {
                    response.json().then(data => {
                        $( ".alert" ).remove();
                        $('#alert_register').css("display", "none");
                        $('#form_register :input').val('');
                        $("#form_register").css("display", "none");
                        $("#form_login").css("display", "flex");
                        $("#form_search").css("display", "flex");
                        $('#alert_login').append('<div class="alert bgreen tbc">Account created, you can login!</div>');
                        $('#alert_login').css("display", "flex");
                    });
                } else {
                response.json().then(data => {
                    
                    $('#alert_register').append('<div class="alert borange tbc">Name from 3 to 50 characters</div>');
                    $('#alert_register').append('<div class="alert borange tbc">Send a valid email</div>');
                    $('#alert_register').append('<div class="alert borange tbc">Password from 3 to 15 characters</div>');
                    $('#alert_register').append('<div class="alert bblue tbc">If above is correct try another email!</div>');
                    $('#alert_register').css("display", "flex");
                    
                });
                }
            })
            .catch(function(error) {
                console.log('There has been a problem with your fetch operation: ' + error.message);
            });

        });

    } else {
        $('#form_search').css("display", "none");
        $('#form_login').css("display", "none");
        $('#form_register').css("display", "none");
        $('#qr_code').css("display", "none");
        $('#profile_user').css("display", "flex");
    }

});