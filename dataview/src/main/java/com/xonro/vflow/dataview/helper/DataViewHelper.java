package com.xonro.vflow.dataview.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alex
 * @date 2018/3/19 17:57
 */
public class DataViewHelper {
    /**
     * 从sql语句中解析出表名
     * @param sql
     * @return
     */
    public static String findTableNameFromSql(String sql){
        String str="from\\s+(.*)\\s+where?";
        Pattern p=Pattern.compile(str);
        Matcher matcher=p.matcher(sql);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
