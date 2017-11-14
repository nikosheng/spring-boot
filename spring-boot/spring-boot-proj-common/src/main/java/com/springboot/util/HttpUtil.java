package com.springboot.util;

import com.dev.protobuf.CourseProtos;
import com.googlecode.protobuf.format.JsonFormat;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class HttpUtil {

    public static InputStream get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        return response.getEntity().getContent();
    }

    public static String proto2Json(InputStream input) throws IOException {
        JsonFormat format = new JsonFormat();
        CourseProtos.Course course = CourseProtos.Course.parseFrom(input);
        return format.printToString(course);
    }
}
