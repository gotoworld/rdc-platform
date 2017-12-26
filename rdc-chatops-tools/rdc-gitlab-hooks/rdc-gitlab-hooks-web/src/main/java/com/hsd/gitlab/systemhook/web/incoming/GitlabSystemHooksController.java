/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.web.incoming;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.gitlab.hook.api.IGitlabSystemHooksService;

import lombok.extern.slf4j.Slf4j;

/**
 * Class Description
 * @version Sep 27, 20171:59:02 PM
 * @author Ford.CHEN
 */
@RestController
@Slf4j
@Api("Gitlab SystemHooks")
public class GitlabSystemHooksController {

    public static final String WEB_PREFIX = "/web/chatops/gitlabhook/incoming";

    
    @Resource
    IGitlabSystemHooksService gitlabSystemHooksService;


    
    /**
     * 
     * Method Description
     * @version Sep 28, 20176:43:54 PM
     * @author Ford.CHEN
     * @param message
     */
    @ApiOperation(value="gitlab systemhooks listener", notes="gitlab systemhooks listener")
    @ApiImplicitParam(name = "message", value = "message", required = true, dataType = "String")
    @PostMapping(value = WEB_PREFIX + "/systemhooks", consumes = "application/json")
    public void trigger(@RequestBody String message) {
        log.info("Received hooks message: {}", message);
        
        if(StringUtils.contains(message, "event_name")){
            gitlabSystemHooksService.handle(message);
        }
        
        log.info("Finished hooks message handle");
    }
    
    
}
