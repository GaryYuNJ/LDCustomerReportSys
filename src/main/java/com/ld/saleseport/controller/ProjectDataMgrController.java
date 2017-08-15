package com.ld.saleseport.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import com.ld.saleseport.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;
import com.ld.core.mybatis.page.*;
import com.ld.saleseport.bo.*;
import com.ld.common.utils.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;

@Controller
@Scope("prototype")
@RequestMapping({ "projectDataManagement" })
public class ProjectDataMgrController extends BaseController
{
    @Autowired
    ProjectDataMgrService projectDataMgrService;
    
    @RequestMapping({ "projectDataManagement" })
    public ModelAndView projectDataManagement(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("pageIndex", (Object)1);
        return new ModelAndView("salesreport/projectDataManagement");
    }
    
    @RequestMapping({ "projectList" })
    @ResponseBody
    public Pagination<HouseBo> projectList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        final Pagination<HouseBo> pageList = this.projectDataMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryProjectList");
        return pageList;
    }
    
    @RequestMapping({ "updateProjectMsgById" })
    @ResponseBody
    public Map<String, Object> updateProjectMsgById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.projectDataMgrService.updateProjectMsgById(bo);
    }
}
