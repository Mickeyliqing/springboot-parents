package com.example.springbootthread.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootthread.config.ManualTransactionManager;
import com.example.springbootthread.dao.RegionData2021Mapper;
import com.example.springbootthread.model.RegionData2021;
import com.example.springbootthread.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Function;


/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Service
@Slf4j
public class AsyncServiceImpl extends ServiceImpl<RegionData2021Mapper, RegionData2021> implements IAsyncService {

    @Resource
    private RegionData2021Mapper regionData2021Mapper;

    @Resource
    private ManualTransactionManager manualTransactionManager;

    @Resource
    private Executor executor;

    public static volatile boolean IS_OK = true;

    /**
     *  @Async("getAsyncExecutor") 这个配置去掉不使用，也可以使用到多线程，那那样的话就不会使用到自定义的线程池大小
     */
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

    @Async("getAsyncExecutor")
    @Override
    public void saveRegion(List<RegionData2021> list) throws Exception {
        int number = list.size();
        int count = 10000;
        int threadCount = (number/count) + 1;
        List<RegionData2021> listChild;
        CountDownLatch mainMonitor = new CountDownLatch(1);
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
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
                try {
                    regionData2021Mapper.insertBatch(finalListChild);
                    childMonitor.countDown();
                    log.info("线程{}正常执行完成,等待其他线程执行结束,判断是否需要回滚", Thread.currentThread().getName());
                    mainMonitor.await();
                } catch (Exception e) {
                    log.error("线程{}发生了异常,开始进行事务回滚", Thread.currentThread().getName());
                    manualTransactionManager.rollBack();
                }
            });
        }
        try {
            childMonitor.await();
            mainMonitor.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async("getAsyncExecutor")
    @Override
    public void getData() throws InterruptedException {
        /**
         * 这里是开启一个主线程，开启十个子线程，十个子线程分别打印数据
         */
        CountDownLatch mainMonitor = new CountDownLatch(1);
        CountDownLatch childMonitor = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    mainMonitor.await();
                    System.out.println("第" + finalI + "完成任务" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    childMonitor.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        childMonitor.await();
        mainMonitor.countDown();
    }

    @Override
    public void getDataTest() throws InterruptedException {
        System.out.println("完成任务" + Thread.currentThread().getName());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean saveOrUpdateBatchByEntity(Collection<T> entityList, int batchSize, Function<T, Object> queryWrapper) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var7 = null;

        try {
            int i = 0;

            for(Iterator var9 = entityList.iterator(); var9.hasNext(); ++i) {
                T entity = (T) var9.next();
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                if (!StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal))) {
                    MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
                    param.put("et", entity);
                    param.put(Constants.WRAPPER, (T) queryWrapper.apply(entity));
                    batchSqlSession.update(this.sqlStatement(SqlMethod.UPDATE), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(SqlMethod.INSERT_ONE), entity);
                }

                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var20) {
            var7 = var20;
            throw var20;
        } finally {
            if (batchSqlSession != null) {
                if (var7 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    /**
     * 重写单条保存和更新
     * getOne 要换成自己的方法，自带的不会查询 deleted 为 1 的数据
     * @param entity
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateEntity(RegionData2021 entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            QueryWrapper<RegionData2021> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("region_source", entity.getRegionSource()).
                    eq("region_code", entity.getRegionCode()).eq("deleted", 1).or().eq("deleted", 0);
            if ((StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) && Objects.isNull(getOne(queryWrapper))) {
                this.save(entity);
            } else {
                entity.setDeleted(0);
                this.update(entity, queryWrapper);
            }
        }
    }

    /**
     * 重写批量的保存和更新
     * @param entityList
     * @param batchSize
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateBatchEntity(Collection<RegionData2021> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty");
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (RegionData2021 entity : entityList) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                QueryWrapper<RegionData2021> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("region_source", entity.getRegionSource()).
                        eq("region_code", entity.getRegionCode()).eq("deleted", 1).or().eq("deleted", 0);
                if ((StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)))  && Objects.isNull(getOne(queryWrapper))) {
                    batchSqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
                } else {
                    entity.setDeleted(0);
                    this.update(entity, queryWrapper);
                }
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
    }
}

