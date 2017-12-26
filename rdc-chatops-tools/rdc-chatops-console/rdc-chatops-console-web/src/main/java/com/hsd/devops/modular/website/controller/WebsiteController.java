package com.hsd.devops.modular.website.controller;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.devops.common.persistence.dao.WebSiteMapper;
import com.hsd.devops.common.persistence.model.Webclassify;
import com.hsd.devops.core.base.controller.BaseController;

/**
 * 网址导航
 *
 * @Date 2017-10-18 15:14:46
 */
@RestController
@RequestMapping("/website")
public class WebsiteController extends BaseController {

    private String PREFIX = "/website/website/";
    
//    @Resource
//    WebSiteMapper websiteMapper;
//
//    @Resource
//    Webclassify webclassify;
    
    
    
    
    
    
    
    

    /**
     * 跳转到网址首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "website.html";
    }

    /**
     * 跳转到添加网址
     */
    @RequestMapping("/website_add")
    public String websiteAdd() {
        return PREFIX + "website_add.html";
    }

    /**
     * 跳转到修改网址
     */
    @RequestMapping("/website_update/{websiteId}")
    public String websiteUpdate(@PathVariable Integer websiteId, Model model) {
        return PREFIX + "website_edit.html";
    }

    /**
     * 获取网址列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增网址
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除网址
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改网址
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 网址详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
