package com.hsd.devops.common.constant.dictmap;

import com.hsd.devops.common.constant.dictmap.base.AbstractDictMap;


public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("tips","备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
