var option = {
    title: {
        text: '示例',
        subtext: '',
        left: 'left'
    },
    tooltip: {},
    legend: {
        left: 'center',
        orient: 'horizontal'
    },
    dataset: {
        // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
        // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
        dimensions: ['product', '2015', '2016', '2017'],
        source: [
            {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
            {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
            {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
            {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
        ]
    },
    xAxis: {
        type: 'category'
    },
    yAxis: {},
    series: [{
        type: 'bar'
    }]
};
var grpstatsdata = [];
var sql = null;
var ext_text = [];
$(function () {
    if (parent.dataviewData.columnProp != null  && parent.dataviewData.columnProp !== ""){
        grpstatsdata = JSON.parse(parent.dataviewData.columnProp);
    }
    if (parent.dataviewData.reportAttr != null  && parent.dataviewData.columnProp !== ""){
        option = JSON.parse(parent.dataviewData.reportAttr);
    }
    if (parent.dataviewData.extText != null  && parent.dataviewData.extText !== ""){
        ext_text = JSON.parse(parent.dataviewData.extText);
    }
    sql = parent.dataviewData.queryStat;
});
layui.use(['form','element', 'layer'], function(){
    var form = layui.form;
    var element = layui.element;
    var layer = layui.layer;

    var columnPropList = getTableColumns(layer, sql);
    var $form = $("#graph");
    initDataViewOption(element,$form,columnPropList,form,option);
    //保存配置
    form.on('submit(save)', function(dataField){
        option = createDataViewOption(dataField);
        if ($("#type").val() === "treemap"){
            var param = {
                parentName: dataField.field.parentName,
                name: dataField.field.name,
                showName: dataField.field.showName,
                value: dataField.field.value,
                rootNodeCondition: dataField.field.rootNodeCondition
            };
            getTreeMapDataView(layer, parent.$("#id").val(),JSON.stringify(param));
        } else {
            getDataSet(layer, parent.$("#id").val(), dataField.field.xAxis, dataField.field.series);
        }
        return false;
    });
    //预览配置
    form.on('submit(advanced_options)', function(dataField){
        option =  createDataViewOption(dataField);
        if ($("#type").val() === "treemap"){
            var param = {
                parentName: dataField.field.parentName,
                name: dataField.field.name,
                showName: dataField.field.showName,
                value: dataField.field.value,
                rootNodeCondition: dataField.field.rootNodeCondition
            };
            getTreeMapDataView(layer, parent.$("#id").val(),JSON.stringify(param));
        } else {
            getDataSet(layer, parent.$("#id").val(), dataField.field.xAxis, dataField.field.series);
        }

        var id = parent.$("#id").val();
        layer.open({
            area: ['800px', '500px'],
            type: 2,
            content: '../../templates/demo/echarts.html?id='+id//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
        return false;
    });
    //监听tab切换
    element.on('tab(select_graph)', function (data) {
        console.log(this); //当前Tab标题所在的原始DOM元素
        console.log(data.index); //得到当前Tab的所在下标
        console.log(data.elem); //得到当前的Tab大容器
        //清空container容器
        $("#container").empty();
        switch(data.index)
        {
            case 0://柱图
                $("#type").val("bar");
                $("#container").html(bar);
                //初始化角色下拉框 数据
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"yAxis", form, "");
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                $("#legend_left").val(option.legend.left);
                $("#orient").val(option.legend.orient);
                form.render();
                element.init();
                break;
            case 1:
                $("#type").val("pie");
                $("#container").html(pie);
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                $("#legend_left").val(option.legend.left);
                $("#orient").val(option.legend.orient);
                form.render();
                element.init();
                break;
            case 2:
                $("#type").val("line");
                $("#container").html(line);
                //初始化角色下拉框 数据
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"yAxis", form, "");
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                $("#legend_left").val(option.legend.left);
                $("#orient").val(option.legend.orient);
                form.render();
                element.init();
                break;
            case 3:
                $("#type").val("scatter");
                $("#container").html(scatter);
                //初始化角色下拉框 数据
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"yAxis", form, "");
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                $("#legend_left").val(option.legend.left);
                $("#orient").val(option.legend.orient);
                form.render();
                element.init();
                break;
            case 4:
                $("#type").val("funnel");
                $("#container").html(funnel);
                //初始化角色下拉框 数据
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"yAxis", form, "");
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                $("#legend_left").val(option.legend.left);
                $("#orient").val(option.legend.orient);
                form.render();
                element.init();
                break;
            case 5:
                $("#type").val("gauge");
                $("#container").html(gauge);
                //初始化角色下拉框 数据
                createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
                createOption($form,columnPropList.data,"yAxis", form, "");
                createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                form.render();
                element.init();
                break;
            case 6:
                $("#type").val("treemap");
                $("#container").html(treemap);
                createOption($form,columnPropList.data,"parentName", form, ext_text.parentName);
                createOption($form,columnPropList.data,"name", form, ext_text.name);
                createOption($form,columnPropList.data,"showName", form, ext_text.showName);
                createOption($form,columnPropList.data,"value", form, ext_text.value);
                $("#rootNodeCondition").val(ext_text.rootNodeCondition);
                $("#text").val(option.title.text);
                $("#subtext").val(option.title.subtext);
                $("#left").val(option.title.left);
                form.render();
                element.init();
                break;
            default:
                $("#container").empty();

        }
    });
});

function getDataSet(layer,id, xAxis, series) {
    $.ajax({
        url: "../../dataview/get_dataset",
        type: "get",
        dataType: "json",
        data:{
            id:id,
            xAxis:xAxis,
            series:series

        },
        async: false,
        success: function (data) {
            if (data.ok){
                if (data.data != null){
                    option.dataset.source = JSON.parse(data.data);
                    console.log(option.dataset.source);
                    var jsonData = {
                        id:parent.$("#id").val(),
                        reportAttr:option
                    };
                    console.log(JSON.stringify(jsonData));
                    $.ajax({
                        url: "../../dataview/save_dataview",
                        type: "post",
                        dataType: "json",
                        async: false,
                        data: {
                            data:JSON.stringify(jsonData)
                        },
                        success: function (data) {
                            if (data.ok){

                            }else {
                                layer.msg(data.msg, {icon: 2,time:3000});
                            }
                        },
                        error : function (data) {
                        }
                    });
                } else {
                    layer.msg(data.msg, {icon: 2,time:3000});
                }

            } else {
                layer.msg(data.msg, {icon: 2,time:3000});
            }
        },
        error : function (data) {
            layer.msg(data.msg, {icon: 2,time:3000});
        }
    });
}

function getTreeMapDataView(layer, id, param) {
    $.ajax({
        url: "../../dataview/get_treemap_dataview",
        type: "get",
        dataType: "json",
        data:{
            id:id,
            param:param
        },
        async: false,
        success: function (data) {
            if (data.ok){
                if (data.data != null){
                    option.series[0].data = JSON.parse(data.data);
                    var jsonData = {
                        id:parent.$("#id").val(),
                        extText:param,
                        reportAttr:option
                    };
                    console.log(jsonData.reportAttr);
                    console.log(JSON.stringify(jsonData));
                    $.ajax({
                        url: "../../dataview/save_dataview",
                        type: "post",
                        dataType: "json",
                        async: false,
                        data: {
                            data:JSON.stringify(jsonData)
                        },
                        success: function (data) {
                            if (data.ok){

                            }else {
                                layer.msg(data.msg, {icon: 2,time:3000});
                            }
                        },
                        error : function (data) {
                        }
                    });
                } else {
                    layer.msg(data.msg, {icon: 2,time:3000});
                }

            } else {
                layer.msg(data.msg, {icon: 2,time:3000});
            }
        },
        error : function (data) {
            layer.msg(data.msg, {icon: 2,time:3000});
        }
    });
}

//创建不同报表的参数配置
function createDataViewOption(dataField) {
    var optionTemp;
    switch(dataField.field.type)
    {
        case "bar":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {},
                series: [{
                    type: 'bar'
                }]
            };
            break;
        case "pie":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                series: [{
                    type: 'pie'
                }]
            };
            break;
        case "line":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {},
                series: [{
                    type: 'line'
                }]
            };
            break;
        case "scatter":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {},
                series: [{
                    type: 'scatter'
                }]
            };
            break;
        case "funnel":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                series: [{
                    type: 'funnel'
                }]
            };
            break;
        case "gauge":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                series: [{
                    type: 'gauge'
                }]
            };
            break;
        case "treemap":
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                series: [{
                    type: 'treemap',
                    data: []
                }]
            };
            break;
        default:
            optionTemp = {
                title: {
                    text: dataField.field.text,
                    subtext: dataField.field.subtext,
                    left: dataField.field.left
                },
                tooltip: {},
                legend: {
                    left: dataField.field.legend_left,
                    orient: dataField.field.orient
                },
                dataset: {
                    // 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
                    // 如果不指定 dimensions，也可以通过指定 series.encode 完成映射，参见后文。
                    dimensions: [dataField.field.xAxis, dataField.field.series],
                    source: [
                        {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                        {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                        {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                        {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
                    ]
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {},
                series: [{
                    type: 'bar'
                }]
            };
    }
    return optionTemp;
}

//页面加载时初始化报表参数配置
function initDataViewOption(element,$form,columnPropList,form,option){

    $("#type").val(option.series[0].type);
    //清空container容器
    $("#container").empty();
    //清除选中样式
    $(".layui-tab-title .layui-this").removeClass("layui-this");
    switch (option.series[0].type)
    {
        case "bar":
            //页面样式初始化
            $("#container").html(bar);
            $(".layui-tab-title").find("li").eq(0).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
            break;
        case "pie":
            //页面样式初始化
            $("#container").html(pie);
            $(".layui-tab-title").find("li").eq(1).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
            break;
        case "line":
            //页面样式初始化
            $("#container").html(line);
            $(".layui-tab-title").find("li").eq(2).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
            break;
        case "scatter":
            //页面样式初始化
            $("#container").html(scatter);
            $(".layui-tab-title").find("li").eq(3).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
            break;
        case "funnel":
            //页面样式初始化
            $("#container").html(funnel);
            $(".layui-tab-title").find("li").eq(4).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
            break;
        case "gauge":
            //页面样式初始化
            $("#container").html(gauge);
            $(".layui-tab-title").find("li").eq(5).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            form.render();
            element.init();
            break;
        case "treemap":
            //页面样式初始化
            $("#container").html(treemap);
            $(".layui-tab-title").find("li").eq(6).addClass("layui-this");
            //初始化下拉框
            createOption($form,columnPropList.data,"parentName", form, ext_text.parentName);
            createOption($form,columnPropList.data,"name", form, ext_text.name);
            createOption($form,columnPropList.data,"showName", form, ext_text.showName);
            createOption($form,columnPropList.data,"value", form, ext_text.value);
            $("#rootNodeCondition").val(ext_text.rootNodeCondition);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            form.render();
            element.init();
            break;
        default:
            //页面样式初始化
            $("#container").html(bar);
            $(".layui-tab-title").find("li").eq(0).addClass("layui-this");
            //初始化角色下拉框
            createOption($form,columnPropList.data,"xAxis", form, option.dataset.dimensions[0]);
            createOption($form,columnPropList.data,"yAxis", form, "");
            createOption($form,columnPropList.data,"series", form, option.dataset.dimensions[1]);
            $("#text").val(option.title.text);
            $("#subtext").val(option.title.subtext);
            $("#left").val(option.title.left);
            $("#legend_left").val(option.legend.left);
            $("#orient").val(option.legend.orient);
            form.render();
            element.init();
    }

}