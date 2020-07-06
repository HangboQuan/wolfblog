package com.tencent.controller.admin;

import com.tencent.entity.Notice;
import com.tencent.enums.NoticeStatus;
import com.tencent.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
@Controller
@RequestMapping("/admin/notice")
public class NoticeController {

    @Autowired(required = false)
    private INoticeService noticeService;

    @RequestMapping("")
    public String index(Model model){
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList",noticeList);
        return "admin/notice/index";
    }

    @RequestMapping("/insert")
    public String insertNoticeView(Notice notice){
        noticeService.insertNotice(notice);
        return "admin/notice/insert";
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice){
       notice.setNoticeCreateTime(new Date());
       notice.setNoticeUpdateTime(new Date());
       //这里的参数没有设置
       notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
       notice.setNoticeOrder(1);
       noticeService.insertNotice(notice);
       return "redirect:/admin/notice";
    }

    @RequestMapping("/edit/{id}")
    public String editNoticeView(@PathVariable("id")Integer id,Model model){
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice",notice);
        return "admin/notice/edit";
    }

    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice){
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }

    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id")Integer id){
        noticeService.deleteNotice(id);
        return "redirect:/admin/notice";
    }
}
