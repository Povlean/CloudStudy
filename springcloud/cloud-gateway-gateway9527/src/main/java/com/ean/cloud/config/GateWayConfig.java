package com.ean.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:TODO
 * @author:Povlean
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_povlean",r -> r.path("/povlean").uri("http://github.com/Povlean")).build();

        return routes.build();
    }
}
