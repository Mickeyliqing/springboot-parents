package com.example.springbootthread.service;
import com.example.springbootthread.model.RegionData2021;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
public interface IAsyncService {

    void saveRegionData(List<RegionData2021> list) throws Exception;

    void saveRegion(List<RegionData2021> list) throws Exception;

    void getData() throws InterruptedException;

    @Async("getAsyncExecutor")
    void getDataTest() throws InterruptedException;

    boolean saveOrUpdateBatchByEntity(Collection<T> entityList, int batchSize, Function<T, Object> queryWrapper);

    void saveOrUpdateEntity(RegionData2021 entity);

    void saveOrUpdateBatchEntity(Collection<RegionData2021> entityList, int batchSize);

}
