package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hsd.devops.core.node.MenuNode;
import com.hsd.devops.core.node.ZTreeNode;


public interface MenuDao {

    
    List<Map<String, Object>> selectMenus(@Param("condition") String condition, @Param("level") String level);

    
    List<Integer> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    
    List<ZTreeNode> menuTreeList();

    
    List<ZTreeNode> menuTreeListByMenuIds(List<Integer> menuIds);

    
    int deleteRelationByMenu(Integer menuId);

    
    List<String> getResUrlsByRoleId(Integer roleId);

    
    List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);


}
