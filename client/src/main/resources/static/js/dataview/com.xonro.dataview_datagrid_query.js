layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#queryCondition'
        ,url: "../../wechat/"
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,height: 'full-160'
        ,cols: [[ //表头
            {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'col', title: '列名称', sort: true}
            ,{field: 'showname', title: '显示名称', sort: true}
            ,{field: 'relationship', title: '关系'}
            ,{field: 'comparison_method', title: '比较方式', }
            ,{field: 'type', title: '类型', }
            ,{field: 'ui', title: 'UI组件'}
            ,{field: 'defaults', title: '默认值',}
            ,{field: 'configuration', title: '参考值配置'}
            ,{field: 'authorized', title: '授权'}
            ,{field: 'key', title: '唯一标识',}
        ]]
        ,loading:true //是否显示加载条 默认true 该参数只适用于 url 参数开启的方式
        ,text: { //自定义文本，如空数据时的异常提示等
            none: '暂无相关数据'
        }
        ,skin: 'nob' //行边框风格
        ,even: false //开启（true）关闭（false）隔行背景
        ,size: 'lg' //大尺寸的表格
    });

});