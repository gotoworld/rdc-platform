package com.hsd.gitlab.hook.util;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.hsd.gitlab.hook.dto.IMType;
import com.hsd.gitlab.hook.dto.SysOutgoingGroupDTO;

/**
 * Description
 *
 * @author uname.chen
 * @date 2017/11/29 0029
 */
public class BeanUtilsTest {


    @Test
    public void testDtoToMap(){
        SysOutgoingGroupDTO dto = new SysOutgoingGroupDTO();
        dto.setGitlabGroupName("goldP2p");
        dto.setImType(IMType.bearychat);
        dto.setId(122l);

        Map map = BeanUtils.dtoToMap(dto);

        Assert.isTrue("goldP2p".equals(map.get("gitlab_group_name")),"should be equals");
    }


}
