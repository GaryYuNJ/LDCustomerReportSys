package com.ld.saleseport.controller;

import com.ld.common.controller.*;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;

import com.ld.saleseport.service.*;

import org.springframework.beans.factory.annotation.*;

import com.ld.saleseport.bo.*;

import org.springframework.web.servlet.*;

import com.ld.core.mybatis.page.*;

import java.util.*;
import java.net.*;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

import org.springframework.ui.*;

import com.ld.common.utils.*;
import com.ld.common.utils.DateUtil;

import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;

import org.springframework.web.multipart.*;

@Controller
@Scope("prototype")
@RequestMapping({ "housingManagement" })
public class HouseMgrController extends BaseController
{
    @Autowired
    HouseMgrService houseMgrService;
    
    @RequestMapping({ "selectcDimData" })
    @ResponseBody
    public List<HouseBo> selectcDimData(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        final String tableName = request.getParameter("tableName");
        final String allName = request.getParameter("allName");
        final String whereStr = request.getParameter("whereStr");
        if (StringUtils.isNotBlank(tableName)) {
            bo.setTableName(tableName);
        }
        if (StringUtils.isNotBlank(whereStr)) {
            bo.setWhereStr(whereStr);
        }
        final List<HouseBo> dataList = this.houseMgrService.selectcDimData(bo);
        if (StringUtils.isNotBlank(allName)) {
            bo.setName(allName);
            dataList.add(0, bo);
        }
        return dataList;
    }
    
    @RequestMapping({ "selectcDimDataAll" })
    @ResponseBody
    public List<HouseBo> selectcDimDataAll(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        final String project_id = request.getParameter("project_id");
        if (StringUtils.isNotBlank(project_id)) {
            bo.setProject_id(Long.valueOf(project_id));
        }
        final List<HouseBo> dataList = this.houseMgrService.selectcDimDataAll(bo);
        return dataList;
    }
    
