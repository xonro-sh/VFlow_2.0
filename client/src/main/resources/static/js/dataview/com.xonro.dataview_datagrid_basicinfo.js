var datagridBasicAttr = [];
$(function () {
    $("#title").val(parent.dataviewData.title);
    if (parent.dataviewData.datagridBasicAttr != null && parent.dataviewData.datagridBasicAttr !== ""){
        datagridBasicAttr = JSON.parse(parent.dataviewData.datagridBasicAttr);
    }
});
layui.use(['layer','form'],function () {
    var form = layui.form;
    var layer = layui.layer;
    //初始化数据
    initDataViewOption(form,layer, datagridBasicAttr);
    //监听指定开关
    form.on('switch(loading)', function(data){
        if (data.elem.checked) {
            layer.tips('温馨提示：加载条开启时，切换分页会出现加载条', data.othis)
        } else {
            layer.tips('温馨提示：加载条关闭时，切换分页将不会出现加载条', data.othis)
        }
    });
    //提交修改
    form.on('submit(save)', function (dataField) {
        var jsonData = {
            id:parent.$("#id").val(),
            title: dataField.field.title,
            datagridBasicAttr: {
                limit: dataField.field.limit,
                loading: dataField.field.loading === undefined?false:dataField.field.loading,
                skin: dataField.field.skin,
                even: dataField.field.even === undefined?false:dataField.field.loading,
                size: dataField.field.size
            }
        };
        $.ajax({
            url: "../../dataview/save_dataview",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                data:JSON.stringify(jsonData)
            },
            success: function (data) {
                if (data.ok){
                    layer.msg("保存成功", {icon: 1,time:3000});
                }else {
                    layer.msg("保存失败，错误信息："+data.msg, {icon: 2,time:3000});
                }
            },
            error : function (data) {
                layer.msg("保存失败，错误信息："+data.msg, {icon: 2,time:3000});
            }
        });
        return false;
    })
});

//初始化页面数据
function initDataViewOption(form,layer, data) {
    if (data.length !== 0){
        $("#limit").val(data.limit);
        if (data.loading === false){
            $("#loading").removeAttr("checked");
        } else {
            $("#loading").attr("checked");
        }
        $("#skin").val(data.skin);
        if (data.even === false){
            $("#even").removeAttr("checked");
        } else {
            $("#even").attr("checked");
        }
        $("#size").val(data.size);

        form.render();
    }
}