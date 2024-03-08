package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

import java.util.Map;

@Service
public class RedisService {
    private static final String HASH_KEY = "STUDENT";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private HashOperations<Object, String, Student> hashOperations;

    @Autowired
    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Student student) {
        hashOperations.put(HASH_KEY, student.getId(), student);
    }

    public Map<String, Student> findAll() {
        return hashOperations.entries(HASH_KEY);
    }

    public Student findById(String id) {
        return hashOperations.get(HASH_KEY, id);
    }

    public void update(Student student) {
        save(student);
    }

    public void delete(String id) {
        hashOperations.delete(HASH_KEY, id);
    }
}