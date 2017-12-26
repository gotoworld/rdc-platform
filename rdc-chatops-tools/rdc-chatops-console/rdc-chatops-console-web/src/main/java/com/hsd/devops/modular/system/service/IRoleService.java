package com.hsd.devops.modular.system.service;


public interface IRoleService {

    
    void setAuthority(Integer roleId, String ids);

    
    void delRoleById(Integer roleId);
}
