package com.ld.report.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;

@Controller
@Scope("prototype")
@RequestMapping({ "historyFinance" })
public class HistoryFinanceController extends BaseController
{
    @RequestMapping({ "historyFinance" })
    public ModelAndView history(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        System.out.println("test222222*************************");
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)1);
        return new ModelAndView("report/historyFinance");
    }
}
