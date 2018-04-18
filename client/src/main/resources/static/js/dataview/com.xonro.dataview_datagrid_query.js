var queryCondition = [];
var sql = null;
$(function () {
    if (parent.dataviewData.queryCondition != null && parent.dataviewData.queryCondition !== ""){
        queryCondition = JSON.parse(parent.dataviewData.queryCondition);
    }
    sql = parent.dataviewData.queryStat;
});
layui.use(['table', 'form', 'layer'], function(){
    var table = layui.table;
    var form = layui.form;
    var layer = layui.layer;
    //初始化下拉
    var columnPropList = getTableColumns(layer, sql);
    var $form = $("#addtemp");
    createOption($form,columnPropList.data,"field", form, "");
    //第一个实例
    var tables = table.render({
        elem: '#queryCondition'
        ,data: queryCondition
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[ //表头
            {field:'id', title: 'id', width:80, templet: '<div>{{ d.LAY_INDEX }}</div>'}
            ,{field: 'field', title: '列名称'}
            ,{field: 'title', title: '显示名称'}
            ,{field: 'relationship', title: '关系'}
            ,{field: 'comparisonMethod', title: '比较方式'}
            ,{field: 'type', title: '类型' }
            ,{field: 'ui', title: 'UI组件'}
            ,{field: 'defaultValue', title: '默认值'}
            ,{field: 'configuration', event:'configuration',title: '参考值配置', templet:'#configurationTemplet'}
            ,{fixed: 'right', title: '操作',width:120, align:'center', toolbar: '#bar'}
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
    });
    //监听单元格事件
    table.on('tool(queryCondition)', function(obj){

        if(obj.event === 'configuration'){
            if ('list' === obj.data.ui) {
                if (obj.data.configuration !== undefined && obj.data.configuration !== ""){
                    $("#config").val(obj.data.configuration.config);
                    $("#configText").val(obj.data.configuration.configText);
                } else {
                    $("#config").val("cons");
                    $("#configText").val("");
                }
                $("#index1").val(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,""));
                form.render();
                layer.open({
                    type: 1,
                    content: $("#configurationtemp"),
                    title: "修改参考值配置",
                    btn: ['提交'],
                    yes: function (index, layero) {
                        //do something
                        console.log(layero.find("#config").val());
                        var configuration = {
                            config: layero.find("#config").val(),
                            configText: layero.find("#configText").val()
                        };
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                        queryCondition[parseInt(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,""))].configuration = configuration;
                        tables.reload({
                            elem: '#queryCondition',
                            data: queryCondition
                        });
                    }
                });
            }
        }
        if(obj.event === 'edit'){ //编辑
            $("#index").val(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,""));
            $("#field").val(obj.data.field);
            $("#title").val(obj.data.title);
            $("#relationship").val(obj.data.relationship);
            $("#comparisonMethod").val(obj.data.comparisonMethod);
            $("#type").val(obj.data.type);
            $("#ui").val(obj.data.ui);
            $("#defaultValue").val(obj.data.defaultValue);
            $("#modify").show();
            $("#up").hide();
            //重新渲染
            form.render();
            addData = layer.open({
                type: 1,
                content: $("#addtemp"),
                title:"修改条件查询"
            });
        }
        if(obj.event === 'del'){ //删除
            layui.layer.confirm('真的删除行么', function(index){
                layui.layer.close(index);
                obj.del();
                queryCondition.splice(parseInt(obj.tr.selector.split(" ")[1].replace(/[^0-9]/ig,"")),1);
                tables.reload({
                    elem: '#queryCondition'
                    ,data: queryCondition
                });
                layer.msg("删除成功", {icon: 1,time:2000});
            });
        }
    });

    //新增字段设置
    var addData;
    $("#add").on("click", function () {
        $("#relationship").val("and");
        $("#index").val("");
        $("#field").val("");
        $("#title").val("");

        $("#comparisonMethod").val("=");
        $("#type").val("text");
        $("#ui").val("list");
        $("#defaultValue").val("");

        $("#modify").hide();
        $("#up").show();
        form.render();
        addData = layer.open({
            type: 1,
            content: $("#addtemp"),
            title:"新增条件查询"
        })
    });

    $("#configuration").on("click", function () {

    });
    //提交新增
    form.on('submit(up)', function(data){
        layer.msg("提交成功", {icon: 1,time:2000});
        layer.close(addData);
        //去除id
        delete(data.field["id"]);
        queryCondition.push(data.field);
        tables.reload({
            elem: '#queryCondition',
            data: queryCondition
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //提交修改
    form.on('submit(modify)', function(data){
        layer.msg("修改成功", {icon: 1,time:2000});
        layer.close(addData);
        console.log($("#index").val());
        var dataNew = data.field;
        delete(dataNew["index"]);
        if (data.field.ui !== 'list' && dataNew.configuration !== null){
            delete(dataNew["configuration"]);
        }
        queryCondition.splice(parseInt($("#index").val()),1,dataNew);
        tables.reload({
            elem: '#queryCondition',
            data: queryCondition
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //提交保存
    $("#save").on('click',function () {
        var jsonData = {
            id:parent.$("#id").val(),
            queryCondition:table.cache.queryCondition
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
    });
});