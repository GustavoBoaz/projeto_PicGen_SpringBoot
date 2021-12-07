import { DOMAIN } from "../configurations/domain.js";

$(document).ready(function(){

    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
    var sessionToken = window.sessionStorage.getItem("token");

    /* Clear session */
    $('#btn_sign_out').click(function(){
        $('#form_search').css("display", "flex");
        $('#form_login').css("display", "flex");
        $('#form_register').css("display", "none");
        $('#profile_user').css("display", "none");
        $('#qr_code').css("display", "none");

        $( ".alert" ).remove();

        $('#form_register :input').val('');
        $('#form_login :input').val('');
        $("#btn_create").addClass("disabled");
        $("#btn_login").addClass("disabled");
        window.sessionStorage.clear();
        location.reload();
    });
    
    /* Clear session */
    $('#btn_exit').click(function(){
        $('#form_search').css("display", "flex");
        $('#form_login').css("display", "flex");
        $('#form_register').css("display", "none");
        $('#profile_user').css("display", "none");
        $('#qr_code').css("display", "none");

        $( ".alert" ).remove();

        $('#form_register :input').val('');
        $('#form_login :input').val('');
        $("#btn_create").addClass("disabled");
        $("#btn_login").addClass("disabled");
        window.sessionStorage.clear();
        location.reload();
    });

    if(sessionToken){

        var myHeaders2 = new Headers();
        myHeaders2.append("Content-Type", "application/json");
        myHeaders2.append("Authorization", sessionBasicToken);

        var myInit2 = { method: 'GET', headers: myHeaders2, mode: 'cors', cache: 'default'};
        
        fetch(DOMAIN.PRO + "/api/v1/user/" + sessionToken, myInit2).then(function(response) {
            if(response.status === 200){
                response.json().then(data => {
                    for (let index = 0; index < data.myOrders.length; index++) {
                        if (data.myOrders[index].statusPayment === 'PAGO') {
                            $('#container_orders').append(
                                '<div class="card bwc" style="border: 1px solid rgb(0, 183, 255)">'+
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
                                    '<div class="pallet">'+
                                        '<a class="font-light-300"> '+data.myOrders[index].idOrder+' <i class="fas fa-trash tred"></i> </a>'+
                                    '</div>'+
                                '</div>'
                            );
                        }
                        if (data.myOrders[index].statusPayment === 'CANCELADO') {
                            $('#container_orders').append(
                                '<div class="card bwc" style="border: 1px solid red">'+
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
                                    '<div class="pallet">'+
                                        '<a class="font-light-300"> '+data.myOrders[index].idOrder+' <i class="fas fa-trash tred"></i> </a>'+
                                    '</div>'+
                                '</div>'
                            );
                        }
                        if (data.myOrders[index].statusPayment === 'PENDENTE') {
                            $('#container_orders').append(
                                '<div class="card bwc" style="border: 1px solid orange">'+
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
                                    '<div class="pallet">'+
                                        '<a class="font-light-300"> '+data.myOrders[index].idOrder+' <i class="fas fa-trash tred"></i> </a>'+
                                    '</div>'+
                                '</div>'
                            );
                        }

                    }
                    $('.pallet').click(function(){
                        var idOrder = $(this).children('a').text();
                        var myInit3 = { method: 'DELETE', headers: myHeaders2, mode: 'cors', cache: 'default'};

                        fetch(DOMAIN.PRO + "/api/v1/order/" + sessionToken + "/" + idOrder, myInit3)
                            .then(function(response){
                                if (response.status === 200) {
                                    location.reload();
                                } else {
                                    console.log("Não DELETOU")
                                }
                            }).catch(function(error) {
                                console.log('There has been a problem with your fetch operation: ' + error.message);
                            });
                    });
                    console.log(data);
                });

            } else {
                response.json().then(data => {
                    $('#form_search').css("display", "flex");
                    $('#form_login').css("display", "flex");
                    $('#form_register').css("display", "none");
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
        $('#form_register').css("display", "none");
        $('#profile_user').css("display", "none");
        $('#qr_code').css("display", "none");
    }
    
});