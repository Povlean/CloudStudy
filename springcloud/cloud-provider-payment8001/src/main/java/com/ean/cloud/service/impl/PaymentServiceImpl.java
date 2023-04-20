package com.ean.cloud.service.impl;

import com.ean.cloud.dao.PaymentDao;
import com.ean.cloud.entities.CommonResult;
import com.ean.cloud.entities.Payment;
import com.ean.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author:Povlean
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        if (payment == null) {
            return -1;
        }
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        if (id == null && id < 1) {
            return null;
        }
        return paymentDao.getPaymentById(id);
    }

}
