package com.xonro.vflow.wxpay.service;

import java.util.Map;

/**
 * 账单相关业务服务接口
 * @author louie
 * @date created in 2018-3-12 9:54
 */
public interface BillService {

    /**
     * 下载对账单
     * @param billDate 对账单日期
     * @param billType 账单类型
     * @param tarType 压缩账单
     * @return
     */
    public Map<String,String> billDownload(String billDate,String billType,String tarType);


}
