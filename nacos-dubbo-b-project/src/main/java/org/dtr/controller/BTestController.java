package org.dtr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liudong
 * 2023/6/20 12:04
 * @version 1.0
 */
@RestController
@RequestMapping("/b/test")
public class BTestController {

    @GetMapping(value = "/get")
    public Object get() {
        return null;
    }
}
