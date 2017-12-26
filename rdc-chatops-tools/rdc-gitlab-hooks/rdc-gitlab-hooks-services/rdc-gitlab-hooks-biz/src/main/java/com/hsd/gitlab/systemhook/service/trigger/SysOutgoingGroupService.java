package com.hsd.gitlab.systemhook.service.trigger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.dto.Response;
import com.hsd.framework.dto.SysErrorCode;
import com.hsd.framework.exception.ServiceException;
import com.hsd.gitlab.hook.api.ISysOutgoingGroupService;
import com.hsd.gitlab.hook.dto.PageDTO;
import com.hsd.gitlab.hook.dto.SysOutgoingGroupDTO;
import com.hsd.gitlab.systemhook.dao.SysOutgoingGroupMapper;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Class Description
 * 
 * @version Jun 26, 20177:57:02 PM
 * @author Ford.CHEN
 */
@FeignService
@Slf4j
public class SysOutgoingGroupService extends ServiceImpl<SysOutgoingGroupMapper, SysOutgoingGroup> implements ISysOutgoingGroupService {



    /**
     * Description
     *
     * @author uname.chen
     * @date ${DATE}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 300, rollbackFor = {Exception.class, RuntimeException.class })
    public Response saveOrUpdateData(@RequestBody SysOutgoingGroupDTO dto) throws Exception {
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                throw new RuntimeException("参数异常!");
            }
            SysOutgoingGroup entity = new SysOutgoingGroup();
            PropertyUtils.copyProperties(entity, dto);
            
            this.insertOrUpdate(entity);
            result.data = entity.getId();
        }
        catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }


    /**
     * Description
     *
     * @author uname.chen
     * @date ${DATE}
     */
    @Override
    public Response deleteData(@RequestBody SysOutgoingGroupDTO dto) throws Exception {
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                throw new RuntimeException("参数异常!");
            }

            if (this.deleteById(dto.getId())) {
                throw new RuntimeException("数据不存在!");
            }
        }
        catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    /**
     * Description
     *
     * @author uname.chen
     * @date ${DATE}
     */
    @Override
    public Page<SysOutgoingGroupDTO> findDataIsPage(PageDTO pageDTO) throws Exception {
        Page<SysOutgoingGroupDTO> pageInfo = null;
        try {
            if (pageDTO == null) {
                throw new RuntimeException("参数异常!");
            }

            Page<SysOutgoingGroup> page = new Page<SysOutgoingGroup>();
            PropertyUtils.copyProperties(page,pageDTO);


            page = this.selectPage(page);

            PropertyUtils.copyProperties(pageInfo,page);
        }
        catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<SysOutgoingGroupDTO> findDataIsList(SysOutgoingGroupDTO dto) throws Exception {
        final List<SysOutgoingGroupDTO> results = new ArrayList<SysOutgoingGroupDTO>();;
        try {
            SysOutgoingGroup entity = new SysOutgoingGroup();
            PropertyUtils.copyProperties(entity, dto);

            List<SysOutgoingGroup> list = this.selectByMap(com.hsd.gitlab.hook.util.BeanUtils.dtoToMap(dto));
            if(list != null){
                list.forEach(sysOutgoingGroup -> {
                    SysOutgoingGroupDTO resultDTO = new SysOutgoingGroupDTO();
                    try {
                        PropertyUtils.copyProperties(resultDTO, sysOutgoingGroup);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    results.add(resultDTO);
                });

            }
        }
        catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return results;
    }

    /**
     * Description
     *
     * @author uname.chen
     * @date ${DATE}
     */
    @Override
    public SysOutgoingGroupDTO findDataById(SysOutgoingGroupDTO dto) throws Exception {
        SysOutgoingGroupDTO result = new SysOutgoingGroupDTO();
        try {
            SysOutgoingGroup entity = this.selectById(dto.getId());

            PropertyUtils.copyProperties(result, entity);
        }
        catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
}
