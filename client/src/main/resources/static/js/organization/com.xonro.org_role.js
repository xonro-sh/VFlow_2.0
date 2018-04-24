var tenantId;
$(function () {
    tenantId = parent.getUrlParameter("id");
});
layui.use(['table', 'form', 'layer'], function () {
    var table = layui.table,
    form = layui.form,
    layer = layui.layer;
    var tables = table.render({
        elem: '#role'
        ,url: "../../role/all_table?tenantId="+tenantId
        ,page: false
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[
            {field:'name',fixed:"left", title: '角色名称'}
            ,{field:'id', title: '角色id'}
            ,{field:'groupId', title: '组id'}
            ,{fixed: 'right', title: '操作',width:120, align:'center', toolbar: '#role_bar'}
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
    });

    var roleTemp;
    //监听角色表事件
    table.on('tool(role)', function(obj){
        if(obj.event === 'edit'){ //编辑
            $("#roleName").val(obj.data.name);
            //重新渲染
            form.render();
            roleTemp = layer.open({
                type: 1,
                content: $("#role_temp"),
                area: '600px',
                title:"角色修改",
                offset: 'rt',
                anim: 3, //从左滑入
                btn: ['提交'],
                yes: function (index, layero) {
                    //do something
                    var jsonData = {
                        roleId: obj.data.id,
                        roleName: layero.find("#roleName").val()
                    };

                    $.ajax({
                        url: "../../role/update",
                        type: "post",
                        dataType: "json",
                        data: jsonData
                        ,async: false,
                        success: function (data) {
                            if ('ok' in data&&!data.ok){
                                layer.msg("角色信息修改失败，错误信息"+data.msg, {icon: 2,time:3000});
                            } else {
                                layer.msg("角色信息修改成功", {icon: 1,time:2000});
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                            }

                        },
                        error : function (data) {
                            layer.msg("角色信息修改失败，错误信息"+data.msg, {icon: 2,time:3000});
                        }
                    });
                    tables.reload({
                        elem: '#role'
                        ,url: "../../role/all_table?tenantId="+tenantId
                    });

                }
            });
        }
        if(obj.event === 'del'){ //删除角色
            layui.layer.confirm('真的删除角色么', function(index){
                $.ajax({
                    url: "../../role/delete",
                    type: "post",
                    dataType: "json",
                    data: {
                        roleId: obj.data.id
                    }
                    ,async: false,
                    success: function (data) {
                        if ('ok' in data&&!data.ok){
                            layer.msg("删除角色失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("删除角色成功", {icon: 1,time:2000});
                            layui.layer.close(index);
                        }

                    },
                    error : function (data) {
                        layer.msg("删除角色失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                tables.reload({
                    elem: '#role'
                    ,url: "../../role/all_table?tenantId="+tenantId
                });
            });
        }
    });

    //增加子部门
    $("#add_role").on("click",function () {
        $("#roleName").val("");
        form.render();
        roleTemp = layer.open({
            type: 1,
            content: $("#role_temp"),
            area: '600px',
            title:"添加角色",
            offset: 'rt',
            anim: 3, //从左滑入
            btn: ['提交'],
            yes: function (index, layero) {
                //do something
                var jsonData = {
                    roleName: layero.find("#roleName").val(),
                    tenantId: tenantId
                };

                $.ajax({
                    url: "../../role/create",
                    type: "post",
                    dataType: "json",
                    data: jsonData
                    ,async: false,
                    success: function (data) {
                        if ('ok' in data&&!data.ok){
                            layer.msg("获取角色信息失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("新增角色成功", {icon: 1,time:2000});
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }

                    },
                    error : function (data) {
                        layer.msg("获取角色信息失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                tables.reload({
                    elem: '#role'
                    ,url: "../../role/all_table?tenantId="+tenantId
                });

            }
        })
    });

});