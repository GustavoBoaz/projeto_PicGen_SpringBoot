$(document).ready(function(){

    $("#input_order").keyup(function () {

        var search = $('#input_order').val();
        var topAfterSearch = 10;
        var topAfterQr = 90;

        if (search.length === 0) {
            $("#qr_code").css("display", "none");
            $("#form_register").css("display", "none");
            $("#form_login").css("display", "flex")
            $('#img_found').remove();
            $("#form_search").css({
                "position": "unset",
                "top":"unset"
            });
            $("#qr_code").css({
                "position": "unset",
                "top":"unset"
            });
        } else {
            $('#img_found').remove();
            $("#form_register").css("display", "none");
            $("#form_login").css("display", "none")
            $("#form_search").css({
                "position": "absolute",
                "top": topAfterSearch+"px"
            });
            $("#qr_code").css({
                "position": "absolute",
                "top": topAfterQr+"px"
            });

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var myInit = { method: 'GET',
                    headers: myHeaders,
                    mode: 'cors',
                    cache: 'default'
                };

            fetch("https://picboaz.herokuapp.com/api/v1/order/"+search, myInit).then(function(response) {
                if(response.status === 200) {
                    response.json().then(data => {
                        $('#img_found').remove();
                        $('#logo_found').remove();

                        if (data.dataOrder.statusPayment === "PAGO") {
                            $("#qr_code").css("display", "flex");
                            $('#qr_code').append(
                                '<img id="logo_found" class="regular-img" src="/img/assets/PicGen-1-sf.png" alt="logo">'+
                                '<div id="img_found" class="bwc rb-10" style="width: 350px;">'+
                                    '<img class="regular-img" style="margin-bottom: 10px;" src="/img/assets/PAGO.png" alt="Pago">'+
                                    '<h2 class="font-black-900">Great!</h2>'+
                                    '<h4 class="font-light-300">This order is paid</h4>'+
                                '</div>'
                            );
                        }
                        if (data.dataOrder.statusPayment === "CANCELADO") {
                            $("#qr_code").css("display", "flex");
                            $('#qr_code').append(
                                '<img id="logo_found" class="regular-img" src="/img/assets/PicGen-1-sf.png" alt="logo">'+
                                '<div id="img_found" class="bwc rb-10" style="width: 350px;">'+
                                    '<img class="regular-img" style="margin-bottom: 10px;" src="/img/assets/CANCELADO.png" alt="Cancelado">'+
                                    '<h2 class="font-black-900">Right!</h2>'+
                                    '<h4 class="font-light-300">This order has been canceled</h4>'+
                                '</div>'
                            );
                        }
                        if (data.dataOrder.statusPayment === "PENDENTE") {
                            $("#qr_code").css("display", "flex");
                            $('#qr_code').append(
                                '<img id="logo_found" class="regular-img" src="/img/assets/PicGen-1-sf.png" alt="logo">'+
                                '<div id="img_found" class="bwc rb-10" style="width: 350px;">'+
                                    '<h2 class="font-black-900">Pay with PicGen</h2>'+
                                    '<h4 class="font-light-300" style="margin-bottom: 10px;">Scan the QR Code below</h4>'+
                                    '<div style="padding: 10px; border: 1px solid #Ff7a00; margin-bottom: 10px;">'+
                                        '<img class="large-img" src="'+data.qrCode+'">'+
                                    '</div>'+
                                    '<h6 class="font-light-200">'+
                                        '<b>Sponsor: </b>'+data.dataOrder.sponsor.name+
                                    '</h6>'+
                                    '<h6 class="font-light-200">'+
                                        '<b>Buyer: </b>'+data.dataOrder.emailBuyer+
                                    '</h6>'+
                                    '<h6 class="font-light-200">'+
                                        '<b>Value: </b>R$'+data.dataOrder.value+
                                    '</h6>'+
                                '</div>'
                            );
                        }
                        console.log(data)
                        
                    });
                } else {
                    response.json().then(data => {
                        $('#img_found').remove();
                        $('#logo_found').remove();
                        $("#qr_code").css("display", "flex");
                        $('#qr_code').append(
                            '<img id="logo_found" class="regular-img" src="/img/assets/PicGen-1-sf.png" alt="logo">'+
                            '<div id="img_found" class="bwc rb-10" style="width: 350px;">'+
                                '<h2 class="font-black-900">Typed id does not exist!</h2>'+
                                '<h5 class="font-light-300" style="margin-bottom: 10px;">Check the order number</h5>'+
                                '<img class="regular-img" style="border: 1px solid black;" src="/img/assets/OrderNF.png" alt="Not Found">'+
                            '</div>'
                        );
                    });
                }
                })
                .catch(function(error) {
                console.log('There has been a problem with your fetch operation: ' + error.message);
            });
        }
    });
    
});