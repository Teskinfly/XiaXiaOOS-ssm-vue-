package redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
    @Test
    public void connect() {
        Jedis jedis=new Jedis("192.168.240.135",6379);
        jedis.auth("12315");
        String ping = jedis.ping();
        System.out.println(ping);
    }
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void templete() {
        redisTemplate.opsForValue().set("teskinfly","测试");
    }
}
