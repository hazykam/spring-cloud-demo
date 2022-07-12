package com.example.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.domain.R;

/**
 * @ClassName CustomBlockHandler
 * @Description TODO
 * @Author Gray
 * @Date 2022/7/8 17:53
 * @Version 1.0
 **/
public class CustomBlockHandler {

    public static R handleException(BlockException exception) {
        return new R("自定义限流信息", 200);
    }

}
