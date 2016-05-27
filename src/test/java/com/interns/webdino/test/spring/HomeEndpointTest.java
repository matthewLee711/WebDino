package com.interns.webdino.test.spring;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.interns.webdino.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class HomeEndpointTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    public HomeEndpointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testShowHomeViewEndpoint() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/home");

        request.accept(MediaType.parseMediaType("text/html;charset=UTF-8"));

        request.param("channel", "mobile");

        MvcResult result = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andReturn();

        Assert.notNull(result.getResponse().getContentAsString());
    }

}
