package com.example.readinglist;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)     //不要具体给定任意一个测试类 那样回加载不到其它的依赖类
//开启自定义端口 改变为上面的这种形式
//使用 @springTest 时，自动集成了许多自定义配置，比如自动开启springSecurity.而在使用@WebAppConfiguration时，则这些需要自己来开启
//@WebAppConfiguration

/*测试环境加载一些配置信息的二种方式：
        第一种是使用@SpringBootTest注解，注解参数properties指定其value值。
        第二种使用EnvironmentTestUtils.addEnvironment方法进行设置。*/
@AutoConfigureMockMvc //自动配置mockmvc 不需要以下的@before
public class ReadingListControllerTest {

   /* @Autowired
    private WebApplicationContext webContext;*/
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment environment;

   /* @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                //开启security
                //.apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }*/

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/readingList"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.is(Matchers.empty())));
    }

    @Test
    public void postBook() throws Exception {
        mockMvc.perform(post("/readingList")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "BOOK TITLE")
                .param("author", "BOOK AUTHOR")
                .param("isbn", "1234567890")
                .param("description", "DESCRIPTION"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/readingList"));
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");
        mockMvc.perform(get("/readingList"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)));
    }

    /**
     * security 安全测试
     * @throws Exception
     */
  /*  @Test
    @WithUserDetails("craig") //无此用户会直接抛出异常
    public void homePage_authenticatedUser() throws Exception {
        //必需实现了UserDetails
        Reader expectedReader = new Reader();
        expectedReader.setUsername("craig");
        expectedReader.setPassword("password");
        expectedReader.setFullname("Craig Walls");
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("reader",
                        samePropertyValuesAs(expectedReader)))
                .andExpect(model().attribute("books", hasSize(0)));
    }*/

    @Test(expected= HttpClientErrorException.class)
    public void pageNotFound() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://localhost:8080/bogusPage", String.class);
            fail("Should result in HTTP 404");
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            throw e;
        }
    }

    @Test
    public void testValue(){
        String[] activeProfiles = environment.getActiveProfiles();
        for (String t : activeProfiles){
            System.out.println(t);
        }
        String[] defaultProfiles = environment.getDefaultProfiles();
        for (String t : defaultProfiles){
            System.out.println(t);
        }
        Assert.assertEquals("xxx",environment.getProperty("developer.name"));
    }

}