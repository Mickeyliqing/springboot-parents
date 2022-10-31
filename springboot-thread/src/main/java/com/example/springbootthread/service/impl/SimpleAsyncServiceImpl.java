package com.example.springbootthread.service.impl;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootthread.dao.RegionData2021Mapper;
import com.example.springbootthread.model.RegionData2021;
import com.example.springbootthread.service.ISimpleAsyncService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 这里提供了一种更简单的使用方式，直接通过类 Executor 进行调用
 */
@Service
@Slf4j
public class SimpleAsyncServiceImpl extends ServiceImpl<RegionData2021Mapper, RegionData2021> implements ISimpleAsyncService {

    /**
     * 引入 Executor，并加上多线程的注解，这样对应的方法就不需要添加
     */
    @Resource
    @Qualifier("getAsyncExecutor")
    private Executor executor;

    @Resource
    private RegionData2021Mapper regionData2021Mapper;

    @Override
    public void saveRegionData(List<RegionData2021> list) throws Exception {
        if (ObjectUtils.isNotEmpty(list)) {
            /**
             * 方式一：list 内的数据比较大，调用 Lists.partition 做一个切分，然后调用多线程
             */
            List<List<RegionData2021>> partition = Lists.partition(list, 1000);
            for (List<RegionData2021> regionData2021List : partition) {
                executor.execute(() -> {
                    this.saveBatch(regionData2021List);
                });
            }

            /**
             * 方式二：直接调用多线程
             */
            executor.execute(() -> {
                this.saveBatch(list);
            });
        }
    }

    /**
     * 这里自定义批量方法，方便控制对应的事务
     * @param list
     */
    @Transactional
    public void saveBatch(List<RegionData2021> list) {
        try {
            regionData2021Mapper.insertBatch(list);
            Thread.sleep(1000);
            log.info("插入数据线程{}正常执行完成", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在对应的 Controller 层直接调用 insertBatch 也会开启多线程
     * 那是因为在接口 ISimpleAsyncService 对应的方法上面已经添加对应的 @Async("getAsyncExecutor") 注解
     * @param list
     */
    @Override
    @Transactional
    public void insertBatch(List<RegionData2021> list) {
        try {
            regionData2021Mapper.insertBatch(list);
            Thread.sleep(1000);
            log.info("插入数据线程{}正常执行完成", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
