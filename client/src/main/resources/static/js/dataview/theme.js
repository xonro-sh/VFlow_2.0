
    var obj = JSON.parse(getTheme());
    echarts.registerTheme('customed', obj);
    console.log(obj);



function getTheme() {
    var theme = '{\n' +
        '    "color": [\n' +
        '        "#c23531",\n' +
        '        "#2f4554",\n' +
        '        "#61a0a8",\n' +
        '        "#d48265",\n' +
        '        "#91c7ae",\n' +
        '        "#749f83",\n' +
        '        "#ca8622",\n' +
        '        "#bda29a",\n' +
        '        "#6e7074",\n' +
        '        "#546570",\n' +
        '        "#c4ccd3"\n' +
        '    ],\n' +
        '    "backgroundColor": "rgba(0, 0, 0, 0)",\n' +
        '    "textStyle": {},\n' +
        '    "title": {\n' +
        '        "textStyle": {\n' +
        '            "color": "#333"\n' +
        '        },\n' +
        '        "subtextStyle": {\n' +
        '            "color": "#aaa"\n' +
        '        }\n' +
        '    },\n' +
        '    "line": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 1\n' +
        '            }\n' +
        '        },\n' +
        '        "lineStyle": {\n' +
        '            "normal": {\n' +
        '                "width": 2\n' +
        '            }\n' +
        '        },\n' +
        '        "symbolSize": 4,\n' +
        '        "symbol": "emptyCircle",\n' +
        '        "smooth": false\n' +
        '    },\n' +
        '    "radar": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 1\n' +
        '            }\n' +
        '        },\n' +
        '        "lineStyle": {\n' +
        '            "normal": {\n' +
        '                "width": 2\n' +
        '            }\n' +
        '        },\n' +
        '        "symbolSize": 4,\n' +
        '        "symbol": "emptyCircle",\n' +
        '        "smooth": false\n' +
        '    },\n' +
        '    "bar": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "barBorderWidth": 0,\n' +
        '                "barBorderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "barBorderWidth": 0,\n' +
        '                "barBorderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "pie": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "scatter": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "boxplot": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "parallel": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "sankey": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "funnel": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "gauge": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "candlestick": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "color": "#c23531",\n' +
        '                "color0": "#314656",\n' +
        '                "borderColor": "#c23531",\n' +
        '                "borderColor0": "#314656",\n' +
        '                "borderWidth": 1\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "graph": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "borderWidth": 0,\n' +
        '                "borderColor": "#ccc"\n' +
        '            }\n' +
        '        },\n' +
        '        "lineStyle": {\n' +
        '            "normal": {\n' +
        '                "width": 1,\n' +
        '                "color": "#aaa"\n' +
        '            }\n' +
        '        },\n' +
        '        "symbolSize": 4,\n' +
        '        "symbol": "emptyCircle",\n' +
        '        "smooth": false,\n' +
        '        "color": [\n' +
        '            "#c23531",\n' +
        '            "#2f4554",\n' +
        '            "#61a0a8",\n' +
        '            "#d48265",\n' +
        '            "#91c7ae",\n' +
        '            "#749f83",\n' +
        '            "#ca8622",\n' +
        '            "#bda29a",\n' +
        '            "#6e7074",\n' +
        '            "#546570",\n' +
        '            "#c4ccd3"\n' +
        '        ],\n' +
        '        "label": {\n' +
        '            "normal": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#eee"\n' +
        '                }\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "map": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "areaColor": "#eee",\n' +
        '                "borderColor": "#444",\n' +
        '                "borderWidth": 0.5\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "areaColor": "rgba(255,215,0,0.8)",\n' +
        '                "borderColor": "#444",\n' +
        '                "borderWidth": 1\n' +
        '            }\n' +
        '        },\n' +
        '        "label": {\n' +
        '            "normal": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#000"\n' +
        '                }\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "rgb(100,0,0)"\n' +
        '                }\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "geo": {\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "areaColor": "#eee",\n' +
        '                "borderColor": "#444",\n' +
        '                "borderWidth": 0.5\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "areaColor": "rgba(255,215,0,0.8)",\n' +
        '                "borderColor": "#444",\n' +
        '                "borderWidth": 1\n' +
        '            }\n' +
        '        },\n' +
        '        "label": {\n' +
        '            "normal": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#000"\n' +
        '                }\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "rgb(100,0,0)"\n' +
        '                }\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "categoryAxis": {\n' +
        '        "axisLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisTick": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisLabel": {\n' +
        '            "show": true,\n' +
        '            "textStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "splitLine": {\n' +
        '            "show": false,\n' +
        '            "lineStyle": {\n' +
        '                "color": [\n' +
        '                    "#ccc"\n' +
        '                ]\n' +
        '            }\n' +
        '        },\n' +
        '        "splitArea": {\n' +
        '            "show": false,\n' +
        '            "areaStyle": {\n' +
        '                "color": [\n' +
        '                    "rgba(250,250,250,0.3)",\n' +
        '                    "rgba(200,200,200,0.3)"\n' +
        '                ]\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "valueAxis": {\n' +
        '        "axisLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisTick": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisLabel": {\n' +
        '            "show": true,\n' +
        '            "textStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "splitLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": [\n' +
        '                    "#ccc"\n' +
        '                ]\n' +
        '            }\n' +
        '        },\n' +
        '        "splitArea": {\n' +
        '            "show": false,\n' +
        '            "areaStyle": {\n' +
        '                "color": [\n' +
        '                    "rgba(250,250,250,0.3)",\n' +
        '                    "rgba(200,200,200,0.3)"\n' +
        '                ]\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "logAxis": {\n' +
        '        "axisLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisTick": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisLabel": {\n' +
        '            "show": true,\n' +
        '            "textStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "splitLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": [\n' +
        '                    "#ccc"\n' +
        '                ]\n' +
        '            }\n' +
        '        },\n' +
        '        "splitArea": {\n' +
        '            "show": false,\n' +
        '            "areaStyle": {\n' +
        '                "color": [\n' +
        '                    "rgba(250,250,250,0.3)",\n' +
        '                    "rgba(200,200,200,0.3)"\n' +
        '                ]\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "timeAxis": {\n' +
        '        "axisLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisTick": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "axisLabel": {\n' +
        '            "show": true,\n' +
        '            "textStyle": {\n' +
        '                "color": "#333"\n' +
        '            }\n' +
        '        },\n' +
        '        "splitLine": {\n' +
        '            "show": true,\n' +
        '            "lineStyle": {\n' +
        '                "color": [\n' +
        '                    "#ccc"\n' +
        '                ]\n' +
        '            }\n' +
        '        },\n' +
        '        "splitArea": {\n' +
        '            "show": false,\n' +
        '            "areaStyle": {\n' +
        '                "color": [\n' +
        '                    "rgba(250,250,250,0.3)",\n' +
        '                    "rgba(200,200,200,0.3)"\n' +
        '                ]\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "toolbox": {\n' +
        '        "iconStyle": {\n' +
        '            "normal": {\n' +
        '                "borderColor": "#999"\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "borderColor": "#666"\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "legend": {\n' +
        '        "textStyle": {\n' +
        '            "color": "#333"\n' +
        '        }\n' +
        '    },\n' +
        '    "tooltip": {\n' +
        '        "axisPointer": {\n' +
        '            "lineStyle": {\n' +
        '                "color": "#ccc",\n' +
        '                "width": 1\n' +
        '            },\n' +
        '            "crossStyle": {\n' +
        '                "color": "#ccc",\n' +
        '                "width": 1\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "timeline": {\n' +
        '        "lineStyle": {\n' +
        '            "color": "#293c55",\n' +
        '            "width": 1\n' +
        '        },\n' +
        '        "itemStyle": {\n' +
        '            "normal": {\n' +
        '                "color": "#293c55",\n' +
        '                "borderWidth": 1\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "color": "#a9334c"\n' +
        '            }\n' +
        '        },\n' +
        '        "controlStyle": {\n' +
        '            "normal": {\n' +
        '                "color": "#293c55",\n' +
        '                "borderColor": "#293c55",\n' +
        '                "borderWidth": 0.5\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "color": "#293c55",\n' +
        '                "borderColor": "#293c55",\n' +
        '                "borderWidth": 0.5\n' +
        '            }\n' +
        '        },\n' +
        '        "checkpointStyle": {\n' +
        '            "color": "#e43c59",\n' +
        '            "borderColor": "rgba(194,53,49, 0.5)"\n' +
        '        },\n' +
        '        "label": {\n' +
        '            "normal": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#293c55"\n' +
        '                }\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#293c55"\n' +
        '                }\n' +
        '            }\n' +
        '        }\n' +
        '    },\n' +
        '    "visualMap": {\n' +
        '        "color": [\n' +
        '            "#bf444c",\n' +
        '            "#d88273",\n' +
        '            "#f6efa6"\n' +
        '        ]\n' +
        '    },\n' +
        '    "dataZoom": {\n' +
        '        "backgroundColor": "rgba(47,69,84,0)",\n' +
        '        "dataBackgroundColor": "rgba(47,69,84,0.3)",\n' +
        '        "fillerColor": "rgba(167,183,204,0.4)",\n' +
        '        "handleColor": "#a7b7cc",\n' +
        '        "handleSize": "100%",\n' +
        '        "textStyle": {\n' +
        '            "color": "#333"\n' +
        '        }\n' +
        '    },\n' +
        '    "markPoint": {\n' +
        '        "label": {\n' +
        '            "normal": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#eee"\n' +
        '                }\n' +
        '            },\n' +
        '            "emphasis": {\n' +
        '                "textStyle": {\n' +
        '                    "color": "#eee"\n' +
        '                }\n' +
        '            }\n' +
        '        }\n' +
        '    }\n' +
        '}';
    $.ajax({
        url: "../../dataview/getDataViewTheme",
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.ok){
                console.log(data.data)
                theme = data.data;
            } else {

            }
        },
        error : function (data) {

        }
    });
    return theme;
}