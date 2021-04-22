package com.example.sentineldemo.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addSentinelWebInterceptor(registry);
    }

    /**
     * SentinelWebInterceptor拦截器，针对每个URL进行流量控制
     */
    private void addSentinelWebInterceptor(InterceptorRegistry registry){
        //创建SentinelWebMvcConfig对象，用于SentinelWebInterceptor拦截器的配置
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        //是否包含请求方法。即基于URL创建的资源，是否包含method。资源是Sentinel的关键概念，它可以是Java应用程序中的任何内容
        //例如由应用程序提供的服务，或由应用程序调用其它应用提供的服务，甚至可以是一段代码。
        //只要通过Sentinel API定义的代码，就是资源，能够被Sentinel保护起来，大部分情况下，可以使用方法签名URL，甚至服务名称作为资源名来标示资源。
        //SentinelWebInterceptor拦截器将URL + Method作为一个资源，进行流量控制。
        config.setHttpMethodSpecify(true);
        //添加SentinelWebInterceptor拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");

    }


}
