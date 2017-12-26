package com.hsd.devops.core.base.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.hsd.devops.core.base.tips.SuccessTip;
import com.hsd.devops.core.base.warpper.BaseControllerWarpper;
import com.hsd.devops.core.page.PageInfoBT;
import com.hsd.devops.core.support.HttpKit;
//import com.hsd.devops.core.util.FileUtil;

public class BaseController {

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }


    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }


    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }
//
//
//    protected void deleteCookieByName(String cookieName) {
//        Cookie[] cookies = this.getHttpServletRequest().getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(cookieName)) {
//                Cookie temp = new Cookie(cookie.getName(), "");
//                temp.setMaxAge(0);
//                this.getHttpServletResponse().addCookie(temp);
//            }
//        }
//    }
//
//
//    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
//        byte[] bytes = FileUtil.toByteArray(filePath);
//        return renderFile(fileName, bytes);
//    }
//
//
//    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
//        String dfileName = null;
//        try {
//            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", dfileName);
//        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
//    }
}
