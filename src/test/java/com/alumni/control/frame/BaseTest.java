package com.alumni.control.frame;

import com.alumni.control.AlumniControlApplication;
import com.alumni.control.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@Slf4j
@SpringBootTest(classes = AlumniControlApplication.class)
public class BaseTest extends AbstractTransactionalTestNGSpringContextTests {

    protected MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeSuite
    public void setUp() {
        ConfigUtil.initProperties();
    }

    @BeforeClass
    public void setUpMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
