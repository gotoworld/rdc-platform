package com.hsd.devops.modular.system.warpper;

import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.common.constant.state.IsMenu;
import com.hsd.devops.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;


public class MenuWarpper extends BaseControllerWarpper {

    public MenuWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((Integer) map.get("status")));
        map.put("isMenuName", IsMenu.valueOf((Integer) map.get("ismenu")));
    }

}
