package com.ean.cloud.controller;

import com.ean.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentOK (@PathVariable("id") Integer id) {
        String res = paymentService.paymentInfo_OK(id);
        log.info("res=====>" + res);
        return res;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String PaymentTimeout (@PathVariable("id") Integer id) {
        String res = paymentService.paymentInfo_Timeout(id);
        log.info("res=====>" + res);
        return res;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String res = paymentService.paymentCircuitBreaker(id);
        log.info("result-->" + res);
        return res;
    }

}
