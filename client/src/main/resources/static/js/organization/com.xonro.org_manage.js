var tenantId;
var tenantInfo;
$(function () {
    tenantId = parent.getUrlParameter("id");
    console.log(tenantId);
});
layui.use(['tree', 'table', 'layer', 'form', 'laydate'], function () {
    var table = layui.table;
    var tree = layui.tree;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    tenantInfo = parent.getTenantInfo(tenantId,layer);
    console.log(tenantInfo);
    $("#parentId").val(null);
    $("#parentName").val(tenantInfo.name);
    $("#departmentName").val(tenantInfo.name);
    $("#dep").html(tenantInfo.name);
    $("#departmentId").val(null);
    //初始化下拉框
    createOption($("#staff_info_temp"),getAllRole(tenantId,layer),"info_role",form,"name","id");
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
            name: tenantInfo.name
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
                    console.log();
                    if (!Verification1("#staff_info_temp")){

                    } else {
                        //按钮【按钮一】的回调
                        var jsonData = {
                            userId: layero.find("#info_userId").val(),
                            active: layero.find("#info_active").val(),
                            sex: layero.find("#info_sex").val(),
                            tel: layero.find("#info_tel").val(),
                            mobile: layero.find("#info_mobile").val(),
                            idCardName: layero.find("#info_idCardName").val(),
                            idCardNo: layero.find("#info_idCardNo").val(),
                            idCardAddress: layero.find("#info_idCardAddress").val(),
                            birthDate: layero.find("#info_birthDate").val(),
                            nation: layero.find("#info_nation").val(),
                            address: layero.find("#info_address").val(),
                            politicalStatus: layero.find("#info_politicalStatus").val(),
                            maritalStatus: layero.find("#info_maritalStatus").val(),
                            education: layero.find("#info_education").val(),
                            university: layero.find("#info_university").val(),
                            major: layero.find("#info_major").val(),
                            graduationTime: layero.find("#info_graduationTime").val(),
                            remark: layero.find("#info_remark").val(),
                            position: layero.find("#info_position").val(),
                            positionLevel: layero.find("#info_positionLevel").val(),
                            hiredDate: layero.find("#info_hiredDate").val(),
                            employeeType: layero.find("#info_employeeType").val(),
                            employeeState: layero.find("#info_employeeState").val(),
                            tenantId:tenantId
                        };
                        console.log(deleteEmptyProperty(jsonData));
                        $.ajax({
                            url: "../../user/update",
                            type: "post",
                            dataType: "json",
                            data: {
                                userId: layero.find("#info_userId").val(),
                                firstName: layero.find("#info_name").val(),
                                lastName: "",
                                email: layero.find("#info_email").val()
                            }
                            ,async: false,
                            success: function (data) {
                                console.log(data);
                                if ('ok' in data&&!data.ok){
                                    layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                                } else {
                                    $.ajax({
                                        url: "../../user/info_save",
                                        type: "post",
                                        dataType: "json",
                                        contentType:"application/json",
                                        data: deleteEmptyProperty(JSON.stringify(jsonData))
                                        ,async: false,
                                        success: function (data) {
                                            console.log(data);
                                            if ('ok' in data&&!data.ok){
                                                layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                                            } else {
                                                //角色更新
                                                $.ajax({
                                                    url: "../../user/set_role",
                                                    type: "post",
                                                    dataType: "json",
                                                    data: {
                                                        userId: obj.data.id,
                                                        roleId: layero.find("#info_role").val()
                                                    }
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

                                            }
                                        },
                                        error : function (data) {
                                            layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                                        }
                                    });
                                }

                            },
                            error : function (data) {
                                layer.msg("提交失败，错误信息"+data.msg, {icon: 2,time:3000});
                            }
                        });
                        staff.reload({
                            elem: '#staff'
                            ,url: '../../department/users_table?departmentId='+layero.find("#info_departmentId").val() //数据接口
                        });
                    }

                }
            });

        }
        if(obj.event === 'del'){ //删除人员
            layui.layer.confirm('真的删除该人员么', function(index){
                layui.layer.close(index);
                $.ajax({
                    url: "../../user/delete",
                    type: "post",
                    dataType: "json",
                    data: {
                        userId: obj.data.id
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
                staff.reload({
                    elem: '#staff'
                    ,url: '../../department/users_table?departmentId='+$("#info_departmentId").val() //数据接口
                });
            });
        }
    });

    var subDepTemp;
    //增加子部门
    $("#add_sub_dep").on("click",function () {
        $("#name").val("");
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
    //表单提交
    form.on('submit(staff_info_up)',function(data){
        return false;
    });
});

//初始化成员详情
function initUserInfo(data, layer) {
    var info = getUserInfoByUserId(layer, data.id);
    var role = getUserRole(data.id);
    $("#info_userId").val(data.id);
    $("#info_name").val(data.firstName);
    $("#info_role").val(role==null?"":role.id);
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

function getUserRole(userId) {
    var info = {};
    $.ajax({
        url: "../../user/role",
        type: "get",
        dataType: "json",
        data: {
            userId: userId
        },
        async: false,
        success: function (data) {
            if ('ok' in data&&!data.ok){
            } else {
                info = data;
            }
        },
        error : function (data) {
        }
    });
    return info;
}

function deleteEmptyProperty(object){
    for (var i in object) {
        var value = object[i];
        console.log(value);
        if (typeof value === 'object') {
            if (Array.isArray(value)) {
                if (value.length == 0) {
                    delete object[i];
                    continue;
                }
            }
            this.deleteEmptyProperty(value);
            if (this.isEmpty(value)) {
                delete object[i];
            }
        } else {
            if (value === '' || value === null || value === undefined) {
                delete object[i];
            } else {
            }
        }
    }
    return object
}


function isEmpty(object) {
    for (var name in object) {
        return false;
    }
    return true;
}

//获取所有角色
function getAllRole(tenantId, layer) {
    var info = {};
    $.ajax({
        url: "../../role/all",
        type: "get",
        dataType: "json",
        data: {
            tenantId: tenantId
        },
        async: false,
        success: function (data) {
            if ('ok' in data&&!data.ok){
                layer.msg("获取角色信息失败，错误信息"+data.msg, {icon: 2,time:3000});
            } else {
                info = data;
            }
        },
        error : function (data) {
            layer.msg("获取角色信息失败，错误信息"+data.msg, {icon: 2,time:3000});
        }
    });
    return info;
}
/**
 * 初始化下拉框  动态添加
 * @param $form
 * @param data
 * @param name
 * @param form
 * @param value
 * @returns
 */
function createOption($form,data,name,form,showName,value){
    var html = '';
    $form.find('select[name='+name+']').html(html);
    for(var i = 0;i<data.length;i++){
        showName = eval('data[i].'+showName);
        value =  eval('data[i].'+value);
        html +='<option value='+value+'>'+showName+'</option>';
    }
    $form.find('select[name='+name+']').append(html);
    form.render();
}

//表单提交校验
/**
 * @return {boolean}
 */
function Verification1(form){
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
};