package com.example.springbootelasticsearch.service;

import org.elasticsearch.client.indices.GetIndexResponse;

import java.util.Map;

/**
 * 定义 Elasticsearch 业务层接口方法
 **/
public interface IndexHandleService {

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    boolean createIndex(String index, String source);

    /**
     * 添加字段
     *
     * @param index
     * @return
     */
    boolean addMapping(String index, Map<String, Object> mappings);

    /**
     * 添加setting
     *
     * @param index
     * @param settings
     * @return
     */
    boolean addSetting(String index, Map<String, Object> settings);

    /**
     * 删
     */

    /**
     * 删除索引
     *
     * @param index
     *            索引名
     * @return
     */
    boolean deleteIndex(String index);

    /**
     * 查
     */

    /**
     * 获取索引
     *
     * @param index
     * @return
     */
    GetIndexResponse getIndex(String index);

    /**
     * 判断索引是否存在
     *
     * @param index
     *            索引名
     * @return
     */
    boolean existIndex(String index);

}
