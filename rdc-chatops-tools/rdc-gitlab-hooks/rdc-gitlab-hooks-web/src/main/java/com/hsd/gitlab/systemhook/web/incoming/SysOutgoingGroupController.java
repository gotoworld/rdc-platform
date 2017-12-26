/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.web.incoming;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsd.framework.dto.Response;
import com.hsd.gitlab.hook.api.ISysOutgoingGroupService;
import com.hsd.gitlab.hook.dto.PageDTO;
import com.hsd.gitlab.hook.dto.SysOutgoingGroupDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class Description
 * @version Oct 10, 20171:45:56 PM
 * @author Ford.CHEN
 */
@RestController
@Slf4j
@Api
public class SysOutgoingGroupController {

    public static final String WEB_PREFIX = "/api/outgoinggroup";
    
    @Resource
    ISysOutgoingGroupService sysOutgoingGroupService;
    
    
    /**
     * 
     * Method Description
     * @version Oct 12, 20176:29:59 PM
     * @author Ford.CHEN
     * @param sysOutgoingGroup
     * @return
     */
    @ApiOperation(value="Create a Group level of Outgoing", notes="Create a Group level of Outgoing")
    @ApiImplicitParam(name = "sysOutgoingGroup", value = "SysOutgoingGroup to be create", required = true, dataType = "SysOutgoingGroup")
    @PostMapping(WEB_PREFIX + "/add")
    public Response addOrEdit(@RequestBody SysOutgoingGroupDTO sysOutgoingGroup){
        log.info("receied groud create or update:{}",sysOutgoingGroup);

        Response response = null;
        try {
            response = sysOutgoingGroupService.saveOrUpdateData(sysOutgoingGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    /**
     * 
     * Method Description
     * @version Oct 20, 20175:18:59 PM
     * @author Ford.CHEN
     * @param outgoinggroupId
     * @return
     */
    @ApiOperation(value="info Group level of Outgoing", notes="Info Group level of Outgoing")
    @ApiImplicitParam(name = "outgoinggroupId", value = "outgoinggroupId", required = true, dataType = "Long")
    @PostMapping(WEB_PREFIX)
    public SysOutgoingGroupDTO info(@RequestBody Long outgoinggroupId){

        SysOutgoingGroupDTO dto = new SysOutgoingGroupDTO();
        dto.setId(outgoinggroupId);
        try {
            dto = sysOutgoingGroupService.findDataById(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }


    /**
     *
     * Method Description
     * @version Oct 11, 20173:05:03 PM
     * @author Ford.CHEN
     * @param pageDTO
     * @return
     */
    @ApiOperation(value="Group level of Outgoing", notes="Group level of Outgoing page list")
    @ApiImplicitParam(name = "pageDTO", value = "分页DTO", required = true, dataType = "Page<SysOutgoingGroup>")
    @PostMapping(WEB_PREFIX + "/list")
    public Page<SysOutgoingGroupDTO> list(@RequestBody PageDTO pageDTO){

        Page<SysOutgoingGroupDTO> page = null;
        try {
            page = sysOutgoingGroupService.findDataIsPage(pageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }


    /**
     *
     * Method Description
     * @version Oct 20, 20175:18:53 PM
     * @author Ford.CHEN
     * @param outgoinggroupId
     * @return
     */
    @ApiOperation(value="Remove Group level of Outgoing", notes="Remove Group level of Outgoing")
    @ApiImplicitParam(name = "outgoinggroupId", value = "outgoinggroupId", required = true, dataType = "Long")
    @PostMapping(WEB_PREFIX + "/delete")
    public Response delete(@RequestBody Long outgoinggroupId){
        log.info("delete group by id:{}",outgoinggroupId);

        Response response = null;

        SysOutgoingGroupDTO dto = new SysOutgoingGroupDTO();
        dto.setId(outgoinggroupId);
        try {
            response = sysOutgoingGroupService.deleteData(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
    
}
