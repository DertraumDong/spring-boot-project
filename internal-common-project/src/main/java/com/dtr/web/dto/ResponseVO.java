package com.dtr.web.dto;

import java.util.LinkedHashMap;

/**
 * 公共的web层返回类
 * @since 2020-11-07
 * @author Dertraum
 */
public class ResponseVO extends LinkedHashMap<String,Object> {

    public void setCode(String code) {
        this.put("code",code);
    }

    public void setMsg(String msg) {
        this.put("msg",msg);
    }

    public void setData(Object data) {
        this.put("data",data);
    }

    public void addData(String key,Object data){
        this.put(key,data);
    }

    public ResponseVO() {
        this.put("code","10001");
        this.put("msg","请求成功");
    }
}
