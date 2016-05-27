package com.interns.webdino.test.basic;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.interns.webdino.model.EchoResponseModel;


public class EchoResponseModelTest {


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
    public void testEchoResponseModel() {

        String beforeString = "echoechoecho";
        String afterString = beforeString.toUpperCase();
        String value = null;

        EchoResponseModel erm = new EchoResponseModel(beforeString);

        Assert.assertNotNull(erm);
        Assert.assertEquals(beforeString, erm.toString());

        value = erm.getValue();

        Assert.assertNotNull(value);
        Assert.assertTrue(value.length() > 0);
        Assert.assertEquals(beforeString, value);

        erm.setValue(afterString);

        value = erm.getValue();

        Assert.assertNotNull(value);
        Assert.assertTrue(value.length() > 0);
        Assert.assertEquals(afterString, value);

    }

}
