$(function () {
    $("#title").val(parent.dataviewData.title);
});
layui.use(['layer','form'],function () {
    var form = layui.form;
    var layer = layui.layer;
    //提交修改
    form.on('submit(save)', function (dataField) {
        var jsonData = {
            id:parent.$("#id").val(),
            title: dataField.field.title
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