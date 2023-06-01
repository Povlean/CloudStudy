package com.ean.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @description:TODO
 * @author:Povlean
 */
@Service
public class PaymentService {

    public String paymentInfo_OK (Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_OK,id: " + id + "\t" + "哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout (Integer id) {
        int timeCount = 3;
        try {
            TimeUnit.SECONDS.sleep(timeCount);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_Timeout,id: " + id + "\t" + "哈哈";
    }

    public String paymentInfo_TimeoutHandler (Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_TimeoutHandler,id: " + id + "\t" + "呜呜";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 打开熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求数阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 滑动时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 错误百分率阈值
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0) {
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试" + "id---->" + id;
    }
}
