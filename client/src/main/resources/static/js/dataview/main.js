/**
 * VFlow报表通用类
 * Created by Alex on 2018/2/11.
 */
    //获取链接后的id
    var id = getUrlParameter("id");
    var container = document.getElementById('main');

    //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
    var resizeContainer = function () {
        container.style.height = window.innerHeight+'px';
    };
    //设置容器高宽
    resizeContainer();
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(container, 'purple-passion');
    myChart.showLoading('default', {
        text: 'funck',
        color: '#c23531',
        textColor: '#000',
        maskColor: 'rgba(255, 255, 255, 0.8)',
        zlevel: 0
    });
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例',
            padding: 10
        },
        tooltip: {},
        legend: {
            data:['销量'],
            x: 'right'
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.hideLoading();
    //用于使chart自适应高度和宽度
    window.onresize = function () {
        //重置容器高宽
        resizeContainer();
        myChart.resize();
    };