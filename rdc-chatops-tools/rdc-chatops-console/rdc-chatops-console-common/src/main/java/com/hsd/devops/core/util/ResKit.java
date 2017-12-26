package com.hsd.devops.core.util;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


public class ResKit {

    
    public static Resource[] getClassPathResources(String pattern) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources(pattern);
        } catch (IOException e) {
            throw new RuntimeException("加载resource文件时,找不到文件,所找文件为：" + pattern);
        }
    }

    
    public static String getClassPathFile(String file) {
		//return ResKit.class.getClassLoader().getResource(file).getPath();
        return Thread.currentThread().getContextClassLoader().getResource(file).getPath();
    }
}
