package com.example.concurrent.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author LuoLi
 * @version 1.0
 * @date 2020/12/10 10:25
 */
@Component
@Slf4j
public class UpdateOrderStock implements SessionCallback {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object execute(RedisOperations operations) throws DataAccessException {
        operations.multi();
        Integer luoli = (Integer) redisTemplate.opsForValue().get("luoli");//null
        log.info(String.valueOf(luoli));
        redisTemplate.opsForValue().increment("luoli", 2);
        //int i = 1 / 0;
        operations.exec();
        return null;
    }
}
