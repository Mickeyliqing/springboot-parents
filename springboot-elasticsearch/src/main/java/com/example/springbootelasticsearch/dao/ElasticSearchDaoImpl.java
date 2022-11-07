package com.example.springbootelasticsearch.dao;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;

import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 Elasticsearch 底层接口的方法实现
 **/
@Repository
public class ElasticSearchDaoImpl implements ElasticSearchDao {

    @Resource
    private RestHighLevelClient client;

    @Override
    public IndexResponse index(IndexRequest indexRequest) {
        try {
            IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
            return index;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SearchResponse search(SearchRequest searchRequest) {
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SearchHit> searchScroll(SearchRequest searchRequest, Scroll scroll) {
        List<SearchHit> hits = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            hits.add(searchHit);
        }
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            scrollId = searchResponse == null ? "" : searchResponse.getScrollId();
            searchHits = searchResponse == null ? new SearchHit[0] : searchResponse.getHits().getHits();
            for (SearchHit searchHit : searchHits) {
                hits.add(searchHit);
            }
        }
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = null;
        try {
            clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean succeeded = clearScrollResponse == null ? false : clearScrollResponse.isSucceeded();
        return hits;
    }

    @Override
    public BulkResponse bulk(BulkRequest bulkRequest) {
        try {
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            return bulk;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean kafkaBulk(BulkRequest bulkRequest) {
        try {
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BulkByScrollResponse deleteByQuery(DeleteByQueryRequest deleteByQueryRequest) {
        try {
            BulkByScrollResponse bulkByScrollResponse =
                    client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
            return bulkByScrollResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean indexCreate(CreateIndexRequest createIndexRequest) {
        try {
            CreateIndexResponse createIndexResponse =
                    client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean indexPutMapping(PutMappingRequest putMappingRequest) {
        try {
            AcknowledgedResponse acknowledgedResponse =
                    client.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean indexPutSettings(UpdateSettingsRequest updateSettingsRequest) {
        try {
            AcknowledgedResponse acknowledgedResponse =
                    client.indices().putSettings(updateSettingsRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean indexDelete(DeleteIndexRequest deleteIndexRequest) {
        try {
            AcknowledgedResponse delete = this.client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return delete.isAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public GetIndexResponse indexGet(GetIndexRequest getIndexRequest) {
        try {
            GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
            return getIndexResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean indexExists(GetIndexRequest request) {
        try {
            boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
