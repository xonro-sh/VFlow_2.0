package com.xonro.vflow.wxpay.helper;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wxpay.bean.Coupon;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信支付接口调用帮助类
 * @author louie
 * @date created in 2018-3-7 17:27
 */
public class ServiceRequestHelper {
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
                            setCouponType(responseData.get("coupon_type_$"+couponIndex));
                            setCouponId(responseData.get("coupon_id_$"+couponIndex));
                            setCouponFee(responseData.get("coupon_fee_$"+couponIndex));
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
                    setCouponType(response.get("coupon_type_$"+index));
                    setCouponId(response.get("coupon_refund_id_$"+index));
                    setCouponFee(response.get("coupon_refund_fee_$"+index));
                }});
            }
        }
        return coupons;
    }

}
