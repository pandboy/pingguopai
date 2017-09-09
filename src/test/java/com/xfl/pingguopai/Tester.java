package com.xfl.pingguopai;


import com.alibaba.fastjson.JSON;
import com.xfl.pingguopai.Application;
import com.xfl.pingguopai.model.Authority;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.security.auth.AnonAuthentication;
import com.xfl.pingguopai.security.auth.TokenBasedAuthentication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Transactional
@Rollback
public abstract class Tester {
    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;
    protected String token = "";
    public abstract void testCRUD();


    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        securityContext = Mockito.mock( SecurityContext.class );
        SecurityContextHolder.setContext( securityContext );
        Mockito.when( securityContext.getAuthentication() ).thenReturn( new AnonAuthentication() );
        mockAuthenticatedUser(buildTestAdmin());
    }

    public void login() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        String json = "username=user&password=123";
        try {
            ResultActions actions = mockMvc.perform(post("/login")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json))
                    .andExpect(status().isOk());
            Object obj = actions.andReturn().getAsyncResult();
            System.out.println(obj);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @After
    public final void afterAbstractTest() {
        SecurityContextHolder.clearContext();
    }

    protected SecurityContext securityContext;

    protected void mockAuthenticatedUser( User user ) {
        mockAuthentication( new TokenBasedAuthentication( user ) );
    }

    private void mockAuthentication( TokenBasedAuthentication auth ) {
        auth.setAuthenticated( true );
        Mockito.when( securityContext.getAuthentication() ).thenReturn( auth );
    }

    protected User buildTestAnonUser() {
        User user = new User();
        user.setUsername("user");
        return user;
    }

    protected User buildTestUser() {

        User user = new User();
        Authority userAuthority = new Authority();
        userAuthority.setName("ROLE_USER");
        List<Authority> userAuthorities = new ArrayList<>();
        userAuthorities.add(userAuthority);
        user.setUsername("user");
        user.setAuthorities(userAuthorities);
        return user;
    }


    protected User buildTestAdmin() {
        Authority userAuthority = new Authority();
        Authority adminAuthority = new Authority();
        userAuthority.setName("ROLE_USER");
        adminAuthority.setName("ROLE_ADMIN");
        List<Authority> adminAuthorities = new ArrayList<>();
        adminAuthorities.add(userAuthority);
        adminAuthorities.add(adminAuthority);
        User admin = new User();
        admin.setUsername("admin");
        admin.setAuthorities(adminAuthorities);
        return admin;
    }

}



