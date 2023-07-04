package com.ean.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description:TODO
 * @author:Povlean
 */
@Configuration
public class ApplicationContextConfig {

    // 自定义第三方依赖的 restTemplate 实例
    // @LoadBalanced 注解用于声明负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
