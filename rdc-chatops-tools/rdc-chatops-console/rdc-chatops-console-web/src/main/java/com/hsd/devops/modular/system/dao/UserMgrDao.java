package com.hsd.devops.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hsd.devops.common.persistence.model.User;
import com.hsd.devops.core.datascope.DataScope;


public interface UserMgrDao {

    
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    
    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    
    User getByAccount(@Param("account") String account);
}
