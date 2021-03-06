layui.use(['table', 'form','layer'], function(){
    var table = layui.table;
    var form = layui.form;
    var layer = layui.layer;
    var tables = table.render({
        elem: '#dataview_center'
        ,url: "../../dataview/get_table_dataview"
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[ //表头
            {field: 'id', title: 'ID', fixed: 'left'}
            ,{field: 'title', title: '标题'}
            ,{field: 'dataSource', title: '数据源', templet:'#datasouorce'}
            ,{field: 'queryStat', title: '查询语句'}
            ,{field: 'type', title: '视图类型', templet:'#dataview'}
            ,{fixed: 'right', title: '操作',width:180, align:'center', toolbar: '#bar'}
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无报表数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
    });
    var addData;
    $("#add_dataview").on("click", function () {
        //数据初始化
        $("#type").val("");
        $("#title").val("");
        form.render();
        addData = layer.open({
            type: 1,
            content: $("#addtemp"),
            title:"新增报表"
        })
    });
    $("#refresh").on("click",function () {
        tables.reload();
    });


    form.on('submit(up)', function(data){//提交新增
        layer.close(addData);
        var jsonData = {
            type: data.field.type,
            title: data.field.title
        };
        console.log(jsonData);
        var result = saveDataView(layer, JSON.stringify(jsonData));
        if (result){
            layer.msg("保存成功", {icon: 1,time:3000});
            tables.reload({
                elem: '#dataview_center'
                ,url: "../../dataview/get_table_dataview"
            });
        }

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //监听工具条
    table.on('tool(dataview_center)', function(obj){

        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'edit'){ //编辑
            var li = parent.$("li[lay-id="+obj.data.id+"]").length;
            if(li > 0){
                parent.layui.element.tabChange("fsTab", obj.data.id);
            } else {
                if (obj.data.type === '0'){
                    parent.layui.element.tabAdd("fsTab", {
                        title: "数据表格"+obj.data.title
                        ,content: '<iframe src="../dataview/com.xonro.dataview_configuration.html?id='+obj.data.id+'&type='+obj.data.type+'"></iframe>'//支持传入html
                        ,id: obj.data.id
                    });
                } else {
                    parent.layui.element.tabAdd("fsTab", {
                        title: "图形报表"+obj.data.title
                        ,content: '<iframe src="../dataview/com.xonro.dataview_configuration.html?id='+obj.data.id+'&type='+obj.data.type+'"></iframe>'//支持传入html
                        ,id: obj.data.id
                    });
                }
                setTimeout(function () {
                    parent.layui.element.tabChange("fsTab", obj.data.id)
                }, 200);

            }

            // window.location.href = '../../templates/report/com.xonro.dataview_configuration.html?id='+id;
        }
        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除该报表么', function(index){
                layer.close(index);
                var result = delDataView(layer, obj.data.id);
                if (result){
                    obj.del();
                    layer.msg("删除成功", {icon: 1,time:3000});
                }

            });
        }
    });
});