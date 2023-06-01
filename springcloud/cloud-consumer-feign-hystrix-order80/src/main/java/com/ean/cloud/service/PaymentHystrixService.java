package com.ean.cloud.service;

import com.ean.cloud.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentOK (@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentTimeout (@PathVariable("id") Integer id);

}
