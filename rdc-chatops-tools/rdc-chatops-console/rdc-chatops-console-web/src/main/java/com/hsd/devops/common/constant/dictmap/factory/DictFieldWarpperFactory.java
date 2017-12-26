package com.hsd.devops.common.constant.dictmap.factory;

import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.common.constant.factory.IConstantFactory;
import com.hsd.devops.common.exception.BizExceptionEnum;
import com.hsd.devops.common.exception.BussinessException;

import java.lang.reflect.Method;


public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object field, String methodName) {
        IConstantFactory me = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, field.getClass());
            Object result = method.invoke(me, field);
            return result;
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                Object result = method.invoke(me, Integer.parseInt(field.toString()));
                return result;
            } catch (Exception e1) {
                throw new BussinessException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
            }
        }
    }

}
