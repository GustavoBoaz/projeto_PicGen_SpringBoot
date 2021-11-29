$(document).ready(function(){

    /* Access form Register */
    $("#btn_register").click(function () {
        $('#form_login :input').val('');
        $('#form_search :input').val('');
        $("#form_register").css("display", "flex");
        $("#form_login").css("display", "none")
        $("#form_search").css("display", "none")
    });
    
});