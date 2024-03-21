package com.lowell.usercenter.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lowell.usercenter.model.domain.User;
import com.lowell.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2024/3/14 13:13
 * @Author: Lowell
 * @Description:
 **/
@Slf4j
public class PreCacheJob {
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    RedissonClient redissonClient;
    // 重点用户
    private List<Long> mainUserList = Arrays.asList(1L);

    // 每天执行预热推荐用户
    @Scheduled(cron = "0 3 0 * * *")
    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("MatchSystem:preCacheJob:doCache:Lock");
        try {
            // 只有一个线程能获取到锁
            if (lock.tryLock(0, 30000, TimeUnit.MILLISECONDS)) {
                for (Long userId : mainUserList) {
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                    String redisKey = String.format("MatchSystem: recommend:%s", userId);
                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                    // 写缓存
                    try {
                        valueOperations.set(redisKey, userPage);
                    } catch (Exception e) {
                        log.error("redis set key failed");
                    }
                }


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
