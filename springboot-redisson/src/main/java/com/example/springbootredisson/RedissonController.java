package com.example.springbootredisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class RedissonController {

    @Resource
    private RedissonClient redissonClient;

    @PostMapping("test")
    public Boolean test(){
        //获取锁实例
        RLock rLock = redissonClient.getLock("lock");
        boolean isLock =false;
        try {
            // 上锁
            isLock = rLock.tryLock();
            System.out.println(Thread.currentThread().getName()+"isLock="+isLock);
            if (isLock) {
                System.out.println(Thread.currentThread().getName()+"我抢到锁了，开心，先休息10秒先");
                Thread.sleep(10 *1000);
                // todo service 业务代码
            }else {
                System.out.println(Thread.currentThread().getName()+"被人锁了，郁闷下次再来");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(isLock){
                System.out.println(Thread.currentThread().getName()+"不玩了，开锁了！！！");
                // 解锁
                rLock.unlock();
            }
        }
        return true;
    }
}
