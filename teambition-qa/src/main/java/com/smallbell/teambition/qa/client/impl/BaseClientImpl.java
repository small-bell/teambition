package com.smallbell.teambition.qa.client.impl;

import com.smallbell.teambition.common.entity.Result;
import com.smallbell.teambition.common.entity.StatusCode;
import com.smallbell.teambition.qa.client.BaseClient;

import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"远程服务出现异常");
    }
}
