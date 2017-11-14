package com.springboot;

import com.dev.protobuf.CourseProtos;
import com.springboot.model.CourseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * nikofeng copyright
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, CourseProtos.Course> courses = new HashMap<>();

        CourseProtos.Course course1 = CourseProtos.Course.newBuilder()
                .setId(1)
                .setCourseName("Rest With Spring")
                .addAllStudent(createTestStudents(
                        1,
                        "Jiasheng",
                        "Feng",
                        "nikofeng@tencent.com",
                        "159-2030-6213",
                        CourseProtos.Student.PhoneType.MOBILE))
                .build();

        CourseProtos.Course course2 = CourseProtos.Course.newBuilder()
                .setId(2)
                .setCourseName("Learn Spring Security")
                .addAllStudent(createTestStudents(
                        2,
                        "Yaya",
                        "Ye",
                        "yayaye@tencent.com",
                        "159-2022-1111",
                        CourseProtos.Student.PhoneType.LANDLINE))
                .build();

        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);
        return new CourseRepository(courses);
    }

    private List<CourseProtos.Student> createTestStudents(int id,
                                                          String firstName,
                                                          String lastName,
                                                          String email,
                                                          String phoneNum,
                                                          CourseProtos.Student.PhoneType phoneType) {
        CourseProtos.Student student = CourseProtos.Student.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .addPhone(CourseProtos.Student.PhoneNumber.newBuilder()
                        .setNumber(phoneNum)
                        .setType(phoneType)
                        .build())
                .build();

        return Arrays.asList(student);
    }
}
