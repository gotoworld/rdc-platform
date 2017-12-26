package com.hsd.devops.modular.system.service;


public interface IMenuService {

    
    void delMenu(Integer menuId);

    
    void delMenuContainSubMenus(Integer menuId);
}
