package com.xonro.vflow.wxpay.helper;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wxpay.bean.Coupon;
import com.xonro.vflow.wxpay.bean.refund.QueryRefundResult;
import com.xonro.vflow.wxpay.bean.refund.RefundDetail;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信支付接口调用帮助类
 * @author louie
 * @date created in 2018-3-7 17:27
 */
public class ServiceRequestHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 校验微信支付接口通信结果
     * @param resultParams 微信支付接口响应参数
     * @return 通信成功返回true，否则返回false
     */
    public boolean validateRequestResult(Map<String,String> resultParams) throws VFlowException {
        if(!resultParams.isEmpty()){
            String returnCode = resultParams.get("return_code");
            if (StringUtils.isNotEmpty(returnCode) && returnCode.equals(WxPayEnum.RETURN_CODE_OK.getValue())){
                return true;
            }else {
                throw new VFlowException(returnCode,resultParams.get("return_msg"));
            }
        }else {
            throw new VFlowException("FAIL","request wxPay service error,response is empty");
        }
    }

    /**
     * 从响应数据中解析订单优惠券
     * @param responseData
     * @return
     */
    public List<Coupon> parseOrderCoupon(Map<String,String> responseData){
        List<Coupon> coupons = new ArrayList<>();
        String couponCount = responseData.get("coupon_count");
        if (StringUtils.isNotEmpty(couponCount)){
            for (Integer i = 0; i < Integer.valueOf(couponCount); i++) {
                int couponIndex = i;
                coupons.add(
                        new Coupon(){{
                            setCouponType(responseData.get("coupon_type_"+couponIndex));
                            setCouponId(responseData.get("coupon_id_"+couponIndex));
                            setCouponFee(responseData.get("coupon_fee_"+couponIndex));
                        }}
                );
            }
        }
        return coupons;
    }

    /**
     * 解析退款代金券
     * @param response
     * @return
     */
    public List<Coupon> parseRefundCoupon(Map<String,String> response){
        List<Coupon> coupons = new ArrayList<>();
        String couponCount = response.get("coupon_refund_count");
        if (StringUtils.isNotEmpty(couponCount)){
            for (Integer i = 0; i < Integer.valueOf(couponCount); i++) {
                int index = i;
                coupons.add(new Coupon(){{
                    setCouponType(response.get("coupon_type_"+index));
                    setCouponId(response.get("coupon_refund_id_"+index));
                    setCouponFee(response.get("coupon_refund_fee_"+index));
                }});
            }
        }
        return coupons;
    }

    /**
     * 解析退款单退款明细
     * @param response
     * @return
     */
    public List<RefundDetail> parseRefundDetail(Map<String,String> response){
        List<RefundDetail> refundDetails = new ArrayList<>();

        //退款次数
        String refundCount = response.get("refund_count");
        if (StringUtils.isNotEmpty(refundCount)){
            for (Integer i = 0; i < Integer.valueOf(refundCount); i++) {

                //根据下标获取每次退款明细
                int refundIndex = i;
                refundDetails.add(new RefundDetail(){{
                    setOutRefundNo(response.get("out_refund_no_"+refundIndex));
                    setRefundId(response.get("refund_id_"+refundIndex));
                    setRefundChannel(response.get("refund_channel_"+refundIndex));
                    setRefundFee(response.get("refund_fee_"+refundIndex)==null? 0:Integer.valueOf(response.get("refund_fee_"+refundIndex)));
                    setSettlementRefundFee(response.get("settlement_refund_fee_"+refundIndex)==null? 0:Integer.valueOf(response.get("settlement_refund_fee_"+refundIndex)));
                    setCouponRefundFee(response.get("coupon_refund_fee_"+refundIndex)==null? 0:Integer.valueOf(response.get("coupon_refund_fee_"+refundIndex)));

                    Integer couponRefundCount = response.get("coupon_refund_count_"+refundIndex)==null? 0:Integer.valueOf(response.get("coupon_refund_count_"+refundIndex));
                    setCouponRefundCount(couponRefundCount);
                    setRefundStatus(response.get("refund_status_"+refundIndex));
                    setRefundAccount(response.get("refund_account_"+refundIndex));
                    setRefundRecvAccout(response.get("refund_recv_accout_"+refundIndex));
                    setRefundSuccessTime(response.get("refund_success_time_"+refundIndex));
                    setCoupons(parseRefundDetailCoupon(refundIndex,couponRefundCount,response));
                }});
            }
        }
        return refundDetails;
    }

    /**
     * 执行退款单查询
     * @param wxPay
     * @param params
     * @return
     */
    public QueryRefundResult queryRefund(WXPay wxPay,Map<String, String> params) throws Exception {
        try {
            Map<String,String> result = wxPay.refundQuery(params);
            if (validateRequestResult(result)){
                QueryRefundResult queryRefundResult = JSON.parseObject(JSON.toJSONString(result),QueryRefundResult.class);
                queryRefundResult.setRefundDetails(parseRefundDetail(result));
                return queryRefundResult;
            }
        } catch (VFlowException e){
            logger.error(e.getMessage(),e);
            throw e;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 拉取订单评价数据
     * @param wxPayConfig
     * @param params
     * @param isUseSandbox
     * @return
     * @throws Exception
     */
    public Map<String,String> batchQueryComment(WXPayConfig wxPayConfig,Map<String,String> params,boolean isUseSandbox) throws Exception {
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5);

        String urlSuffix ;
        if (isUseSandbox){
            urlSuffix = WxPayEnum.URL_QUERYCOMMENT_SANDBOX_SUFFIX.getValue();
        }else {
            urlSuffix = WxPayEnum.URL_QUERYCOMMENT_SUFFIX.getValue();
        }

        try {
            String responseXml = wxPay.requestWithCert(
                    urlSuffix,
                    wxPay.fillRequestData(params),
                    wxPayConfig.getHttpConnectTimeoutMs(),wxPayConfig.getHttpReadTimeoutMs()
            );
            return WXPayUtil.xmlToMap(responseXml);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 获取退款明细中代金券明细
     * @param refundIndex
     * @param couponCount
     * @param response
     * @return
     */
    private List<Coupon> parseRefundDetailCoupon(Integer refundIndex,Integer couponCount,Map<String,String> response){
        List<Coupon> coupons = new ArrayList<>();
        for (Integer i = 0; i < couponCount; i++) {
            int couponIndex = i;
            coupons.add(new Coupon(){{
                setCouponType(response.get("coupon_type_"+refundIndex+"_"+couponIndex));
                setCouponId(response.get("coupon_refund_id_"+refundIndex+"_"+couponIndex));
                setCouponFee(response.get("coupon_refund_fee_"+refundIndex+"_"+couponIndex));
            }});
        }
        return coupons;
    }
}
