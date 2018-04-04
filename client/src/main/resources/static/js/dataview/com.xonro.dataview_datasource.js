var newSql = "";
var grpstatsdata = [];
$(function () {
    if (parent.dataviewData.columnProp != null && parent.dataviewData.columnProp !== ""){
        grpstatsdata = JSON.parse(parent.dataviewData.columnProp);
    }

    $("#queryStat").val(parent.dataviewData.queryStat);
});
//layui初始化
layui.use(['table', 'form', 'layer', "jquery"], function(){
    var form = layui.form;
    var table = layui.table;
    var layer = layui.layer;
    var tables = table.render({
        elem: '#grpstats'
        ,data: grpstatsdata
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[
            {field:'id', title: 'id', templet: '<div>{{ d.LAY_INDEX }}</div>'}
            ,{field:'columnName', title: '列名称'}
            ,{field:'title', title: '列标题'}
            ,{field:'group', title: '分组'}
            ,{field:'statistical_type', title: '统计类型'}
            ,{fixed: 'right', title: '操作',width:120, align:'center', toolbar: '#bar'}
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
        ,limit: 20
    });
    var addData;
    $("#add").on("click", function () {
        var columnPropList = getTableColumns(layer, newSql);
        var $form = $("#addtemp");
        //初始化角色下拉框
        createOption($form,columnPropList.data,"columnName", form, "");
        $("#index").val("");
        $("#columnName").val("");
        $("#title").val("");
        $("#group").val("");
        $("#statistical_type").val("");
        $("#modify").hide();
        $("#up").show();
        addData = layer.open({
            type: 1,
            content: $("#addtemp"),
            title:"新增字段配置"
        })
    });

    $("#save").on("click", function () {
        var data = new FormData();
        data.append("id",parent.$("#id").val());
        data.append("dataSource",parent.$("#id").val());
        data.append("queryStat",newSql);
        data.append("columnProp",JSON.stringify(table.cache.grpstats));
    });

    //监听工具条
    table.on('tool(grpstats)', function(obj){

        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'edit'){ //编辑
            var columnPropList = getTableColumns(layer, newSql);
            var $form = $("#addtemp");
            //
            createOption($form,columnPropList.data,"columnName", form, obj.data.columnName);
            $("#index").val(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,""));
            $("#columnName").val(obj.data.columnName);
            $("#title").val(obj.data.title);
            $("#group").val(obj.data.group);
            $("#statistical_type").val(obj.data.statistical_type);
            $("#modify").show();
            $("#up").hide();
            addData = layer.open({
                type: 1,
                content: $("#addtemp"),
                title:"编辑字段配置"
            });
        }
        if(layEvent === 'del'){ //删除
            layui.layer.confirm('真的删除行么', function(index){
                layui.layer.close(index);
                obj.del();
                grpstatsdata.splice(parseInt(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,"")),1);
                layer.msg("删除成功", {icon: 1,time:2000});
            });
        }
    });

    form.on('submit(up)', function(data){//提交新增
        layer.msg("提交成功", {icon: 1,time:2000});
        layer.close(addData);
        //去除id
        delete(data.field["id"]);
        grpstatsdata.push(data.field);
        tables.reload({
            elem: '#grpstats',
            data: grpstatsdata
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    form.on('submit(modify)', function(data){//提交修改
        layer.msg("修改成功", {icon: 1,time:2000});
        layer.close(addData);
        var dataNew = data.field;
        delete(dataNew["index"]);
        grpstatsdata.splice(parseInt($("#index").val()),1,dataNew);
        tables.reload({
            elem: '#grpstats',
            data: grpstatsdata
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    form.on('submit(save)', function(dataField){//提交修改
        var jsonData = {
            id:parent.$("#id").val(),
            dataSource:dataField.field.dataSource,
            queryStat:newSql,
            columnProp:table.cache.grpstats
        };
        console.log(JSON.stringify(jsonData));
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
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});

function compareSQL() {
    newSql = $.trim($('#queryStat').val());
    newSql = newSql.toLowerCase();
}




