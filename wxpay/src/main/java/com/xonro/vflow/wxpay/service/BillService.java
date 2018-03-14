package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.bill.QueryComment;

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
    public WxPayResponse billDownload(String billDate,String billType,String tarType);

    /**
     * 拉取订单评价数据
     * @param queryComment
     * @return
     */
    public WxPayResponse batchQueryComment(QueryComment queryComment);
}
