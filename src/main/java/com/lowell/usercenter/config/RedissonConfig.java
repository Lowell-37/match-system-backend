package com.lowell.usercenter.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @Date: 2024/3/14 15:10
 * @Author: Lowell
 * @Description: Redisson 配置
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;
    private String port;

    @Bean
    public RedissonClient redissonClient() {
        // 1. Create config object
        Config config = new Config();

        String redissonAddress = String.format("redis://%s:%s", host, port);

        config.useSingleServer().setAddress(redissonAddress).setDatabase(3);

        // 2. Create Redisson instance

        RedissonClient redisson = Redisson.create(config);

        return redisson;


    }


}
