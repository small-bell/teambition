package com.smallbell.teambition.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
