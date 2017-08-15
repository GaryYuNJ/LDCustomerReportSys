package com.ld.report.controller;

import com.ld.common.controller.*;

import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;

import com.ld.report.service.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;

import java.text.*;

import org.springframework.web.multipart.*;

import com.ld.core.mybatis.page.*;
import com.ld.report.bo.*;
import com.ld.common.utils.*;
import com.ld.common.utils.DateUtil;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import java.math.*;

import org.springframework.ui.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.awt.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

@Controller
@Scope("prototype")
@RequestMapping({ "financeReport" })
public class FinanceReportController extends BaseController
{
  /*  @Autowired
    FinanceReportService financeReportService;
    private DecimalFormat decimalFormat;
    
    public FinanceReportController() {
        this.decimalFormat = new DecimalFormat("#");
    }
    
    @RequestMapping({ "financeReport" })
    public ModelAndView report(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)2);
        return new ModelAndView("report/financeReport");
    }
    
    @RequestMapping({ "uploadApplyFile" })
    public void uploadApplyFile(final HttpServletRequest request, final HttpServletResponse response) {
        final String fileNameId = request.getParameter("picTypeId");
        final MultipartHttpServletRequest mgsq = (MultipartHttpServletRequest)request;
        final MultipartFile mpFile = mgsq.getFile(fileNameId);
        final String suffix = mpFile.getOriginalFilename().substring(mpFile.getOriginalFilename().lastIndexOf("."));
        if (!".png".equals(suffix.toLowerCase()) && !".gif".equals(suffix.toLowerCase()) && !".bmp".equals(suffix.toLowerCase()) && !".jpg".equals(suffix.toLowerCase()) && !".jpeg".equals(suffix.toLowerCase())) {
            ResponseUtils.renderText(response, "inValidFile");
            return;
        }
        String pathDir = this.getProperties("config.properties", "FILE_PATH");
        final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        pathDir = pathDir + "/" + date;
        final String uid = StringUtils.getUUID(30);
        final String fileName = pathDir + "/" + uid + suffix;
        final File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            mpFile.transferTo(file);
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        final String showPath = date + "/" + uid + suffix;
        ResponseUtils.renderText(response, showPath);
    }
    
    public String getProperties(final String fileNamePath, final String key) {
        final Properties props = new Properties();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream(fileNamePath);
            props.load(in);
            final String value = props.getProperty(key);
            return value;
        }
        catch (IOException e) {
            return null;
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    @RequestMapping({ "cityFinanceReportList" })
    @ResponseBody
    public Pagination<FinanceReportBo> cityFinanceReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        String cityCurrentDateQuery = request.getParameter("cityCurrentDateQuery");
        final String cityBranchCompanyQuery = request.getParameter("cityBranchCompanyQuery");
        final String citySubCompanyQuery = request.getParameter("citySubCompanyQuery");
        final String statusQuery = request.getParameter("statusQuery");
        if (StringUtils.isBlank(cityCurrentDateQuery)) {
            cityCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        if (StringUtils.isNotBlank(cityBranchCompanyQuery) && StringUtils.isBlank(citySubCompanyQuery)) {
            modelMap.put("queryType", (Object)cityBranchCompanyQuery);
        }
        else if (StringUtils.isNotBlank(citySubCompanyQuery)) {
            modelMap.put("id", (Object)citySubCompanyQuery);
        }
        if (StringUtils.isNotBlank(statusQuery)) {
            modelMap.put("status", (Object)statusQuery);
        }
        modelMap.put("tableName", "c_sub_company");
        modelMap.put("type", "type");
        modelMap.put("userId", (Object)Long.valueOf(userId));
        modelMap.put("dateStr", (Object)cityCurrentDateQuery);
        final Pagination<FinanceReportBo> pageList = this.financeReportService.findPage((Map<String, Object>)modelMap, pageNo, 15);
        if (null != pageList.getList() && pageList.getList().size() > 0) {
            final List<FinanceReportBo> dateList = pageList.getList();
            final List<Long> companyIdList = new ArrayList<Long>();
            for (final FinanceReportBo bean : dateList) {
                companyIdList.add(bean.getId());
            }
            final long start = System.currentTimeMillis();
            final Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", companyIdList);
            map.put("tableName", "c_sub_company_his_balance");
            map.put("dateStr", DateUtil.getPreDate(cityCurrentDateQuery));
            List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceTotalAllList(map);
            if (null != list && list.size() > 0) {
                this.setCompanyBalance(dateList, list, "lastday");
            }
            map.put("dateStr", cityCurrentDateQuery);
            map.put("tableName", "c_income");
            list = this.financeReportService.selectSubCompBalanceTotalAllList(map);
            if (null != list && list.size() > 0) {
                this.setCompanyBalance(dateList, list, "income");
            }
            map.put("tableName", "c_cost");
            list = this.financeReportService.selectSubCompBalanceTotalAllList(map);
            if (null != list && list.size() > 0) {
                this.setCompanyBalance(dateList, list, "cost");
            }
            map.put("tableName", "c_his_invalid_capital");
            list = this.financeReportService.selectSubCompBalanceTotalAllList(map);
            if (null != list && list.size() > 0) {
                this.setCompanyBalance(dateList, list, "invalid");
            }
            map.put("tableName", "c_sub_company_his_balance");
            list = this.financeReportService.selectSubCompBalanceTotalAllList(map);
            if (null != list && list.size() > 0) {
                this.setCompanyBalance(dateList, list, "today");
            }
            final long end = System.currentTimeMillis();
            System.out.println("\u67e5\u8be2\u8017\u65f6:[" + (end - start) + "ms," + (end - start) / 1000L + "\u79d2]");
        }
        //pageList.setFinacePageHtml("querycityFinanceReport");
        return pageList;
    }
    
    private void setCompanyBalance(final List<FinanceReportBo> companyList, final List<FinanceReportBo> balanceList, final String type) {
        if (companyList != null && companyList.size() > 0) {
            for (final FinanceReportBo company : companyList) {
                for (final FinanceReportBo balance : balanceList) {
                    if (company.getId() == balance.getId()) {
                        if ("lastday".equals(type)) {
                            company.setLastdayAmount(balance.getAmount());
                            break;
                        }
                        if ("today".equals(type)) {
                            company.setTodayAmount(balance.getAmount());
                            company.setAvailableAmount(company.getTodayAmount().subtract((null == company.getInvalidAmount()) ? new BigDecimal(0) : company.getInvalidAmount()));
                            break;
                        }
                        if ("income".equals(type)) {
                            company.setIncomeAmount(balance.getAmount());
                            break;
                        }
                        if ("cost".equals(type)) {
                            company.setCostAmount(balance.getAmount());
                            break;
                        }
                        if ("invalid".equals(type)) {
                            company.setInvalidAmount(balance.getAmount());
                            break;
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    @RequestMapping({ "areaFinanceReportList" })
    @ResponseBody
    public Pagination<FinanceReportBo> areaFinanceReportList(final HttpServletRequest request, final ModelMap modelMap, final Integer pageNo) {
        final String userId = request.getParameter("userId");
        String areaCurrentDateQuery = request.getParameter("areaCurrentDateQuery");
        final String areaBranchCompanyQuery = request.getParameter("areaBranchCompanyQuery");
        final String areaSubBranchCompanyQuery = request.getParameter("areaSubBranchCompanyQuery");
        if (StringUtils.isBlank(areaCurrentDateQuery)) {
            areaCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        if (StringUtils.isNotBlank(areaBranchCompanyQuery) && StringUtils.isBlank(areaSubBranchCompanyQuery)) {
            modelMap.put("areaType", (Object)areaBranchCompanyQuery);
        }
        else if (StringUtils.isNotBlank(areaSubBranchCompanyQuery)) {
            modelMap.put("id", (Object)areaSubBranchCompanyQuery);
        }
        modelMap.put("tableName", "c_branch_company");
        modelMap.put("branchUserId", (Object)Long.valueOf(userId));
        final Pagination<FinanceReportBo> pageList = this.financeReportService.findPage((Map<String, Object>)modelMap, pageNo, 15);
        if (null != pageList.getList() && pageList.getList().size() > 0) {
            final List<FinanceReportBo> dateList = pageList.getList();
            for (final FinanceReportBo bean : dateList) {
                final FinanceReportBo bo = new FinanceReportBo();
                bo.setId(bean.getId());
                bo.setDateStr(DateUtil.getPreDate(areaCurrentDateQuery));
                List<FinanceReportBo> list = this.financeReportService.selectBranchCompBalanceTotal(bo);
                if (null != list && list.size() > 0 && list.get(0) != null) {
                    bean.setLastdayAmount(list.get(0).getAmount());
                }
                bo.setDateStr(areaCurrentDateQuery);
                list = this.financeReportService.selectBranchCompIncomeTotal(bo);
                if (null != list && list.size() > 0) {
                    bean.setIncomeAmount(list.get(0).getAmount());
                }
                list = this.financeReportService.selectBranchCompCostTotal(bo);
                if (null != list && list.size() > 0) {
                    bean.setCostAmount(list.get(0).getAmount());
                }
                list = this.financeReportService.selectBranchCompInvalidCapitalTotal(bo);
                if (null != list && list.size() > 0) {
                    bean.setInvalidAmount(list.get(0).getAmount());
                }
                BigDecimal todayAmount = null;
                list = this.financeReportService.selectBranchCompBalanceTotal(bo);
                if (null != list && list.size() > 0 && list.get(0) != null) {
                    bean.setTodayAmount(list.get(0).getAmount());
                    todayAmount = bean.getTodayAmount();
                }
                if (StringUtils.isNotBlank(new Object[] { bean.getInvalidAmount() }) || StringUtils.isNotBlank(new Object[] { todayAmount })) {
                    if (StringUtils.isBlank(new Object[] { todayAmount })) {
                        todayAmount = new BigDecimal(0);
                    }
                    bean.setAvailableAmount(todayAmount.subtract((null == bean.getInvalidAmount()) ? new BigDecimal(0) : bean.getInvalidAmount()));
                }
            }
        }
        //pageList.setFinacePageHtml("queryareaFinanceReport");
        return pageList;
    }
    
    @RequestMapping({ "/exportCityBalanceReportList" })
    protected String exportCityBalanceReportList(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        final String cityUserIdQuery = request.getParameter("cityUserIdQuery");
        String cityCurrentDateQuery = request.getParameter("cityCurrentDateQuery");
        final String cityBranchCompanyQuery = request.getParameter("cityBranchCompanyQuery");
        final String citySubCompanyQuery = request.getParameter("citySubCompanyQuery");
        if (StringUtils.isBlank(cityCurrentDateQuery)) {
            cityCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setUserId(Long.valueOf(cityUserIdQuery));
        bo.setDateStr(cityCurrentDateQuery);
        List<FinanceReportBo> joinCompanyList = null;
        List<FinanceReportBo> noJoinCompanyList = null;
        if (StringUtils.isBlank(cityBranchCompanyQuery) && StringUtils.isBlank(citySubCompanyQuery)) {
            bo.setType(1L);
            joinCompanyList = this.financeReportService.selectSubCompany(bo);
            bo.setType(0L);
            noJoinCompanyList = this.financeReportService.selectSubCompany(bo);
        }
        else if (StringUtils.isNotBlank(cityBranchCompanyQuery) && StringUtils.isBlank(citySubCompanyQuery)) {
            bo.setType(Long.valueOf(cityBranchCompanyQuery));
            if ("1".equals(cityBranchCompanyQuery)) {
                joinCompanyList = this.financeReportService.selectSubCompany(bo);
            }
            else {
                noJoinCompanyList = this.financeReportService.selectSubCompany(bo);
            }
        }
        else if (StringUtils.isNotBlank(citySubCompanyQuery)) {
            bo.setId(Long.valueOf(citySubCompanyQuery));
            joinCompanyList = this.financeReportService.selectSubCompany(bo);
            if (null != joinCompanyList && joinCompanyList.size() > 0) {
                if (joinCompanyList.get(0).getType() == 1L) {
                    noJoinCompanyList = new ArrayList<FinanceReportBo>();
                }
                else {
                    noJoinCompanyList = joinCompanyList;
                    joinCompanyList = new ArrayList<FinanceReportBo>();
                }
            }
        }
        final List<FinanceReportBo> joinCompanyRowList = new ArrayList<FinanceReportBo>();
        if (null != joinCompanyList && joinCompanyList.size() > 0) {
            this.buildCompany("city", Long.valueOf(cityUserIdQuery), cityCurrentDateQuery, joinCompanyList, joinCompanyRowList);
        }
        final List<FinanceReportBo> noJoinCompanyRowList = new ArrayList<FinanceReportBo>();
        if (null != noJoinCompanyList && noJoinCompanyList.size() > 0) {
            this.buildCompany("city", Long.valueOf(cityUserIdQuery), cityCurrentDateQuery, noJoinCompanyList, noJoinCompanyRowList);
        }
        final String fileName = "\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u4f59\u989d\u62a5\u8868" + DateUtil.getCurrentDateString();
        final HSSFWorkbook workbook = new HSSFWorkbook();
        final HSSFSheet noJoinCompanySheet = workbook.createSheet();
        workbook.setSheetName(0, "\u5b50\u516c\u53f8");
        this.buildExportDate("2", workbook, noJoinCompanySheet, noJoinCompanyRowList);
        final HSSFSheet joinCompanySheet = workbook.createSheet();
        workbook.setSheetName(1, "\u5408\u8d44\u516c\u53f8");
        this.buildExportDate("1", workbook, joinCompanySheet, joinCompanyRowList);
        return this.exportExcel(response, fileName + ".xls", workbook);
    }
    
    @RequestMapping({ "/exportAreaBalanceReportList" })
    protected String exportAreaBalanceReportList(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        final String areaUserIdQuery = request.getParameter("areaUserIdQuery");
        String areaCurrentDateQuery = request.getParameter("areaCurrentDateQuery");
        final String areaBranchCompanyQuery = request.getParameter("areaBranchCompanyQuery");
        final String areaSubBranchCompanyQuery = request.getParameter("areaSubBranchCompanyQuery");
        if (StringUtils.isBlank(areaCurrentDateQuery)) {
            areaCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setDateStr(areaCurrentDateQuery);
        bo.setUserId(Long.valueOf(areaUserIdQuery));
        List<FinanceReportBo> joinCompanyList = null;
        List<FinanceReportBo> noJoinCompanyList = null;
        if (StringUtils.isBlank(areaBranchCompanyQuery) && StringUtils.isBlank(areaSubBranchCompanyQuery)) {
            bo.setType(1L);
            joinCompanyList = this.financeReportService.selectcBranchCompany(bo);
            bo.setType(0L);
            noJoinCompanyList = this.financeReportService.selectcBranchCompany(bo);
        }
        else if (StringUtils.isNotBlank(areaBranchCompanyQuery) && StringUtils.isBlank(areaSubBranchCompanyQuery)) {
            bo.setType(Long.valueOf(areaBranchCompanyQuery));
            if ("1".equals(areaBranchCompanyQuery)) {
                joinCompanyList = this.financeReportService.selectcBranchCompany(bo);
            }
            else {
                noJoinCompanyList = this.financeReportService.selectcBranchCompany(bo);
            }
        }
        else if (StringUtils.isNotBlank(areaSubBranchCompanyQuery)) {
            bo.setId(Long.valueOf(areaSubBranchCompanyQuery));
            joinCompanyList = this.financeReportService.selectcBranchCompany(bo);
            if (null != joinCompanyList && joinCompanyList.size() > 0) {
                if (joinCompanyList.get(0).getType() == 1L) {
                    noJoinCompanyList = new ArrayList<FinanceReportBo>();
                }
                else {
                    noJoinCompanyList = joinCompanyList;
                    joinCompanyList = new ArrayList<FinanceReportBo>();
                }
            }
        }
        final List<FinanceReportBo> joinCompanyRowList = new ArrayList<FinanceReportBo>();
        if (null != joinCompanyList && joinCompanyList.size() > 0) {
            this.buildCompany("area", Long.valueOf(areaUserIdQuery), areaCurrentDateQuery, joinCompanyList, joinCompanyRowList);
        }
        final List<FinanceReportBo> noJoinCompanyRowList = new ArrayList<FinanceReportBo>();
        if (null != noJoinCompanyList && noJoinCompanyList.size() > 0) {
            this.buildCompany("area", Long.valueOf(areaUserIdQuery), areaCurrentDateQuery, noJoinCompanyList, noJoinCompanyRowList);
        }
        final String fileName = "\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u4f59\u989d\u62a5\u8868" + DateUtil.getCurrentDateString();
        final HSSFWorkbook workbook = new HSSFWorkbook();
        final HSSFSheet noJoinCompanySheet = workbook.createSheet();
        workbook.setSheetName(0, "\u5b50\u516c\u53f8");
        this.buildExportDate("2", workbook, noJoinCompanySheet, noJoinCompanyRowList);
        final HSSFSheet joinCompanySheet = workbook.createSheet();
        workbook.setSheetName(1, "\u5408\u8d44\u516c\u53f8");
        this.buildExportDate("1", workbook, joinCompanySheet, joinCompanyRowList);
        return this.exportExcel(response, fileName + ".xls", workbook);
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
    
    private String exportExcel(final HttpServletResponse response, final String fileName, final XSSFWorkbook workbook) {
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
    
    private void buildExportDate(final String type, final HSSFWorkbook book, final HSSFSheet sheet, final List<FinanceReportBo> dateList) {
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setDateStr(DateUtil.getCurrentDateString());
        int bankCount = 0;
        final List<FinanceReportBo> maxList = this.financeReportService.selectMaxSubCompanyBalance(bo);
        if (null != maxList && maxList.size() > 0) {
            bankCount = Integer.valueOf(maxList.get(0).getCount()) + 2;
        }
        else {
            bankCount = 2;
        }
        final int rowCount = dateList.size();
        for (int allRowCount = bankCount * rowCount + 1, i = 0; i < allRowCount + 100; ++i) {
            sheet.createRow(i);
        }
        sheet.setDefaultColumnWidth(17);
        if (null != dateList && dateList.size() > 0) {
            BigDecimal totalAmount = new BigDecimal(0);
            for (int j = 0; j < dateList.size(); ++j) {
                final List<FinanceReportBo> cellList = dateList.get(j).getCellList();
                final HSSFRow row = sheet.getRow((j == 0) ? (j + 1) : (j + bankCount * j + 1));
                if (null != cellList && cellList.size() > 0) {
                    int writeIndex = 0;
                    for (int k = 0; k < cellList.size(); ++k) {
                        final HSSFFont font = book.createFont();
                        font.setFontHeightInPoints((short)10);
                        font.setBoldweight((short)700);
                        final HSSFCellStyle style = book.createCellStyle();
                        style.setFillPattern((short)1);
                        style.setFillForegroundColor((short)44);
                        style.setAlignment((short)2);
                        style.setFont(font);
                        style.setBorderTop((short)1);
                        style.setBorderBottom((short)1);
                        style.setBorderLeft((short)1);
                        style.setBorderRight((short)1);
                        style.setTopBorderColor((short)8);
                        style.setBottomBorderColor((short)8);
                        style.setLeftBorderColor((short)8);
                        style.setRightBorderColor((short)8);
                        final FinanceReportBo cellObj = cellList.get(k);
                        HSSFCell cell = row.createCell(writeIndex);
                        cell.setCellValue(cellObj.getName());
                        cell.setCellStyle(style);
                        cell = row.createCell(writeIndex + 1);
                        cell.setCellValue("\u4f59\u989d");
                        cell.setCellStyle(style);
                        writeIndex += 3;
                        if (null != cellObj.getSubCompBalanceList() && cellObj.getSubCompBalanceList().size() > 0) {
                            for (int l = 1; l <= cellObj.getSubCompBalanceList().size(); ++l) {
                                final FinanceReportBo bank = cellObj.getSubCompBalanceList().get(l - 1);
                                HSSFRow bankRow = sheet.getRow(l);
                                if (!bank.getName().equals("\u5408\u8ba1")) {
                                    totalAmount = totalAmount.add(bank.getAmount());
                                }
                                if (j < 1) {
                                    bankRow = sheet.getRow(l + 1);
                                }
                                else {
                                    bankRow = sheet.getRow(l + bankCount * j + j + 1);
                                }
                                final HSSFCellStyle cellStyle = book.createCellStyle();
                                cellStyle.setBorderTop((short)1);
                                cellStyle.setBorderBottom((short)1);
                                cellStyle.setBorderLeft((short)1);
                                cellStyle.setBorderRight((short)1);
                                cellStyle.setTopBorderColor((short)8);
                                cellStyle.setBottomBorderColor((short)8);
                                cellStyle.setLeftBorderColor((short)8);
                                cellStyle.setRightBorderColor((short)8);
                                final HSSFCellStyle amountStyle = book.createCellStyle();
                                amountStyle.setBorderTop((short)1);
                                amountStyle.setBorderBottom((short)1);
                                amountStyle.setBorderLeft((short)1);
                                amountStyle.setBorderRight((short)1);
                                amountStyle.setAlignment((short)3);
                                final String amount = new DecimalFormat("#").format(bank.getAmount());
                                int x = 0;
                                int y = 0;
                                switch (k) {
                                    case 0: {
                                        x = 0;
                                        y = 1;
                                        break;
                                    }
                                    case 1: {
                                        x = 3;
                                        y = 4;
                                        break;
                                    }
                                    case 2: {
                                        x = 6;
                                        y = 7;
                                        break;
                                    }
                                    case 3: {
                                        x = 9;
                                        y = 10;
                                        break;
                                    }
                                    case 4: {
                                        x = 12;
                                        y = 13;
                                        break;
                                    }
                                }
                                final HSSFCell bankCell1 = bankRow.createCell(x);
                                bankCell1.setCellValue(bank.getName());
                                bankCell1.setCellStyle(cellStyle);
                                final HSSFCell bankCell2 = bankRow.createCell(y);
                                bankCell2.setCellValue(amount);
                                bankCell2.setCellStyle(amountStyle);
                            }
                        }
                    }
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
            final HSSFRow headRow = sheet.getRow(0);
            final HSSFCell headCell = headRow.createCell(0);
            final HSSFFont font2 = book.createFont();
            font2.setFontHeightInPoints((short)12);
            font2.setBoldweight((short)700);
            final HSSFPalette HeadPalette = book.getCustomPalette();
            HeadPalette.setColorAtIndex((short)9, (byte)(-40), (byte)(-40), (byte)(-40));
            final HSSFCellStyle headStyle = book.createCellStyle();
            headStyle.setFillPattern((short)1);
            headStyle.setFillForegroundColor((short)9);
            headStyle.setAlignment((short)2);
            headStyle.setFont(font2);
            if (type.equals("1")) {
                headCell.setCellValue("\u5408\u8d44\u516c\u53f8\u4f59\u989d\u5408\u8ba1\uff08\u5355\u4f4d\uff1a\u4e07\u5143\uff09" + new DecimalFormat("#").format(totalAmount));
            }
            else {
                headCell.setCellValue("\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u4f59\u989d\u5408\u8ba1\uff08\u5355\u4f4d\uff1a\u4e07\u5143\uff09 " + new DecimalFormat("#").format(totalAmount));
            }
            headCell.setCellStyle(headStyle);
        }
    }
    
    private void buildCompany(final String type, final Long userId, final String date, final List<FinanceReportBo> dateList, final List<FinanceReportBo> rowList) {
        if (null != dateList && dateList.size() > 0) {
            List<FinanceReportBo> cellList = new ArrayList<FinanceReportBo>();
            final int rowCount = (dateList.size() + 5 - 1) / 5;
            int addCount = 0;
            boolean lastAddFlag = true;
            for (int i = 1; i <= dateList.size(); ++i) {
                final FinanceReportBo cellObj = new FinanceReportBo();
                cellObj.setName(dateList.get(i - 1).getName());
                cellObj.setUserId(userId);
                cellObj.setDateStr(date);
                List<FinanceReportBo> balanceList = null;
                if ("city".equals(type)) {
                    cellObj.setId(dateList.get(i - 1).getId());
                    balanceList = this.financeReportService.selectSubCompBalance(cellObj);
                }
                else if ("area".equals(type)) {
                    cellObj.setBranchCompanyId(dateList.get(i - 1).getId());
                    balanceList = this.financeReportService.selectSubCompBalanceTotal(cellObj);
                }
                BigDecimal totalAmount = new BigDecimal(0);
                if (null != balanceList && balanceList.size() > 0) {
                    for (final FinanceReportBo bean : balanceList) {
                        totalAmount = totalAmount.add(bean.getAmount());
                    }
                }
                final FinanceReportBo totalBo = new FinanceReportBo();
                totalBo.setName("\u5408\u8ba1");
                totalBo.setAmount(totalAmount);
                balanceList.add(totalBo);
                cellObj.setSubCompBalanceList(balanceList);
                cellList.add(cellObj);
                if (i % 5 == 0 && lastAddFlag) {
                    final FinanceReportBo rowObj = new FinanceReportBo();
                    rowObj.setCellList(cellList);
                    rowList.add(rowObj);
                    ++addCount;
                    cellList = new ArrayList<FinanceReportBo>();
                }
                else if (addCount == rowCount - 1 && lastAddFlag) {
                    final FinanceReportBo rowObj = new FinanceReportBo();
                    rowObj.setCellList(cellList);
                    rowList.add(rowObj);
                    lastAddFlag = false;
                }
            }
        }
    }
    
    @RequestMapping({ "/exportCityCapitalReportList" })
    protected String exportCityCapitalReportList(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        final String cityUserIdQuery = request.getParameter("cityUserIdQuery");
        String cityCurrentDateQuery = request.getParameter("cityCurrentDateQuery");
        final String cityBranchCompanyQuery = request.getParameter("cityBranchCompanyQuery");
        final String citySubCompanyQuery = request.getParameter("citySubCompanyQuery");
        if (StringUtils.isBlank(cityCurrentDateQuery)) {
            cityCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setUserId(Long.valueOf(cityUserIdQuery));
        bo.setDateStr(cityCurrentDateQuery);
        bo.setOrderByType("type");
        if (StringUtils.isNotBlank(cityBranchCompanyQuery) && StringUtils.isBlank(citySubCompanyQuery)) {
            bo.setType(Long.valueOf(cityBranchCompanyQuery));
        }
        else if (StringUtils.isNotBlank(citySubCompanyQuery)) {
            bo.setId(Long.valueOf(citySubCompanyQuery));
        }
        bo.setTableName("c_income");
        bo.setFieldName("income_type_id");
        bo.setOpe("!=");
        bo.setIncomeTypeId(2L);
        List<FinanceReportBo> incomeTypeLengthList = this.financeReportService.selectMaxSubCompanyIncomeLength(bo);
        bo.setNotWorkRoomIncomeMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setOpe("=");
        incomeTypeLengthList = this.financeReportService.selectMaxSubCompanyIncomeLength(bo);
        bo.setWorkRoomIncomeListMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setTableName("c_cost");
        bo.setFieldName("cost_type_id");
        bo.setOpe("!=");
        incomeTypeLengthList = this.financeReportService.selectMaxSubCompanyIncomeLength(bo);
        bo.setNotWorkRoomCostListMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setOpe("=");
        incomeTypeLengthList = this.financeReportService.selectMaxSubCompanyIncomeLength(bo);
        bo.setWorkRoomCostListMaxLeng((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setTableName("c_his_invalid_capital");
        bo.setFieldName("1");
        bo.setOpe("=");
        bo.setIncomeTypeId(1L);
        incomeTypeLengthList = this.financeReportService.selectMaxSubCompanyIncomeLength(bo);
        bo.setInvalidCapitalListMaxLeng((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? incomeTypeLengthList.get(0).getCount() : 0);
        final List<FinanceReportBo> companyList = this.financeReportService.selectSubCompany(bo);
        for (final FinanceReportBo bean : companyList) {
            final List<FinanceReportBo> workRoomIncomeList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> notWorkRoomIncomeList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> workRoomCostList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> notWorkRoomCostList = new ArrayList<FinanceReportBo>();
            bo.setId(bean.getId());
            bo.setDateStr(DateUtil.getPreDate(cityCurrentDateQuery));
            List<FinanceReportBo> balanceList = this.financeReportService.selectSubCompBalanceTotal(bo);
            if (null != balanceList && balanceList.size() > 0) {
                bean.setLastdayAmount(balanceList.get(0).getAmount());
            }
            bo.setDateStr(cityCurrentDateQuery);
            balanceList = this.financeReportService.selectSubCompBalanceTotal(bo);
            if (null != balanceList && balanceList.size() > 0) {
                bean.setTodayAmount(balanceList.get(0).getAmount());
            }
            final List<FinanceReportBo> incomeList = this.financeReportService.selectIncome(bo);
            for (final FinanceReportBo income : incomeList) {
                if (income.getIncomeTypeId() == 2L) {
                    workRoomIncomeList.add(income);
                }
                else {
                    notWorkRoomIncomeList.add(income);
                }
            }
            bean.setWorkRoomIncomeList(workRoomIncomeList);
            bean.setNotWorkRoomIncomeList(notWorkRoomIncomeList);
            final List<FinanceReportBo> costList = this.financeReportService.selectCost(bo);
            for (final FinanceReportBo cost : costList) {
                if (cost.getCostTypeId() == 2L) {
                    workRoomCostList.add(cost);
                }
                else {
                    notWorkRoomCostList.add(cost);
                }
            }
            bean.setWorkRoomCostList(workRoomCostList);
            bean.setNotWorkRoomCostList(notWorkRoomCostList);
            bean.setSubCompInvalidCapitalList(this.financeReportService.selectInvalidCapital(bo));
        }
        XSSFWorkbook wb = null;
        try {
            System.out.println("begin");
            final DecimalFormat amountFormat = new DecimalFormat("#");
            final File file = new File(this.getProperties("config.properties", "TEMPLATE_PATH") + "capitalRreportTemplate.xlsx");
            wb = new XSSFWorkbook((InputStream)new FileInputStream(file));
            final XSSFSheet sheet = wb.getSheetAt(0);
            final XSSFCellStyle style1 = wb.createCellStyle();
            style1.setBorderTop((short)1);
            style1.setBorderBottom((short)1);
            style1.setBorderLeft((short)1);
            style1.setBorderRight((short)1);
            style1.setFillForegroundColor(new XSSFColor(new Color(0, 176, 240)));
            style1.setFillPattern((short)1);
            final XSSFCellStyle style2 = wb.createCellStyle();
            style2.setBorderTop((short)1);
            style2.setBorderBottom((short)1);
            style2.setBorderLeft((short)1);
            style2.setBorderRight((short)1);
            style2.setFillForegroundColor(new XSSFColor(new Color(255, 255, 0)));
            style2.setFillPattern((short)1);
            final XSSFCellStyle style2_1 = wb.createCellStyle();
            style2_1.setBorderTop((short)1);
            style2_1.setBorderBottom((short)1);
            style2_1.setBorderLeft((short)1);
            style2_1.setBorderRight((short)1);
            style2_1.setFillForegroundColor(new XSSFColor(new Color(255, 128, 128)));
            style2_1.setFillPattern((short)1);
            final XSSFCellStyle style2_2 = wb.createCellStyle();
            style2_2.setBorderTop((short)1);
            style2_2.setBorderBottom((short)1);
            style2_2.setBorderLeft((short)1);
            style2_2.setBorderRight((short)1);
            style2_2.setFillForegroundColor(new XSSFColor(new Color(153, 204, 255)));
            style2_2.setFillPattern((short)1);
            final XSSFCellStyle style2_3 = wb.createCellStyle();
            style2_3.setBorderTop((short)1);
            style2_3.setBorderBottom((short)1);
            style2_3.setBorderLeft((short)1);
            style2_3.setBorderRight((short)1);
            style2_3.setFillForegroundColor(new XSSFColor(new Color(150, 150, 150)));
            style2_3.setFillPattern((short)1);
            final XSSFCellStyle style3 = wb.createCellStyle();
            style3.setBorderTop((short)1);
            style3.setBorderBottom((short)1);
            style3.setBorderLeft((short)1);
            style3.setBorderRight((short)1);
            final int totalRow = bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 13;
            for (int i = 0; i < totalRow + 200; ++i) {
                sheet.createRow(i);
            }
            sheet.setDefaultColumnWidth(17);
            for (int i = 0; i < totalRow; ++i) {
                for (int j = 0; j < companyList.size(); ++j) {
                    final XSSFCell cell1 = sheet.getRow(i).createCell(2 * j);
                    cell1.setCellStyle((CellStyle)style3);
                    sheet.setColumnWidth(2 * j, 5120);
                    final XSSFCell cell2 = sheet.getRow(i).createCell(2 * j + 1);
                    cell2.setCellStyle((CellStyle)style3);
                    sheet.setColumnWidth(2 * j + 1, 3328);
                }
            }
            for (int i = 0; i < companyList.size(); ++i) {
                BigDecimal incomeAmoutTotal = new BigDecimal(0);
                BigDecimal costAmoutTotal = new BigDecimal(0);
                BigDecimal invalidAmoutTotal = new BigDecimal(0);
                final FinanceReportBo company = companyList.get(i);
                final XSSFCell cell3 = sheet.getRow(0).createCell(2 * i);
                sheet.setColumnWidth(2 * i, 5120);
                cell3.setCellValue(company.getName());
                final XSSFCell cell4 = sheet.getRow(0).createCell(2 * i + 1);
                sheet.setColumnWidth(2 * i + 1, 3328);
                cell4.setCellValue("\u5355\u4f4d\uff1a\u4e07\u5143");
                if (company.getType() == 1L) {
                    cell3.setCellStyle((CellStyle)style2);
                    cell4.setCellStyle((CellStyle)style2);
                }
                else {
                    cell3.setCellStyle((CellStyle)style1);
                    cell4.setCellStyle((CellStyle)style1);
                }
                final XSSFCell cell5 = sheet.getRow(1).createCell(2 * i);
                cell5.setCellValue("\u6628\u65e5\u4f59\u989d");
                final XSSFCell cell6 = sheet.getRow(1).createCell(2 * i + 1);
                cell6.setCellValue((null == company.getLastdayAmount()) ? "-" : amountFormat.format(company.getLastdayAmount()));
                if (company.getType() == 1L) {
                    cell5.setCellStyle((CellStyle)style2);
                    cell6.setCellStyle((CellStyle)style2);
                }
                else {
                    cell5.setCellStyle((CellStyle)style1);
                    cell6.setCellStyle((CellStyle)style1);
                }
                final XSSFCell cell7 = sheet.getRow(2).createCell(2 * i);
                cell7.setCellValue("\u6536\u5165\u5185\u5bb9");
                cell7.setCellStyle((CellStyle)style3);
                final XSSFCell cell8 = sheet.getRow(2).createCell(2 * i + 1);
                cell8.setCellValue("\u91d1\u989d");
                cell8.setCellStyle((CellStyle)style3);
                for (int k = 0; k < company.getNotWorkRoomIncomeList().size(); ++k) {
                    final FinanceReportBo incomeBean = company.getNotWorkRoomIncomeList().get(k);
                    final XSSFCell cell9 = sheet.getRow(k + 3).createCell(2 * i);
                    cell9.setCellValue(incomeBean.getName());
                    cell9.setCellStyle((CellStyle)style3);
                    final XSSFCell cell10 = sheet.getRow(k + 3).createCell(2 * i + 1);
                    cell10.setCellValue((null == incomeBean.getAmount()) ? "-" : amountFormat.format(incomeBean.getAmount()));
                    cell10.setCellStyle((CellStyle)style3);
                    incomeAmoutTotal = incomeAmoutTotal.add(incomeBean.getAmount());
                }
                final XSSFCell cell11 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + 3).createCell(2 * i);
                cell11.setCellValue("\u5de5\u62b5\u623f\u6536\u5165");
                cell11.setCellStyle((CellStyle)style2_1);
                final XSSFCell cell12 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + 3).createCell(2 * i + 1);
                cell12.setCellValue("\u91d1\u989d");
                cell12.setCellStyle((CellStyle)style2_1);
                for (int l = 0; l < company.getWorkRoomIncomeList().size(); ++l) {
                    final FinanceReportBo incomeBean2 = company.getWorkRoomIncomeList().get(l);
                    final XSSFCell cell13 = sheet.getRow(l + bo.getNotWorkRoomIncomeMaxLength() + 4).createCell(2 * i);
                    cell13.setCellValue(incomeBean2.getName());
                    cell13.setCellStyle((CellStyle)style3);
                    final XSSFCell cell14 = sheet.getRow(l + bo.getNotWorkRoomIncomeMaxLength() + 4).createCell(2 * i + 1);
                    cell14.setCellValue((null == incomeBean2.getAmount()) ? "-" : amountFormat.format(incomeBean2.getAmount()));
                    cell14.setCellStyle((CellStyle)style3);
                    incomeAmoutTotal = incomeAmoutTotal.add(incomeBean2.getAmount());
                }
                final XSSFCell cell15 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 2).createCell(2 * i);
                cell15.setCellValue("\u5c0f\u8ba1");
                cell15.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell16 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 2).createCell(2 * i + 1);
                cell16.setCellValue(amountFormat.format(incomeAmoutTotal));
                cell16.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell17 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 5).createCell(2 * i);
                cell17.setCellValue("\u652f\u51fa\u5185\u5bb9");
                cell17.setCellStyle((CellStyle)style3);
                final XSSFCell cell18 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 5).createCell(2 * i + 1);
                cell18.setCellValue("\u91d1\u989d");
                cell18.setCellStyle((CellStyle)style3);
                for (int m = 0; m < company.getNotWorkRoomCostList().size(); ++m) {
                    final FinanceReportBo incomeBean3 = company.getNotWorkRoomCostList().get(m);
                    final XSSFCell cell19 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 6 + m).createCell(2 * i);
                    cell19.setCellValue(incomeBean3.getName());
                    cell19.setCellStyle((CellStyle)style3);
                    final XSSFCell cell20 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 6 + m).createCell(2 * i + 1);
                    cell20.setCellValue((null == incomeBean3.getAmount()) ? "-" : amountFormat.format(incomeBean3.getAmount()));
                    cell20.setCellStyle((CellStyle)style3);
                    costAmoutTotal = costAmoutTotal.add(incomeBean3.getAmount());
                }
                final XSSFCell cell21 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 6).createCell(2 * i);
                cell21.setCellValue("\u5de5\u62b5\u623f\u652f\u51fa");
                cell21.setCellStyle((CellStyle)style2_1);
                final XSSFCell cell22 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 6).createCell(2 * i + 1);
                cell22.setCellValue("\u91d1\u989d");
                cell22.setCellStyle((CellStyle)style2_1);
                for (int j2 = 0; j2 < company.getWorkRoomCostList().size(); ++j2) {
                    final FinanceReportBo incomeBean4 = company.getWorkRoomCostList().get(j2);
                    final XSSFCell cell23 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 7 + j2).createCell(2 * i);
                    cell23.setCellValue(incomeBean4.getName());
                    cell23.setCellStyle((CellStyle)style3);
                    final XSSFCell cell24 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 7 + j2).createCell(2 * i + 1);
                    cell24.setCellValue((null == incomeBean4.getAmount()) ? "-" : amountFormat.format(incomeBean4.getAmount()));
                    cell24.setCellStyle((CellStyle)style3);
                    costAmoutTotal = costAmoutTotal.add(incomeBean4.getAmount());
                }
                final XSSFCell cell25 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 5).createCell(2 * i);
                cell25.setCellValue("\u5c0f\u8ba1");
                cell25.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell26 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 5).createCell(2 * i + 1);
                cell26.setCellValue(amountFormat.format(costAmoutTotal));
                cell26.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell27 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 8).createCell(2 * i);
                cell27.setCellValue("\u5f53\u65e5\u4f59\u989d");
                cell27.setCellStyle((CellStyle)style2_3);
                final XSSFCell cell28 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 8).createCell(2 * i + 1);
                cell28.setCellValue((null == company.getTodayAmount()) ? "-" : amountFormat.format(company.getTodayAmount()));
                cell28.setCellStyle((CellStyle)style2_3);
                final XSSFCell cell29 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 11).createCell(2 * i);
                cell29.setCellValue("\u4e0d\u53ef\u7528\u8d44\u91d1\u5185\u5bb9");
                cell29.setCellStyle((CellStyle)style3);
                final XSSFCell cell30 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 11).createCell(2 * i + 1);
                cell30.setCellValue("\u91d1\u989d");
                cell30.setCellStyle((CellStyle)style3);
                for (int j3 = 0; j3 < company.getSubCompInvalidCapitalList().size(); ++j3) {
                    final FinanceReportBo incomeBean5 = company.getSubCompInvalidCapitalList().get(j3);
                    final XSSFCell cell31 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 12 + j3).createCell(2 * i);
                    cell31.setCellValue(incomeBean5.getName());
                    cell31.setCellStyle((CellStyle)style3);
                    final XSSFCell cell32 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 12 + j3).createCell(2 * i + 1);
                    cell32.setCellValue((null == incomeBean5.getAmount()) ? "-" : amountFormat.format(incomeBean5.getAmount()));
                    cell32.setCellStyle((CellStyle)style3);
                    invalidAmoutTotal = invalidAmoutTotal.add(incomeBean5.getAmount());
                }
                final XSSFCell cell33 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 12).createCell(2 * i);
                cell33.setCellValue("\u5c0f\u8ba1");
                cell33.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell34 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 12).createCell(2 * i + 1);
                cell34.setCellValue(amountFormat.format(invalidAmoutTotal));
                cell34.setCellStyle((CellStyle)style2_2);
            }
            final XSSFSheet sheet2 = wb.getSheetAt(1);
            bo.setType(0L);
            final List<FinanceReportBo> noJoinCompanyList = this.financeReportService.selectSubCompanyForTemplate(bo);
            bo.setType(1L);
            final List<FinanceReportBo> joinCompanyList = this.financeReportService.selectSubCompanyForTemplate(bo);
            BigDecimal lastdayAmount = new BigDecimal(0);
            BigDecimal incomeAmount = new BigDecimal(0);
            BigDecimal costAmount = new BigDecimal(0);
            BigDecimal todayAmount = new BigDecimal(0);
            BigDecimal invalidAmount = new BigDecimal(0);
            BigDecimal availableAmount = new BigDecimal(0);
            for (int i2 = 0; i2 < noJoinCompanyList.size(); ++i2) {
                final FinanceReportBo bean2 = noJoinCompanyList.get(i2);
                final FinanceReportBo subQueryBean = new FinanceReportBo();
                subQueryBean.setId(bean2.getId());
                subQueryBean.setUserId(Long.valueOf(cityUserIdQuery));
                subQueryBean.setDateStr(DateUtil.getPreDate(cityCurrentDateQuery));
                List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    lastdayAmount = lastdayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(2).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(2).getCell(i2 + 2).setCellValue("-");
                }
                subQueryBean.setDateStr(cityCurrentDateQuery);
                list = this.financeReportService.selectIncomeTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    incomeAmount = incomeAmount.add(list.get(0).getAmount());
                    sheet2.getRow(3).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(3).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectCostTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    costAmount = costAmount.add(list.get(0).getAmount());
                    sheet2.getRow(4).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(4).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    bean2.setTodayAmount(list.get(0).getAmount());
                    todayAmount = todayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(5).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(5).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectInvalidCapitalTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    invalidAmount = invalidAmount.add(list.get(0).getAmount());
                    sheet2.getRow(6).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(7).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount().subtract(list.get(0).getAmount())));
                    }
                }
                else {
                    sheet2.getRow(6).getCell(i2 + 2).setCellValue("-");
                    sheet2.getRow(7).getCell(i2 + 2).setCellValue("-");
                }
                availableAmount = todayAmount.subtract(invalidAmount);
            }
            sheet2.getRow(2).getCell(1).setCellValue(amountFormat.format(lastdayAmount));
            sheet2.getRow(3).getCell(1).setCellValue(amountFormat.format(incomeAmount));
            sheet2.getRow(4).getCell(1).setCellValue(amountFormat.format(costAmount));
            sheet2.getRow(5).getCell(1).setCellValue(amountFormat.format(todayAmount));
            sheet2.getRow(6).getCell(1).setCellValue(amountFormat.format(invalidAmount));
            sheet2.getRow(7).getCell(1).setCellValue(amountFormat.format(availableAmount));
            sheet2.getRow(8).getCell(1).setCellValue(sheet2.getRow(7).getCell(1).getStringCellValue());
            sheet2.getRow(8).getCell(2).setCellValue(this.countCellValue(sheet2, 7, 2, 4));
            sheet2.getRow(8).getCell(5).setCellValue(this.countCellValue(sheet2, 7, 5, 6));
            sheet2.getRow(8).getCell(7).setCellValue(this.countCellValue(sheet2, 7, 7, 7));
            sheet2.getRow(8).getCell(8).setCellValue(this.countCellValue(sheet2, 7, 8, 9));
            sheet2.getRow(8).getCell(10).setCellValue(this.countCellValue(sheet2, 7, 10, 12));
            sheet2.getRow(8).getCell(13).setCellValue(this.countCellValue(sheet2, 7, 13, 13));
            sheet2.getRow(8).getCell(14).setCellValue(this.countCellValue(sheet2, 7, 14, 16));
            sheet2.getRow(8).getCell(17).setCellValue(this.countCellValue(sheet2, 7, 17, 19));
            sheet2.getRow(8).getCell(20).setCellValue(this.countCellValue(sheet2, 7, 20, 22));
            sheet2.getRow(8).getCell(23).setCellValue(this.countCellValue(sheet2, 7, 23, 27));
            sheet2.getRow(8).getCell(28).setCellValue(this.countCellValue(sheet2, 7, 28, 28));
            lastdayAmount = new BigDecimal(0);
            incomeAmount = new BigDecimal(0);
            costAmount = new BigDecimal(0);
            todayAmount = new BigDecimal(0);
            invalidAmount = new BigDecimal(0);
            availableAmount = new BigDecimal(0);
            for (int i2 = 0; i2 < joinCompanyList.size(); ++i2) {
                final FinanceReportBo bean2 = joinCompanyList.get(i2);
                final FinanceReportBo subQueryBean = new FinanceReportBo();
                subQueryBean.setId(bean2.getId());
                subQueryBean.setUserId(Long.valueOf(cityUserIdQuery));
                subQueryBean.setDateStr(DateUtil.getPreDate(cityCurrentDateQuery));
                List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    lastdayAmount = lastdayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(11).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(11).getCell(i2 + 2).setCellValue("-");
                }
                subQueryBean.setDateStr(cityCurrentDateQuery);
                list = this.financeReportService.selectIncomeTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    incomeAmount = incomeAmount.add(list.get(0).getAmount());
                    sheet2.getRow(12).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(12).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectCostTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    costAmount = costAmount.add(list.get(0).getAmount());
                    sheet2.getRow(13).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(13).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    bean2.setTodayAmount(list.get(0).getAmount());
                    todayAmount = todayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(14).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(14).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectInvalidCapitalTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    invalidAmount = invalidAmount.add(list.get(0).getAmount());
                    sheet2.getRow(15).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount().subtract(list.get(0).getAmount())));
                    }
                }
                else {
                    sheet2.getRow(15).getCell(i2 + 2).setCellValue("-");
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount()));
                    }
                    else {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue("-");
                    }
                }
                availableAmount = todayAmount.subtract(invalidAmount);
                sheet2.getRow(17).getCell(i2 + 2).setCellValue(sheet2.getRow(16).getCell(i2 + 2).getStringCellValue());
            }
            sheet2.getRow(11).getCell(1).setCellValue(amountFormat.format(lastdayAmount));
            sheet2.getRow(12).getCell(1).setCellValue(amountFormat.format(incomeAmount));
            sheet2.getRow(13).getCell(1).setCellValue(amountFormat.format(costAmount));
            sheet2.getRow(14).getCell(1).setCellValue(amountFormat.format(todayAmount));
            sheet2.getRow(15).getCell(1).setCellValue(amountFormat.format(invalidAmount));
            sheet2.getRow(16).getCell(1).setCellValue(amountFormat.format(availableAmount));
            sheet2.getRow(17).getCell(1).setCellValue(sheet2.getRow(16).getCell(1).getStringCellValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final String fileName = "\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u8d44\u91d1\u62a5\u8868" + DateUtil.getCurrentDateString();
        return this.exportExcel(response, fileName + ".xlsx", wb);
    }
    
    private String countCellValue(final XSSFSheet sheet, final int rowIndex, final int startCelleIndex, final int endCellIndex) {
        BigDecimal total = new BigDecimal(0);
        for (int i = startCelleIndex; i <= endCellIndex; ++i) {
            final XSSFCell cell = sheet.getRow(rowIndex).getCell(i);
            if (!cell.getStringCellValue().equals("-")) {
                total = total.add(new BigDecimal(StringUtils.isBlank(cell.getStringCellValue()) ? "0" : cell.getStringCellValue()));
            }
        }
        return new DecimalFormat("#").format(total);
    }
    
    @RequestMapping({ "/exportAreaCapitalReportList" })
    protected String exportAreaCapitalReportList(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap, final Model model) {
        final String areaUserIdQuery = request.getParameter("areaUserIdQuery");
        String areaCurrentDateQuery = request.getParameter("areaCurrentDateQuery");
        final String areaBranchCompanyQuery = request.getParameter("areaBranchCompanyQuery");
        final String areaSubCompanyQuery = request.getParameter("areaSubCompanyQuery");
        if (StringUtils.isBlank(areaCurrentDateQuery)) {
            areaCurrentDateQuery = DateUtil.getCurrentDateString();
        }
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setUserId(Long.valueOf(areaUserIdQuery));
        bo.setDateStr(areaCurrentDateQuery);
        bo.setOrderByType("type");
        if (StringUtils.isNotBlank(areaBranchCompanyQuery) && StringUtils.isBlank(areaSubCompanyQuery)) {
            bo.setType(Long.valueOf(areaBranchCompanyQuery));
        }
        else if (StringUtils.isNotBlank(areaSubCompanyQuery)) {
            bo.setId(Long.valueOf(areaSubCompanyQuery));
        }
        bo.setTableName("c_income");
        bo.setFieldName("income_type_id");
        bo.setOpe("!=");
        bo.setIncomeTypeId(2L);
        List<FinanceReportBo> incomeTypeLengthList = this.financeReportService.selectMaxBranchCompanyIncomeLength(bo);
        bo.setNotWorkRoomIncomeMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setOpe("=");
        incomeTypeLengthList = this.financeReportService.selectMaxBranchCompanyIncomeLength(bo);
        bo.setWorkRoomIncomeListMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setTableName("c_cost");
        bo.setFieldName("cost_type_id");
        bo.setOpe("!=");
        incomeTypeLengthList = this.financeReportService.selectMaxBranchCompanyIncomeLength(bo);
        bo.setNotWorkRoomCostListMaxLength((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setOpe("=");
        incomeTypeLengthList = this.financeReportService.selectMaxBranchCompanyIncomeLength(bo);
        bo.setWorkRoomCostListMaxLeng((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? (incomeTypeLengthList.get(0).getCount() + 2) : 2);
        bo.setTableName("c_his_invalid_capital");
        bo.setFieldName("1");
        bo.setOpe("=");
        bo.setIncomeTypeId(1L);
        incomeTypeLengthList = this.financeReportService.selectMaxBranchCompanyIncomeLength(bo);
        bo.setInvalidCapitalListMaxLeng((null != incomeTypeLengthList && incomeTypeLengthList.size() > 0) ? incomeTypeLengthList.get(0).getCount() : 0);
        final List<FinanceReportBo> companyList = this.financeReportService.selectcBranchCompany(bo);
        for (final FinanceReportBo bean : companyList) {
            final List<FinanceReportBo> workRoomIncomeList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> notWorkRoomIncomeList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> workRoomCostList = new ArrayList<FinanceReportBo>();
            final List<FinanceReportBo> notWorkRoomCostList = new ArrayList<FinanceReportBo>();
            bo.setId(bean.getId());
            bo.setDateStr(DateUtil.getPreDate(areaCurrentDateQuery));
            List<FinanceReportBo> balanceList = this.financeReportService.selectBranchCompBalanceTotal(bo);
            if (null != balanceList && balanceList.size() > 0) {
                bean.setLastdayAmount(balanceList.get(0).getAmount());
            }
            bo.setDateStr(areaCurrentDateQuery);
            balanceList = this.financeReportService.selectBranchCompBalanceTotal(bo);
            if (null != balanceList && balanceList.size() > 0) {
                bean.setTodayAmount(balanceList.get(0).getAmount());
            }
            final List<FinanceReportBo> incomeList = this.financeReportService.selectSubCompanyIncomeTotal(bo);
            for (final FinanceReportBo income : incomeList) {
                if (income.getIncomeTypeId() == 2L) {
                    workRoomIncomeList.add(income);
                }
                else {
                    notWorkRoomIncomeList.add(income);
                }
            }
            bean.setWorkRoomIncomeList(workRoomIncomeList);
            bean.setNotWorkRoomIncomeList(notWorkRoomIncomeList);
            final List<FinanceReportBo> costList = this.financeReportService.selectSubCompanyCostTotal(bo);
            for (final FinanceReportBo cost : costList) {
                if (cost.getCostTypeId() == 2L) {
                    workRoomCostList.add(cost);
                }
                else {
                    notWorkRoomCostList.add(cost);
                }
            }
            bean.setWorkRoomCostList(workRoomCostList);
            bean.setNotWorkRoomCostList(notWorkRoomCostList);
            bean.setSubCompInvalidCapitalList(this.financeReportService.selectSubCompanyInvalidCapitalTotal(bo));
        }
        XSSFWorkbook wb = null;
        try {
            System.out.println("begin");
            final DecimalFormat amountFormat = new DecimalFormat("#");
            final File file = new File(this.getProperties("config.properties", "TEMPLATE_PATH") + "capitalRreportTemplate.xlsx");
            wb = new XSSFWorkbook((InputStream)new FileInputStream(file));
            final XSSFSheet sheet = wb.getSheetAt(0);
            final XSSFCellStyle style1 = wb.createCellStyle();
            style1.setBorderTop((short)1);
            style1.setBorderBottom((short)1);
            style1.setBorderLeft((short)1);
            style1.setBorderRight((short)1);
            style1.setFillForegroundColor(new XSSFColor(new Color(0, 176, 240)));
            style1.setFillPattern((short)1);
            final XSSFCellStyle style2 = wb.createCellStyle();
            style2.setBorderTop((short)1);
            style2.setBorderBottom((short)1);
            style2.setBorderLeft((short)1);
            style2.setBorderRight((short)1);
            style2.setFillForegroundColor(new XSSFColor(new Color(255, 255, 0)));
            style2.setFillPattern((short)1);
            final XSSFCellStyle style2_1 = wb.createCellStyle();
            style2_1.setBorderTop((short)1);
            style2_1.setBorderBottom((short)1);
            style2_1.setBorderLeft((short)1);
            style2_1.setBorderRight((short)1);
            style2_1.setFillForegroundColor(new XSSFColor(new Color(255, 128, 128)));
            style2_1.setFillPattern((short)1);
            final XSSFCellStyle style2_2 = wb.createCellStyle();
            style2_2.setBorderTop((short)1);
            style2_2.setBorderBottom((short)1);
            style2_2.setBorderLeft((short)1);
            style2_2.setBorderRight((short)1);
            style2_2.setFillForegroundColor(new XSSFColor(new Color(153, 204, 255)));
            style2_2.setFillPattern((short)1);
            final XSSFCellStyle style2_3 = wb.createCellStyle();
            style2_3.setBorderTop((short)1);
            style2_3.setBorderBottom((short)1);
            style2_3.setBorderLeft((short)1);
            style2_3.setBorderRight((short)1);
            style2_3.setFillForegroundColor(new XSSFColor(new Color(150, 150, 150)));
            style2_3.setFillPattern((short)1);
            final XSSFCellStyle style3 = wb.createCellStyle();
            style3.setBorderTop((short)1);
            style3.setBorderBottom((short)1);
            style3.setBorderLeft((short)1);
            style3.setBorderRight((short)1);
            for (int totalRow = bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 13, i = 0; i < totalRow; ++i) {
                for (int j = 0; j < companyList.size(); ++j) {
                    final XSSFCell cell1 = sheet.getRow(i).createCell(2 * j);
                    cell1.setCellStyle((CellStyle)style3);
                    sheet.setColumnWidth(2 * j, 5120);
                    final XSSFCell cell2 = sheet.getRow(i).createCell(2 * j + 1);
                    cell2.setCellStyle((CellStyle)style3);
                    sheet.setColumnWidth(2 * j + 1, 3328);
                }
            }
            for (int i = 0; i < companyList.size(); ++i) {
                BigDecimal incomeAmoutTotal = new BigDecimal(0);
                BigDecimal costAmoutTotal = new BigDecimal(0);
                BigDecimal invalidAmoutTotal = new BigDecimal(0);
                final FinanceReportBo company = companyList.get(i);
                final XSSFCell cell3 = sheet.getRow(0).createCell(2 * i);
                sheet.setColumnWidth(2 * i, 5120);
                cell3.setCellValue(company.getName());
                final XSSFCell cell4 = sheet.getRow(0).createCell(2 * i + 1);
                sheet.setColumnWidth(2 * i + 1, 3328);
                cell4.setCellValue("\u5355\u4f4d\uff1a\u4e07\u5143");
                if (company.getType() == 1L) {
                    cell3.setCellStyle((CellStyle)style2);
                    cell4.setCellStyle((CellStyle)style2);
                }
                else {
                    cell3.setCellStyle((CellStyle)style1);
                    cell4.setCellStyle((CellStyle)style1);
                }
                final XSSFCell cell5 = sheet.getRow(1).createCell(2 * i);
                cell5.setCellValue("\u6628\u65e5\u4f59\u989d");
                final XSSFCell cell6 = sheet.getRow(1).createCell(2 * i + 1);
                cell6.setCellValue((null == company.getLastdayAmount()) ? "-" : amountFormat.format(company.getLastdayAmount()));
                if (company.getType() == 1L) {
                    cell5.setCellStyle((CellStyle)style2);
                    cell6.setCellStyle((CellStyle)style2);
                }
                else {
                    cell5.setCellStyle((CellStyle)style1);
                    cell6.setCellStyle((CellStyle)style1);
                }
                final XSSFCell cell7 = sheet.getRow(2).createCell(2 * i);
                cell7.setCellValue("\u6536\u5165\u5185\u5bb9");
                cell7.setCellStyle((CellStyle)style3);
                final XSSFCell cell8 = sheet.getRow(2).createCell(2 * i + 1);
                cell8.setCellValue("\u91d1\u989d");
                cell8.setCellStyle((CellStyle)style3);
                for (int k = 0; k < company.getNotWorkRoomIncomeList().size(); ++k) {
                    final FinanceReportBo incomeBean = company.getNotWorkRoomIncomeList().get(k);
                    final XSSFCell cell9 = sheet.getRow(k + 3).createCell(2 * i);
                    cell9.setCellValue(incomeBean.getName());
                    cell9.setCellStyle((CellStyle)style3);
                    final XSSFCell cell10 = sheet.getRow(k + 3).createCell(2 * i + 1);
                    cell10.setCellValue((null == incomeBean.getAmount()) ? "-" : amountFormat.format(incomeBean.getAmount()));
                    cell10.setCellStyle((CellStyle)style3);
                    incomeAmoutTotal = incomeAmoutTotal.add(incomeBean.getAmount());
                }
                final XSSFCell cell11 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + 3).createCell(2 * i);
                cell11.setCellValue("\u5de5\u62b5\u623f\u6536\u5165");
                cell11.setCellStyle((CellStyle)style2_1);
                final XSSFCell cell12 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + 3).createCell(2 * i + 1);
                cell12.setCellValue("\u91d1\u989d");
                cell12.setCellStyle((CellStyle)style2_1);
                for (int l = 0; l < company.getWorkRoomIncomeList().size(); ++l) {
                    final FinanceReportBo incomeBean2 = company.getWorkRoomIncomeList().get(l);
                    final XSSFCell cell13 = sheet.getRow(l + bo.getNotWorkRoomIncomeMaxLength() + 4).createCell(2 * i);
                    cell13.setCellValue(incomeBean2.getName());
                    cell13.setCellStyle((CellStyle)style3);
                    final XSSFCell cell14 = sheet.getRow(l + bo.getNotWorkRoomIncomeMaxLength() + 4).createCell(2 * i + 1);
                    cell14.setCellValue((null == incomeBean2.getAmount()) ? "-" : amountFormat.format(incomeBean2.getAmount()));
                    cell14.setCellStyle((CellStyle)style3);
                    incomeAmoutTotal = incomeAmoutTotal.add(incomeBean2.getAmount());
                }
                final XSSFCell cell15 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 2).createCell(2 * i);
                cell15.setCellValue("\u5c0f\u8ba1");
                cell15.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell16 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 2).createCell(2 * i + 1);
                cell16.setCellValue(amountFormat.format(incomeAmoutTotal));
                cell16.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell17 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 5).createCell(2 * i);
                cell17.setCellValue("\u652f\u51fa\u5185\u5bb9");
                cell17.setCellStyle((CellStyle)style3);
                final XSSFCell cell18 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 5).createCell(2 * i + 1);
                cell18.setCellValue("\u91d1\u989d");
                cell18.setCellStyle((CellStyle)style3);
                for (int m = 0; m < company.getNotWorkRoomCostList().size(); ++m) {
                    final FinanceReportBo incomeBean3 = company.getNotWorkRoomCostList().get(m);
                    final XSSFCell cell19 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 6 + m).createCell(2 * i);
                    cell19.setCellValue(incomeBean3.getName());
                    cell19.setCellStyle((CellStyle)style3);
                    final XSSFCell cell20 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + 6 + m).createCell(2 * i + 1);
                    cell20.setCellValue((null == incomeBean3.getAmount()) ? "-" : amountFormat.format(incomeBean3.getAmount()));
                    cell20.setCellStyle((CellStyle)style3);
                    costAmoutTotal = costAmoutTotal.add(incomeBean3.getAmount());
                }
                final XSSFCell cell21 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 6).createCell(2 * i);
                cell21.setCellValue("\u5de5\u62b5\u623f\u652f\u51fa");
                cell21.setCellStyle((CellStyle)style2_1);
                final XSSFCell cell22 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 6).createCell(2 * i + 1);
                cell22.setCellValue("\u91d1\u989d");
                cell22.setCellStyle((CellStyle)style2_1);
                for (int j2 = 0; j2 < company.getWorkRoomCostList().size(); ++j2) {
                    final FinanceReportBo incomeBean4 = company.getWorkRoomCostList().get(j2);
                    final XSSFCell cell23 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 7 + j2).createCell(2 * i);
                    cell23.setCellValue(incomeBean4.getName());
                    cell23.setCellStyle((CellStyle)style3);
                    final XSSFCell cell24 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + 7 + j2).createCell(2 * i + 1);
                    cell24.setCellValue((null == incomeBean4.getAmount()) ? "-" : amountFormat.format(incomeBean4.getAmount()));
                    cell24.setCellStyle((CellStyle)style3);
                    costAmoutTotal = costAmoutTotal.add(incomeBean4.getAmount());
                }
                final XSSFCell cell25 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 5).createCell(2 * i);
                cell25.setCellValue("\u5c0f\u8ba1");
                cell25.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell26 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 5).createCell(2 * i + 1);
                cell26.setCellValue(amountFormat.format(costAmoutTotal));
                cell26.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell27 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 8).createCell(2 * i);
                cell27.setCellValue("\u5f53\u65e5\u4f59\u989d");
                cell27.setCellStyle((CellStyle)style2_3);
                final XSSFCell cell28 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 8).createCell(2 * i + 1);
                cell28.setCellValue((null == company.getTodayAmount()) ? "-" : amountFormat.format(company.getTodayAmount()));
                cell28.setCellStyle((CellStyle)style2_3);
                final XSSFCell cell29 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 11).createCell(2 * i);
                cell29.setCellValue("\u4e0d\u53ef\u7528\u8d44\u91d1\u5185\u5bb9");
                cell29.setCellStyle((CellStyle)style3);
                final XSSFCell cell30 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 11).createCell(2 * i + 1);
                cell30.setCellValue("\u91d1\u989d");
                cell30.setCellStyle((CellStyle)style3);
                for (int j3 = 0; j3 < company.getSubCompInvalidCapitalList().size(); ++j3) {
                    final FinanceReportBo incomeBean5 = company.getSubCompInvalidCapitalList().get(j3);
                    final XSSFCell cell31 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 12 + j3).createCell(2 * i);
                    cell31.setCellValue(incomeBean5.getName());
                    cell31.setCellStyle((CellStyle)style3);
                    final XSSFCell cell32 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + 12 + j3).createCell(2 * i + 1);
                    cell32.setCellValue((null == incomeBean5.getAmount()) ? "-" : amountFormat.format(incomeBean5.getAmount()));
                    cell32.setCellStyle((CellStyle)style3);
                    invalidAmoutTotal = invalidAmoutTotal.add(incomeBean5.getAmount());
                }
                final XSSFCell cell33 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 12).createCell(2 * i);
                cell33.setCellValue("\u5c0f\u8ba1");
                cell33.setCellStyle((CellStyle)style2_2);
                final XSSFCell cell34 = sheet.getRow(bo.getNotWorkRoomIncomeMaxLength() + bo.getWorkRoomIncomeListMaxLength() + bo.getNotWorkRoomCostListMaxLength() + bo.getWorkRoomCostListMaxLeng() + bo.getInvalidCapitalListMaxLeng() + 12).createCell(2 * i + 1);
                cell34.setCellValue(amountFormat.format(invalidAmoutTotal));
                cell34.setCellStyle((CellStyle)style2_2);
            }
            final XSSFSheet sheet2 = wb.getSheetAt(1);
            bo.setType(0L);
            final List<FinanceReportBo> noJoinCompanyList = this.financeReportService.selectSubCompanyForTemplate(bo);
            bo.setType(1L);
            final List<FinanceReportBo> joinCompanyList = this.financeReportService.selectSubCompanyForTemplate(bo);
            BigDecimal lastdayAmount = new BigDecimal(0);
            BigDecimal incomeAmount = new BigDecimal(0);
            BigDecimal costAmount = new BigDecimal(0);
            BigDecimal todayAmount = new BigDecimal(0);
            BigDecimal invalidAmount = new BigDecimal(0);
            BigDecimal availableAmount = new BigDecimal(0);
            for (int i2 = 0; i2 < noJoinCompanyList.size(); ++i2) {
                final FinanceReportBo bean2 = noJoinCompanyList.get(i2);
                final FinanceReportBo subQueryBean = new FinanceReportBo();
                subQueryBean.setId(bean2.getId());
                subQueryBean.setUserId(Long.valueOf(areaUserIdQuery));
                subQueryBean.setDateStr(DateUtil.getPreDate(areaCurrentDateQuery));
                List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    lastdayAmount = lastdayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(2).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(2).getCell(i2 + 2).setCellValue("-");
                }
                subQueryBean.setDateStr(areaCurrentDateQuery);
                list = this.financeReportService.selectIncomeTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    incomeAmount = incomeAmount.add(list.get(0).getAmount());
                    sheet2.getRow(3).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(3).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectCostTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    costAmount = costAmount.add(list.get(0).getAmount());
                    sheet2.getRow(4).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(4).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    bean2.setTodayAmount(list.get(0).getAmount());
                    todayAmount = todayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(5).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(5).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectInvalidCapitalTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    invalidAmount = invalidAmount.add(list.get(0).getAmount());
                    sheet2.getRow(6).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(7).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount().subtract(list.get(0).getAmount())));
                    }
                }
                else {
                    sheet2.getRow(6).getCell(i2 + 2).setCellValue("-");
                    sheet2.getRow(7).getCell(i2 + 2).setCellValue("-");
                }
                availableAmount = todayAmount.subtract(invalidAmount);
            }
            sheet2.getRow(2).getCell(1).setCellValue(amountFormat.format(lastdayAmount));
            sheet2.getRow(3).getCell(1).setCellValue(amountFormat.format(incomeAmount));
            sheet2.getRow(4).getCell(1).setCellValue(amountFormat.format(costAmount));
            sheet2.getRow(5).getCell(1).setCellValue(amountFormat.format(todayAmount));
            sheet2.getRow(6).getCell(1).setCellValue(amountFormat.format(invalidAmount));
            sheet2.getRow(7).getCell(1).setCellValue(amountFormat.format(availableAmount));
            sheet2.getRow(8).getCell(1).setCellValue(sheet2.getRow(7).getCell(1).getStringCellValue());
            sheet2.getRow(8).getCell(2).setCellValue(this.countCellValue(sheet2, 7, 2, 4));
            sheet2.getRow(8).getCell(5).setCellValue(this.countCellValue(sheet2, 7, 5, 6));
            sheet2.getRow(8).getCell(7).setCellValue(this.countCellValue(sheet2, 7, 7, 7));
            sheet2.getRow(8).getCell(8).setCellValue(this.countCellValue(sheet2, 7, 8, 9));
            sheet2.getRow(8).getCell(10).setCellValue(this.countCellValue(sheet2, 7, 10, 12));
            sheet2.getRow(8).getCell(13).setCellValue(this.countCellValue(sheet2, 7, 13, 13));
            sheet2.getRow(8).getCell(14).setCellValue(this.countCellValue(sheet2, 7, 14, 16));
            sheet2.getRow(8).getCell(17).setCellValue(this.countCellValue(sheet2, 7, 17, 19));
            sheet2.getRow(8).getCell(20).setCellValue(this.countCellValue(sheet2, 7, 20, 22));
            sheet2.getRow(8).getCell(23).setCellValue(this.countCellValue(sheet2, 7, 23, 27));
            sheet2.getRow(8).getCell(28).setCellValue(this.countCellValue(sheet2, 7, 28, 28));
            lastdayAmount = new BigDecimal(0);
            incomeAmount = new BigDecimal(0);
            costAmount = new BigDecimal(0);
            todayAmount = new BigDecimal(0);
            invalidAmount = new BigDecimal(0);
            availableAmount = new BigDecimal(0);
            for (int i2 = 0; i2 < joinCompanyList.size(); ++i2) {
                final FinanceReportBo bean2 = joinCompanyList.get(i2);
                final FinanceReportBo subQueryBean = new FinanceReportBo();
                subQueryBean.setId(bean2.getId());
                subQueryBean.setUserId(Long.valueOf(areaUserIdQuery));
                subQueryBean.setDateStr(DateUtil.getPreDate(areaCurrentDateQuery));
                List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    lastdayAmount = lastdayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(11).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(11).getCell(i2 + 2).setCellValue("-");
                }
                subQueryBean.setDateStr(areaCurrentDateQuery);
                list = this.financeReportService.selectIncomeTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    incomeAmount = incomeAmount.add(list.get(0).getAmount());
                    sheet2.getRow(12).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(12).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectCostTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    costAmount = costAmount.add(list.get(0).getAmount());
                    sheet2.getRow(13).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(13).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectSubCompBalanceTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    bean2.setTodayAmount(list.get(0).getAmount());
                    todayAmount = todayAmount.add(list.get(0).getAmount());
                    sheet2.getRow(14).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                }
                else {
                    sheet2.getRow(14).getCell(i2 + 2).setCellValue("-");
                }
                list = this.financeReportService.selectInvalidCapitalTotal(subQueryBean);
                if (null != list && list.size() > 0) {
                    invalidAmount = invalidAmount.add(list.get(0).getAmount());
                    sheet2.getRow(15).getCell(i2 + 2).setCellValue(amountFormat.format(list.get(0).getAmount()));
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount().subtract(list.get(0).getAmount())));
                    }
                }
                else {
                    sheet2.getRow(15).getCell(i2 + 2).setCellValue("-");
                    if (null != bean2.getTodayAmount()) {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue(amountFormat.format(bean2.getTodayAmount()));
                    }
                    else {
                        sheet2.getRow(16).getCell(i2 + 2).setCellValue("-");
                    }
                }
                availableAmount = todayAmount.subtract(invalidAmount);
                sheet2.getRow(17).getCell(i2 + 2).setCellValue(sheet2.getRow(16).getCell(i2 + 2).getStringCellValue());
            }
            sheet2.getRow(11).getCell(1).setCellValue(amountFormat.format(lastdayAmount));
            sheet2.getRow(12).getCell(1).setCellValue(amountFormat.format(incomeAmount));
            sheet2.getRow(13).getCell(1).setCellValue(amountFormat.format(costAmount));
            sheet2.getRow(14).getCell(1).setCellValue(amountFormat.format(todayAmount));
            sheet2.getRow(15).getCell(1).setCellValue(amountFormat.format(invalidAmount));
            sheet2.getRow(16).getCell(1).setCellValue(amountFormat.format(availableAmount));
            sheet2.getRow(17).getCell(1).setCellValue(sheet2.getRow(16).getCell(1).getStringCellValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final String fileName = "\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u8d44\u91d1\u62a5\u8868" + DateUtil.getCurrentDateString();
        return this.exportExcel(response, fileName + ".xlsx", wb);
    }*/
}
