package com.ean.cloud.controller;

import com.ean.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author:Povlean
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentOK (@PathVariable("id") Integer id) {
        String res = paymentHystrixService.PaymentOK(id);
        log.info("res====>" + res);
        return res;
    }

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String PaymentTimeout (@PathVariable("id") Integer id) {
        int age = 10 / 0;
        String res = paymentHystrixService.PaymentTimeout(id);
        log.info("res====>" + res);
        return res;
    }

    public String paymentTimeOutFallbackMethod (@PathVariable("id") Integer id) {
        return "提供者80服务熔断";
    }

    public String payment_Global_FallbackMethod (@PathVariable("id") Integer id) {
        return "全局 FallbackMethod--> 80 服务熔断";
    }

}
