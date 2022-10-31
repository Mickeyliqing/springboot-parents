package com.example.springbootthread.service;

import com.example.springbootthread.model.RegionData2021;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ISimpleAsyncService {
    void saveRegionData(List<RegionData2021> list) throws Exception;

    /**
     * 在对应的 Controller 层直接调用 insertBatch 也会开启多线程
     * 那是因为在接口 ISimpleAsyncService 对应的方法上面已经添加对应的 @Async("getAsyncExecutor") 注解
     * @param list
     */
    @Async("getAsyncExecutor")
    void insertBatch(List<RegionData2021> list);
}
