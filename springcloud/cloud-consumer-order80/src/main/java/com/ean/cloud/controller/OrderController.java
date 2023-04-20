package com.ean.cloud.controller;

import com.ean.cloud.entities.CommonResult;
import com.ean.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author:Povlean
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult createPayment(Payment payment) {
        // 传入模块的请求接口，达到模块之间的相互调用
        // postForObject() 请求路径，请求参数，返回值类型
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject( PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}