package com.example.springbootredis.controller;

import com.example.springbootredis.config.redis.IRedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class RedisController {

    @Resource
    private IRedisService redisService;

    @PostMapping("getRedis/01")
    public String getRedis_01(String message) {

        /**
         * 获取这个参数为 key 的缓存数据
         * 这里实际的操作的时候，需要判断缓存数据是否存在，然后根据不同的情况做不同的业务操作
         */
        Object cacheObject = redisService.getCacheObject(message);
        if (ObjectUtils.isNotEmpty(cacheObject)) {

        } else {

        }

        /**
         * 最后业务执行完，需要在缓存里设置数据，第一个参数为对应的 key 值，第二个参数为对应的值
         */
        redisService.setCacheObject(message, message);

        return "success";
    }

}
