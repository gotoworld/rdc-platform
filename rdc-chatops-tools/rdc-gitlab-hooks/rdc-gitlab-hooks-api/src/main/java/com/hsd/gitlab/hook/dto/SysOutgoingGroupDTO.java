package com.hsd.gitlab.hook.dto;

import lombok.Data;

/**
 * Description
 *
 * @author uname.chen
 * @date ${DATE}
 */
@Data
public class SysOutgoingGroupDTO extends IdEntityDTO {

    private String gitlabGroupName;
    private IMType imType;
    private String imUrl;
    
    private String name;
    private String description;
    
    private String event;




    
}