    @RequestMapping({ "housingManagement" })
    public ModelAndView housingManagement(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)0);
        return new ModelAndView("salesreport/housingManagement");
    }
    
    @RequestMapping({ "houseList" })
    @ResponseBody
    public Pagination<HouseBo> houseList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        try {
            final String userId = request.getParameter("userId");
            this.setRequestParam(request, modelMap, "");
            modelMap.put("userId", (Object)userId);
            final Pagination<HouseBo> pageList = this.houseMgrService.findPage((Map<String, Object>)modelMap, pageNo, 10);
            pageList.setFinacePageHtml("queryHouseList");
            return pageList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void setRequestParam(final HttpServletRequest request, final ModelMap modelMap, final String type) throws Exception {
        if (StringUtils.isNotBlank(request.getParameter("cityCompanyIdQuery"))) {
            modelMap.put("cityCompanyId", (Object)request.getParameter("cityCompanyIdQuery"));
        }
        if (StringUtils.isNotBlank(request.getParameter("projectNameQuery"))) {
            if ("export".equals(type)) {
                modelMap.put("projectName", (Object)URLDecoder.decode(request.getParameter("projectNameQuery"), "UTF-8"));
            }
            else {
                modelMap.put("projectName", (Object)request.getParameter("projectNameQuery"));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("projectPhaseQuery"))) {
            if ("export".equals(type)) {
                modelMap.put("projectPhase", (Object)URLDecoder.decode(request.getParameter("projectPhaseQuery"), "UTF-8"));
            }
            else {
                modelMap.put("projectPhase", (Object)request.getParameter("projectPhaseQuery"));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("houseTypeIdQuery"))) {
            modelMap.put("houseTypeId", (Object)request.getParameter("houseTypeIdQuery"));
        }
        if (StringUtils.isNotBlank(request.getParameter("houseBusinessTypeIdQuery"))) {
            modelMap.put("houseBusinessTypeId", (Object)request.getParameter("houseBusinessTypeIdQuery"));
        }
        if (StringUtils.isNotBlank(request.getParameter("houseConsultantQuery"))) {
            if ("export".equals(type)) {
                modelMap.put("houseConsultant", (Object)URLDecoder.decode(request.getParameter("houseConsultantQuery"), "UTF-8"));
            }
            else {
                modelMap.put("houseConsultant", (Object)request.getParameter("houseConsultantQuery"));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("customerNameQuery"))) {
            if ("export".equals(type)) {
                modelMap.put("customerName", (Object)URLDecoder.decode(request.getParameter("customerNameQuery"), "UTF-8"));
            }
            else {
                modelMap.put("customerName", (Object)request.getParameter("customerNameQuery"));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("erpNameQuery"))) {
            if ("export".equals(type)) {
                modelMap.put("erpName", (Object)URLDecoder.decode(request.getParameter("erpNameQuery"), "UTF-8"));
            }
            else {
                modelMap.put("erpName", (Object)request.getParameter("erpNameQuery"));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("isLargeAmountQuery"))) {
            modelMap.put("isLargeAmount", (Object)request.getParameter("isLargeAmountQuery"));
        }
    }
    
    @RequestMapping({ "updateHouseMsg" })
    @ResponseBody
    public Map<String, Object> updateHouseMsg(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateHouseById(bo);
    }
    
    @RequestMapping({ "selectcEarnestMoneyById" })
    @ResponseBody
    public List<HouseBo> selectcEarnestMoneyById(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setHouse_id(Long.valueOf(request.getParameter("house_id")));
        return this.houseMgrService.selectcEarnestMoneyById(bo);
    }
    
    @RequestMapping({ "selectcEarnestMoneyByProject" })
    @ResponseBody
    public List<HouseBo> selectcEarnestMoneyByProject(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        final String project_id = request.getParameter("project_id");
        final String status = request.getParameter("status");
        final String pay_time = request.getParameter("pay_time");
        if (StringUtils.isNotBlank(project_id)) {
            bo.setProject_id(Long.valueOf(project_id));
        }
        if (StringUtils.isNotBlank(status)) {
            bo.setStatus(Long.valueOf(status));
        }
        if (StringUtils.isNotBlank(pay_time)) {
            bo.setPay_time(pay_time);
        }
        return this.houseMgrService.selectcEarnestMoneyByProject(bo);
    }
    
    @RequestMapping({ "insertEarnestMoneyById" })
    @ResponseBody
    public Map<String, Object> insertEarnestMoneyById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.insertEarnestMoneyById(bo);
    }
    
    @RequestMapping({ "updateEarnestMoneyById" })
    @ResponseBody
    public Map<String, Object> updateEarnestMoneyById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateEarnestMoneyById(bo);
    }
    
    @RequestMapping({ "selectHouseContractMsg" })
    @ResponseBody
    public List<HouseBo> selectHouseContractMsg(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setHouse_id(Long.valueOf(request.getParameter("house_id")));
        return this.houseMgrService.selectHouseContractMsg(bo);
    }
    
    @RequestMapping({ "selectHouseMortgageDataMsg" })
    @ResponseBody
    public List<HouseBo> selectHouseMortgageDataMsg(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setContract_id(Long.valueOf(request.getParameter("contract_id")));
        return this.houseMgrService.selectHouseMortgageDataMsg(bo);
    }
    
    @RequestMapping({ "selectHouseContractMoneyMsg" })
    @ResponseBody
    public List<HouseBo> selectHouseContractMoneyMsg(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setHouse_id(Long.valueOf(request.getParameter("house_id")));
        return this.houseMgrService.selectHouseContractMoneyMsg(bo);
    }
    
    @RequestMapping({ "updateHouseContractMsgById" })
    @ResponseBody
    public Map<String, Object> updateHouseContractMsgById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateHouseContractMsgById(bo);
    }
    
    @RequestMapping({ "updateHouseMortgageDataById" })
    @ResponseBody
    public Map<String, Object> updateHouseMortgageDataById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateHouseMortgageDataById(bo);
    }
    
    @RequestMapping({ "updateHouseContractMoneyById" })
    @ResponseBody
    public Map<String, Object> updateHouseContractMoneyById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateHouseContractMoneyById(bo);
    }
    
    @RequestMapping({ "deleteHouseContractMoneyById" })
    @ResponseBody
    public Map<String, Object> deleteHouseContractMoneyById(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.deleteHouseContractMoneyById(bo);
    }
    
    @RequestMapping({ "selectMoneyPayDetailById" })
    @ResponseBody
    public List<HouseBo> selectMoneyPayDetailById(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setHouse_id(Long.valueOf(request.getParameter("house_id")));
        return this.houseMgrService.selectMoneyPayDetailById(bo);
    }
    
    @RequestMapping({ "insertMoneyPayDetailData" })
    @ResponseBody
    public Map<String, Object> insertMoneyPayDetailData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.insertMoneyPayDetailData(bo);
    }
    
    @RequestMapping({ "updateMoneyPayDetailData" })
    @ResponseBody
    public Map<String, Object> updateMoneyPayDetailData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateMoneyPayDetailData(bo);
    }
    
    @RequestMapping({ "deleteMoneyPayDetailData" })
    @ResponseBody
    public Map<String, Object> deleteMoneyPayDetailData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.deleteMoneyPayDetailData(bo);
    }
    
    @RequestMapping({ "deleteEarnestMoneyDetailData" })
    @ResponseBody
    public Map<String, Object> deleteEarnestMoneyDetailData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.deleteEarnestMoneyDetailData(bo);
    }
    
    @RequestMapping({ "selectReceiptInvoiceDataById" })
    @ResponseBody
    public List<HouseBo> selectReceiptInvoiceDataById(final HttpServletRequest request) {
        final HouseBo bo = new HouseBo();
        bo.setHouse_id(Long.valueOf(request.getParameter("house_id")));
        return this.houseMgrService.selectReceiptInvoiceDataById(bo);
    }
    
    @RequestMapping({ "insertReceiptInvoiceData" })
    @ResponseBody
    public Map<String, Object> insertReceiptInvoiceData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.insertReceiptInvoiceData(bo);
    }
    
    @RequestMapping({ "updateReceiptInvoiceData" })
    @ResponseBody
    public Map<String, Object> updateReceiptInvoiceData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.updateReceiptInvoiceData(bo);
    }
    
    @RequestMapping({ "deleteReceiptInvoiceData" })
    @ResponseBody
    public Map<String, Object> deleteReceiptInvoiceData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        return this.houseMgrService.deleteReceiptInvoiceData(bo);
    }
    
    @RequestMapping({ "/exportHouseData" })
    protected String exportHouseData(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        try {
            final String userId = request.getParameter("userId");
            final String type = request.getParameter("type");
            this.setRequestParam(request, modelMap, type);
            modelMap.put("userId", (Object)userId);
            final List<HouseBo> list = this.houseMgrService.findHouseAll((Map<String, Object>)modelMap);
            final String fileName = "\u623f\u6e90\u5217\u8868_" + DateUtil.getCurrentDateString();
            final HSSFWorkbook workbook = new HSSFWorkbook();
            final HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "\u623f\u6e90\u5217\u8868");
            HSSFRow row = sheet.createRow(0);
            final CellStyle style = (CellStyle)workbook.createCellStyle();
            style.setFillForegroundColor(new HSSFColor.GREY_50_PERCENT().getIndex());
            style.setFillPattern((short)1);
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue("\u9879\u76ee\u540d\u79f0");
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue("\u5206\u671f");
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue("\u697c\u680b");
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue("ERP\u623f\u6e90\u540d\u79f0");
            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue("\u7c7b\u578b");
            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue("\u7f6e\u4e1a\u987e\u95ee");
            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue("\u5ba2\u6237");
            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue("\u4e1a\u6001\u5927\u7c7b");
            cell = row.createCell(8);
            cell.setCellStyle(style);
            cell.setCellValue("\u72b6\u6001");
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); ++i) {
                    row = sheet.createRow(i + 1);
                    final HouseBo bean = list.get(i);
                    row.createCell(0).setCellValue(bean.getProjectName());
                    row.createCell(1).setCellValue(bean.getProjectPhase());
                    row.createCell(2).setCellValue(bean.getBuildName());
                    row.createCell(3).setCellValue(bean.getErpName());
                    row.createCell(4).setCellValue(bean.getHouseTypeName());
                    row.createCell(5).setCellValue(bean.getHouseConsultant());
                    row.createCell(6).setCellValue(bean.getCustomerName());
                    row.createCell(7).setCellValue(bean.getHouseBusinessTypeName());
                    row.createCell(8).setCellValue(bean.getHouseStatusName());
                }
            }
            return this.exportExcel(response, fileName + ".xls", workbook);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    private String exportExcel(final HttpServletResponse response, final String fileName, final HSSFWorkbook workbook) {
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        OutputStream fOut = null;
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "ISO8859-1"));
            fOut = (OutputStream)response.getOutputStream();
            workbook.write(fOut);
        }
        catch (UnsupportedEncodingException ex) {}
        catch (Exception ex2) {}
        finally {
            try {
                fOut.flush();
                fOut.close();
            }
            catch (IOException ex3) {}
        }
        return null;
    }
    
    @RequestMapping({ "addHouseData" })
    @ResponseBody
    public Map<String, Object> addHouseData(@ModelAttribute final HouseBo bo, final HttpServletRequest request, final HttpServletResponse response, final ModelMap model) {
        final String fileNameId = request.getParameter("picTypeId");
        final MultipartHttpServletRequest mgsq = (MultipartHttpServletRequest)request;
        final MultipartFile file = mgsq.getFile(fileNameId);
        return this.houseMgrService.insertHouseData(file, bo);
    }
}
