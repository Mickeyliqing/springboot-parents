package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.model.ElasticSearchDocument;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 定义 Elasticsearch 接口方法
 **/
@Component
public interface ElasticSearchService {

    /**
     * 创建 ES 索引
     *
     * @param index      索引
     * @param properties 文档属性集合
     * @return 返回 true，表示创建成功
     */
    boolean createIndex(String index, Map<String, Map<String, Object>> properties) throws IOException;

    /**
     * 判断索引是否存在
     *
     * @param index 索引
     * @return 返回 true，表示存在
     */
    boolean isExistIndex(String index) throws IOException;

    /**
     * 删除索引
     *
     * @param index 索引
     * @return 返回 true，表示删除成功
     */
    boolean deleteIndex(String index) throws IOException;

    /**
     * 保存文档
     * <p>
     * 如果文档存在，则更新文档；如果文档不存在，则保存文档。
     *
     * @param document 文档数据
     */
    void save(String index, ElasticSearchDocument<?> document) throws IOException;

    /**
     * 更新 ES 文档
     *
     * @param index    索引
     * @param document 文档
     */
    void update(String index, ElasticSearchDocument<?> document) throws IOException;

    /**
     * 批量保存文档
     * <p>
     * 如果集合中有些文档已经存在，则更新文档；不存在，则保存文档。
     *
     * @param index        索引
     * @param documentList 文档集合
     * @param <T>          数据类型
     */
    <T> void saveAll(String index, List<ElasticSearchDocument<T>> documentList) throws IOException;

    /**
     * 根据文档 ID 删除文档
     *
     * @param index 索引
     * @param id    文档 ID
     */
    void delete(String index, String id) throws IOException;

    /**
     * 根据查询条件删除文档
     *
     * @param index        索引
     * @param queryBuilder 查询条件构建器
     */
    void deleteByQuery(String index, QueryBuilder queryBuilder) throws IOException;

    /**
     * 根据文档 ID 批量删除文档
     *
     * @param index  索引
     * @param idList 文档 ID 集合
     */
    void deleteAll(String index, List<String> idList) throws IOException;

    /**
     * 根据索引和文档 ID 获取数据
     *
     * @param index 索引
     * @param id    文档 ID
     * @param <T>   数据类型
     * @return T  返回 T 类型的数据
     */
    <T> T get(String index, String id, Class<T> resultType) throws IOException;

    /**
     * 条件查询
     *
     * @param index         索引
     * @param sourceBuilder 条件查询构建器
     * @param <T>           数据类型
     * @return T 类型的集合
     */
    <T> List<T> searchByQuery(String index, SearchSourceBuilder sourceBuilder, Class<T> resultType) throws IOException;

    /**
     * 获取聚合数据
     *
     * @param searchSourceBuilder 条件查询构建器
     * @param indices             索引
     * @return 返回聚合集合
     */
    Aggregations searchAgg(SearchSourceBuilder searchSourceBuilder, String... indices);
}
