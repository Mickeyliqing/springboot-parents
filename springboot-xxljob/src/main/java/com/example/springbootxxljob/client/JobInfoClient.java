package com.example.springbootxxljob.client;
import com.example.springbootxxljob.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@FeignClient(name = "${config.job.url}")
@Component
@RequestMapping("/xxlJobAdmin/v1")
public interface JobInfoClient {

    /**
     * CRON 数据校验
     *
     * @param scheduleType
     * @param scheduleConf
     * @return
     */
    @GetMapping("/jobinfo/cronTriggerTime")
    ReturnT<List<String>> cronTriggerTime(@RequestParam("scheduleType") String scheduleType, @RequestParam("scheduleConf") String scheduleConf);

    /**
     * 新增任务
     *
     * @param jobInfo
     * @return
     */
    @PostMapping("/jobinfo/addJob")
    ReturnT<String> addJob(@RequestBody XxlJobInfo jobInfo);

    /**
     * 更新任务
     *
     * @param jobInfo
     * @return
     */
    @PutMapping("/jobinfo/updateJob")
    ReturnT<String> updateJob(@RequestBody XxlJobInfo jobInfo);

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    @DeleteMapping("/jobinfo/removeJob")
    ReturnT<String> removeJob(@RequestParam("id") Integer id);

    /**
     * 停止任务
     *
     * @param id
     * @return
     */
    @Transactional
    @PutMapping("/jobinfo/stopJob")
    ReturnT<String> pauseJob(@RequestParam("id") Integer id);

    /**
     * 开始任务
     *
     * @param id
     * @return
     */
    @Transactional
    @PutMapping("/jobinfo/startJob")
    ReturnT<String> startJob(@RequestParam("id") Integer id);

    /**
     * 添加并开始任务
     *
     * @param jobInfo
     * @return
     */
    @PostMapping("/jobinfo/addAndStart")
    ReturnT<String> addAndStart(@RequestBody XxlJobInfo jobInfo);

    /**
     * 根据名称获取ID
     *
     * @param jobDesc
     * @return
     */
    @GetMapping("/jobinfo/getId")
    Integer getId(@RequestParam("jobDesc") String jobDesc);

    /**
     * 根据描述获取对应的 XxlJobInfo 实体类
     * @param jobDesc
     * @return
     */
    @GetMapping("/jobinfo/getXxlJobInfo")
    XxlJobInfo getXxlJobInfo(@RequestParam("jobDesc") String jobDesc);

    /**
     * 根据 ID 获取 对应的 XxlJobInfo 实体类
     * @param id
     * @return
     */
    @GetMapping("/jobinfo/loadById")
    XxlJobInfo loadById(@RequestParam("id") Integer id);

    /**
     * 根据运行项目名称，拿到对应执行器的 ID
     *
     * @param appName
     * @return
     */
    @PutMapping("/jobgroup/getGroupId")
    ReturnT<Integer> getGroupId(@RequestParam("appName") String appName);
}
