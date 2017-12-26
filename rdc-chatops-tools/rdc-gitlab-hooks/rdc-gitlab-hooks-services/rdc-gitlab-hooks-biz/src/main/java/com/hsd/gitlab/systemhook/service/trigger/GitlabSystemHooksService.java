package com.hsd.gitlab.systemhook.service.trigger;

import com.alibaba.fastjson.JSON;
import com.hsd.framework.annotation.FeignService;
import com.hsd.gitlab.systemhook.bean.event.BaseEvent;
import com.hsd.gitlab.systemhook.service.IEventHandleService;
import com.hsd.gitlab.systemhook.service.webhooks.EventHandleServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.hsd.gitlab.hook.api.IGitlabSystemHooksService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * Description
 *
 * @author uname.chen
 * @date 2017/11/27 0027
 */
@FeignService
@Slf4j
public class GitlabSystemHooksService implements IGitlabSystemHooksService {


    @Resource
    EventHandleServiceFactory eventHandleServiceFactory;

    /**
     *
     * Method Description
     * @version Sep 28, 20176:43:54 PM
     * @author Ford.CHEN
     * @param message
     */
    public void handle(@RequestBody String message) {
        log.info("received hooks message: {}", message);

        if(StringUtils.contains(message, "event_name")){
            BaseEvent event = JSON.parseObject(message, BaseEvent.class);

            IEventHandleService eventHandleService = eventHandleServiceFactory.getEventHandleService(event.getEventName());
            eventHandleService.handle(message);
        }

        log.info("Finished hooks message handle");
    }

}
