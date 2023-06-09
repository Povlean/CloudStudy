package com.ean.controller;

import com.ean.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:TODO
 * @author:Povlean
 */
@RequestMapping("/rest")
@RestController
@Slf4j
public class RestTemplateController {

    @Resource
    private RestService restService;

}
