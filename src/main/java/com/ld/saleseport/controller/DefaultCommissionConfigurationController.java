package com.ld.saleseport.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;

@Controller
@Scope("prototype")
@RequestMapping({ "defaultCommissionConfiguration" })
public class DefaultCommissionConfigurationController extends BaseController
{
    @RequestMapping({ "defaultCommissionConfiguration" })
    public ModelAndView defaultCommissionConfiguration(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)3);
        return new ModelAndView("salesreport/defaultCommissionConfiguration");
    }
}
