package com.example.springbootelasticsearch.dao;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import java.util.List;


/**
 * 定义 Elasticsearch 底层接口方法
 **/
public interface ElasticSearchDao {

    /**
     * 添加es数据
     *
     * @param indexRequest
     * @return
     */
    IndexResponse index(IndexRequest indexRequest);

    /**
     * 查询es数据
     *
     * @param searchRequest
     * @return
     */
    SearchResponse search(SearchRequest searchRequest);

    /**
     * 滚动查询es数据
     *
     * @param searchRequest
     * @param scroll
     * @return
     */
    List<SearchHit> searchScroll(SearchRequest searchRequest, Scroll scroll);

    /**
     * 批量新增es数据
     *
     * @param bulkRequest
     * @return
     */
    BulkResponse bulk(BulkRequest bulkRequest);

    /**
     * 批量新增es数据
     *
     * @param bulkRequest
     * @return
     */
    Boolean kafkaBulk(BulkRequest bulkRequest);

    /**
     * 根据条件删除es数据
     *
     * @param deleteByQueryRequest
     * @return
     */
    BulkByScrollResponse deleteByQuery(DeleteByQueryRequest deleteByQueryRequest);

    /**
     * 创建es索引
     *
     * @param createIndexRequest
     * @return
     */
    boolean indexCreate(CreateIndexRequest createIndexRequest);

    /**
     * 增加es字段
     *
     * @param putMappingRequest
     * @return
     */
    boolean indexPutMapping(PutMappingRequest putMappingRequest);

    /**
     * 设置es索引
     *
     * @param updateSettingsRequest
     * @return
     */
    boolean indexPutSettings(UpdateSettingsRequest updateSettingsRequest);

    /**
     * 删除索引
     *
     * @param deleteIndexRequest
     * @return
     */
    boolean indexDelete(DeleteIndexRequest deleteIndexRequest);

    /**
     * 查询es索引
     *
     * @param getIndexRequest
     * @return
     */
    GetIndexResponse indexGet(GetIndexRequest getIndexRequest);

    /**
     * 判断es索引是否存在
     *
     * @param request
     * @return
     */
    boolean indexExists(GetIndexRequest request);
}
