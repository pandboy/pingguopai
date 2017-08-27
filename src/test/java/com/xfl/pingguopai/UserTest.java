package com.xfl.pingguopai;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
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
        user.setUserName("timely");
        user.setPassword("abc123");
        user.setEmpName("my timely");
        user.setEmpName("my timely");
        String json = JSON.toJSONString(user);
        logger.info("*****json is {}", json);
        try {
            mockMvc.perform(post("/api/admin/user/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk());

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testUserUpdate() throws Exception {
        User user = new User();
        user.setUserName("helloworld");
        user.setPassword("abc123");
        user.setEmpName("xfl");
        userService.save(user);
        user.setEmpName("hibaby");
        mockMvc.perform(post("/api/admin/user/update")
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
        mockMvc.perform(post("/api/admin/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(info)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.total", is(1)));
    }
}
