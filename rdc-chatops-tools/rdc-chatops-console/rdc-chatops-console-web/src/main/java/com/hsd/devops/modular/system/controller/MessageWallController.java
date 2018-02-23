package com.hsd.devops.modular.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hsd.devops.common.persistence.dao.MessageWallMapper;
import com.hsd.devops.common.persistence.dao.UserMapper;
import com.hsd.devops.common.persistence.model.MessageWall;
import com.hsd.devops.common.persistence.model.User;
import com.hsd.devops.core.base.controller.BaseController;
import com.hsd.devops.core.base.tips.ErrorTip;
import com.hsd.devops.core.base.tips.Tip;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 留言墙
 *
 */
@Controller
@RequestMapping("/message/wall")
public class MessageWallController extends BaseController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageWallMapper messageWallMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 进入留言墙页面
     */
    @RequestMapping("/view")
//    @RequiresPermissions("publish:message:view")
    public String toChatView(ModelMap map) {
        int num = 50;
        //获取指定数量的留言列表
        List<MessageWall> messageList = messageWallMapper.getMessageList(num);
        map.put("messageList",messageList);
        return "/system/msg_wall/view.html";
    }

    /**
     * 新增留言消息
     * @param request
     * @param msg
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Tip addMessage(HttpServletRequest request, MessageWall msg) {
        if(StringUtils.isBlank(msg.getContent()) || "".equals(msg.getContent().replaceAll(" ",""))){
            return new ErrorTip(10001,"请输入留言内容");
        }
        try {
            msg.setCreateTime(new Date());
            msg.setUpdateTime(new Date());
            msg.setStatus(0);

            //用户名为空，就指定当前登录的系统用户名
            if(StringUtils.isBlank(msg.getUserName())){
                Subject subject = SecurityUtils.getSubject();
                String userName = String.valueOf(subject.getPrincipal());
                User user = new User();
                user.setName(userName);
                user = userMapper.selectOne(user);
                msg.setUserName(userName);
                msg.setUserId(Long.parseLong(""+user.getId()));
            }
            msg.setIpAddress(getIpAddr(request));
            messageWallMapper.insert(msg);
        } catch (Exception e) {
            return new ErrorTip(10002,"留言提交失败，请稍后重试");
        }
        return SUCCESS_TIP;
    }


    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        //这种情况下，是因为客户端使用了localhost访问项目，将localhos换成127.0.0.1就变成了IPV4
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }

        return ip;
    }
}
