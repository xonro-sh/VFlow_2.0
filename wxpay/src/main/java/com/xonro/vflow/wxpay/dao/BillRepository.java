package com.xonro.vflow.wxpay.dao;

import com.xonro.vflow.wxpay.bean.bill.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 微信账单数据仓储
 * @author louie
 * @date created in 2018-3-14 15:09
 */
@Repository
public interface BillRepository extends JpaRepository<Bill,Long>{

    /**
     * 分页查询订单数据
     * @param billDate
     * @param pageRequest
     * @return
     */
    public Page<Bill> findBillByBillDate(String billDate, Pageable pageRequest);

    
}
