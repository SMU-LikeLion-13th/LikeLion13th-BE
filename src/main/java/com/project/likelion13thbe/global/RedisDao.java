package com.project.likelion13thbe.global;

import com.project.likelion13thbe.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RedisDao {
    
    //redisTemplate 생성
    private final RedisTemplate<String, String> redisTemplate;

    //ket, value 넣기
    public void setValues(String key, String data) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }
    
    //key, value를 리스트 형태로 넣기
    public void setValueList(String key, String data) {
        redisTemplate.opsForList().rightPushAll(key, data);
    }
    
    //key를 기반으로 value 리스트 가져오기
    public List<String> getValuesList(String key) {
        Long len = redisTemplate.opsForList().size(key);
        return len == 0 ? new ArrayList<>() : redisTemplate.opsForList().range(key, 0, len-1);
    }
    
    //지속시간(TTL)까지 포함한 setValues
    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    
    //key를 기반으로 value 가져오기
    public String getValues(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }
    
    //key를 기반으로 value 삭제
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
