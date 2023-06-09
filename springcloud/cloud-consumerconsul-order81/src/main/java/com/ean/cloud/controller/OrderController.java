package com.ean.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String res = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return res;
    }

}
