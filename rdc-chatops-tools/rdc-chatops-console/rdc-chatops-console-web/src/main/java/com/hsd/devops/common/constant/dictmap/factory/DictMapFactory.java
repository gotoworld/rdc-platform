package com.hsd.devops.common.constant.dictmap.factory;

import com.hsd.devops.common.constant.dictmap.base.AbstractDictMap;
import com.hsd.devops.common.constant.dictmap.base.SystemDict;
import com.hsd.devops.common.exception.BizExceptionEnum;
import com.hsd.devops.common.exception.BussinessException;


public class DictMapFactory {

    private static final String basePath = "com.hsd.devops.common.constant.dictmap.";

    
    public static AbstractDictMap createDictMap(String className) {
        if("SystemDict".equals(className)){
            return new SystemDict();
        }else{
            try {
                Class<AbstractDictMap> clazz = (Class<AbstractDictMap>) Class.forName(basePath + className);
                return clazz.newInstance();
            } catch (Exception e) {
                throw new BussinessException(BizExceptionEnum.ERROR_CREATE_DICT);
            }
        }
    }
}
