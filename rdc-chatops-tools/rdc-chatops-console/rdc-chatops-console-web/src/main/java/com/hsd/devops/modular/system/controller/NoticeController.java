package com.hsd.devops.modular.system.controller;

import com.hsd.devops.common.annotion.BussinessLog;
import com.hsd.devops.common.constant.Dict;
import com.hsd.devops.common.constant.factory.ConstantFactory;
import com.hsd.devops.common.exception.BizExceptionEnum;
import com.hsd.devops.common.exception.BussinessException;
import com.hsd.devops.common.persistence.dao.NoticeMapper;
import com.hsd.devops.common.persistence.model.Notice;
import com.hsd.devops.core.base.controller.BaseController;
import com.hsd.devops.core.log.LogObjectHolder;
import com.hsd.devops.core.shiro.ShiroKit;
import com.hsd.devops.core.util.ToolUtil;
import com.hsd.devops.modular.system.dao.NoticeDao;
import com.hsd.devops.modular.system.warpper.NoticeWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private NoticeDao noticeDao;

    
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Notice notice = this.noticeMapper.selectById(noticeId);
        model.addAttribute("notice",notice);
        LogObjectHolder.me().set(notice);
        return PREFIX + "notice_edit.html";
    }

    
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> notices = noticeDao.list(null);
        super.setAttr("noticeList",notices);
        return "/blackboard.html";
    }

    
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.noticeDao.list(condition);
        return super.warpObject(new NoticeWrapper(list));
    }

    
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增通知",key = "title",dict = Dict.NoticeMap)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreater(ShiroKit.getUser().getId());
        notice.setCreatetime(new Date());
        notice.insert();
        return super.SUCCESS_TIP;
    }

    
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知",key = "noticeId",dict = Dict.DeleteDict)
    public Object delete(@RequestParam Integer noticeId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.noticeMapper.deleteById(noticeId);

        return SUCCESS_TIP;
    }

    
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改通知",key = "title",dict = Dict.NoticeMap)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeMapper.selectById(notice.getId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.updateById();
        return super.SUCCESS_TIP;
    }

}
