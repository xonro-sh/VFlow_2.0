var tenantId;
$(function () {
    tenantId = parent.getUrlParameter("id");
});
layui.use(['tree', 'table', 'form', 'layer'], function () {
    var table = layui.table,
        form = layui.form,
        layer = layui.layer,
        tree = layui.tree;

    $("#parentId").val("0");
    //初始化权限下拉
    $("#groupType").val("department");
    createOption($("#permission_temp"),getAllDepartment(tenantId),"groupId",form,"name","groupId");
    form.render();
    //数据表格初始化
    var menuStructureTable = table.render({
        elem: '#menu_structure'
        ,url: '../../portal/menu_firsts_table?tenantId='+tenantId //数据接口
        ,page: false //关闭
        ,cols: [[ //表头
            {checkbox: true}
            ,{field: 'id', title: '代码', width:80}
            ,{field: 'cnName', title: '中文名称', width:120}
            ,{field: 'enName', title: '英文名称', width:120}
            ,{field: 'isActive', title: '有效性', width:120}
            ,{field: 'url', title: '菜单链接', width:120}
            ,{field: 'menuIcon', title: '菜单图标', width:120}
            ,{field: 'orderIndex', title: '顺序', width:60}
            ,{fixed: 'right', title: '操作',width:250, align:'center', toolbar: '#menu_structure_bar'} //这里的toolbar值是模板元素的选择器
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无子菜单'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'sm' //小尺寸的表格
    });

    var permission = table.render({
        elem: '#permission'
        ,url: ''//数据接口
        ,page: false //关闭
        ,cols: [[ //表头
            {checkbox: true}
            ,{field: 'groupId', title: '权限组id', width:80}
            ,{field: 'name', title: '权限名称', width:120}
            ,{field: 'groupType', title: '权限类型', width:120}
            ,{fixed: 'right', title: '操作',width:250, align:'center', toolbar: '#permission_bar'} //这里的toolbar值是模板元素的选择器
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无权限信息'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'sm' //小尺寸的表格
    });

    var trees = layui.tree({
        elem: '#menu_tree' //传入元素选择器
        ,nodes: getPortalMenusByTree(tenantId, layer)
        ,click: function(node){
            console.log(node.id);
            if (node.id==="0"){
                menuStructureTable.reload({
                    elem: '#menu_structure',
                    url: '../../portal/menu_firsts_table?tenantId='+tenantId
                });
            } else {
                menuStructureTable.reload({
                    elem: '#menu_structure',
                    url: '../../portal/menu_subs_table?parentId='+node.id
                });
            }
            //子部门模板增加初始值
            $("#parentId").val(node.id);
        }
    });

    var subMenuTemp;
    var deployTemp;
    //监听菜单结构表事件
    table.on('tool(menu_structure)', function(obj){
        if(obj.event === 'edit'){ //编辑菜单
            $("#cnName").val(obj.data.cnName);
            $("#enName").val(obj.data.enName);
            $("#isActive").val(obj.data.isActive);
            $("#url").val(obj.data.url);
            $("#menuIcon").val(obj.data.menuIcon);
            $("#orderIndex").val(obj.data.orderIndex);
            //重新渲染
            form.render();
            subMenuTemp = layer.open({
                type: 1,
                content: $("#sub_menu_temp"),
                area: '600px',
                title:obj.data.cnName+"菜单详细信息",
                offset: 'rt', //快捷设置右上角
                anim: 3, //从左滑入
                btn: ['提交'],
                yes: function(index, layero){
                    console.log();
                    if (!Verification("#staff_info_temp")){

                    } else {
                        //按钮【按钮一】的回调
                        var jsonData = {
                            id: obj.data.id,
                            cnName: layero.find("#cnName").val(),
                            enName: layero.find("#enName").val(),
                            isActive: layero.find("#isActive").val(),
                            url: layero.find("#url").val(),
                            menuIcon: layero.find("#menuIcon").val(),
                            orderIndex: layero.find("#orderIndex").val(),
                            parentId: layero.find("#parentId").val(),
                            tenantId: tenantId
                        };
                        console.log(JSON.stringify(jsonData));
                        $.ajax({
                            url: "../../portal/menu_update",
                            type: "post",
                            dataType: "json",
                            contentType:"application/json",
                            data: JSON.stringify(jsonData)
                            ,async: false,
                            success: function (data) {
                                console.log(data);
                                if ('ok' in data&&!data.ok){
                                    layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                                } else {
                                    layer.msg("提交成功", {icon: 1,time:2000});
                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                }

                            },
                            error : function (data) {
                                layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                            }
                        });
                        if (layero.find("#parentId").val()==="0"){
                            menuStructureTable.reload({
                                elem: '#menu_structure',
                                url: '../../portal/menu_firsts_table?tenantId='+tenantId
                            });
                        } else {
                            menuStructureTable.reload({
                                elem: '#menu_structure',
                                url: '../../portal/menu_subs_table?parentId='+layero.find("#parentId").val()
                            });
                        }
                    }

                }
            });

        }
        if(obj.event === 'del'){ //删除菜单
            layui.layer.confirm('真的删除该菜单么', function(index){
                layui.layer.close(index);
                $.ajax({
                    url: "../../portal/menu_delete",
                    type: "post",
                    dataType: "json",
                    data: {
                        id: obj.data.id
                    }
                    ,async: false,
                    success: function (data) {
                        console.log(data);
                        if ('ok' in data&&!data.ok){
                            layer.msg("删除人员失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("删除人员成功", {icon: 1,time:2000});
                        }

                    },
                    error : function (data) {
                        layer.msg("删除人员失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                if ($("#parentId").val()==="0"){
                    menuStructureTable.reload({
                        elem: '#menu_structure',
                        url: '../../portal/menu_firsts_table?tenantId='+tenantId
                    });
                } else {
                    menuStructureTable.reload({
                        elem: '#menu_structure',
                        url: '../../portal/menu_subs_table?parentId='+$("#parentId").val()
                    });
                }
            });
        }
        if (obj.event === 'deploy'){//部署
            permission.reload({
                elem: '#permission',
                url: '../../permission/get_table?resourceId='+obj.data.id
            });
            $("#resourceId").val(obj.data.id);
            deployTemp = layer.open({
                type: 1,
                content: $("#deploy_temp"),
                area: '600px',
                title:"部署",
                offset: 'rt',
                anim: 3 //从左滑入
                // btn: ['提交'],
                // yes: function (index, layero) {
                //     //do something
                //
                // }
            })
        }
    });
    //监听权限表事件
    table.on('tool(permission)', function (obj) {
        if(obj.event === 'del'){ //删除权限
            layui.layer.confirm('真的删除该条权限么', function(index){
                layui.layer.close(index);
                $.ajax({
                    url: "../../permission/delete",
                    type: "post",
                    dataType: "json",
                    data: {
                        permissionId: obj.data.id
                    }
                    ,async: false,
                    success: function (data) {
                        console.log(data);
                        if ('ok' in data&&!data.ok){
                            layer.msg("删除权限失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("删除权限成功", {icon: 1,time:2000});
                        }

                    },
                    error : function (data) {
                        layer.msg("删除权限失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                permission.reload({
                    elem: '#permission',
                    url: '../../permission/get_table?resourceId='+$("#resourceId").val()
                });
            });
        }
    });
    //监听select选择
    form.on('select(groupType)', function(data){
        if ("department"===data.value){
            $("#groupType").val("department");
            createOption($("#permission_temp"),getAllDepartment(tenantId),"groupId",form,"name","groupId");
        } else {
            $("#groupType").val("role");
            createOption($("#permission_temp"),getAllRole(tenantId),"groupId",form,"name","groupId");
        }
        form.render();
    });
    //增加子菜单
    $("#add_sub_menu").on("click",function () {
        $("#cnName").val("");
        $("#enName").val("");
        $("#isActive").val("");
        $("#url").val("");
        $("#menuIcon").val("");
        $("#orderIndex").val("");
        form.render();
        subMenuTemp = layer.open({
            type: 1,
            content: $("#sub_menu_temp"),
            area: '600px',
            title:"添加菜单",
            offset: 'rt',
            anim: 3, //从左滑入
            btn: ['提交'],
            yes: function (index, layero) {
                //do something
                var jsonData = {
                    cnName: layero.find("#cnName").val(),
                    enName: layero.find("#enName").val(),
                    isActive: layero.find("#isActive").val(),
                    url: layero.find("#url").val(),
                    menuIcon: layero.find("#menuIcon").val(),
                    orderIndex: layero.find("#orderIndex").val(),
                    parentId: layero.find("#parentId").val(),
                    tenantId: tenantId
                };
                console.log(JSON.stringify(jsonData))
                $.ajax({
                    url: "../../portal/menu_create",
                    type: "post",
                    dataType: "json",
                    contentType:"application/json",
                    data: JSON.stringify(jsonData)
                    ,async: false,
                    success: function (data) {
                        console.log(data)
                        if ('ok' in data&&!data.ok){
                            layer.msg("新增菜单失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("新增菜单成功", {icon: 1,time:2000});
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }

                    },
                    error : function (data) {
                        layer.msg("新增菜单失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                if (layero.find("#parentId").val()==="0"){
                    menuStructureTable.reload({
                        elem: '#menu_structure',
                        url: '../../portal/menu_firsts_table?tenantId='+tenantId
                    });
                } else {
                    menuStructureTable.reload({
                        elem: '#menu_structure',
                        url: '../../portal/menu_subs_table?parentId='+layero.find("#parentId").val()
                    });
                }
            }
        })
    });

    var permissionTemp;
    //新建权限组
    $("#add_permission").on("click", function () {
        $("#groupType").val("");
        $("#groupId").val("");
        form.render();
        permissionTemp = layer.open({
            type: 1,
            content: $("#permission_temp"),
            area: '600px',
            title:"添加权限组",
            offset: 'rt',
            anim: 3, //从左滑入
            btn: ['提交'],
            yes: function (index, layero) {
                //do something
                var jsonData = {
                    groupId: layero.find("#groupId").val(),
                    resourceId: $("#resourceId").val(),
                    resourceType: "menu"
                };
                console.log(JSON.stringify(jsonData));
                $.ajax({
                    url: "../../permission/create",
                    type: "post",
                    dataType: "json",
                    contentType:"application/json",
                    data: JSON.stringify(jsonData)
                    ,async: false,
                    success: function (data) {
                        console.log(data)
                        if ('ok' in data&&!data.ok){
                            layer.msg("新增权限失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("新增权限成功", {icon: 1,time:2000});
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }

                    },
                    error : function (data) {
                        layer.msg("新增权限失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                permission.reload({
                    elem: '#permission',
                    url: '../../permission/get_table?resourceId='+$("#resourceId").val()
                });
            }
        })
    })

});



//获取树形菜单结构
function getPortalMenusByTree(tenantId, layer) {
    var info = {};
    $.ajax({
        url: "../../portal/menu_tree",
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
            layer.msg("获取门户菜单结构失败，错误信息"+data.msg, {icon: 2,time:3000});
        }
    });
    return info;
}

//表单提交校验
/**
 * @return {boolean}
 */
function Verification(form){
    var button = $(this), verify = {

        required: [
            /[\S]+/
            , '必填项不能为空'
        ]
        , phone: [
            /^1\d{10}$/
            , '请输入正确的手机号'
        ]
        , email: [
            /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
            , '邮箱格式不正确'
        ]
        , url: [
            /(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/
            , '链接格式不正确'
        ]
        , number: [
            /^\d+$/
            , '只能填写数字'
        ]
        , date: [
            /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/
            , '日期格式不正确'
        ]
        , identity: [
            /(^\d{15}$)|(^\d{17}(x|X|\d)$)/
            , '请输入正确的身份证号'
        ]
    }, stop = null
        ,DANGER = 'layui-form-danger', field = {}
        ,formElem = $(form) //获取当前所在的form元素，如果存在的话
        ,verifyElem = formElem.find('*[lay-verify]') //获取需要校验的元素

        ,fieldElem = formElem.find('input,select,textarea') //获取所有表单域


    //开始校验
    layui.each(verifyElem, function(_, item){
        var othis = $(this)
            ,vers = othis.attr('lay-verify').split('|')
            ,verType = othis.attr('lay-verType') //提示方式
            ,value = othis.val();
        console.log(vers);
        othis.removeClass(DANGER);
        layui.each(vers, function(_, thisVer){
            var isTrue //是否命中校验
                ,errorText = '' //错误提示文本
                ,isFn = typeof verify[thisVer] === 'function';

            //匹配验证规则
            if(verify[thisVer]){
                var isTrue = isFn ? errorText = verify[thisVer](value, item) : !verify[thisVer][0].test(value);
                errorText = errorText || verify[thisVer][1];

                //如果是必填项或者非空命中校验，则阻止提交，弹出提示
                if(isTrue){
                    //提示层风格
                    if(verType === 'tips'){
                        layer.tips(errorText, function(){
                            if(typeof othis.attr('lay-ignore') !== 'string'){
                                if(item.tagName.toLowerCase() === 'select' || /^checkbox|radio$/.test(item.type)){
                                    return othis.next();
                                }
                            }
                            return othis;
                        }(), {tips: 1});
                    } else if(verType === 'alert') {
                        layer.alert(errorText, {title: '提示', shadeClose: true});
                    } else {
                        layer.msg(errorText, {icon: 5, shift: 6});
                    }
                    if(!layui.device().android && !layui.device().ios){
                        item.focus();
                    }  //非移动设备自动定位焦点
                    othis.addClass(DANGER);
                    return stop = true;
                }
            }
        });
        if(stop) return stop;
    });

    if(stop) {
        return false;
    } else {
        return true;
    }

    // var nameIndex = {}; //数组 name 索引
    // layui.each(fieldElem, function(_, item){
    //     item.name = (item.name || '').replace(/^\s*|\s*&/, '');
    //
    //     if(!item.name) return;
    //
    //     //用于支持数组 name
    //     if(/^.*\[\]$/.test(item.name)){
    //         var key = item.name.match(/^(.*)\[\]$/g)[0];
    //         nameIndex[key] = nameIndex[key] | 0;
    //         item.name = item.name.replace(/^(.*)\[\]$/, '$1['+ (nameIndex[key]++) +']');
    //     }
    //
    //     if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
    //     field[item.name] = item.value;
    // });
    //
    // //获取字段
    // return true;
}

