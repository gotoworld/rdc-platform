package com.hsd.devops.modular.system.factory;

import com.hsd.devops.modular.system.transfer.UserDto;
import com.hsd.devops.common.persistence.model.User;
import org.springframework.beans.BeanUtils;


public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
