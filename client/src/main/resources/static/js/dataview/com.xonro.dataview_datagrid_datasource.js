var columnProp = [];
var newSql = "";
var datagridSortAttr = [];
$(function () {
    if (parent.dataviewData.columnProp != null && parent.dataviewData.columnProp !== ""){
        columnProp = JSON.parse(parent.dataviewData.columnProp);
    }
    $("#queryStat").val(parent.dataviewData.queryStat);
    newSql = parent.dataviewData.queryStat;
    if (parent.dataviewData.datagridSortAttr != null && parent.dataviewData.datagridSortAttr !== ""){
        datagridSortAttr = JSON.parse(parent.dataviewData.datagridSortAttr);
    }
});
var layer;
var form;
layui.use(['form','table', 'layer'], function () {
    form = layui.form;
    var table = layui.table;
    layer = layui.layer;
    //初始化数据
    initDataViewOption(form,layer, datagridSortAttr);
    var tables = table.render({
        elem: '#columnProp'
        ,data: columnProp
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[
            {field:'id', title: 'id', width:80, templet: '<div>{{ d.LAY_INDEX }}</div>'}
            ,{field:'field', title: '列名称'}
            ,{field:'title', title: '列标题'}
            ,{field:'sort', title: '排序', templet:'#sort_template'}
            ,{field:'unresize', title: '禁用拖拽列宽', templet:'#unresize_template'}
            ,{fixed: 'right', title: '操作',width:120, align:'center', toolbar: '#bar'}
        ]]
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
        ,limit: 30
    });

    //新增字段设置
    var addData;
    $("#add").on("click", function () {
        $("#index").val("");
        $("#field").val("");
        $("#title").val("");
        $("#sort").val("1");
        $("#unresize").val("1");
        $("#modify").hide();
        $("#up").show();
        form.render();
        addData = layer.open({
            type: 1,
            content: $("#addtemp"),
            title:"新增字段配置"
        })
    });

    //监听工具条
    table.on('tool(columnProp)', function(obj){

        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'edit'){ //编辑
            // var columnPropList = getTableColumns(layer, newSql);
            var $form = $("#addtemp");
            // createOption($form,columnPropList.data,"columnName", form, obj.data.columnName);
            $("#index").val(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,""));
            $("#field").val(obj.data.field);
            $("#title").val(obj.data.title);
            $("#sort").val(obj.data.sort);
            $("#unresize").val(obj.data.unresize);
            $("#modify").show();
            $("#up").hide();
            //重新渲染
            form.render();
            form.render('checkbox');
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
                columnProp.splice(parseInt(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,"")),1);
               tables.reload({
                    elem: '#columnProp'
                    ,data: columnProp
               });
                layer.msg("删除成功", {icon: 1,time:2000});
            });
        }
    });

    //预览配置
    form.on('submit(advanced_options)', function(dataField){
        var jsonData = {
            id:parent.$("#id").val(),
            datagridSortAttr: {
                initSort: {
                    field: dataField.field.field1,
                    type: dataField.field.type
                }
            },
            dataSource:dataField.field.dataSource,
            queryStat: dataField.field.queryStat,
            columnProp:table.cache.columnProp
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
        var id = parent.$("#id").val();
        layer.open({
            area: ['800px', '500px'],
            type: 2,
            content: '../../templates/demo/'+id+'/datagrid.html?id='+id//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
        return false;
    });

    //提交新增
    form.on('submit(up)', function(data){
        layer.msg("提交成功", {icon: 1,time:2000});
        layer.close(addData);
        //去除id
        delete(data.field["id"]);
        columnProp.push(data.field);
        tables.reload({
            elem: '#columnProp',
            data: columnProp
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //提交修改
    form.on('submit(modify)', function(data){
        layer.msg("修改成功", {icon: 1,time:2000});
        layer.close(addData);
        var dataNew = data.field;
        delete(dataNew["index"]);
        columnProp.splice(parseInt($("#index").val()),1,dataNew);
        tables.reload({
            elem: '#columnProp',
            data: columnProp
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //提交保存
    form.on('submit(save)', function(dataField){
        var jsonData = {
            id:parent.$("#id").val(),
            datagridSortAttr: {
                initSort: {
                    field: dataField.field.field1,
                    type: dataField.field.type
                }
            },
            dataSource:dataField.field.dataSource,
            queryStat: newSql,
            columnProp:table.cache.columnProp
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
    var columnPropList = getTableColumns(layer, newSql);
    var $form = $("#datasource");
    var $form1 = $("#addtemp");
    //初始化下拉框
    createOption($form,columnPropList.data,"field1", form, "");
    createOption($form1,columnPropList.data,"field", form, "");
}

//初始化页面数据
function initDataViewOption(form,layer, data) {
    if (newSql != null && newSql !== ""){
        var columnPropList = getTableColumns(layer, newSql);
        var $form = $("#datasource");
        var $form1 = $("#addtemp");
        //初始化下拉框
        createOption($form,columnPropList.data,"field1", form, "");
        createOption($form1,columnPropList.data,"field", form, "");
    }
    if (data.length !== 0){
        $("#field1").val(data.initSort.field);
        $("#type").val(data.initSort.type);

        form.render();
    }
}