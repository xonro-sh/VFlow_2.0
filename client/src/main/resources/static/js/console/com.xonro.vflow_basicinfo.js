

layui.use(['upload','form'], function(){
    var upload = layui.upload;
    var form = layui.form;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        ,url: '/upload/' //上传接口
        ,done: function(res){
            //上传完毕回调
        }
        ,error: function(){
            //请求异常回调
        }
    });
});