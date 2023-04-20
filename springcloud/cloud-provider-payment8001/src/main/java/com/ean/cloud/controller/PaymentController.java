package com.ean.cloud.controller;
import com.ean.cloud.entities.CommonResult;
import com.ean.cloud.entities.Payment;
import com.ean.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    // 添加支付实体类
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if (res < 1) {
            return new CommonResult(444,"创建失败");
        }
        log.info("create payment-->" + payment.toString());
        return new CommonResult(200,"创建成功",payment);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new CommonResult(444,"查询失败");
        }
        log.info("get payment-->" + payment);
        return new CommonResult(200,"查询成功",payment);
    }
}
