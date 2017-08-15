package com.ld.saleseport.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import com.ld.saleseport.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.*;
import com.ld.core.mybatis.page.*;
import com.ld.saleseport.bo.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import org.springframework.ui.*;
import com.alibaba.fastjson.*;
import com.ld.common.utils.*;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping({ "salesReportManagement" })
public class SalesReportMgrController extends BaseController
{
    @Autowired
    SalesReportMgrService salesReportMgrService;
    
    @RequestMapping({ "salesReportManagement" })
    public ModelAndView salesReportManagement(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)2);
        return new ModelAndView("salesreport/salesReportManagement");
    }
    
    @RequestMapping({ "earnestMoneyReportList" })
    @ResponseBody
    public Pagination<SalesReportBo> earnestMoneyReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        final String dateQuery = request.getParameter("dateQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        if (StringUtils.isNotBlank(dateQuery)) {
            modelMap.put("dateStr", (Object)dateQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("selectSql", "find_all_earnest_money_rpt");
        modelMap.put("countSql", "find_count_earnest_money_rpt");
        final Pagination<SalesReportBo> pageList = this.salesReportMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryEarnestMoneyReportList");
        return pageList;
    }
    
    @RequestMapping({ "moneyPayReportList" })
    @ResponseBody
    public Pagination<SalesReportBo> moneyPayReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        final String dateQuery = request.getParameter("dateQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        if (StringUtils.isNotBlank(dateQuery)) {
            modelMap.put("dateStr", (Object)dateQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("selectSql", "find_all_money_pay_rpt");
        modelMap.put("countSql", "find_count_money_pay_rpt");
        final Pagination<SalesReportBo> pageList = this.salesReportMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryMoneyPayReportList");
        return pageList;
    }
    
    @RequestMapping({ "receivableMoneyReportList" })
    @ResponseBody
    public Pagination<SalesReportBo> receivableMoneyList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        final String dateQuery = request.getParameter("dateQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        if (StringUtils.isNotBlank(dateQuery)) {
            modelMap.put("dateStr", (Object)dateQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("selectSql", "find_all_receivable_money_rpt");
        modelMap.put("countSql", "find_count_receivable_money_rpt");
        final Pagination<SalesReportBo> pageList = this.salesReportMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryReceivableMoneyReportList");
        return pageList;
    }
    
    @RequestMapping({ "incomeBillReportList" })
    @ResponseBody
    public Pagination<SalesReportBo> incomeBillReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        final String dateQuery = request.getParameter("dateQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        if (StringUtils.isNotBlank(dateQuery)) {
            modelMap.put("dateStr", (Object)dateQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("selectSql", "find_all_income_bill_rpt");
        modelMap.put("countSql", "find_count_income_bill_rpt");
        final Pagination<SalesReportBo> pageList = this.salesReportMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryIncomeBillReportList");
        return pageList;
    }
    
    @RequestMapping({ "invoiceBillReportList" })
    @ResponseBody
    public Pagination<SalesReportBo> invoiceBillReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
        final String projectDataQuery = request.getParameter("projectDataQuery");
        final String dateQuery = request.getParameter("dateQuery");
        if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
            modelMap.put("cityCompanyId", (Object)cityCompanyIdQuery);
        }
        else if (StringUtils.isNotBlank(projectDataQuery)) {
            modelMap.put("id", (Object)projectDataQuery);
        }
        if (StringUtils.isNotBlank(dateQuery)) {
            modelMap.put("dateStr", (Object)dateQuery);
        }
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("selectSql", "find_all_invoice_bill_rpt");
        modelMap.put("countSql", "find_count_invoice_bill_rpt");
        final Pagination<SalesReportBo> pageList = this.salesReportMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
        pageList.setFinacePageHtml("queryInvoiceBillReportList");
        return pageList;
    }
    
    @RequestMapping({ "/exportReportData" })
    protected String exportReportData(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        try {
            final Map<String, Object> queryMap = new HashMap<String, Object>();
            final String userId = request.getParameter("userId");
            final String reportType = request.getParameter("reportType");
            final String cityCompanyIdQuery = request.getParameter("cityCompanyIdQuery");
            final String projectDataQuery = request.getParameter("projectDataQuery");
            final String dateQuery = request.getParameter("dateQuery");
            String fileName = "";
            String columnZh = "";
            String columnEn = "";
            if (StringUtils.isNotBlank(cityCompanyIdQuery) && StringUtils.isBlank(projectDataQuery)) {
                queryMap.put("cityCompanyId", cityCompanyIdQuery);
            }
            else if (StringUtils.isNotBlank(projectDataQuery)) {
                queryMap.put("id", projectDataQuery);
            }
            if (StringUtils.isNotBlank(dateQuery)) {
                queryMap.put("dateStr", dateQuery);
            }
            List<SalesReportBo> dataList = new ArrayList<SalesReportBo>();
            final String date = DateUtil.getCurrentDateString("yyyyMMdd");
            if ("1".equals(reportType)) {
                fileName = "\u8bda\u610f\u91d1\u53f0\u8d26\u62a5\u8868_" + date;
                columnZh = "['\u9879\u76ee\u540d\u79f0','ERP\u623f\u6e90\u540d\u79f0','\u6536\u6b3e\u65f6\u95f4','\u59d3\u540d','\u6536\u636e\u53f7','\u5b9e\u6536\u91d1\u989d','\u8f6c\u623f\u6b3e','\u9000\u8f6c\u65e5\u671f','\u7ed3\u4f59\u91d1\u989d']";
                columnEn = "['project_name','erp_name','pay_time','customer_name','code','pay_amount','convert_house_amount','refund_time','remain_amount']";
                dataList = this.salesReportMgrService.findAllTableEarnestMoneyRpt(queryMap);
            }
            else if ("2".equals(reportType)) {
                fileName = "\u6536\u6b3e\u65e5\u62a5\u8868_" + date;
                columnZh = "['\u65e5\u671f','\u9879\u76ee\u540d\u79f0','\u697c\u680b','ERP\u623f\u6e90\u540d\u79f0','\u6536\u636e\u59d3\u540d','\u91d1\u989d\u7c7b\u578b','\u6536\u6b3e\u65b9\u5f0f','\u5b9e\u6536\u6b3e','\u5df2\u6536\u6b3e']";
                columnEn = "['project_name','erp_name','pay_time','customer_name','code','pay_amount','convert_house_amount','refund_time','remain_amount']";
                dataList = this.salesReportMgrService.findAllTableMoneyPayRpt(queryMap);
            }
            else if ("3".equals(reportType)) {
                fileName = "\u5e94\u6536\u6b3e\u62a5\u8868_" + date;
                columnZh = "['\u9879\u76ee\u540d\u79f0','ERP\u623f\u6e90\u540d\u79f0','\u5ba2\u6237\u59d3\u540d','\u5e94\u6536\u65e5\u671f','\u8d44\u91d1\u7c7b\u578b','\u5408\u540c\u91d1\u989d','\u5df2\u6536\u6b3e','\u5e94\u6536\u6b3e','\u903e\u671f\u539f\u56e0','\u662f\u5426\u80fd\u6536\u56de','\u9884\u8ba1\u6536\u56de\u65f6\u95f4','\u5176\u4ed6\u539f\u56e0','\u903e\u671f\u65f6\u6bb5']";
                columnEn = "['project_name','erp_name','pay_time','customer_name','code','pay_amount','convert_house_amount','refund_time','remain_amount']";
                dataList = this.salesReportMgrService.findAllTableReceivableMoneyRpt(queryMap);
            }
            else if ("4".equals(reportType)) {
                fileName = "\u6536\u5165\u7ed3\u8d26\u62a5\u8868_" + date;
                columnZh = "['\u9879\u76ee\u540d\u79f0','ERP\u623f\u6e90\u540d\u79f0','\u5ba2\u6237\u59d3\u540d','\u5b9e\u6d4b\u9762\u79ef','\u623f\u6e90\u5355\u4ef7','\u623f\u6e90\u603b\u91d1\u989d','\u662f\u5426\u5df2\u5f00\u4ea4\u623f\u53d1\u7968','\u662f\u5426\u5df2\u5168\u989d\u9884\u7f34\u589e\u503c\u7a0e','\u6210\u672c\u5355\u4ef7']";
                columnEn = "['project_name','erp_name','pay_time','customer_name','code','pay_amount','convert_house_amount','refund_time','remain_amount']";
                dataList = this.salesReportMgrService.findAllTableIncomeBillRpt(queryMap);
            }
            else if ("5".equals(reportType)) {
                fileName = "\u5f00\u7968\u60c5\u51b5\u62a5\u8868_" + date;
                columnZh = "['\u9879\u76ee\u540d\u79f0','ERP\u623f\u6e90\u540d\u79f0','\u5f00\u7968\u65e5\u671f','\u7968\u636e\u7c7b\u578b','\u7968\u636e\u53f7','\u91d1\u989d','\u91d1\u989d\u7c7b\u578b','\u662f\u5426\u5df2\u9884\u7f34\u589e\u503c\u7a0e','\u72b6\u6001']";
                columnEn = "['project_name','erp_name','pay_time','customer_name','code','pay_amount','convert_house_amount','refund_time','remain_amount']";
                dataList = this.salesReportMgrService.findAllTableInvoiceBillRpt(queryMap);
            }
            final JSONArray column_zh_Array = JSONArray.parseArray(columnZh);
            final JSONArray column_en_Array = JSONArray.parseArray(columnEn);
            final String[] column_zh = new String[column_zh_Array.size()];
            Iterator i = column_zh_Array.iterator();
            int index = 0;
            while (i.hasNext()) {
                final String str = i.next().toString();
                column_zh[index] = str;
                ++index;
            }
            final String[] column_en = new String[column_en_Array.size()];
            i = column_en_Array.iterator();
            index = 0;
            while (i.hasNext()) {
                final String str2 = i.next().toString();
                column_en[index] = str2;
                ++index;
            }
            final ExcelUtil<SalesReportBo> export = new ExcelUtil<SalesReportBo>();
            return export.prepareExcel(column_en, column_zh, fileName, dataList, SalesReportBo.class, response);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
