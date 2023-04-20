package com.ean.cloud.service;

import com.ean.cloud.entities.Payment;

/**
 * @description:TODO
 * @author:Povlean
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
