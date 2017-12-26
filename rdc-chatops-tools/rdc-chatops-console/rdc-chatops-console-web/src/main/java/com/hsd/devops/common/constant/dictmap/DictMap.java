package com.hsd.devops.common.constant.dictmap;

import com.hsd.devops.common.constant.dictmap.base.AbstractDictMap;


public class DictMap extends AbstractDictMap {

    @Override
    public void init() {
        put("dictId","字典名称");
        put("dictName","字典名称");
        put("dictValues","字典内容");
    }

    @Override
    protected void initBeWrapped() {

    }
}
