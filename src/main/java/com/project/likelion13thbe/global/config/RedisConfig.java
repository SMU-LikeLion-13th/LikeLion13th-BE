package com.project.likelion13thbe.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
//redis 설정 클래스
public class RedisConfig {
    //host 선언
    @Value("localhost")
    private String host;

    //port 선언
    @Value("6379")
    private int port;

    @Bean
    //redis와의 연결을 담당하는 팩토리
    public RedisConnectionFactory redisConnectionFactory() {
        //LettuceConnectionFactory -> 구체적인 구현체
        return new LettuceConnectionFactory(host, port);
    }

    //redisTemplate 만들어서 Bean으로 주입
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        //새 redisTemplate 생성
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        //빈 redisTemplate 생성
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

}
