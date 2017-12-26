package com.hsd.devops.modular.biz.service.impl;

import com.hsd.devops.common.constant.DSEnum;
import com.hsd.devops.common.persistence.dao.TestMapper;
import com.hsd.devops.common.persistence.model.Test;
import com.hsd.devops.core.mutidatasource.annotion.DataSource;
import com.hsd.devops.modular.biz.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public void testBiz() {
        Test test = testMapper.selectById(1);
        test.setId(22);
        test.insert();
    }


    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_DEVOPS)
    public void testDevops() {
        Test test = testMapper.selectById(1);
        test.setId(33);
        test.insert();
    }

    @Override
    @Transactional
    public void testAll() {
        testBiz();
        testDevops();
        //int i = 1 / 0;
    }

}
