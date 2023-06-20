package org.dtr.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liudong
 * 2023/6/20 12:04
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class ATestController {
    @NacosValue(value = "${useLocalCache}", autoRefreshed = true)
    private String useLocalCache;

    @GetMapping(value = "/get")
    public Object get() {
        return useLocalCache;
    }
}
