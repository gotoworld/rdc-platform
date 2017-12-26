package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hsd.devops.core.node.ZTreeNode;


public interface RoleDao {

    
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    
    int deleteRolesById(@Param("roleId") Integer roleId);

    
    List<ZTreeNode> roleTreeList();

    
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);


}
