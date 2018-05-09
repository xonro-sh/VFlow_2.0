$(function () {
    $("#sign_in").on("submit", function () {
        $("#alert_div").hide();
        portalLogin($("#account").val(),$("#password").val());
        return false;

    })
});
//门户登陆
function portalLogin(account, password) {
    $("#alert_div").show();
    $.ajax({
        url: "../../user/login",
        type: "post",
        dataType: "json",
        data: {
            userId: account,
            password: password
        },
        async: false,
        success: function (data) {
            if ('ok' in data&&!data.ok){
                $("#alert_div").html(data.msg);
            } else {
                $("#alert_div").html("login succeeds");
                window.location = "../portal.html?id="+data.id;
            }
        },
        error : function (data) {
            $("#alert_div").html(data.msg);
        }
    });
}