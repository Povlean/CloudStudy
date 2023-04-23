package com.ean.cloud.entities;

import lombok.Data;

/**
 * @description:TODO
 * @author:Povlean
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String msg,T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public CommonResult(Integer code,String msg) {
        this(code,msg,null);
    }

    public CommonResult() {

    }
}
