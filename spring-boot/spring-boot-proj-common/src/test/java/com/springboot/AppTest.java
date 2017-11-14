package com.springboot;

import com.dev.protobuf.CourseProtos;
import com.springboot.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class AppTest {
    private static final String COURSE1_URL = "http://localhost:8080/courses/2";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void restTest() {
        ResponseEntity<CourseProtos.Course> course = restTemplate.getForEntity(COURSE1_URL, CourseProtos.Course.class);
        System.out.println(course.toString());
    }

    @Test
    public void jsonTest() throws IOException {
        String json = HttpUtil.proto2Json(HttpUtil.get(COURSE1_URL));
        System.out.println(json);
    }
}
