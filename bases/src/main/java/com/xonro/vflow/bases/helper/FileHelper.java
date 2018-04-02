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
    public void createDataViewFile(String pathName){

        try {
            File file = new File(pathName);
            if (file.mkdirs()){
                File file1 = new File(pathName+File.separator+"echarts.html");
                if (file1.createNewFile()){
                    String sb = "<!DOCTYPE html>" +
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
                    PrintStream printStream = new PrintStream(new FileOutputStream(file1));
                    printStream.println(sb);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
