/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interns.webdino.test.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.interns.webdino.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EchoServiceIntTest{

    @Value("${local.server.port}")
    private int port;

    @Value("${server.context-path}")
    private String context;

    private RestTemplate rt = new RestTemplate();

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testShowHomeViewEndpoint(){

        String result = rt.getForObject("http://localhost:" + port + context + "/echo?value=echoechoecho", String.class);

        Assert.notNull(result);
        Assert.hasLength(result);

    }

}
