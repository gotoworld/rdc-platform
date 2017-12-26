package com.hsd.gitlab.hook.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.hsd.framework.dto.Response;
import com.hsd.gitlab.hook.config.FeignConfiguration;
import com.hsd.gitlab.hook.dto.PageDTO;
import com.hsd.gitlab.hook.dto.SysOutgoingGroupDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description
 *
 * @author Uname.CHEN
 * @version ${date}${time}
 */
@FeignClient(value = "${feign.client.name}", configuration = FeignConfiguration.class) // , fallback = TestServiceHystrix.class)
public interface ISysOutgoingGroupService {
    public static final String FEIGN_URL_PREFIX = "/feign/chatops/gitlabhook/ISysOutgoingGroupService";

    /**
     * <p>信息编辑。
     */
    @PostMapping(FEIGN_URL_PREFIX + "/saveOrUpdateData")
    public Response saveOrUpdateData(SysOutgoingGroupDTO dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @PostMapping(FEIGN_URL_PREFIX + "/deleteData")
    public Response deleteData(SysOutgoingGroupDTO dto) throws Exception;


    /**
     * <p>信息列表 分页。
     */
    @PostMapping(FEIGN_URL_PREFIX + "/findDataIsPage")
    public Page<SysOutgoingGroupDTO> findDataIsPage(PageDTO pageDTO) throws Exception;

    /**
     * <p>信息列表。
     */
    @PostMapping(FEIGN_URL_PREFIX + "/findDataList")
    public List<SysOutgoingGroupDTO> findDataIsList(SysOutgoingGroupDTO dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @PostMapping(FEIGN_URL_PREFIX + "/findDataById")
    public SysOutgoingGroupDTO findDataById(SysOutgoingGroupDTO dto) throws Exception;


}
