package com.example.springbootelasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.springbootelasticsearch.dao.ElasticSearchDao;
import com.example.springbootelasticsearch.service.IndexHandleService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class IndexHandleServiceImpl implements IndexHandleService {

    @Resource
    private ElasticSearchDao elasticSearchDao;

    @Override
    public boolean createIndex(String index, String source) {
        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
        indexRequest.source(source, XContentType.JSON);
        return elasticSearchDao.indexCreate(indexRequest);
    }

    @Override
    public boolean addMapping(String index, Map<String, Object> mappings) {
        PutMappingRequest putMappingRequest = new PutMappingRequest(index);
        putMappingRequest.source(JSON.toJSONString(mappings), XContentType.JSON);
        return elasticSearchDao.indexPutMapping(putMappingRequest);
    }

    @Override
    public boolean addSetting(String index, Map<String, Object> settings) {
        UpdateSettingsRequest updateSettingsRequest = new UpdateSettingsRequest(index);
        updateSettingsRequest.settings(settings);
        return elasticSearchDao.indexPutSettings(updateSettingsRequest);
    }

    @Override
    public boolean deleteIndex(String index) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        return elasticSearchDao.indexDelete(deleteIndexRequest);
    }

    @Override
    public GetIndexResponse getIndex(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        return elasticSearchDao.indexGet(request);
    }

    @Override
    public boolean existIndex(String index) {
        GetIndexRequest indexRequest = new GetIndexRequest(index);
        return elasticSearchDao.indexExists(indexRequest);
    }
}
