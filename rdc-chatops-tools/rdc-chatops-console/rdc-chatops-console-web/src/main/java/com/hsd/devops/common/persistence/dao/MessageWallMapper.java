package com.hsd.devops.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsd.devops.common.persistence.model.MessageWall;
import com.hsd.devops.common.persistence.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MessageWallMapper extends BaseMapper<MessageWall> {
    /**
     * 获取指定数量的留言列表
     * @param num
     * @return
     */
    List<MessageWall> getMessageList(@Param("num") int num);
}