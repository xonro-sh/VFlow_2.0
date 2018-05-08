/**
 * 流程组件，任务相关js
 * @author louie
 * @date created in 2018-05-08
 */

/**
 * 获取任务列表
 * @param start 获取偏移量
 * @param size 获取数量
 * @param sort 排序字段 默认为id
 * @param order 排序，asc或desc，默认为asc
 * @return 任务集合
 {"data": [],
    "total": 0,
    "start": 0,
    "sort": "id",
    "order": "asc",
    "size": 0}
 */
function listTasks(start,size,sort,order) {
    return listUserTasks(start,size,sort,order);
}

/**
 * 获取用户任务列表
 * @param start 获取偏移量
 * @param size 获取数量
 * @param sort 排序字段 默认为id
 * @param order 排序，asc或desc，默认为asc
 * @param assignee 任务办理人
 * @returns 任务集合
 * {{"data": [],
    "total": 0,
    "start": 0,
    "sort": "id",
    "order": "asc",
    "size": 0}}
 */
function listUserTasks(start,size,sort,order,assignee) {
    var url = "/runtime/tasks";
    var result = {};

    $.ajax({
        url:url,
        type:"GET",
        data:{
            start:start,
            size:size,
            sort:sort,
            order:order,
            assignee:assignee
        },
        async:false,
        success:function (data) {
            result = data;
        }
    });
    return result;
}

/**
 * 查询任务
 * @param queryData 请求条件，TaskQueryRequest
 * @return 任务集合
 */
function queryTasks(queryData) {
    var result = {};
    $.ajax({
        url:"/query/tasks",
        data:JSON.stringify(queryData),
        async:false,
        type:"POST",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        success:function (response) {
            result = response;
        },
        error:function (data) {
            console.log(data.responseJSON);
        }
    });
    return result;
}

/**
 * 获取任务详情
 * @param taskId
 * @return 任务详情，json对象，结构与TaskResponse一致
 */
function taskDetail(taskId) {
    var result;
    $.ajax({
        url:"/runtime/tasks/"+taskId,
        async:false,
        dataType:"json",
        success:function (response) {
            result = response;
        },
        error:function (data) {
            console.log(data.responseJSON);
        }
    });
    return result;
}