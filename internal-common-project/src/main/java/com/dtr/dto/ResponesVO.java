package com.dtr.dto;

import java.util.LinkedHashMap;

/**
 * 公共的web层返回类
 * @since 2020-11-07
 * @author Dertraum
 */
public class ResponesVO extends LinkedHashMap<String,Object> {
    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.get("data");
    }

    public void setData(Object data) {
        this.put("data",data);
    }

    public void addData(String key,Object data){
        this.put(key,data);
    }
}
