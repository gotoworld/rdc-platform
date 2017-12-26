/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service.webhooks;

import org.springframework.stereotype.Service;

import com.hsd.gitlab.systemhook.service.IEventHandleService;

import lombok.extern.slf4j.Slf4j;

/**
 * Class Description
 * @version Sep 28, 20176:48:58 PM
 * @author Ford.CHEN
 */
@Service("othersEventHandleService")
@Slf4j
public class OthersEventHandleService implements IEventHandleService {
    
    /* (non-Javadoc)
     * @see com.hsd.gitlab.systemhook.service.IEventHandleService#handle(java.lang.String)
     */
    @Override
    public void handle(String message) {
        log.info("handle other event message:{}",message);
        
    }
    
}
