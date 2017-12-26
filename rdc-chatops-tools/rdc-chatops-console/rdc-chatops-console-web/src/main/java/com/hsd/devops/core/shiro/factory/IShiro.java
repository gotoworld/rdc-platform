package com.hsd.devops.core.shiro.factory;

import com.hsd.devops.core.shiro.ShiroUser;
import com.hsd.devops.common.persistence.model.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;


public interface IShiro {

    
    User user(String account);

    
    ShiroUser shiroUser(User user);

    
    List<String> findPermissionsByRoleId(Integer roleId);

    
    String findRoleNameByRoleId(Integer roleId);

    
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);

}
