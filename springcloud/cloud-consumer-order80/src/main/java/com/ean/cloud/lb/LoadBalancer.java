package com.ean.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    // 获取服务实例
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
