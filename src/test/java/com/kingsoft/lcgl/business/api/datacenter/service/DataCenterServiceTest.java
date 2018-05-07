package com.kingsoft.lcgl.business.api.datacenter.service;

import com.kingsoft.lcgl.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by yangdiankang on 2018/3/9.
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class DataCenterServiceTest {
    @Autowired
    private DataCenterService dataCenterService;

    @Test
    public void everyDayData() throws Exception {
        dataCenterService.everyDayData();
        dataCenterService.everyMonthData();
    }

}