package com.smallbell.teambition.common.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private long total;

    private List<T> rows;

}
