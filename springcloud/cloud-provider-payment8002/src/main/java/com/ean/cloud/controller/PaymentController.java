package com.ean.cloud.controller;
import com.ean.cloud.entities.CommonResult;
import com.ean.cloud.entities.Payment;
import com.ean.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

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
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    // 添加支付实体类
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if (res < 1) {
            return new CommonResult(444,"创建失败");
        }
        log.info("create payment-->" + payment.toString());
        return new CommonResult(200,"创建成功,serverPort:" + serverPort, payment);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new CommonResult(444,"查询失败");
        }
        log.info("get payment-->" + payment);
        return new CommonResult(200,"查询成功,serverPort:" + serverPort, payment);
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for(String element : services) {
            log.info("*****element:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri()+"\t");
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
