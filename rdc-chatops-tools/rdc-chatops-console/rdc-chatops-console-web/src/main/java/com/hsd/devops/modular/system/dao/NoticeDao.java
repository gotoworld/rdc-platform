package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface NoticeDao {

    List<Map<String, Object>> list(@Param("condition") String condition);
}
