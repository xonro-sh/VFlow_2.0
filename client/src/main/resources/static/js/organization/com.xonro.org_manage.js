var tenantId;
$(function () {
    tenantId = parent.getUrlParameter("tenantId");
    console.log(tenantId);
});
layui.use(['tree', 'table', 'layer', 'form', 'laydate'], function () {
    var table = layui.table;
    var tree = layui.tree;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    $("#parentId").val(null);
    $("#parentName").val("上海象融");
    $("#departmentName").val("上海象融");
    $("#dep").html("上海象融");
    $("#departmentId").val(null);
    //日期初始化
    laydate.render({
        elem: '#info_birthDate' //指定元素
    });
    laydate.render({
        elem: '#info_graduationTime' //指定元素
    });
    laydate.render({
        elem: '#info_hiredDate' //指定元素
    });
    //表单初始化
    var subDepTable = table.render({
        elem: '#sub_dep'
        ,url: '../../department/roots_table?tenantId='+tenantId //数据接口
        ,page: false //关闭
        ,cols: [[ //表头
            {checkbox: true}
            ,{field: 'id', title: '代码', width:80}
            ,{field: 'name', title: '部门名称', width:120}
            ,{fixed: 'right', title: '操作',width:200, align:'center', toolbar: '#sub_dep_bar'} //这里的toolbar值是模板元素的选择器
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无子部门'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'sm' //大尺寸的表格
    });

    var staff = table.render({
        elem: '#staff'
        ,url: '' //数据接口
        ,page: false //关闭
        ,cols: [[ //表头
            // {checkbox: true}
            {field: 'firstName', title: '姓名', width:120,fixed: 'left'}
            ,{field: 'id', title: '账号', width:80}
            ,{field: 'email', title: '邮箱', width:120}
            ,{fixed: 'right', title: '操作',width:200, align:'center', toolbar: '#staff_bar'} //这里的toolbar值是模板元素的选择器
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无成员'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'sm' //大尺寸的表格
    });

    var trees = layui.tree({
        elem: '#org' //传入元素选择器
        ,nodes: [{ //节点
            name: '上海象融'
            ,id: null
            ,spread: true
            ,children: getDepartmentsByTree()
        }]
        ,click: function(node){
            console.log(node.id);
            if (node.id==null||node.id===""){
                subDepTable.reload({
                    elem: '#sub_dep',
                    url: '../../department/roots_table?tenantId='+tenantId
                });
            } else {
                console.log(11);
                subDepTable.reload({
                    elem: '#sub_dep',
                    url: '../../department/subs_table?departmentId='+node.id
                });
                staff.reload({
                    elem: '#staff'
                    ,url: '../../department/users_table?departmentId='+node.id //数据接口
                });
            }

            //子部门模板增加初始值
            $("#parentId").val(node.id);
            $("#parentName").val(node.name);
            $("#departmentId").val(node.id);
            $("#departmentName").val(node.name);
            $("#info_departmentId").val(node.id);
            $("#info_departmentName").val(node.name);
            $("#dep").html(node.name);

        }
    });
    var staffInfoTemp;
    //监听成员表事件
    table.on('tool(staff)', function(obj){
        if(obj.event === 'edit'){ //编辑
            initUserInfo(obj.data,layer);
            //重新渲染
            form.render();
            staffInfoTemp = layer.open({
                type: 1,
                content: $("#staff_info_temp"),
                area: '600px',
                title:obj.data.firstName+"详细信息",
                offset: 'rt', //快捷设置右上角
                anim: 3, //从左滑入
                btn: ['提交'],
                yes: function(index, layero){
                    //按钮【按钮一】的回调
                }
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

    var subDepTemp;
    //增加子部门
    $("#add_sub_dep").on("click",function () {
        $("#name").val("");
        $("#sub_dep_up").show();
        $("#sub_dep_modify").hide();
        form.render();
        subDepTemp = layer.open({
            type: 1,
            content: $("#sub_dep_temp"),
            area: '600px',
            title:"添加子部门",
            offset: 'rt',
            anim: 3, //从左滑入
            btn: ['提交'],
            yes: function (index, layero) {
                //do something
                var jsonData = {
                    name: layero.find("#name").val(),
                    tenantId: tenantId
                };
                if (layero.find("#parentId").val() != null && layero.find("#parentId").val() !== ""){
                    jsonData.parentId=layero.find("#parentId").val();
                }

                $.ajax({
                    url: "../../department/create",
                    type: "post",
                    dataType: "json",
                    contentType:"application/json",
                    data: JSON.stringify(jsonData)
                    ,async: false,
                    success: function (data) {
                        if ('ok' in data&&!data.ok){
                            layer.msg("获取组织架构失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("提交成功", {icon: 1,time:2000});
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }

                    },
                    error : function (data) {
                        layer.msg("获取组织架构失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                if (layero.find("#parentId").val() == null || layero.find("#parentId").val() === ""){
                    subDepTable.reload({
                        elem: '#sub_dep',
                        url: '../../department/roots_table?tenantId='+tenantId
                    });
                } else {
                    subDepTable.reload({
                        elem: '#sub_dep',
                        url: '../../department/subs_table?departmentId='+layero.find("#parentId").val()
                    });
                }
            }
        })
    });
    var addStaffTemp;
    //增加人员
    $("#add_staff").on("click",function () {
        $("#userId").val("");
        $("#firstName").val("");
        $("#email").val("");
        $("#password").val("");
        $("#staff_up").show();
        $("#staff_modify").hide();
        form.render();
        addStaffTemp = layer.open({
            type: 1,
            area: '600px',
            content: $("#add_staff_temp"),
            title:"添加成员",
            offset: 'rt',
            anim: 3, //从左滑入
            btn: ['添加'],
            yes: function (index, layero) {
                //do something
                var jsonData = {
                    userId: layero.find("#userId").val(),
                    firstName: layero.find("#firstName").val(),
                    email: layero.find("#email").val(),
                    password: layero.find("#password").val(),
                    departmentId: layero.find("#departmentId").val(),
                    tenantId: tenantId
                };
                $.ajax({
                    url: "../../user/create",
                    type: "post",
                    dataType: "json",
                    contentType:"application/json",
                    data: JSON.stringify(jsonData)
                    ,async: false,
                    success: function (data) {
                        console.log(data);
                        if ('ok' in data&&!data.ok){
                            layer.msg("新增成员失败，错误信息"+data.msg, {icon: 2,time:3000});
                        } else {
                            layer.msg("提交成功", {icon: 1,time:2000});
                            layer.close(index);
                        }

                    },
                    error : function (data) {
                        layer.msg("新增成员失败，错误信息"+data.msg, {icon: 2,time:3000});
                    }
                });
                staff.reload({
                    elem: '#staff'
                    ,url: '../../department/users_table?departmentId='+layero.find("#departmentId").val() //数据接口
                });
            }
        })
    });

    //获取树形部门结构
    function getDepartmentsByTree() {
        var info = {};
        $.ajax({
            url: "../../department/tree",
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
                layer.msg("获取组织架构失败，错误信息"+data.msg, {icon: 2,time:3000});
            }
        });
        return info;
    }
});

//初始化成员详情
function initUserInfo(data, layer) {
    var info = getUserInfoByUserId(layer, data.id);
    $("#info_userId").val(data.id);
    $("#info_name").val(data.firstName);
    $("#info_role").val(info.role);
    $("#info_active").val(info.active);
    $("#info_sex").val(info.sex);
    $("#info_email").val(data.email);
    $("#info_tel").val(info.tel);
    $("#info_mobile").val(info.mobile);
    $("#info_idCardName").val(info.idCardName);
    $("#info_idCardNo").val(info.idCardNo);
    $("#info_idCardAddress").val(info.idCardAddress);
    $("#info_birthDate").val(info.birthDate);
    $("#info_nation").val(info.nation);
    $("#info_address").val(info.address);
    $("#info_politicalStatus").val(info.politicalStatus);
    $("#info_maritalStatus").val(info.maritalStatus);
    $("#info_education").val(info.education);
    $("#info_university").val(info.university);
    $("#info_major").val(info.major);
    $("#info_graduationTime").val(info.graduationTime);
    $("#info_remark").val(info.remark);
    $("#info_position").val(info.position);
    $("#info_positionLevel").val(info.positionLevel);
    $("#info_hiredDate").val(info.hiredDate);
    $("#info_employeeType").val(info.employeeType);
    $("#info_employeeState").val(info.employeeState);


}
//根据账号获取成员详情
function getUserInfoByUserId(layer, userId) {
    var info = {};
    $.ajax({
        url: "../../user/info_get",
        type: "get",
        dataType: "json",
        data: {
            userId: userId
        },
        async: false,
        success: function (data) {
            if ('ok' in data&&!data.ok){
                layer.msg("获取成员信息失败，错误信息"+data.msg, {icon: 2,time:3000});
            } else {
                info = data;
            }
        },
        error : function (data) {
            layer.msg("获取成员信息失败，错误信息"+data.msg, {icon: 2,time:3000});
        }
    });
    return info;
}