package com.hsd.devops.modular.system.warpper;

import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.core.base.warpper.BaseControllerWarpper;
import com.hsd.devops.core.util.ToolUtil;

import java.util.Map;


public class DeptWarpper extends BaseControllerWarpper {

    public DeptWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {

        Integer pid = (Integer) map.get("pid");

        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            map.put("pName", "--");
        } else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
    }

}
