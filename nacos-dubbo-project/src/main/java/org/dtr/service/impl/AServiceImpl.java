package org.dtr.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.dtr.service.AService;

/**
 * @author liudong
 * 2023/6/20 17:40
 * @version 1.0
 */
@DubboService
public class AServiceImpl implements AService {
    @Override
    public String demoA() {
        return "AAA";
    }
}
