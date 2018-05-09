$(function () {
    toastr.options = {
        closeButton: true,
        positionClass: "toast-top-full-width"
    };
    var menu = getPortalMenu(getUrlParameter("id"));
    console.log(menu);
    console.log(JSON.stringify(menu));
   var leftMenu = new Vue({
       el: '#left_menu',
       data :{
           items : JSON.parse(JSON.stringify(menu))
       },
       methods:{
            iframe_open: function (dataUrl) {
                console.log(dataUrl);
                $("#frameBody").attr('src', dataUrl);
            }
       }
   });
    $("#logout").on("click", function () {
        var r=confirm("确认退出系统吗？");
        if (r===true)
        {
            portallogout();
        }
        else
        {

        }

    });
    $("#profile").on("click", function () {
        $("#frameBody").attr('src', 'portal/com.xonro.portal_userinfo.html?id='+ getUrlParameter("id"));
    })
});

function getPortalMenu(userId) {
    var info = [];
    $.ajax({
        url: "../../portal/menu",
        type: "get",
        dataType: "json",
        data: {
            userId: userId
        }
        ,async: false,
        success: function (data) {
            console.log(data);
            if ('ok' in data&&!data.ok){
                if (data.code === '4001') {
                    //提示错误消息
                    toastr.warning(data.msg, {timeOut: 2000});
                    // alert(data.msg);
                    setTimeout(function () {
                        window.location = "../templates/portal/com.xonro.portal_login.html";
                    }, 2000);

            }
            } else {
                info = data;
            }
        },
        error : function (data) {
        }
    });
    return info;
}

function portallogout() {
    $.ajax({
        url: "../../user/logout",
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            window.location="../templates/portal/com.xonro.portal_login.html";
        },
        error : function (data) {

        }
    });
}
