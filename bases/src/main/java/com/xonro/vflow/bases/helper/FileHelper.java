package com.xonro.vflow.bases.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 文件helper
 * @author Alex
 * @date 2018/3/30 15:09
 */
@Component
public class FileHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @param pathName 文件夹路径
     */
    public void createDataViewFile(String pathName, String type){
        try {
            File file = new File(pathName);
            if (file.mkdirs()){
                File file1;
                String sb;
                if (type.equals("1")){
                    file1 = new File(pathName+File.separator+"echarts.html");
                    sb = "<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                            "<head>" +
                            "<meta charset=\"UTF-8\">" +
                            "<title>echarts</title>" +
                            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>" +
                            "<script src=\"//cdn.bootcss.com/vue/2.3.4/vue.min.js\"></script>" +
                            "<script src=\"https://cdn.bootcss.com/echarts/4.0.2/echarts.min.js\"></script>" +
                            "<script type=\"text/javascript\" src=\"../../../plugins/jquery/jquery.min.js\"></script>" +
                            "<script src=\"../../../lib/layui/layui.js\"></script>" +
                            "<script type=\"text/javascript\" src=\"../../../js/dataview/com.xonro.dataview_util.js\"></script>" +
                            "<script type=\"text/javascript\" src=\"../../../js/util.js\"></script>" +
                            "<script type=\"text/javascript\" src=\"../../../js/dataview/theme.js\"></script>" +
                            "</head>" +
                            "<body style=\"margin:0;padding:0;\">" +
                            "<div id=\"main\" style=\"width: 100%;height: 300px\">" +
                            "</div>" +
                            "<script type=\"text/javascript\">" +
                            "var myChart = echarts.init(document.getElementById('main'), 'customed');" +
                            "var option = JSON.parse(getDataView(getUrlParameter(\"id\")).reportAttr);" +
                            "myChart.setOption(option);" +
                            "</script>" +
                            "</body>" +
                            "</html>";
                } else {
                    file1 = new File(pathName+File.separator+"datagrid.html");
                    sb = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>数据视图模板</title>\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\"/>\n" +
                            "    <link rel=\"stylesheet\" href=\"../../../css/children.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"../../../lib/layui/css/layui.css\" media=\"all\">\n" +
                            "    <script type=\"text/javascript\" src=\"../../../plugins/jquery/jquery.min.js\"></script>\n" +
                            "    <script type=\"text/javascript\" src=\"../../../js/dataview/com.xonro.dataview_util.js\"></script>\n" +
                            "    <script type=\"text/javascript\" src=\"../../../js/util.js\"></script>\n" +
                            "    <script src=\"../../../lib/layui/layui.js\"></script>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class=\"layui-col-md12 layui-col-space1\">\n" +
                            "    <!--<div class=\"layui-btn-group\">-->\n" +
                            "        <!--<button class=\"layui-btn layui-btn-normal\" id=\"add\">新增</button>-->\n" +
                            "    <!--</div>-->\n" +
                            "    <table class=\"layui-hide\" id=\"datagrid\" lay-filter=\"datagrid\" lay-data=\"{id:'datagrid'}\"></table>\n" +
                            "</div>\n" +
                            "<script>\n" +
                            "    layui.use(['table'],function () {\n" +
                            "        var table = layui.table;\n" +
                            "        var datagrid = getDataGridConf(getUrlParameter(\"id\"));\n" +
                            "        var datagridAttr = JSON.parse(datagrid.datagridAttr);\n" +
                            "        console.log(datagrid);\n" +
                            "        console.log(JSON.parse(datagrid.columnProp));\n" +
                            "        var tables = table.render({\n" +
                            "            elem: '#datagrid'\n" +
                            "            ,url: '../../../dataview/get_datagrid_dataset?id='+getUrlParameter(\"id\")\n" +
                            "            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增\n" +
                            "            ,height: 'full-160'\n" +
                            "            ,page: true\n" +
                            "            ,cols: [JSON.parse(datagrid.columnProp)]\n" +
                            "            ,text: { //自定义文本，如空数据时的异常提示等\n" +
                            "                none: '暂无相关数据'\n" +
                            "            },\n" +
                            "            loading : datagridAttr.loading,\n" +
                            "            initSort: datagridAttr.initSort,\n" +
                            "            limit: datagridAttr.limit,\n" +
                            "            skin: datagridAttr.skin,\n" +
                            "            even: datagridAttr.even,\n" +
                            "            size: datagridAttr.size\n" +
                            "        });\n" +
                            "        console.log(tables);\n" +
                            "    });\n" +
                            "</script>\n" +
                            "</body>\n" +
                            "</html>";
                }
                if (file1.createNewFile()){
                    PrintStream printStream = new PrintStream(new FileOutputStream(file1));
                    printStream.println(sb);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
