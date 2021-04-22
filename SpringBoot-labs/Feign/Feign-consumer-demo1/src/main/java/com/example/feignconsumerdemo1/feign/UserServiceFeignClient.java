package com.example.feignconsumerdemo1.feign;

import com.example.feignconsumerdemo1.feign.request.UserAddRequest;
import com.example.feignconsumerdemo1.feign.response.UserResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

public interface UserServiceFeignClient {

    /*
     *获取用户详情
     * @RequestLine注解，添加在方法上，设置请求方法和请求地址，按照（请求方法，请求地址）的格式，
     * 同时可以通过{param}表达式声明占位参数，搭配@Param注解一起使用
     * @Param注解，添加在方法参数上，设置占位参数，@Param是必传参数，传入null会报错
     */
    @RequestLine("GET /user/get?id={id}")
    UserResponse get(@Param("id") Integer id);

    @RequestLine("GET /user/list?name={name}&gender={gender}")
    List<UserResponse> list(@Param("name") String name, @Param("gender") Integer gender);


    /*
     *@QueryMap 请求query参数集，无需像@Param在@RequestLine定义{Param}表达式进行占位
     * 可以解决@Param传入null报错的问题
     */
    @RequestLine("GET /user/list")
    List<UserResponse> list(@QueryMap Map<String,Object> queryMap);

    /*
     *@Headers注解，添加在方法上，设置请求头
     * 对应的HTTP接口 /user/add 使用@RequstBody + JSON 格式接收参数
     */
    @RequestLine("POST /user/add")
    @Headers("Content-Type: application/json")
    Integer add(UserAddRequest request);


}
