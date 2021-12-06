$(document).ready(function(){

    var sessionBasicToken = window.sessionStorage.getItem("basicToken")
    var sessionToken = window.sessionStorage.getItem("token");
    
    if(!sessionToken){

        /* Access form Register */
        $("#btn_register").click(function () {
            $('#form_login :input').val('');
            $('#form_search :input').val('');
            $("#form_register").css("display", "flex");
            $("#form_login").css("display", "none")
            $("#form_search").css("display", "none")
        });

    } else {
        $('#form_search').css("display", "none");
        $('#form_login').css("display", "none");
        $('#form_register').css("display", "none");
        $('#qr_code').css("display", "none");
        $('#profile_user').css("display", "flex");
    }
    
});