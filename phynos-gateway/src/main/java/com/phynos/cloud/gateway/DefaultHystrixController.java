package com.phynos.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认降级处理
 * @author by lupc
 * @date 2020-09-01 9:42
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultfallback")
    public Map<String, String> defaultfallback() {
        System.out.println("降级操作...");
        Map<String, String> map = new HashMap<>();
        map.put("resultCode", "fail");
        map.put("resultMessage", "服务异常");
        map.put("resultObj", "null");
        return map;
    }

}
