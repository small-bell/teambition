package com.smallbell.teambition.qa.client;

import com.smallbell.teambition.common.entity.Result;
import com.smallbell.teambition.qa.client.impl.BaseClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "teambition-base", fallback = BaseClientImpl.class)
public interface BaseClient {
    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
