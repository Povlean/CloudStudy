package com.ean.cloud.controller;

import com.ean.cloud.entities.CommonResult;
import com.ean.cloud.entities.Payment;
import com.ean.cloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @description:TODO
 * @author:Povlean
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

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

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Integer id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult(444,"获取信息失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        // 通过微服务名称获取服务实例数量
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances.size() <= 0 || CollectionUtils.isEmpty(instances)) {
            return null;
        }
        // 获取正在使用的服务实例的uri
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        // 远程调用
        return restTemplate.getForObject(uri + "/payment/lb",String.class);
    }

}
