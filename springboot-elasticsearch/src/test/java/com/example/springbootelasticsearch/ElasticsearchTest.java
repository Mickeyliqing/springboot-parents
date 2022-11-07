package com.example.springbootelasticsearch;
import com.example.springbootelasticsearch.dao.ElasticSearchDao;

import com.example.springbootelasticsearch.service.IndexHandleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;



@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Resource
    private IndexHandleService indexHandleService;

    @Test
    public void existIndex() {
        boolean test_02 = indexHandleService.existIndex("test_02");
        System.out.println(test_02);
    }
}
