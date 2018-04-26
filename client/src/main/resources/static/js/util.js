/**
 * Created by user on 2018/1/25.
 */
// 根据参数名称获取value
function getUrlParameter(paramKey) {
    var sURLVariables, i, sParameterName, sPageURL = window.location.search.substring(1);
    console.log(sPageURL)
    if (sPageURL) {
        sURLVariables = sPageURL.split("&");
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split("=");
            if (sParameterName[0] === paramKey) return decodeURI(sParameterName[1],"utf-8")
        }
    }
}

function getTenantInfo(tenantId, layer) {
    var info = {};
    $.ajax({
        url: "../../console/tenant_info",
        type: "get",
        dataType: "json",
        data: {
            tenantId: tenantId
        },
        async: false,
        success: function (data) {
            if ('ok' in data&&!data.ok){
                layer.msg("获取租户信息失败,请联系相关人员");
                setTimeout(function () {
                    logout();
                }, 2000)
            } else {
                info = data.data;
            }
        },
        error : function (data) {
            layer.msg("获取租户信息失败，错误信息"+data.msg, {icon: 2,time:3000});
            setTimeout(function () {
                logout();
            }, 2000)
        }
    });
    return info;
}

function logout() {
    $.ajax({
        url: "../../user/logout",
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            window.location="../console/com.xonro.vflow_login.html";
        },
        error : function (data) {

        }
        });
}

//获取租户下所有部门
function getAllDepartment(tenantId) {
    var info = {};
    $.ajax({
        url: "../../department/all",
        type: "get",
        dataType: "json",
        data: {
            tenantId: tenantId
        },
        async: false,
        success: function (data) {
            info = data;
        },
        error : function (data) {
        }
    });
    return info;
}

//获取租户下所有角色
function getAllRole() {
    var info = {};
    $.ajax({
        url: "../../role/all",
        type: "get",
        dataType: "json",
        data: {
            tenantId: tenantId
        },
        async: false,
        success: function (data) {
            info = data;
        },
        error : function (data) {
        }
    });
    return info;
}

/**
 * 初始化下拉框  动态添加
 * @param $form
 * @param data
 * @param name
 * @param form
 * @param value
 * @returns
 */
function createOption($form,data,name,form,showName,value){
    var html = '';
    console.log(data)
    $form.find('select[name='+name+']').html(html);
    for(var i = 0;i<data.length;i++){
        var showNameNew = eval('data[i].'+showName);
        var valueNew =  eval('data[i].'+value);
        html +='<option value='+valueNew+'>'+showNameNew+'</option>';
    }
    $form.find('select[name='+name+']').append(html);
    form.render();
}


