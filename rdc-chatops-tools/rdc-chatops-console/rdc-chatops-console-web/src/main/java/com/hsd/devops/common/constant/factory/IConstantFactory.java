package com.hsd.devops.common.constant.factory;

import com.hsd.devops.common.constant.cache.Cache;
import com.hsd.devops.common.constant.cache.CacheKey;
import com.hsd.devops.common.persistence.model.Dict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface IConstantFactory {

    
    String getUserNameById(Integer userId);

    
    String getUserAccountById(Integer userId);

    
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    String getRoleName(String roleIds);

    
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    String getSingleRoleName(Integer roleId);

    
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    String getSingleRoleTip(Integer roleId);

    
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    String getDeptName(Integer deptId);

    
    String getMenuNames(String menuIds);

    
    String getMenuName(Integer menuId);

    
    String getMenuNameByCode(String code);

    
    String getDictName(Integer dictId);

    
    String getNoticeTitle(Integer dictId);

    
    String getDictsByName(String name, Integer val);

    
    String getSexName(Integer sex);

    
    String getStatusName(Integer status);

    
    String getMenuStatusName(Integer status);

    
    List<Dict> findInDict(Integer id);

    
    String getCacheObject(String para);

    
    List<Integer> getSubDeptId(Integer deptid);

    
    List<Integer> getParentDeptIds(Integer deptid);

}
