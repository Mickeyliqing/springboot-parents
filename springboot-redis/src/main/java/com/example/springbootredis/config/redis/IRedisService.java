package com.example.springbootredis.config.redis;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Component
public interface IRedisService {

        /**
         * 在字符串后边追加 字符串
         * @param key
         * @param appendValue
         * @return
         */
        <T> ValueOperations<String, T> append(String key, String appendValue);

        /**
         * 缓存基本的对象，Integer、String、实体类等
         * @param key      缓存的健值
         * @param value    缓存的值
         * @return         缓存的对象
         */
        <T> ValueOperations<String, T> setCacheObject(String key, T value);

        /**
         * 缓存基本的对象，Integer、String、实体类等
         * @param key      缓存的健值
         * @param value    缓存的值
         * @param timeout  时间
         * @param timeUnit 时间颗粒度
         * @return         缓存的对象
         */
        <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit);

        /**
         * 缓存基本的对象，Integer、String、实体类等
         * @param key      缓存的健值
         * @param value    缓存的值
         * @param timeout  时间
         * @return         缓存的对象
         */
        <T> ValueOperations<String, T> setCacheObject(String key, T value, Duration timeout);

        /**
         * 获得缓存的基本对象。
         *
         * @param key 缓存键值
         * @return 缓存键值对应的数据
         */
        <T> T getCacheObject(String key);

        /**
         * 设置过期时间
         * @param key           key值
         * @param timeout       时间
         * @param timeUnit      时间单位
         * @return
         */
        Boolean setExpire(String key, Long timeout, TimeUnit timeUnit);

        /**
         * 删除单个对象
         * @param key
         */
        void deleteObject(String key);

        /**
         * 删除集合对象
         * @param collection
         */
        void deleteObject(Collection collection);

        /**
         * 缓存List数据
         *
         * @param key      缓存的键值
         * @param dataList 待缓存的List数据
         * @return         缓存的对象
         */
        <T> ListOperations<String, T> setCacheList(String key, List<T> dataList);

        /**
         * 获得缓存的list对象
         *
         * @param key 缓存的键值
         * @return 缓存键值对应的数据
         */
        <T> List<T> getCacheList(String key);

        /**
         * 缓存Set
         *
         * @param key     缓存键值
         * @param dataSet 缓存的数据
         * @return 缓存数据的对象
         */
        <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet);

        /**
         * 获得缓存的set
         *
         * @param key
         * @return
         */
        <T> Set<T> getCacheSet(String key);

        /**
         * 缓存Map
         *
         * @param key
         * @param dataMap
         * @return
         */
        void hashPutAll(String key, Map<String, Object> dataMap);

        /**
         * 在hash中新增一个值
         * @param key
         * @param field
         * @param value
         */
        void hashPut(String key, String field, Object value);

        /**
         * 当field不存在时put
         * @param key
         * @param field
         * @param value
         */
        void hashPutIfAbsent(String key, String field, Object value);

        /**
         * 获得缓存的Map
         *
         * @param key
         */
        Map<String, Object> hashGetAll(String key);

        /**
         * 获取键值为key的hash中的field字段的值
         * @param key
         * @param field
         */
        Object hashGet(String key, String field);

        /**
         * 获取键值为key的hash表中所有字段
         * @param key
         */
        Set<String> hashKeys(String key);

        /**
         * 获取键值为key的hash表中所有value
         * @param key
         */
        List<Object> hashValues(String key);

        /**
         * 给hash表中指定字段（整形）增加increment
         */
        Long hashIncrement(String key, String field, long increment);

        /**
         * 给hash表中指定字段（Double）增加increment
         */
        Double hashIncrement(String key, String field, double increment);

        /**
         * 判断hashKey是否存在
         * @param key
         * @param hashKey
         * @return 存在返回true，不存在返回false
         */
        Boolean hasKey(String key, String hashKey);

        /**
         * 根据key删除一个或多个字段
         * @param key
         * @param fields
         */
        void hashDelete(String key, String... fields);

        /**
         * 获得缓存的基本对象列表
         *
         * @param pattern 字符串前缀
         * @return 对象列表
         */
        Collection<String> keys(String pattern);

        /**
         * 自增
         * @param key
         * @return     自增后的值
         */
        Long increment(String key);

        /**
         * 自增 num
         * @param key
         * @return   自增后的值
         */
        Long increment(String key, long num);

        /**
         * 返回RedisTemplate
         *
         * @return RedisTemplate
         */
        RedisTemplate getRedisTemplate();

        /**
         * 执行lua脚本，返回执行结果
         * @param redisScript
         * @param key
         * @param value
         * @return
         */
        Object execute(DefaultRedisScript redisScript, String key, Object value);


}
