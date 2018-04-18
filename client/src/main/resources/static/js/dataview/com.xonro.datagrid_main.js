layui.use(['table','form', 'laydate', 'layer','element'],function () {
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;
    var layer = layui.layer;
    var element = layui.element;
    var datagrid = getDataGridConf(getUrlParameter("id"));
    var datagridSortAttr = JSON.parse(datagrid.datagridSortAttr);
    var datagridBasicAttr = JSON.parse(datagrid.datagridBasicAttr);
    var queryCondition = JSON.parse(datagrid.queryCondition);
    console.log(queryCondition);
    if (queryCondition === "" || queryCondition === null){
        $("#searchCollapse").remove();
    }
    $.each(queryCondition,function(n,value) {
        switch (value.ui){
            //列表
            case 'list':
                var option = value.configuration.configText.split("|");
                var optionHtml = "";
                    $.each(option,function(n,value){
                        optionHtml = optionHtml + "<option value=\""+value.split(":")[0]+"\">"+value.split(":")[1]+"</option>\n";
                });
                $("#searchDiv").append("<div class=\"layui-row layui-col-space10\">\n" +
                    "                            <div class=\"layui-form-item\">\n" +
                    "                                <label class=\"layui-form-label\">"+value.title+"</label>\n" +
                    "                                <div class=\"layui-input-block\">\n" +
                    "                                    <select id=\""+value.field+"\" name=\""+value.field+"\">\n" +optionHtml+
                    "                                    </select>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>");
                break;
            //文本框
            case 'input':
                if (value.type === 'date'){
                    $("#searchDiv").append(" <div class=\"layui-row layui-col-space10\">\n" +
                        "                            <div class=\"layui-form-item\">\n" +
                        "                                <label class=\"layui-form-label\">"+value.title+"</label>\n" +
                        "                                <div class=\"layui-input-block\">\n" +
                        "                                    <input id=\""+value.field+"\" name=\""+value.field+"\" class=\"layui-input\" type=\"text\">\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>");
                    laydate.render({
                        elem: '#'+value.field+'' //指定元素
                    });
                } else {
                    $("#searchDiv").append(" <div class=\"layui-row layui-col-space10\">\n" +
                        "                            <div class=\"layui-form-item\">\n" +
                        "                                <label class=\"layui-form-label\">"+value.title+"</label>\n" +
                        "                                <div class=\"layui-input-block\">\n" +
                        "                                    <input id=\""+value.field+"\" name=\""+value.field+"\" autocomplete=\"off\" class=\"layui-input\" type=\""+value.type+"\">\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>");
                }
                break;
            //日期
            case 'date':
                $("#searchDiv").append(" <div class=\"layui-row layui-col-space10\">\n" +
                    "                            <div class=\"layui-form-item\">\n" +
                    "                                <label class=\"layui-form-label\">"+value.title+"</label>\n" +
                    "                                <div class=\"layui-input-block\">\n" +
                    "                                    <input id=\""+value.field+"\" name=\""+value.field+"\" class=\"layui-input\" type=\"text\">\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>");
                laydate.render({
                    elem: '#'+value.field+'' //指定元素
                });
                break;
        }
        form.render();
    });
    var tables = table.render({
        elem: '#datagrid'
        ,url: '../../../../dataview/get_datagrid_dataset?id='+getUrlParameter("id")
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,page: true
        ,cols: [JSON.parse(datagrid.columnProp)]
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        },
        loading : datagridBasicAttr.loading,
        initSort: datagridSortAttr.initSort,
        limit: datagridBasicAttr.limit,
        skin: datagridBasicAttr.skin,
        even: datagridBasicAttr.even,
        size: datagridBasicAttr.size
    });
    //查询
    form.on('submit(search)', function(data){
        console.log(data.field);
        tables.reload({
            elem: '#datagrid',
            url: '../../../../dataview/get_datagrid_dataset?id='+getUrlParameter("id"),
            where: {
                data: JSON.stringify(data.field)
            }
        });
        // $.ajax({
        //     url: "../../dataview/save_dataview",
        //     type: "post",
        //     dataType: "json",
        //     async: false,
        //     data: {
        //         data:JSON.stringify(jsonData)
        //     },
        //     success: function (data) {
        //         if (data.ok){
        //             layer.msg("保存成功", {icon: 1,time:3000});
        //         }else {
        //             layer.msg("保存失败，错误信息："+data.msg, {icon: 2,time:3000});
        //         }
        //     },
        //     error : function (data) {
        //         layer.msg("保存失败，错误信息："+data.msg, {icon: 2,time:3000});
        //     }
        // });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    console.log(tables);
});