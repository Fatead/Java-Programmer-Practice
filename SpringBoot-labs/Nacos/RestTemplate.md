# RestTemplate
RestTemplate 是从Spring3.0开始支持的一个HTTP请求工具，提供了常见的REST
请求方案的模板，例如GET、POST、PUT、DELETE。

## GET请求
### getForEntity
RestTemplate发送的是HTTP请求，响应的数据中也有响应头。如果需要或者响应头的话，就需要使用
getForEntity来发送HTTP请求，此时返回的对象是一个ResponseEntity实例，这个实例中包含了响应
数据以及响应头。

~~~
RestTemplate restTemplate = new RestTemplate();
String url = "http://127.0.0.1:8848/hello?name={1}";
//第一个参数是url，第二个参数是接口返回的数据类型，最后是一个可变长度的参数，用来给占位符填值
ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class,"");
//获得HTTP状态码
HttpStatus statusCode = responseEntity.getStatusCode();
//获得HTTP header
HttpHeaders headers = responseEntity.getHeaders();
//获得HTTP body
String body = responseEntity.getBody();
~~~

### getForObject
getForObject的返回值就是服务提供者返回的数据，使用getForObject无法获取到响应头。

## POST请求
### postForEntity
在POST请求中，参数的传递可以是key/value的形式，也可以是JSON数据。
### postForObject

### postForLocation
postForLocation方法的返回值是一个uri对象，使用场景：用户注册成功后跳转至登录界面。
