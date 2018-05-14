$(function () {
    $('#task_history').bootstrapTable({
        url:"../../task/history",//请求数据url
        queryParams: function (params) {
            return {
                // start: params.offset,  //页码
                // size: params.limit,   //页面大小
                order : params.order, //排序
                sort : params.sort, //排序
                assignee: parent.getUrlParameter("id"), //办理人
                createdBefore: $("#createdBefore").val(),
                createdAfter: $("#createdAfter").val(),
                nameLike: $("#nameLike").val()
            };
        },
        columns: [
            {
                field: 'id',
                title: 'id',
                width:'5%'
            },{
                checkbox: true,
                visible: true  //是否显示复选框
            },{
                field: 'owner',
                title: '来自'
            }, {
                field: 'name',
                title: '标题',
                width:'50%'
            }, {
                field: 'processDefinitionName',
                title: '流程类型'
            }, {
                field: 'createTime',
                title: '接收时间'

            }
        ]
        ,search: true
        ,pagination: true
        ,pageNumber: 1
        ,pageSize: 10
        ,height: $(window).height() - 610
        ,showRefresh: true
        ,rowStyle: function rowStyle(row, index) {
            return {
                classes: 'text-nowrap another-class',
                css: {"font-size": "12px"}
            };
        },
        sidePagination: "client",
        sortName: 'createTime', // 要排序的字段
        sortOrder: 'desc', // 排序规则
        responseHandler:function(res){
            //在ajax获取到数据，渲染表格之前，修改数据源
            return {
                "total": res.total,
                "rows": res.data
            };
        }
    });
});