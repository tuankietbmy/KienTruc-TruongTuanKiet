package com.example.demo.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentRepository {

    private static final String KEY = "Student";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, com.example.demo.model.Student> hashOperations;

    public StudentRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(String id, com.example.demo.model.Student student) {
        hashOperations.put(KEY, id, student);
    }

    public Map<String, com.example.demo.model.Student> findAll() {
        return hashOperations.entries(KEY);
    }

    public com.example.demo.model.Student findById(String id) {
        return hashOperations.get(KEY, id);
    }

    public void update(String id, com.example.demo.model.Student student) {
        save(id, student);
    }

    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
