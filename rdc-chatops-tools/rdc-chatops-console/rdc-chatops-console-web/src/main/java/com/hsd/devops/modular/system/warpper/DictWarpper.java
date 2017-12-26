package com.hsd.devops.modular.system.warpper;

import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.common.persistence.model.Dict;
import com.hsd.devops.core.base.warpper.BaseControllerWarpper;
import com.hsd.devops.core.util.ToolUtil;

import java.util.List;
import java.util.Map;


public class DictWarpper extends BaseControllerWarpper {

    public DictWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Integer id = (Integer) map.get("id");
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if(dicts != null){
            for (Dict dict : dicts) {
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
    }

}
