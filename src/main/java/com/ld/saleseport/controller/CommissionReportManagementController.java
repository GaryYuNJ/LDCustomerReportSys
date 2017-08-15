package com.ld.saleseport.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;

@Controller
@Scope("prototype")
@RequestMapping({ "commissionReportManagement" })
public class CommissionReportManagementController extends BaseController
{
    @RequestMapping({ "commissionReportManagement" })
    public ModelAndView commissionReportManagement(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)4);
        return new ModelAndView("salesreport/commissionReportManagement");
    }
}
