package com.bego.controller;

import com.bego.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    //private static final String REST_URL_PREFIX = "http://localhost:8001";

    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/addDept", method = RequestMethod.GET)
    public boolean addDept(Dept dept){
        System.out.println("Dept:" + dept);
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/addDept", dept, Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/getDept/{deptNo}", method = RequestMethod.GET)
    public Dept getDept(@PathVariable Integer deptNo){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/getDept/" + deptNo, Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/getDept", method = RequestMethod.GET)
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/getDept", List.class);
    }
}
