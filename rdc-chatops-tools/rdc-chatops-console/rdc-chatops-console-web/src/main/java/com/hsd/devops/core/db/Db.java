package com.hsd.devops.core.db;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsd.devops.core.util.SpringContextHolder;

import java.util.HashMap;
import java.util.List;


@SuppressWarnings("all")
public class Db<T> {

    
    private Class<T> clazz;

    
    private BaseMapper<?> baseMapper;

    
    private Db(Class clazz) {
        this.clazz = clazz;
        this.baseMapper = (BaseMapper<?>) SpringContextHolder.getBean(clazz);
    }

    
    public static <T> Db<T> create(Class<T> clazz) {
        return new Db<T>(clazz);
    }

    
    public BaseMapper<?> getMapper() {
        return this.baseMapper;
    }

    
    public static <T> T getMapper(Class<T> clazz) {
        return SpringContextHolder.getBean(clazz);
    }

    
    public <E> E selectOneByCon(String condition, Object value) {
        List<?> results = selectOneByConList(condition, value);
        if (results != null && results.size() > 0) {
            return (E) results.get(0);
        } else {
            return null;
        }
    }

    
    public <E> List<E> selectOneByConList(String condition, Object value) {
        HashMap<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put(condition, value);

        List<E> results = (List<E>) this.baseMapper.selectByMap(conditionMap);
        if (results == null || results.size() == 0) {
            return null;
        } else {
            return results;
        }
    }
}
