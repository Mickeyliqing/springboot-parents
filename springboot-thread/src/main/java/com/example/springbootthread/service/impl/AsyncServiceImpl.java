package com.example.springbootthread.service.impl;
import com.example.springbootthread.config.ManualTransactionManager;
import com.example.springbootthread.dao.RegionData2021Mapper;
import com.example.springbootthread.model.RegionData2021;
import com.example.springbootthread.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {

    @Resource
    private RegionData2021Mapper regionData2021Mapper;

    @Resource
    private ManualTransactionManager manualTransactionManager;

    public static volatile boolean IS_OK = true;

    @Async("getAsyncExecutor")
    @Override
    public void saveRegionData(List<RegionData2021> list) throws Exception {
        /**
         * 先拿到这个 list,然后判断大小
         * 在定义每个线程处理的数据量的大小
         * 在定义存放每个线程执行数据的 list
         */
        int number = list.size();
        int count = 7000;
        int threadCount = (number/count) + 1;
        List<RegionData2021> listChild;

        /**
         * 定义线程开始队列和阻塞队列
         * 在定义主线程收集子线程的执行结果
         * 开启线程
         */
        CountDownLatch mainMonitor = new CountDownLatch(1);
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
        List<Boolean> childResponse = new ArrayList<Boolean>();
        ExecutorService executor = Executors.newCachedThreadPool();

        /**
         * 循环创建线程
         * 先封装每个线程要执行的 list
         */
        for (int i = 0; i < threadCount; i++) {
            int start  = (i * count);
            int end;
            if (i+1 == threadCount) {
                end = list.size();
            } else {
                end = (i +1) * count;
            }
            listChild = list.subList(start, end);
            List<RegionData2021> finalListChild = listChild;

            executor.execute(() -> {
                /**
                 * 手动开启事务
                 */
                TransactionStatus transactionStatus = manualTransactionManager.begin();
                try {
                    regionData2021Mapper.insertBatch(finalListChild);
                    childResponse.add(Boolean.TRUE);
                    childMonitor.countDown();
                    log.info("线程{}正常执行完成,等待其他线程执行结束,判断是否需要回滚", Thread.currentThread().getName());
                    mainMonitor.await();
                    if (IS_OK) {
                        log.info("所有线程都正常完成,线程{}事务提交", Thread.currentThread().getName());
                        manualTransactionManager.commit(transactionStatus);
                    } else {
                        log.info("有线程出现异常,线程{}事务回滚", Thread.currentThread().getName());
                        manualTransactionManager.rollBack();
                    }
                } catch (Exception e) {
                    childResponse.add(Boolean.FALSE);
                    childMonitor.countDown();
                    log.error("线程{}发生了异常,开始进行事务回滚", Thread.currentThread().getName());
                    manualTransactionManager.rollBack();
                }
            });
        }
        try {
            //主线程等待所有子线程执行完成
            childMonitor.await();
            for (Boolean resp : childResponse) {
                if (!resp) {
                    //如果有一个子线程执行失败了，则改变mainResult，让所有子线程回滚
                    log.info("{}:IS_OK的值被修改为false", Thread.currentThread().getName());
                    IS_OK = false;
                    break;
                }
            }
            //主线程获取结果成功，让子线程开始根据主线程的结果执行（提交或回滚）
            mainMonitor.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

