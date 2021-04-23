package com.example.nacosdiscoverydemo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    //Nacos命名服务，通过指定的名字来获取资源或者服务的地址，提供者的信息
    @NacosInjected
    private NamingService namingService;

    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/demo")
    public String consumer(){
        Instance instance = null;
        try {
            //根据服务名找到服务实例
            List<Instance> instances = namingService.getAllInstances("demo-application-discovery");
            instance = instances.stream().findFirst()
                    .orElseThrow(() -> new IllegalStateException("未找到对应的 Instance"));
        } catch (NacosException e) {
            e.printStackTrace();
        }
        //执行请求
        return restTemplate.getForObject("http://" + instance.toInetAddr() + "/provider/demo",
                String.class);
    }

}
