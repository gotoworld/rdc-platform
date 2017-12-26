package com.hsd.devops.modular.system.warpper;

import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.core.base.warpper.BaseControllerWarpper;

import java.util.Map;


public class NoticeWrapper extends BaseControllerWarpper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
