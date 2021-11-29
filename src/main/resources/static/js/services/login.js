import { UserLoginDTO } from "../models/UserLoginDTO.js";
import { enviroment } from "../configurations/enviroment.js";

$(document).ready(function(){

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
        fetch("https://picboaz.herokuapp.com/api/v1/user/credentials", myInit).then(function(response) {
            if(response.status === 201) {
                response.json().then(data => {

                    window.sessionStorage.setItem("token", data.token);
                    window.sessionStorage.setItem("basicToken", data.authorizationBasic);
                    enviroment.token = data.token;
                    enviroment.basicToken = data.authorizationBasic;

                    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
                    var sessionToken = window.sessionStorage.getItem("token");
                    
                    if(sessionToken !== ''){

                        $('#form_search').css("display", "none");
                        $('#form_login').css("display", "none");
                        $('#form_register').css("display", "none");
                        $('#qr_code').css("display", "none");
                        $('#profile_user').css("display", "flex");

                        var myHeaders2 = new Headers();
                        myHeaders2.append("Content-Type", "application/json");
                        myHeaders2.append("Authorization", sessionBasicToken);

                        var myInit2 = { method: 'GET',
                                headers: myHeaders2,
                                mode: 'cors',
                                cache: 'default'
                            };
                        fetch("https://picboaz.herokuapp.com/api/v1/user/"+sessionToken, myInit2).then(function(response) {

                            if(response.status === 200){
                                response.json().then(data => {
                                    for (let index = 0; index < data.myOrders.length; index++) {
                                        if (data.myOrders[index].statusPayment === 'PAGO') {
                                            $('#container_orders').append(
                                                '<article class="card bwc" style="border: 2px solid rgb(0, 183, 255)">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Status: </b>'+data.myOrders[index].statusPayment+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>E-mail Buyer: </b>'+data.myOrders[index].emailBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Document Buyer: </b>'+data.myOrders[index].documentBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Value: </b> R$'+data.myOrders[index].value+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Request date: </b>'+data.myOrders[index].requestDate+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Deadline date: </b>'+data.myOrders[index].deadlineDate+
                                                    '</h4>'+
                                                '</article>'
                                            );
                                        }
                                        if (data.myOrders[index].statusPayment === 'CANCELADO') {
                                            $('#container_orders').append(
                                                '<article class="card bwc" style="border: 2px solid red">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Status: </b>'+data.myOrders[index].statusPayment+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>E-mail Buyer: </b>'+data.myOrders[index].emailBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Document Buyer: </b>'+data.myOrders[index].documentBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Value: </b> R$'+data.myOrders[index].value+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Request date: </b>'+data.myOrders[index].requestDate+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Deadline date: </b>'+data.myOrders[index].deadlineDate+
                                                    '</h4>'+
                                                '</article>'
                                            );
                                        }
                                        if (data.myOrders[index].statusPayment === 'PENDENTE') {
                                            $('#container_orders').append(
                                                '<article class="card bwc" style="border: 2px solid orange">'+
                                                    '<h4 class="font-light-200">'+
                                                        '<b>Status: </b>'+data.myOrders[index].statusPayment+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>E-mail Buyer: </b>'+data.myOrders[index].emailBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Document Buyer: </b>'+data.myOrders[index].documentBuyer+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Value: </b> R$'+data.myOrders[index].value+
                                                    '</h4>'+
                                                    '<hr style="width:95%">'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Request date: </b>'+data.myOrders[index].requestDate+
                                                    '</h4>'+
                                                    '<h4 class="font-light-300">'+
                                                        '<b>Deadline date: </b>'+data.myOrders[index].deadlineDate+
                                                    '</h4>'+
                                                '</article>'
                                            );
                                        }

                                    }

                                    console.log(data);
                                });

                            } else {
                                response.json().then(data => {
                                    $('#form_search').css("display", "flex");
                                    $('#form_login').css("display", "flex");
                                    $('#form_register').css("display", "flex");
                                    $('#qr_code').css("display", "none");
                                    $('#profile_user').css("display", "none");
                                });
                            }
                        })
                        .catch(function(error) {
                            console.log('There has been a problem with your fetch operation: ' + error.message);
                        });

                    } else {
                        $('#form_search').css("display", "flex");
                        $('#form_login').css("display", "flex");
                        $('#form_register').css("display", "flex");
                        $('#qr_code').css("display", "none");
                    }
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

});