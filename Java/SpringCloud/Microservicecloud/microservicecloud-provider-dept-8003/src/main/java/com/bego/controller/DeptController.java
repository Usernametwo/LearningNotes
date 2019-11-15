package com.bego.controller;

import com.bego.entity.Dept;
import com.bego.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/addDept", method = RequestMethod.POST)
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @RequestMapping(value = "/dept/getDept/{deptNo}", method = RequestMethod.GET)
    public Dept getDept(@PathVariable Integer deptNo){
        return deptService.findById(deptNo);
    }

    @RequestMapping(value = "/dept/getDept", method = RequestMethod.GET)
    public List<Dept> list(){
        return deptService.findAll();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println("**************" + list);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for(ServiceInstance serviceInstance : serviceInstances) {
            System.out.println(serviceInstance);
        }
        return this.discoveryClient;
    }

}
