package com.dtr.base.dto;

/**
 * 2021/2/20
 *
 * @author DerTraum
 * @since 1.0.0
 */
public enum BaseExceptionState {
    COMMON_ERROR("10002","错误"),
    NET_ERROR("10003","网络错误");

    private String code;
    private String msg;

    BaseExceptionState(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
