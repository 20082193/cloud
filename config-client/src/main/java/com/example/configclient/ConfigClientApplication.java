package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@RestController
@EnableEurekaClient
@RequestMapping("/config")
@EnableFeignClients
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @Autowired
    private Servicea servicea;

    @GetMapping("/test")
    public String test(HttpServletRequest request,String name) throws InterruptedException {
        System.out.println("接受到请求");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            System.out.println(s+"===="+request.getHeader(s));
        }
        String name1 = request.getParameter("name");
        System.out.println("name 1= " + name1);
        System.out.println("name= " + name);
        return port;
    }

    @GetMapping("/service")
    public String service() {
        System.out.println("service is calling");
        return servicea.test();
    }

}
