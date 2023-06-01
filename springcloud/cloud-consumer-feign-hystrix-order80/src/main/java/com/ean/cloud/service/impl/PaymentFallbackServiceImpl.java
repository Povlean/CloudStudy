package com.ean.cloud.service.impl;

import com.ean.cloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @description:TODO
 * @author:Povlean
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String PaymentOK(Integer id) {
        return "paymentOK hahaha~";
    }

    @Override
    public String PaymentTimeout(Integer id) {
        return "paymentTimeout wuwuwu~";
    }
}
