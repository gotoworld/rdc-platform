package com.hsd.gitlab.hook.api;

import com.hsd.gitlab.hook.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description
 *
 * @author uname.chen
 * @date 2017/11/27 0027
 */
@FeignClient(value = "${feign.client.name}", configuration = FeignConfiguration.class) // , fallback = TestServiceHystrix.class)
public interface IGitlabSystemHooksService {
    public static final String FEIGN_URL_PREFIX = "/feign/chatops/gitlabhook/gitlabsystemhooksservice";

    /**
     *
     * Method Description
     * @version Sep 28, 20176:38:04 PM
     * @author Ford.CHEN
     * @param message
     */
//    @PostMapping(name=FEIGN_URL_PREFIX + "/incoming", consumes = "application/json")
    @RequestMapping(method = {RequestMethod.POST},value = FEIGN_URL_PREFIX + "/incoming")
    public void handle(String message);

}
