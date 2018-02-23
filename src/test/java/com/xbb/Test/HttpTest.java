package com.xbb.Test;

import com.xbb.Bean.Result;
import com.xbb.ClientControlApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

// 启动执行测试项目  //这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class) //这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ClientControlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {


    // restFull 模板
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/helloWorld", String.class);   // postForObject 为post方法
        assert body.equals("hello world"):" answer is not 'hello world'";
    }

    @Test
    public void post() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("name","lake");
        Result result = restTemplate.postForObject("/test/post",multiValueMap,Result.class);
        Assert.assertEquals(result.getCode(),0);
    }

    @Test
    public void get() throws Exception {
        Map<String,String> multiValueMap = new HashMap<>();
        multiValueMap.put("name","lake");//传值，但要在url上配置相应的参数
        //testRestTemplate.postForObject("/test/upload",multiValueMap,ActResult.class) //  /test/upload地址  multiValueMap 传入参数  ActResult.class为返回对象类型
        Result result = restTemplate.getForObject("/test/post?name={name}",Result.class,multiValueMap);
        Assert.assertEquals(result.getCode(),0);
    }

    @Test   // http 发送测试
    public void download() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<byte[]> response = restTemplate.exchange("/test/download?name={1}", HttpMethod.GET,formEntity,byte[].class,urlVariables);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("执行成功！");
            /*Files.write(response.getBody(),new File("/home/lake/github/file/test.gradle"));*/
        }
    }

    @Test // put
    public void putHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxxx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("name","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        ResponseEntity<Result> result = restTemplate.exchange("/test/putHeader", HttpMethod.PUT,formEntity,Result.class);
        Assert.assertEquals(result.getBody().getCode(),0);
    }

}
