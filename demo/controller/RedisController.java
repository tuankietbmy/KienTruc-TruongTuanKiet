package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.service.RedisService;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        redisService.save(student);
    }

    @GetMapping
    public Map<String, Student> getAllStudents() {
        return redisService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return redisService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student existingStudent = redisService.findById(id);
        if (existingStudent != null) {
            student.setId(id);
            redisService.update(student);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        redisService.delete(id);
    }
}