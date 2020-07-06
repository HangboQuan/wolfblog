package com.tencent.controller.admin;

import com.tencent.entity.Options;
import com.tencent.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/options")
public class OptionsController {

    @Autowired(required = false)
    private IOptionsService optionsService;

    /**
     * 基本信息展示
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        Options options = optionsService.getOptions();
        modelAndView.addObject("options",options);

        modelAndView.setViewName("admin/options/index");
        return modelAndView;
    }

    /**
     * 编辑基本信息展示
     */
    @RequestMapping("/edit")
    public ModelAndView editOptionsView(){
        ModelAndView modelAndView = new ModelAndView();
        Options options = optionsService.getOptions();
        modelAndView.addObject("options",options);

        modelAndView.setViewName("admin/options/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionsSubmit(Options options){
        Options optionsCustom = optionsService.getOptions();
        if(null == optionsCustom.getOptionId()){
            optionsService.insertOptions(options);
        }else{
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }

}
