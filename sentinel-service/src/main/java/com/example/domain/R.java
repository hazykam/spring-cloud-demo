package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName R
 * @Description 通用返回结果实体
 * @Author Gray
 * @Date 2022/5/30 10:20
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private Object data;
    private String message;
    private Integer code;

    public R(Object data) {
        this(data, "成功", SUCCESS);
    }

    public R(String message, Integer code) {
        this(null, message, code);
    }

}
