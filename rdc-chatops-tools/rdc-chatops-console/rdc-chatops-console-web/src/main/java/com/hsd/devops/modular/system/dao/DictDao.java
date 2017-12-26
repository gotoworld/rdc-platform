package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hsd.devops.common.persistence.model.Dict;


public interface DictDao {

    
    List<Dict> selectByCode(@Param("code") String code);

    
    List<Map<String,Object>> list(@Param("condition") String conditiion);
}
