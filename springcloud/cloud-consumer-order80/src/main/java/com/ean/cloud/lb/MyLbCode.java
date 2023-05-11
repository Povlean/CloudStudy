package com.ean.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:TODO
 * @author:Povlean
 */
@Component
public class MyLbCode implements LoadBalancer{

    // 设置原子数
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // 获取并自增
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("访问次数为next==>" + next);
        return next;
    }

    // 获取服务实例
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
