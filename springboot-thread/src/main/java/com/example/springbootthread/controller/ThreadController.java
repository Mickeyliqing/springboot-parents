package com.example.springbootthread.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootthread.model.RegionData2021;
import com.example.springbootthread.service.IAsyncService;
import com.example.springbootthread.service.ISimpleAsyncService;
import com.example.springbootthread.service.impl.ThreadService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class ThreadController {

    @Resource
    private ThreadService threadService;

    @Resource
    private IAsyncService asyncService;

    @Resource
    private ISimpleAsyncService simpleAsyncService;

    @PostMapping("/saveList")
    public boolean importRegionCode(@RequestParam("files") MultipartFile files) throws Exception {
        List<RegionData2021> list =
                EasyExcel.read(files.getInputStream()).sheet().head(RegionData2021.class).doReadSync();
        asyncService.saveRegionData(list);
        return true;

    }

    @PostMapping("/save")
    public boolean saveRegionData(@RequestBody RegionData2021 regionData2021) {
        asyncService.saveOrUpdateEntity(regionData2021);
        return true;
    }

    @PostMapping("/update")
    public boolean updateRegionData(@RequestBody RegionData2021 regionData2021) {
        List<RegionData2021> list = new ArrayList<>();
        list.add(regionData2021);
        asyncService.saveOrUpdateBatchEntity(list, 1000);
        return true;
    }

    @PostMapping("/get")
    public boolean getRegionData() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            asyncService.getDataTest();
        }
        return true;
    }

    /**
     * 异步方法无返回值，在 controller
     * @return
     */
    @PostMapping("thread/01")
    public String getThread_01() {
       /* int counts = 10;
        for (int i = 0; i < counts; i++ ) {
            threadService.getThread_01("index = " + i);
        }
        return "success";*/

        // 返回的是 null
        String thread_01 = threadService.getThread_01("index = " + 1);
        return thread_01;
    }

    /**
     * 异步方法有返回值，在 controller
     * @return
     */
    @PostMapping("thread/02")
    public String getThread_02() throws ExecutionException, InterruptedException {
        CompletableFuture thread_02 = threadService.getThread_02("getThread_02");
        CompletableFuture thread_03 = threadService.getThread_03("getThread_03");
        CompletableFuture.allOf(thread_02, thread_03);
        return (String) thread_02.get() + "," + thread_03.get();
    }

    @PostMapping("/saveBatch")
    public boolean saveBatch(@RequestParam("files") MultipartFile files) throws Exception {
        List<RegionData2021> list =
                EasyExcel.read(files.getInputStream()).sheet().head(RegionData2021.class).doReadSync();
        simpleAsyncService.saveRegionData(list);
        return true;

    }

    @PostMapping("/insertBatch")
    public boolean insertBatch(@RequestParam("files") MultipartFile files) throws Exception {
        List<RegionData2021> list =
                EasyExcel.read(files.getInputStream()).sheet().head(RegionData2021.class).doReadSync();
        simpleAsyncService.insertBatch(list);
        return true;

    }
}
