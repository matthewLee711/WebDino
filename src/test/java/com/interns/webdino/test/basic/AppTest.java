package com.interns.webdino.test.basic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.interns.webdino.App;

public class AppTest {


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void appStartTest(){

        String args[] = {"--spring.config.location=./src/test/resources/config.properties"};

        try{

            App.main(args);

            App.getCtx().close();

        } catch(Exception ex){
            Assert.fail();
        }

    }

}
