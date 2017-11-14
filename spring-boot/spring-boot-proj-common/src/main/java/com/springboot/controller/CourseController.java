package com.springboot.controller;

import com.dev.protobuf.CourseProtos;
import com.springboot.model.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/courses/{id}")
    CourseProtos.Course customer(@PathVariable Integer id) {
        return courseRepository.getCourse(id);
    }
}
