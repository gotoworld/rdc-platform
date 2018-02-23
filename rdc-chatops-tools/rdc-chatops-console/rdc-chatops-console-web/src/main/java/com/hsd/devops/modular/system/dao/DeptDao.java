package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hsd.devops.core.node.ZTreeNode;


public interface DeptDao  {

    
    List<ZTreeNode> tree();

    List<Map<String, Object>> list(@Param("condition") String condition);
}
