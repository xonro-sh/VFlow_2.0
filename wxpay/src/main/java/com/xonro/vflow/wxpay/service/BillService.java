package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.bill.Bill;
import com.xonro.vflow.wxpay.bean.bill.QueryComment;
import org.springframework.data.domain.Page;

import java.util.List;

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
    WxPayResponse billDownload(String billDate,String billType,String tarType) throws Exception;

    /**
     * 拉取订单评价数据
     * @param queryComment
     * @return
     */
    WxPayResponse batchQueryComment(QueryComment queryComment);

    /**
     * 分页查询订单数据
     * @param billDate
     * @param perPageNumber
     * @param pageNow
     * @return
     */
    Page<Bill> getAllBillByDateAndPage(String billDate, Integer pageNow, Integer perPageNumber);

    /**
     * 保存账单数据
     * @param bill
     * @return
     */
    Bill saveBill(Bill bill);

    /**
     * 批量保存账单数据
     * @param bills
     * @return
     */
    List<Bill> saveBillList(List<Bill> bills);
}
