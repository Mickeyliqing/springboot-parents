package com.example.springbootxxljob.controller;

import com.example.springbootxxljob.client.JobInfoClient;
import com.example.springbootxxljob.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/job")
public class XxljobController {

    @Resource
    private JobInfoClient jobInfoClient;

    /**
     * 执行器名称，这个值默认在配置项中配置
     */
    @Value("${xxl.job.executor.appname}")
    private String appname;

    /**
     * 这里添加一个例子说明如何调用 xxl-job 对应的方法
     * 具体使用的时候，需要根据实际的使用情况封装对应的参数
     */
    @PostMapping("/addJob")
    public void addTask() {
        /**
         * 定义参数的值
         * handler 的值就是 SampleXxlJob 类对应方法的名称注解
         */
        String name = "这是测试Demo";
        String param = "这是Job的参数";
        String handler = "demoJobHandler";
        ReturnT<Integer> groupId = jobInfoClient.getGroupId(appname);
        Integer infoClientId = jobInfoClient.getId(name);
        XxlJobInfo xxlJobInfo = XxlJobInfo.taskJob(groupId.getContent(), name, null, null, handler, param, 0);
        if (ObjectUtils.isEmpty(infoClientId)) {
            // 添加 xxl-job 的任务
            ReturnT<String> returnT = jobInfoClient.addJob(xxlJobInfo);
            if (returnT.getCode() != 200) {
                log.error("调度任务创建失败，传入的数据为：{}", xxlJobInfo);
                return;
            }
        }
    }
}
