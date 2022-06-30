package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CommonResult
 * @Description 通用返回结果实体
 * @Author Gray
 * @Date 2022/5/30 10:20
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private T data;
    private String message;
    private Integer code;

    public CommonResult(T data) {
        this(data, "成功", SUCCESS);
    }

    public CommonResult(String message, Integer code) {
        this(null, message, code);
    }

}
