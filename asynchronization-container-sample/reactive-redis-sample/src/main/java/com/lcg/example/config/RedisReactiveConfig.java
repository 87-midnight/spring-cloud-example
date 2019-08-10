package com.lcg.example.config;

import com.lcg.example.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisReactiveConfig {

    @Bean(name = "jdkReactiveRedis")
    @Primary
    public ReactiveRedisTemplate<String,User> reactiveJdkRedisTemplate(ReactiveRedisConnectionFactory connectionFactory){
        RedisSerializationContext<String, User> serializationContext = RedisSerializationContext
                .<String, User>newSerializationContext(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(new Jackson2JsonRedisSerializer<>(User.class))
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean(name = "stringReactiveRedis")
    public ReactiveRedisTemplate<String,String> reactiveStringRedisTemplate(ReactiveRedisConnectionFactory connectionFactory){
        return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
    }
}
