package com.example.springbootelasticsearch.config;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHost;
import org.apache.http.util.Asserts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义 Elasticsearch 的配置
 **/
@Configuration
public class RestHighLevelClientConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(this.createHttpHost()));
    }

    /**
     * 创建 HttpHost 对象
     * @return 返回 HttpHost 对象
     */
    private HttpHost createHttpHost() {
        Asserts.check(ObjectUtils.isEmpty(uris), "ElasticSearch cluster ip address cannot empty");
        String url = uris.split(",")[0].trim();
        return new HttpHost(url.split(":")[0], Integer.parseInt(url.split(":")[1]));
    }
}
