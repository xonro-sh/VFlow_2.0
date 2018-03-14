package com.xonro.vflow.wxpay.web;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.bill.Bill;
import com.xonro.vflow.wxpay.bean.order.QueryOrderResult;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import com.xonro.vflow.wxpay.service.BillService;
import com.xonro.vflow.wxpay.service.OrderService;
import com.xonro.vflow.wxpay.service.PayService;
import com.xonro.vflow.wxpay.service.RefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * web服务转化器
 * @author louie
 * @date created in 2018-3-14 15:16
 */
@Service
public class WebConverter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private RefundService refundService;

    @Autowired
    private BillService billService;

    /**
     * 微信支付结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    public String accessPayNotify(String notifyData){
        try {
            return WXPayUtil.mapToXml(
                    JSON.parseObject(JSON.toJSONString(payService.accessPayNotify(notifyData)),Map.class)
            );
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 退款结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    public String accessRefundNotify(String notifyData){
        try {
            return WXPayUtil.mapToXml(
                    JSON.parseObject(JSON.toJSONString(refundService.accessRefundNotify(notifyData)),Map.class)
            );
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 分页查询对账单
     * 先从本地数据库查询，有数据则返回，无数据则访问微信接口获取并将接口数据保存本地
     * @param billDate 对账单日期
     * @param perPageNumber 每页行数
     * @param pageNow 当前页码
     * @return
     */
    public Page<Bill> getAllBillByPageAndDate(String billDate, Integer pageNow, Integer perPageNumber){
        Page<Bill> billPage = billService.getAllBillByDateAndPage(billDate,pageNow,perPageNumber);

        //本地数据库无对账单数据，则访问微信
        try {
            if (billPage.getTotalElements() <= 0){
                WxPayResponse response = billService.billDownload(billDate, WxPayEnum.BILL_TYPE_ALL.getValue(),null);
                List<List<String>> billDatas = payService.parseWxData(response.getData());
                int dataSize = billDatas.size();

                List<Bill> bills = new ArrayList<>();
                //第一行为标题、后两行为统计数据，去除
                for (int i=1; i< dataSize-2; i++){
                    List<String> billData = billDatas.get(i);
                    bills.add(new Bill(){{
                                setBillDate(billDate);
                                setTradeTime(billData.get(0));
                                setAppId(billData.get(1));
                                setMchId(billData.get(2));
                                setSubMchId(billData.get(3));
                                setDeviceId(billData.get(4));
                                setTransactionId(billData.get(5));
                                setOutTradeNo(billData.get(6));
                                setOpenId(billData.get(7));
                                setTradeType(billData.get(8));
                                setTradeStatus(billData.get(9));
                                setBank(billData.get(10));
                                setFeeType(billData.get(11));
                                setTotalFee(billData.get(12));
                                setRedpacketFee(billData.get(13));
                                setRefundId(billData.get(14));
                                setOutRefundNo(billData.get(15));
                                setRefundFee(billData.get(16));
                                setRedpacketRefund(billData.get(17));
                                setRefundType(billData.get(18));
                                setRefundStatus(billData.get(19));
                                setBody(billData.get(20));
                                setDataPacket(billData.get(21));
                                setFee(billData.get(22));
                                setRate(billData.get(23));
                            }});
                }
                billService.saveBillList(bills);

                //重新获取
                billPage = billService.getAllBillByDateAndPage(billDate,pageNow,perPageNumber);
            }
        } catch (VFlowException e){
            logger.error(e.getMessage(),e);
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return billPage;
    }

    /**
     * 根据订单号查询微信订单
     * @param transactionId
     * @return
     */
    public QueryOrderResult queryOrderByTransactionId(String transactionId){
        return orderService.queryOrderByTradeId(transactionId);
    }

}
