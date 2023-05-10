package com.ean.cloud.dao;

import com.ean.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {
    // 按照id修改支付数据
    int updateById(@Param("id") Long id);

    // 按id删除
    int deleteById(@Param("id") Long id);

    // 按照id获取支付数据
    Payment getPaymentById(@Param("id") Long id);

    // 获取支付的所有数据
    List<Payment> getPayments();

    // 创建支付实体
    int create(Payment payment);
}
