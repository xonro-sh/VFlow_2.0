/**
 * 初始化下拉框  动态添加
 * @param $form
 * @param data
 * @param name
 * @param form
 * @param value
 * @returns
 */
function createOption($form,data,name,form,value){
    var html = '';
    $form.find('select[name='+name+']').html(html);
    for(var i = 0;i<data.length;i++){
        if (data[i] === value){
            html +='<option value='+data[i]+' selected ="selected" >'+data[i]+'</option>';
        } else {
            html +='<option value='+data[i]+'>'+data[i]+'</option>';
        }

    }
    $form.find('select[name='+name+']').append(html);
    form.render();
}

function getTableColumns(layer, sql) {
    var info = {};
    $.ajax({
        url: "../../dataview/get_table_columns",
        type: "get",
        dataType: "json",
        data:{
            sql:sql
        },
        async: false,
        success: function (data) {
            if (data.ok){
                if (data.data != null){
                    info = data;
                } else {
                    layer.msg(data.msg, {icon: 1,time:3000});
                }

            } else {
                layer.msg(data.msg, {icon: 1,time:3000});
            }
        },
        error : function (data) {
            layer.msg(data.msg, {icon: 1,time:3000});
        }
    });
    return info;
}
function getDataView(id) {
    var info = [];
    $.ajax({
        url: "../../../dataview/get_dataview",
        type: "get",
        dataType: "json",
        data:{
            id:id
        },
        async: false,
        success: function (data) {
            if (data.ok){
                if (data.data != null){
                    info = JSON.parse(data.data);
                } else {
                }

            } else {
            }
        },
        error : function (data) {
        }
    });
    return info;
}


function getDataGridConf(id) {
    var info = [];
    $.ajax({
        url: "../../../dataview/get_datagrid_conf",
        type: "get",
        dataType: "json",
        data:{
            id:id
        },
        async: false,
        success: function (data) {
            if (data.ok){
                if (data.data != null){
                    info = data.data;
                    console.log(info);
                } else {
                }

            } else {
            }
        },
        error : function (data) {
        }
    });
    return info;
}


function delDataView(layer, id) {
    var jsonData = {
        id:id
    };
    var result = false;
    $.ajax({
        url: "../../dataview/del_dataview",
        type: "get",
        dataType: "json",
        data:{
            data:JSON.stringify(jsonData)
        },
        async: false,
        success: function (data) {
            if (data.ok){
                result = true;
            } else {
                layer.msg("删除失败！"+data.msg, {icon: 1,time:3000});
            }
        },
        error : function (data) {
            layer.msg("删除失败！"+data.msg, {icon: 1,time:3000});
        }
    });
    return result;
}

function saveDataView(layer, data) {
    var result = false;
    $.ajax({
        url: "../../dataview/save_dataview",
        type: "post",
        dataType: "json",
        async: false,
        data: {
            data:data
        },
        success: function (data) {
            if (data.ok){
                result = true;
            }else {
                layer.msg("保存失败！"+data.msg, {icon: 1,time:3000});
            }
        },
        error : function (data) {
            layer.msg("保存失败！"+data.msg, {icon: 1,time:3000});
        }
    });
    return result;
}