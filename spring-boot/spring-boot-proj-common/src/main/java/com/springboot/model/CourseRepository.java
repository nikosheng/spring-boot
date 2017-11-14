package com.springboot.model;

import com.dev.protobuf.CourseProtos;

import java.util.Map;

public class CourseRepository {
    private Map<Integer, CourseProtos.Course> courses;

    public CourseRepository(Map<Integer, CourseProtos.Course> courses) {
        this.courses = courses;
    }

    public CourseProtos.Course getCourse(int id) {
        return courses.get(id);
    }
}
