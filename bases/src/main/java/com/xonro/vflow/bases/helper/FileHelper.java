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
                            "    <script type=\"text/javascript\" src=\"../../../js/dataview/com.xonro.datagrid_main.js\"></script>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class=\"layui-col-md12 layui-col-space1\">\n" +
                            "    <div class=\"layui-collapse\" id=\"searchCollapse\">\n" +
                            "        <div class=\"layui-colla-item\">\n" +
                            "            <h2 class=\"layui-colla-title\">查询条件</h2>\n" +
                            "            <div class=\"layui-colla-content layui-show\">\n" +
                            "                <form class=\"layui-form layui-row\">\n" +
                            "                    <div class=\"layui-col-md10\" id=\"searchDiv\">\n" +
                            "                    </div>\n" +
                            "                    <div class=\"layui-col-md2\">\n" +
                            "                        <div class=\"layui-col-md12 layui-col-space1\">\n" +
                            "                            <span style=\"float: right\">\n" +
                            "                                <button id=\"search\" class=\"layui-btn\" lay-filter=\"search\" lay-submit>查询</button>\n" +
                            "                            </span>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </form>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "    <!--<div class=\"layui-btn-group\">-->\n" +
                            "        <!--<button class=\"layui-btn layui-btn-normal\" id=\"add\">新增</button>-->\n" +
                            "    <!--</div>-->\n" +
                            "    <table class=\"layui-hide\" id=\"datagrid\" lay-filter=\"datagrid\" lay-data=\"{id:'datagrid'}\"></table>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>\n";
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
