$(function () {
    toastr.options = {
        closeButton: true,
        positionClass: "toast-top-full-width"
    };
    var userId = parent.getUrlParameter("id");
    var userInfo = getUserInfoByUserId(userId);
    var tenantInfo = getTenantInfo_Portal(userInfo.tenantId);
    $('#jstree').jstree({
        'core' : {
            'data' : [
                {
                    "text" : tenantInfo.name,
                    "state" : {"opened" : true },
                    "id" : null,
                    "children" : getDepartmentsByJsTree(tenantInfo.id)
                }
            ],
            'strings' : {
                'Loading ...' : 'Please wait ...'
            },
            'check_callback' : true
        },
        'plugins' : ["dnd","search"]
    });
    // 7 bind to events triggered on the tree
    $('#jstree').on("changed.jstree", function (e, data) {
        console.log(data.selected[0]);
        $('#table').bootstrapTable('refresh', {
            url: '../../department/userinfos?departmentId='+data.selected
        });
    });

    $('#table').bootstrapTable({
        columns: [{
            field: 'userId',
            title: '工号'
        }, {
            field: 'idCardName',
            title: '姓名'
        }, {
            field: 'position',
            title: '职级'
        }, {
            field: 'tel',
            title: '电话'

        }, {
            field: 'mobile',
            title: '手机'
        }
        ],
        url: ''
        ,search: true
    });

    $("#q").on('change keyup', function (e) {
        e.preventDefault();
        $("#jstree").jstree(true).search($("#q").val());
    })
});

//获取树形部门结构
function getDepartmentsByJsTree(tenantId) {
    var info = {};
    $.ajax({
        url: "../../department/js_tree",
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
            toastr.warning("获取组织架构失败，错误信息"+data.msg, {timeOut: 2000});
        }
    });
    return info;
}

