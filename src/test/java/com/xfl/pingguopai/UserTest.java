package com.xfl.pingguopai;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by timely on 2017/8/27.
 */
public class UserTest extends Tester {

    private  static final Logger logger = LoggerFactory.getLogger("UserTest");
    @Autowired
    private UserService userService;
    @Override
    public void testCRUD() {
        testUserAdd();
    }
    @Test
    public void testUserAdd() {
        User user = new User();
        user.setUsername("中国");
        user.setPassword("abc123");
        user.setEmpName("my timely");
        user.setEmpName("my timely");
        User u =  beanMapUtil.clone(user);
        try {
            String json = (new ObjectMapper()).writeValueAsString(user);

            mockMvc.perform(post("/auth/user/add")
                    .param("username", "中国")
                    .param("password", "abc123")
                    .param("empName", "my timely"))
                .andExpect(status().isOk());

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testUserUpdate() throws Exception {
        User user = new User();
        user.setUsername("helloworld");
        user.setPassword("abc123");
        user.setEmpName("xfl");
        userService.save(user);
        user.setEmpName("hibaby");
        mockMvc.perform(post("/auth/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(user)))
                .andExpect(status().isOk());

        Assert.assertEquals(userService.findById(user.getId()).getEmpName(), "hibaby");
    }

    @Test
    public void testUserList() throws Exception {
        testUserAdd();
        PageInfo info = new PageInfo();
        info.setPageNum(0);
        info.setPageSize(10);
        mockMvc.perform(post("/auth/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(info)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total", is(1)));
    }
}
