package com.ean.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:TODO
 * @author:Povlean
 */
@RestController
@Slf4j
public class SentinelController {

    @Value("${server.port}")
    String serverPort;

    @GetMapping("/test1")
    public String test1() {
        return "test1===>" + serverPort;
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2===>" + serverPort;
    }
}
