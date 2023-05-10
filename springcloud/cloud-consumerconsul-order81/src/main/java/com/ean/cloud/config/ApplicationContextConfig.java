package com.ean.cloud.config;

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

    /**
    * @description:远程调用
    * @author:Povlean
    * @date:2023/5/10 14:36
    * @return RestTemplate
    */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
