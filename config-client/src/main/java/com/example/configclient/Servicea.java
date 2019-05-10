package com.example.configclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-a",fallback = MyServicea.class)
public interface Servicea {

    @RequestMapping(method = RequestMethod.GET,value = "/service")
    String test();

}
