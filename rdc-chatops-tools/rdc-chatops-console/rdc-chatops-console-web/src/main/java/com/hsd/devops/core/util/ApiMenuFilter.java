package com.hsd.devops.core.util;

import com.hsd.devops.common.constant.Const;
//import com.hsd.devops.config.properties.DevopsProperties;
import com.hsd.devops.core.node.MenuNode;

import java.util.ArrayList;
import java.util.List;


public class ApiMenuFilter extends MenuNode {


    public static List<MenuNode> build(List<MenuNode> nodes) {

        //如果关闭了接口文档,则不显示接口文档菜单
//        DevopsProperties devopsProperties = SpringContextHolder.getBean(DevopsProperties.class);
//        if (!devopsProperties.getSwaggerOpen()) {
            List<MenuNode> menuNodesCopy = new ArrayList<>();
            for (MenuNode menuNode : nodes) {
                if (Const.API_MENU_NAME.equals(menuNode.getName())) {
                    continue;
                } else {
                    menuNodesCopy.add(menuNode);
                }
            }
            nodes = menuNodesCopy;
//        }

        return nodes;
    }
}
