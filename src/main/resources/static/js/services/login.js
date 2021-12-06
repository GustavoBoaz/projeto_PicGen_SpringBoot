import { UserLoginDTO } from "../models/UserLoginDTO.js";
import { enviroment } from "../configurations/enviroment.js";

$(document).ready(function(){

    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
    var sessionToken = window.sessionStorage.getItem("token");

    /* Check values in form */
    $('#form_login input').keyup(function(){

        var email = $('#email_auth').val();
        var pass = $('#pass_auth').val();
        if( email.length > 1 && pass.length > 2) {
            $("#btn_login").removeClass("disabled");
        }else{
            $("#btn_login").addClass("disabled");
        }
    });

    if(!sessionToken){

        /* Access Login */
        $("#btn_login").click(function () {
            $( ".alert" ).remove();

            var email = $('#email_auth').val();
            var pass = $('#pass_auth').val();

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            const loginUser = new UserLoginDTO(email, pass);

            var myInit = { method: 'PUT',
                    headers: myHeaders,
                    mode: 'cors',
                    cache: 'default',
                    body: JSON.stringify(loginUser)
                };
            fetch("http://localhost:8080/api/v1/user/credentials", myInit).then(function(response) {
                if(response.status === 201) {
                    response.json().then(data => {

                        window.sessionStorage.setItem("token", data.token);
                        window.sessionStorage.setItem("basicToken", data.authorizationBasic);
                        enviroment.token = data.token;
                        enviroment.basicToken = data.authorizationBasic;
                        
                        $('#form_search').css("display", "none");
                        $('#form_login').css("display", "none");
                        $('#form_register').css("display", "none");
                        $('#qr_code').css("display", "none");
                        $('#profile_user').css("display", "flex");
                        location.reload();
                    });
                } else {
                    response.json().then(data => {
                        if (data.message === "Email não existe!") {
                            $('#alert_login').append('<div class="alert bblue tbc">E-mail not exist!</div>');
                        }
                        if (data.message === "Senha incorreta!") {
                            $('#alert_login').append('<div class="alert bblue tbc">Incorrect password!</div>');
                        } else {
                            $('#alert_login').append('<div class="alert bblue tbc">Wrong email format!</div>');
                        }
                        $('#alert_login').css("display", "flex");
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