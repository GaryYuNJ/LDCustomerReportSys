package com.ld.report.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;

@Controller
@Scope("prototype")
@RequestMapping({ "samedayFinanceSms" })
public class SamedayFinanceSmsController extends BaseController
{
    @RequestMapping({ "samedayFinanceSms" })
    public ModelAndView samedayFinanceSms(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        return new ModelAndView("report/samedayFinanceSms");
    }
}
