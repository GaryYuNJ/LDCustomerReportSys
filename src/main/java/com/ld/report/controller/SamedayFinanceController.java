package com.ld.report.controller;

import com.ld.common.controller.*;
import org.springframework.stereotype.*;
import org.springframework.context.annotation.*;
import com.ld.report.service.*;
import org.springframework.beans.factory.annotation.*;
import javax.servlet.http.*;
import com.ld.report.bo.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;
import java.math.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.ld.common.utils.*;

@Controller
@Scope("prototype")
@RequestMapping({ "samedayFinance" })
public class SamedayFinanceController extends BaseController
{
    /*@Autowired
    FinanceReportService financeReportService;
    
    @RequestMapping({ "selectViewFilePath" })
    @ResponseBody
    public FinanceReportBo selectViewFilePath(final HttpServletRequest request) {
        final String pathDir = FileUtil.getProperties("config.properties", "VIEW_FILE_PATH");
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setName(pathDir);
        return bo;
    }
    
    @RequestMapping({ "samedayFinance" })
    public ModelAndView someday(final ModelMap modelMap, final Integer pageNo, final String findContent) {
        modelMap.put("findContent", (Object)findContent);
        modelMap.put("pageIndex", (Object)0);
        return new ModelAndView("report/samedayFinance");
    }
    
    @RequestMapping({ "selectSubCompTotalBalanceByCond" })
    @ResponseBody
    public FinanceReportBo selectSubCompTotalBalanceByCond(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        final String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(request.getParameter("date"));
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectSubCompTotalBalanceByCond(bo);
        if (null != bos && bos.size() > 0) {
            return bos.get(0);
        }
        return null;
    }
    
    @RequestMapping({ "selectSubCompLastDayBalance" })
    @ResponseBody
    public List<FinanceReportBo> selectSubCompLastDayBalance(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isBlank(date)) {
            date = DateUtil.getCurrentDateString();
        }
        date = DateUtil.getPreDate(date);
        bo.setDateStr(date);
        final List<FinanceReportBo> bos = this.financeReportService.selectSubCompBalanceTotal(bo);
        return bos;
    }
    
    @RequestMapping({ "selectSubCompBalance" })
    @ResponseBody
    public List<FinanceReportBo> selectSubCompBalance(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        final String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(request.getParameter("date"));
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectSubCompBalance(bo);
        return bos;
    }
    
    @RequestMapping({ "selectIncome" })
    @ResponseBody
    public List<FinanceReportBo> selectIncome(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        final String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(request.getParameter("date"));
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectIncome(bo);
        return bos;
    }
    
    @RequestMapping({ "selectCost" })
    @ResponseBody
    public List<FinanceReportBo> selectCost(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        final String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(request.getParameter("date"));
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectCost(bo);
        return bos;
    }
    
    @RequestMapping({ "selectInvalidCapital" })
    @ResponseBody
    public List<FinanceReportBo> selectInvalidCapital(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        final String date = request.getParameter("date");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(request.getParameter("date"));
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectInvalidCapital(bo);
        return bos;
    }
    
    @RequestMapping({ "querySubCompanyAll" })
    @ResponseBody
    public FinanceReportBo querySubCompanyAll(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String id = request.getParameter("id");
        String date = request.getParameter("date");
        final String userId = request.getParameter("userId");
        if (StringUtils.isNotBlank(id)) {
            bo.setId(Long.valueOf(id));
        }
        if (StringUtils.isBlank(date)) {
            date = DateUtil.getCurrentDateString();
        }
        bo.setDateStr(date);
        if (StringUtils.isNotBlank(userId)) {
            bo.setUserId(Long.valueOf(userId));
        }
        bo.setSubCompBalanceList(this.financeReportService.selectSubCompBalance(bo));
        bo.setSubCompGongdiBalanceList(this.financeReportService.selectSubCompGongdiBalance(bo));
        final List<FinanceReportBo> incomeList = this.financeReportService.selectIncome(bo);
        bo.setSubCompIncomeList(incomeList);
        final List<FinanceReportBo> costList = this.financeReportService.selectCost(bo);
        bo.setSubCompCostList(costList);
        bo.setSubCompInvalidCapitalList(this.financeReportService.selectInvalidCapital(bo));
        date = DateUtil.getPreDate(date);
        bo.setDateStr(date);
        bo.setSubCompLastDayBalanceList(this.financeReportService.selectSubCompBalanceTotal(bo));
        final List<FinanceReportBo> lastDayGongdiList = this.financeReportService.selectSubCompGongdiBalance(bo);
        bo.setSubCompLastDayGongdiBalanceList(lastDayGongdiList);
        BigDecimal lastDayGongdiAmount = new BigDecimal(0);
        BigDecimal incomeAmount = new BigDecimal(0);
        BigDecimal costAmount = new BigDecimal(0);
        if (null != lastDayGongdiList && lastDayGongdiList.size() > 0 && lastDayGongdiList.get(0) != null) {
            lastDayGongdiAmount = lastDayGongdiAmount.add(lastDayGongdiList.get(0).getAmount());
        }
        if (null != incomeList && incomeList.size() > 0) {
            for (final FinanceReportBo bean : incomeList) {
                if (bean.getIncomeTypeId() == 2L) {
                    incomeAmount = incomeAmount.add(bean.getAmount());
                }
            }
        }
        if (null != costList && costList.size() > 0) {
            for (final FinanceReportBo bean : costList) {
                if (bean.getCostTypeId() == 2L) {
                    costAmount = costAmount.add(bean.getAmount());
                }
            }
        }
        if (null != bo.getSubCompGongdiBalanceList() && bo.getSubCompGongdiBalanceList().size() > 0 && null != bo.getSubCompGongdiBalanceList().get(0)) {
            bo.setGongdiBalance(bo.getSubCompGongdiBalanceList().get(0).getAmount());
        }
        return bo;
    }
    
    @RequestMapping({ "selectSubCompany" })
    @ResponseBody
    public List<FinanceReportBo> selectSubCompany(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String userId = request.getParameter("userId");
        final String type = request.getParameter("type");
        final String flag = request.getParameter("flag");
        if (StringUtils.isNotBlank(type) && !"-1".equals(type)) {
            bo.setType(Long.valueOf(type));
        }
        bo.setUserId(Long.valueOf(userId));
        final List<FinanceReportBo> boList = this.financeReportService.selectSubCompany(bo);
        if ("all".equals(flag)) {
            final List<FinanceReportBo> list = new ArrayList<FinanceReportBo>();
            bo.setId(-1L);
            bo.setName("\u5168\u90e8");
            list.add(bo);
            for (final FinanceReportBo obj : boList) {
                list.add(obj);
            }
            return list;
        }
        return boList;
    }
    
    @RequestMapping({ "selectcBranchCompany" })
    @ResponseBody
    public List<FinanceReportBo> selectcBranchCompany(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String userId = request.getParameter("userId");
        final String type = request.getParameter("type");
        final String flag = request.getParameter("flag");
        if (StringUtils.isNotBlank(type) && !"-1".equals(type)) {
            bo.setType(Long.valueOf(type));
        }
        bo.setUserId(Long.valueOf(userId));
        final List<FinanceReportBo> boList = this.financeReportService.selectcBranchCompany(bo);
        if ("all".equals(flag)) {
            final List<FinanceReportBo> list = new ArrayList<FinanceReportBo>();
            bo.setId(-1L);
            bo.setName("\u5168\u90e8");
            list.add(bo);
            for (final FinanceReportBo obj : boList) {
                list.add(obj);
            }
            return list;
        }
        return boList;
    }
    
    @RequestMapping({ "selectcDistrict" })
    @ResponseBody
    public List<FinanceReportBo> selectcDistrict(final HttpServletRequest request) {
        final String flag = request.getParameter("flag");
        final List<FinanceReportBo> boList = this.financeReportService.selectcDistrict();
        if ("all".equals(flag)) {
            final List<FinanceReportBo> list = new ArrayList<FinanceReportBo>();
            final FinanceReportBo bo = new FinanceReportBo();
            bo.setId(-1L);
            bo.setName("\u5168\u90e8");
            list.add(bo);
            for (final FinanceReportBo obj : boList) {
                list.add(obj);
            }
            return list;
        }
        return boList;
    }
    
    @RequestMapping({ "selectInvalidCapitalType" })
    @ResponseBody
    public List<FinanceReportBo> selectInvalidCapitalType(final Long id) {
        final List<FinanceReportBo> bos = this.financeReportService.selectInvalidCapitalType();
        return bos;
    }
    
    @RequestMapping({ "selectBank" })
    @ResponseBody
    public List<FinanceReportBo> selectBank(final Long id) {
        final List<FinanceReportBo> bos = this.financeReportService.selectBank(null);
        return bos;
    }
    
    @RequestMapping({ "selectIncomeType" })
    @ResponseBody
    public List<FinanceReportBo> selectIncomeType(final Long id) {
        final List<FinanceReportBo> bos = this.financeReportService.selectIncomeType();
        return bos;
    }
    
    @RequestMapping({ "selectCostType" })
    @ResponseBody
    public List<FinanceReportBo> selectCostType(final Long id) {
        final List<FinanceReportBo> bos = this.financeReportService.selectCostType();
        return bos;
    }
    
    @RequestMapping({ "insertSubCompBalance" })
    @ResponseBody
    public Map<String, Object> insertSubCompBalance(@RequestBody final String requestBody) {
        return this.financeReportService.insertSubCompBalance(requestBody);
    }
    
    @RequestMapping({ "inertIncome" })
    @ResponseBody
    public Map<String, Object> inertIncome(@RequestBody final String requestBody) {
        return this.financeReportService.insertIncome(requestBody);
    }
    
    @RequestMapping({ "insertCost" })
    @ResponseBody
    public Map<String, Object> insertCost(@RequestBody final String requestBody) {
        return this.financeReportService.insertCost(requestBody);
    }
    
    @RequestMapping({ "insertInvalidCapital" })
    @ResponseBody
    public Map<String, Object> insertInvalidCapital(@RequestBody final String requestBody) {
        return this.financeReportService.insertInvalidCapital(requestBody);
    }
    
    @RequestMapping({ "selectSmsRecord" })
    @ResponseBody
    public List<FinanceReportBo> selectSmsRecord(final HttpServletRequest request) {
        final FinanceReportBo bo = new FinanceReportBo();
        final String date = request.getParameter("date");
        final String code = request.getParameter("code");
        if (StringUtils.isNotBlank(date)) {
            bo.setDateStr(date);
        }
        if (StringUtils.isNotBlank(code)) {
            bo.setRandomCode(code);
        }
        final List<FinanceReportBo> bos = this.financeReportService.selectSmsRecord(bo);
        return bos;
    }
    
    @RequestMapping({ "insertSmsRecord" })
    @ResponseBody
    public Map<String, Object> insertSmsRecord(@RequestBody final String requestBody) {
        return this.financeReportService.insertSmsRecord(requestBody);
    }
    
    @RequestMapping({ "updateSmsRecord" })
    @ResponseBody
    public Map<String, Object> updateSmsRecord(@RequestBody final String requestBody) {
        return this.financeReportService.updateSmsRecord(requestBody);
    }
    
    @RequestMapping({ "sendSmsRecord" })
    @ResponseBody
    public Map<String, Object> sendSmsRecord(@RequestBody final String requestBody) {
        return this.financeReportService.sendSmsRecord(requestBody);
    }
    
    @RequestMapping({ "selectCapitalBalanceTemplate" })
    @ResponseBody
    public FinanceReportBo selectCapitalBalanceTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final StringBuffer invalidAllStr = new StringBuffer();
        final StringBuffer balanceAllStr = new StringBuffer();
        final StringBuffer incomeStr = new StringBuffer();
        final StringBuffer costStr = new StringBuffer();
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        BigDecimal balanceTotalAllAmount = new BigDecimal(0);
        BigDecimal invalidTotalAllAmount = new BigDecimal(0);
        BigDecimal unInvalidTotalAllAmount = new BigDecimal(0);
        BigDecimal incomeTotalAmount = new BigDecimal(0);
        BigDecimal costTotalAmount = new BigDecimal(0);
        BigDecimal gongdiBalanceAmount = new BigDecimal(0);
        BigDecimal gongdiIncomeTotalAmount = new BigDecimal(0);
        BigDecimal gongdiBalanceAllAmount = new BigDecimal(0);
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select sum(t.amount) amount from c_gongdi_balance t where t.sub_company_id in (  select t1.id from c_sub_company t1 where t1.type = 0) and t.date = '" + currentDate + "' ";
        bo.setSql(sql);
        final List<FinanceReportBo> gongdiAllList = this.financeReportService.selectcDataBysql(bo);
        if (null != gongdiAllList && gongdiAllList.size() > 0 && gongdiAllList.get(0) != null) {
            gongdiBalanceAllAmount = gongdiAllList.get(0).getAmount();
        }
        sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 0 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            sql = " select sum(t1.amount) amount from c_income t1 where t1.income_type_id != 1 and  t1.date = '" + currentDate + "' and t1.income_type_id not in (1,2) and t1.sub_company_id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ")";
            distract.setSql(sql);
            final List<FinanceReportBo> incomeList = this.financeReportService.selectcDataBysql(distract);
            if (null != incomeList && incomeList.size() > 0 && incomeList.get(0) != null) {
                for (int j = 0; j < incomeList.size(); ++j) {
                    final FinanceReportBo bean = incomeList.get(j);
                    if (null != bean) {
                        incomeStr.append(distract.getName()).append(" ").append(MathUtil.format(bean.getAmount())).append(" \u4e07");
                        if (j != incomeList.size() - 1) {
                            incomeStr.append("\u3001");
                        }
                        incomeTotalAmount = incomeTotalAmount.add(bean.getAmount());
                    }
                }
                if (i != distractList.size() - 1) {
                    incomeStr.append("\u3001");
                }
            }
            sql = " select sum(t1.amount) amount from c_cost t1 where t1.cost_type_id != 1 and  t1.date = '" + currentDate + "' and t1.cost_type_id not in (1,2) and t1.sub_company_id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ")";
            distract.setSql(sql);
            final List<FinanceReportBo> costList = this.financeReportService.selectcDataBysql(distract);
            if (null != costList && costList.size() > 0 && costList.get(0) != null) {
                for (int k = 0; k < costList.size(); ++k) {
                    final FinanceReportBo bean2 = costList.get(k);
                    if (null != bean2) {
                        costStr.append(distract.getName()).append(" ").append(MathUtil.format(bean2.getAmount())).append(" \u4e07");
                        if (k != costList.size() - 1) {
                            costStr.append("\u3001");
                        }
                        costTotalAmount = costTotalAmount.add(bean2.getAmount());
                    }
                }
                if (i != distractList.size() - 1) {
                    costStr.append("\u3001");
                }
            }
            sql = " select t2.name, sum(t1.amount) amount from c_his_invalid_capital t1, c_invalid_capital_type t2  where t1.invalid_capital_type_id = t2.id  and t1.date = '" + currentDate + "' and t1.sub_company_id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ") group by t2.name";
            distract.setSql(sql);
            final List<FinanceReportBo> invalidTypeList = this.financeReportService.selectcDataBysql(distract);
            BigDecimal invalidTypeAmount = new BigDecimal(0);
            final StringBuffer invalidTypeStr = new StringBuffer();
            for (int l = 0; l < invalidTypeList.size(); ++l) {
                final FinanceReportBo bean3 = invalidTypeList.get(l);
                invalidTypeStr.append(bean3.getName()).append(' ').append(MathUtil.format(bean3.getAmount())).append(" \u4e07\uff0c");
                invalidTypeAmount = invalidTypeAmount.add(bean3.getAmount());
                invalidTotalAllAmount = invalidTotalAllAmount.add(bean3.getAmount());
            }
            final StringBuffer temp1 = new StringBuffer();
            if (invalidTypeAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp1.append(MathUtil.format(invalidTypeAmount)).append(' ').append("\u4e07\u3010").append(invalidTypeStr).append("<br/>\u5176\u4e2d\uff1a");
            }
            else {
                temp1.append("0 \u4e07");
            }
            invalidAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp1);
            BigDecimal balanceTotalAmount = new BigDecimal(0);
            BigDecimal invalidTotalAmount = new BigDecimal(0);
            final StringBuffer balanceStr = new StringBuffer();
            final StringBuffer companyInvalidStr = new StringBuffer();
            sql = " select t1.id, t1.name from c_sub_company t1 where t1.id in (  select t2.id from c_sub_company t2, c_branch_company t3 where t2.type = 0 and  t2.branch_company_id = t3.id and t3.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> companyList = this.financeReportService.selectcDataBysql(distract);
            BigDecimal canUseredTotalAmount = new BigDecimal(0);
            for (int m = 0; m < companyList.size(); ++m) {
                final FinanceReportBo bean4 = companyList.get(m);
                final StringBuffer compInvalidDetailStr = new StringBuffer();
                BigDecimal canUseredAmount = new BigDecimal(0);
                BigDecimal gongdiAmount = new BigDecimal(0);
                BigDecimal invalidAmount = new BigDecimal(0);
                sql = " select t2.name, t1.amount  from c_his_invalid_capital t1, c_invalid_capital_type t2  where t1.invalid_capital_type_id = t2.id and  t1.date = '" + currentDate + "' and  t1.sub_company_id  = " + bean4.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> companyInvalidList = this.financeReportService.selectcDataBysql(distract);
                if (null != companyInvalidList && companyInvalidList.size() > 0 && companyInvalidList.get(0) != null) {
                    companyInvalidStr.append(bean4.getName()).append(' ');
                    for (int k2 = 0; k2 < companyInvalidList.size(); ++k2) {
                        final FinanceReportBo invalidBean = companyInvalidList.get(k2);
                        compInvalidDetailStr.append(invalidBean.getName()).append(' ').append(MathUtil.format(invalidBean.getAmount())).append(" \u4e07");
                        invalidAmount = invalidAmount.add(invalidBean.getAmount());
                        if (k2 != companyInvalidList.size() - 1) {
                            compInvalidDetailStr.append("\u3001");
                        }
                        invalidTotalAmount = invalidTotalAmount.add(invalidBean.getAmount());
                    }
                    companyInvalidStr.append(MathUtil.format(invalidAmount)).append(" \u4e07").append("\uff08").append(compInvalidDetailStr).append("\uff09");
                    invalidTotalAmount = invalidTotalAmount.add(invalidAmount);
                    if (m != companyList.size() - 1) {
                        companyInvalidStr.append("\uff0c");
                    }
                }
                BigDecimal gongdiIncomeAmount = new BigDecimal(0);
                sql = " select sum(t1.amount) amount from c_gongdi_balance t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean4.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> gongdiList = this.financeReportService.selectcDataBysql(distract);
                if (null != gongdiList && gongdiList.size() > 0 && gongdiList.get(0) != null) {
                    gongdiAmount = gongdiList.get(0).getAmount();
                }
                sql = " select sum(t1.amount) amount from c_income t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean4.getId() + " and t1.income_type_id = " + 2;
                distract.setSql(sql);
                final List<FinanceReportBo> gongdiIncomelist = this.financeReportService.selectcDataBysql(distract);
                if (null != gongdiIncomelist && gongdiIncomelist.size() > 0 && gongdiIncomelist.get(0) != null) {
                    gongdiIncomeAmount = gongdiIncomelist.get(0).getAmount();
                    gongdiIncomeTotalAmount = gongdiIncomeTotalAmount.add(gongdiIncomelist.get(0).getAmount());
                }
                gongdiBalanceAmount = gongdiBalanceAmount.add(gongdiAmount);
                BigDecimal balanceAmount = new BigDecimal(0);
                sql = " select sum(t1.amount) amount from c_sub_company_his_balance t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean4.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(distract);
                if (null != list && list.size() > 0 && list.get(0) != null) {
                    final FinanceReportBo balanceBean = list.get(0);
                    balanceAmount = balanceBean.getAmount();
                    balanceTotalAmount = balanceTotalAmount.add(balanceBean.getAmount());
                    balanceTotalAmount = balanceTotalAmount.add(((null == balanceBean.getAmount()) ? new BigDecimal(0) : balanceBean.getAmount()).subtract(invalidAmount));
                    balanceTotalAllAmount = balanceTotalAllAmount.add((null == balanceBean.getAmount()) ? new BigDecimal(0) : balanceBean.getAmount());
                }
                canUseredAmount = balanceAmount.subtract(invalidAmount).subtract(gongdiAmount).subtract(gongdiIncomeAmount);
                if (canUseredAmount.compareTo(BigDecimal.ZERO) != 0) {
                    balanceStr.append(bean4.getName()).append(' ').append(MathUtil.format(canUseredAmount)).append(" \u4e07");
                    if (i != distractList.size() - 1) {
                        balanceStr.append("\u3001");
                    }
                }
                canUseredTotalAmount = canUseredTotalAmount.add(canUseredAmount);
            }
            final StringBuffer temp2 = new StringBuffer();
            if (invalidTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp2.append("\u3011\uff1b");
            }
            invalidAllStr.append((CharSequence)(companyInvalidStr.toString().endsWith("\uff0c") ? companyInvalidStr.substring(0, companyInvalidStr.length() - 1) : companyInvalidStr)).append(temp2);
            final StringBuffer temp3 = new StringBuffer();
            if (canUseredTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp3.append(MathUtil.format(canUseredTotalAmount)).append(' ').append("\u4e07\uff08\u5176\u4e2d\uff1a").append((CharSequence)(balanceStr.toString().endsWith("\u3001") ? balanceStr.substring(0, balanceStr.length() - 1) : balanceStr)).append("\uff09\uff1b<br/>");
            }
            else {
                temp3.append("0 \u4e07<br/>");
            }
            balanceAllStr.append(indexArray[i]).append(distract.getName()).append(' ').append(temp3);
        }
        unInvalidTotalAllAmount = balanceTotalAllAmount.subtract(invalidTotalAllAmount).subtract(gongdiBalanceAmount).subtract(gongdiIncomeTotalAmount);
        allStr.append(DateUtil.getCurrentDateString()).append("\u65e5\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u8d44\u91d1\u4f59\u989d " + MathUtil.format(balanceTotalAllAmount.subtract(gongdiBalanceAllAmount))).append(" \u4e07\uff0c\u5176\u4e2d\u4e0d\u53ef\u7528\u8d44\u91d1 ").append(MathUtil.format(invalidTotalAllAmount)).append(" \u4e07\uff1a").append(invalidAllStr).append("<br/><br/>\u5b9e\u9645\u53ef\u7528\u8d44\u91d1\uff08\u4e0d\u542b\u5de5\u62b5\uff0c\u5f53\u65e5\u4f59\u989d - \u4e0d\u53ef\u7528 - \u5de5\u62b5\u4f59\u989d\uff09 ").append(MathUtil.format(unInvalidTotalAllAmount)).append(" \u4e07\uff0c\u4f59\u989d\u5206\u522b\u662f\uff1a<br/>").append(balanceAllStr).append("<br/><br/>\u4e0e\u6628\u65e5\u5f62\u6210\u5dee\u5f02 ").append(MathUtil.format(incomeTotalAmount.subtract(costTotalAmount))).append(" \u4e07\uff08\u6536\u5165-\u652f\u51fa\uff09\uff0c\u5dee\u5f02\u539f\u56e0\u5982\u4e0b\uff1a<br/>").append("\u6536\u5165\u5408\u8ba1  ").append(MathUtil.format(incomeTotalAmount)).append("  \u4e07\uff08\u4e0d\u542b\u5f80\u6765\u6b3e\u548c\u5de5\u62b5\u623f\u6536\u5165\uff09").append((incomeTotalAmount.compareTo(BigDecimal.ZERO) == 0) ? "" : "\u3010\u5176\u4e2d\uff1a").append((CharSequence)(incomeStr.toString().endsWith("\u3001") ? incomeStr.substring(0, incomeStr.length() - 1) : incomeStr)).append((incomeTotalAmount.compareTo(BigDecimal.ZERO) == 0) ? "" : "\u3011").append("<br/>\u652f\u51fa\u5408\u8ba1  ").append(MathUtil.format(costTotalAmount)).append("  \u4e07\uff08\u4e0d\u542b\u5f80\u6765\u6b3e\u548c\u5de5\u62b5\u623f\u652f\u51fa\uff09").append((costTotalAmount.compareTo(BigDecimal.ZERO) == 0) ? "" : "\u3010\u5176\u4e2d\uff1a").append((CharSequence)(costStr.toString().endsWith("\u3001") ? costStr.substring(0, costStr.length() - 1) : costStr)).append((costTotalAmount.compareTo(BigDecimal.ZERO) == 0) ? "" : "\u3011");
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectInvalidCapitalTDiffTemplate" })
    @ResponseBody
    public FinanceReportBo selectInvalidCapitalTDiffTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final StringBuffer str1 = new StringBuffer();
        final StringBuffer str2 = new StringBuffer();
        BigDecimal totalAmount1 = new BigDecimal(0);
        BigDecimal totalAmount2 = new BigDecimal(0);
        BigDecimal diffAmount = new BigDecimal(0);
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select sum(t.change_amount) amount from c_his_invalid_capital t where t.sub_company_id in (  select t1.id from c_sub_company t1 where t1.type = 0) and t.date = '" + currentDate + "' ";
        bo.setSql(sql);
        final List<FinanceReportBo> changeList = this.financeReportService.selectcDataBysql(bo);
        if (null != changeList && changeList.size() > 0 && changeList.get(0) != null) {
            diffAmount = changeList.get(0).getAmount();
        }
        sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 0 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            distract.setType(0L);
            distract.setDistrict_id(distract.getId());
            sql = " select t1.id, t1.name from c_sub_company t1  where t1.id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(distract);
            final StringBuffer compValidToInvalidStr = new StringBuffer();
            BigDecimal compValidToInvalidTotalAllAmount = new BigDecimal(0);
            final StringBuffer compInvalidToValidStr = new StringBuffer();
            BigDecimal compInvalidToValidTotalAllAmount = new BigDecimal(0);
            for (int j = 0; j < list.size(); ++j) {
                final FinanceReportBo company = list.get(j);
                sql = " select t2.name, t1.change_amount amount,t1.descs from c_his_invalid_capital t1,c_invalid_capital_type t2 where  t1.invalid_capital_type_id = t2.id and t1.date = '" + currentDate + "' and t1.sub_company_id = " + company.getId();
                bo.setSql(sql);
                final List<FinanceReportBo> validList = this.financeReportService.selectcDataBysql(bo);
                final StringBuffer validToInvalidStr = new StringBuffer();
                BigDecimal validToInvalidTotalAmount = new BigDecimal(0);
                final StringBuffer invalidToValidStr = new StringBuffer();
                BigDecimal invalidToValidTotalAmount = new BigDecimal(0);
                if (null != validList && validList.size() > 0 && validList.get(0) != null) {
                    for (int k = 0; k < validList.size(); ++k) {
                        final FinanceReportBo validBean = validList.get(k);
                        if (validBean.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                            validToInvalidStr.append(StringUtils.isNotBlank(validBean.getDescs()) ? validBean.getDescs() : validBean.getName()).append(' ').append(MathUtil.format(validBean.getAmount().negate())).append(" \u4e07");
                            if (k != validList.size() - 1) {
                                validToInvalidStr.append("\u3001");
                            }
                            validToInvalidTotalAmount = validToInvalidTotalAmount.add(MathUtil.format(validBean.getAmount()));
                        }
                        else {
                            invalidToValidStr.append(StringUtils.isNotBlank(validBean.getDescs()) ? validBean.getDescs() : validBean.getName()).append(' ').append(MathUtil.format(validBean.getAmount().negate())).append(" \u4e07");
                            if (k != validList.size() - 1) {
                                invalidToValidStr.append("\u3001");
                            }
                            invalidToValidTotalAmount = invalidToValidTotalAmount.add(MathUtil.format(validBean.getAmount()));
                        }
                    }
                    if (validToInvalidTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                        compValidToInvalidStr.append(company.getName()).append(' ').append(MathUtil.format(validToInvalidTotalAmount.negate())).append(" \u4e07\uff08").append((CharSequence)(validToInvalidStr.toString().endsWith("\u3001") ? validToInvalidStr.substring(0, validToInvalidStr.length() - 1) : validToInvalidStr)).append(" \uff09");
                        if (j != list.size() - 1) {
                            compValidToInvalidStr.append("\uff0c");
                        }
                        compValidToInvalidTotalAllAmount = compValidToInvalidTotalAllAmount.add(validToInvalidTotalAmount);
                        totalAmount1 = totalAmount1.add(validToInvalidTotalAmount);
                    }
                    if (invalidToValidTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                        compInvalidToValidStr.append(company.getName()).append(' ').append(MathUtil.format(invalidToValidTotalAmount.negate())).append(" \u4e07\uff08").append((CharSequence)(invalidToValidStr.toString().endsWith("\u3001") ? invalidToValidStr.substring(0, invalidToValidStr.length() - 1) : invalidToValidStr)).append(" \uff09");
                        if (j != list.size() - 1) {
                            compInvalidToValidStr.append("\uff0c");
                        }
                        compInvalidToValidTotalAllAmount = compInvalidToValidTotalAllAmount.add(invalidToValidTotalAmount);
                        totalAmount2 = totalAmount2.add(invalidToValidTotalAmount);
                    }
                }
            }
            final StringBuffer temp1 = new StringBuffer();
            if (compValidToInvalidTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp1.append(MathUtil.format(compValidToInvalidTotalAllAmount.negate())).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(compValidToInvalidStr.toString().endsWith("\uff0c") ? compValidToInvalidStr.substring(0, compValidToInvalidStr.length() - 1) : compValidToInvalidStr)).append("\u3011");
            }
            else {
                temp1.append("0 \u4e07");
            }
            str1.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp1);
            final StringBuffer temp2 = new StringBuffer();
            if (compInvalidToValidTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp2.append(MathUtil.format(compInvalidToValidTotalAllAmount.negate())).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(compInvalidToValidStr.toString().endsWith("\uff0c") ? compInvalidToValidStr.substring(0, compInvalidToValidStr.length() - 1) : compInvalidToValidStr)).append("\u3011");
            }
            else {
                temp2.append("0 \u4e07");
            }
            str2.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp2);
        }
        allStr.append("\u4e0d\u53ef\u7528\u8d44\u91d1\u4e0e\u6628\u65e5\u5f62\u6210\u5dee\u5f02 ").append(MathUtil.format(diffAmount.negate())).append(" \u4e07\uff0c\u539f\u56e0\u5982\u4e0b\uff1a<br/><br/>").append("1\u3001\u53ef\u7528\u8d44\u91d1\u8f6c\u4e3a\u4e0d\u53ef\u7528\u8d44\u91d1 ").append(MathUtil.format(totalAmount1.negate())).append(" \u4e07").append(str1).append("<br/><br/>").append("2\u3001\u4e0d\u53ef\u7528\u8d44\u91d1\u8f6c\u4e3a\u53ef\u7528\u8d44\u91d1 ").append(MathUtil.format(totalAmount2.negate())).append(" \u4e07").append(str2);
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectIncomeDetailTemplate" })
    @ResponseBody
    public FinanceReportBo selectIncomeDetailTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 0 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        allStr.append("\u6536\u5165\u660e\u7ec6\uff08\u4e0d\u542b\u5de5\u62b5\u623f\u6536\u5165\u3001\u4e0d\u542b\u5f80\u6765\u6b3e\uff09\uff1a");
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            distract.setType(0L);
            distract.setDistrict_id(distract.getId());
            sql = " select t1.id, t1.name from c_sub_company t1  where t1.id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(distract);
            final StringBuffer subCompanyIncomeStr = new StringBuffer();
            BigDecimal incomeTotalAllAmount = new BigDecimal(0);
            for (int j = 0; j < list.size(); ++j) {
                final FinanceReportBo subCompany = list.get(j);
                sql = " select t1.name, t1.amount from c_income t1 where t1.income_type_id not in (1,2)  and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + subCompany.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> incomeList = this.financeReportService.selectcDataBysql(distract);
                final StringBuffer incomeStr = new StringBuffer();
                BigDecimal incomeTotalAmount = new BigDecimal(0);
                if (null != incomeList && incomeList.size() > 0 && incomeList.get(0) != null) {
                    for (int k = 0; k < incomeList.size(); ++k) {
                        final FinanceReportBo bean = incomeList.get(k);
                        incomeStr.append(bean.getName()).append(' ').append(MathUtil.format(bean.getAmount())).append(" \u4e07");
                        if (k != incomeList.size() - 1) {
                            incomeStr.append("\u3001");
                        }
                        incomeTotalAmount = incomeTotalAmount.add(MathUtil.format(bean.getAmount()));
                    }
                    subCompanyIncomeStr.append(subCompany.getName()).append(' ').append(MathUtil.format(incomeTotalAmount)).append(" \u4e07\uff08").append(incomeStr).append(" \uff09");
                    if (j != list.size() - 1) {
                        subCompanyIncomeStr.append("\uff0c");
                    }
                    incomeTotalAllAmount = incomeTotalAllAmount.add(incomeTotalAmount);
                }
            }
            final StringBuffer temp1 = new StringBuffer();
            if (incomeTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp1.append(MathUtil.format(incomeTotalAllAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyIncomeStr.toString().endsWith("\uff0c") ? subCompanyIncomeStr.substring(0, subCompanyIncomeStr.length() - 1) : subCompanyIncomeStr)).append("\u3011");
            }
            else {
                temp1.append("0 \u4e07");
            }
            allStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp1);
        }
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectCostDetailTemplate" })
    @ResponseBody
    public FinanceReportBo selectCostDetailTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final StringBuffer travelsAllStr = new StringBuffer();
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 0 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        allStr.append("\u652f\u51fa\u660e\u7ec6(\u4e0d\u542b\u5de5\u62b5\u623f\u652f\u51fa\u3001\u4e0d\u542b\u5f80\u6765\u6b3e)\uff1a<br/>");
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            distract.setType(0L);
            distract.setDistrict_id(distract.getId());
            final StringBuffer subCompanyTravelsStr = new StringBuffer();
            BigDecimal travelsTotalAllAmount = new BigDecimal(0);
            sql = " select t1.id, t1.name from c_sub_company t1  where t1.id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(distract);
            final StringBuffer subCompanyCostStr = new StringBuffer();
            BigDecimal costTotalAllAmount = new BigDecimal(0);
            for (int j = 0; j < list.size(); ++j) {
                final FinanceReportBo subCompany = list.get(j);
                sql = " select t1.name, t1.amount, t1.cost_type_id,t1.descs from c_cost t1 where t1.cost_type_id not in (2)  and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + subCompany.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> costList = this.financeReportService.selectcDataBysql(distract);
                final StringBuffer costStr = new StringBuffer();
                BigDecimal costTotalAmount = new BigDecimal(0);
                final StringBuffer travelsStr = new StringBuffer();
                BigDecimal travelsTotalAmount = new BigDecimal(0);
                if (null != costList && costList.size() > 0 && costList.get(0) != null) {
                    for (int k = 0; k < costList.size(); ++k) {
                        final FinanceReportBo bean = costList.get(k);
                        if (1L == bean.getCost_type_id()) {
                            travelsStr.append(StringUtils.isNotBlank(bean.getDescs()) ? bean.getDescs() : bean.getName()).append(' ').append(MathUtil.format(bean.getAmount())).append(" \u4e07");
                            if (k != costList.size() - 1) {
                                travelsStr.append("\u3001");
                            }
                            travelsTotalAmount = travelsTotalAmount.add(MathUtil.format(bean.getAmount()));
                        }
                        else {
                            costStr.append(StringUtils.isNotBlank(bean.getDescs()) ? bean.getDescs() : bean.getName()).append(' ').append(MathUtil.format(bean.getAmount())).append(" \u4e07");
                            if (k != costList.size() - 1) {
                                costStr.append("\u3001");
                            }
                            costTotalAmount = costTotalAmount.add(MathUtil.format(bean.getAmount()));
                        }
                    }
                    if (costTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
                        subCompanyCostStr.append(subCompany.getName()).append(" ").append(MathUtil.format(costTotalAmount)).append(" \u4e07\uff08").append((CharSequence)(costStr.toString().endsWith("\u3001") ? costStr.substring(0, costStr.length() - 1) : costStr)).append(" \uff09");
                    }
                    if (j != list.size() - 1 && StringUtils.isNotBlank(new Object[] { costStr })) {
                        subCompanyCostStr.append("\uff0c");
                    }
                    costTotalAllAmount = costTotalAllAmount.add(costTotalAmount);
                    if (travelsTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
                        subCompanyTravelsStr.append(subCompany.getName()).append(" ").append(MathUtil.format(travelsTotalAmount)).append(" \u4e07\uff08").append((CharSequence)(travelsStr.toString().endsWith("\u3001") ? travelsStr.substring(0, travelsStr.length() - 1) : travelsStr)).append(" \uff09");
                    }
                    if (j != list.size() - 1 && StringUtils.isNotBlank(new Object[] { travelsStr })) {
                        subCompanyTravelsStr.append("\uff0c");
                    }
                    travelsTotalAllAmount = travelsTotalAllAmount.add(travelsTotalAmount);
                }
            }
            final StringBuffer temp1 = new StringBuffer();
            if (costTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp1.append(MathUtil.format(costTotalAllAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyCostStr.toString().endsWith("\uff0c") ? subCompanyCostStr.substring(0, subCompanyCostStr.length() - 1) : subCompanyCostStr)).append("\u3011");
            }
            else {
                temp1.append("0 \u4e07");
            }
            allStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp1);
            final StringBuffer temp2 = new StringBuffer();
            if (travelsTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp2.append(MathUtil.format(travelsTotalAllAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyTravelsStr.toString().endsWith("\uff0c") ? subCompanyTravelsStr.substring(0, subCompanyTravelsStr.length() - 1) : subCompanyTravelsStr)).append("\u3011");
            }
            else {
                temp2.append("0 \u4e07");
            }
            travelsAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp2);
        }
        allStr.append("<br/><br/>\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u5404\u57ce\u5e02\u516c\u53f8\u4e4b\u95f4\u5f80\u6765\u6b3e\u660e\u7ec6\u5982\u4e0b\uff1a<br/>").append(travelsAllStr);
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectWorkArrivedBalanceTemplate" })
    @ResponseBody
    public FinanceReportBo selectWorkArrivedBalanceTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final StringBuffer workArrivedStr = new StringBuffer();
        final StringBuffer incomeAllStr = new StringBuffer();
        final StringBuffer costAllStr = new StringBuffer();
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        BigDecimal preDayAmount = new BigDecimal(0);
        BigDecimal workArrivedAllAmount = new BigDecimal(0);
        BigDecimal incomeTotalAllAmount = new BigDecimal(0);
        BigDecimal costTotalAllAmount = new BigDecimal(0);
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select sum(t1.amount) amount from c_gongdi_balance t1, c_sub_company t2  where t1.sub_company_id = t2.id and t1.date = '" + DateUtil.getPreDate(currentDate) + "'  and t1.sub_company_id in ( select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 0 and t3.branch_company_id = t4.id) ";
        bo.setSql(sql);
        final List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(bo);
        if (null != list && list.size() > 0 && list.get(0) != null) {
            preDayAmount = list.get(0).getAmount();
        }
        sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 0 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            BigDecimal distractIncomeTotalAmount = new BigDecimal(0);
            BigDecimal distractCostTotalAmount = new BigDecimal(0);
            final StringBuffer companyWorkArrivedStr = new StringBuffer();
            BigDecimal companyWorkArrivedAmount = new BigDecimal(0);
            sql = " select t1.id, t1.name from c_sub_company t1 where t1.id in (  select t2.id from c_sub_company t2, c_branch_company t3 where t2.type = 0 and  t2.branch_company_id = t3.id and t3.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> companyList = this.financeReportService.selectcDataBysql(distract);
            final StringBuffer subCompanyIncomeStr = new StringBuffer();
            final StringBuffer subCompanyCostStr = new StringBuffer();
            for (int j = 0; j < companyList.size(); ++j) {
                final FinanceReportBo bean = companyList.get(j);
                sql = " select sum(t1.amount) amount from c_gongdi_balance t1   where t1.date = '" + currentDate + "' and t1.sub_company_id = " + bean.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> workArrivedList = this.financeReportService.selectcDataBysql(distract);
                if (null != workArrivedList && workArrivedList.size() > 0 && workArrivedList.get(0) != null) {
                    companyWorkArrivedStr.append(bean.getName()).append(' ').append(MathUtil.format(workArrivedList.get(0).getAmount())).append(" \u4e07");
                    companyWorkArrivedAmount = companyWorkArrivedAmount.add(workArrivedList.get(0).getAmount());
                    workArrivedAllAmount = workArrivedAllAmount.add(workArrivedList.get(0).getAmount());
                    if (j != companyList.size() - 1) {
                        companyWorkArrivedStr.append("\u3001");
                    }
                }
                BigDecimal incomeTotalAmount = new BigDecimal(0);
                final StringBuffer incomeStr = new StringBuffer();
                sql = " select t1.name, t1.amount from c_income t1 where t1.income_type_id = 2   and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> incomeList = this.financeReportService.selectcDataBysql(distract);
                if (null != incomeList && incomeList.size() > 0 && incomeList.get(0) != null) {
                    for (int k = 0; k < incomeList.size(); ++k) {
                        final FinanceReportBo incomeBean = incomeList.get(k);
                        incomeStr.append(incomeBean.getName()).append(' ').append(MathUtil.format(incomeBean.getAmount())).append(" \u4e07");
                        if (k != incomeList.size() - 1) {
                            incomeStr.append("\u3001");
                        }
                        incomeTotalAmount = incomeTotalAmount.add(MathUtil.format(incomeBean.getAmount()));
                    }
                    subCompanyIncomeStr.append(bean.getName()).append(MathUtil.format(incomeTotalAmount)).append(" \u4e07\uff08").append(incomeStr).append(" \uff09");
                    if (j != companyList.size() - 1) {
                        subCompanyIncomeStr.append("\uff0c");
                    }
                    distractIncomeTotalAmount = distractIncomeTotalAmount.add(incomeTotalAmount);
                    incomeTotalAllAmount = incomeTotalAllAmount.add(incomeTotalAmount);
                }
                BigDecimal costTotalAmount = new BigDecimal(0);
                final StringBuffer costStr = new StringBuffer();
                sql = " select t1.name, t1.amount from c_cost t1 where t1.cost_type_id = 2  and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> costList = this.financeReportService.selectcDataBysql(distract);
                if (null != costList && costList.size() > 0 && costList.get(0) != null) {
                    for (int l = 0; l < costList.size(); ++l) {
                        final FinanceReportBo costBean = costList.get(l);
                        costStr.append(costBean.getName()).append(' ').append(MathUtil.format(costBean.getAmount())).append(" \u4e07");
                        if (l != costList.size() - 1) {
                            costStr.append("\u3001");
                        }
                        costTotalAmount = costTotalAmount.add(MathUtil.format(costBean.getAmount()));
                    }
                    subCompanyCostStr.append(bean.getName()).append(MathUtil.format(costTotalAmount)).append(" \u4e07\uff08").append(costStr).append(" \uff09");
                    if (j != companyList.size() - 1) {
                        subCompanyCostStr.append("\uff0c");
                    }
                    distractCostTotalAmount = distractCostTotalAmount.add(costTotalAmount);
                    costTotalAllAmount = costTotalAllAmount.add(costTotalAmount);
                }
            }
            final StringBuffer temp1 = new StringBuffer();
            if (distractIncomeTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp1.append(MathUtil.format(distractIncomeTotalAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyIncomeStr.toString().endsWith("\uff0c") ? subCompanyIncomeStr.substring(0, subCompanyIncomeStr.length() - 1) : subCompanyIncomeStr)).append("\u3011");
            }
            else {
                temp1.append("0 \u4e07");
            }
            incomeAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp1);
            final StringBuffer temp2 = new StringBuffer();
            if (distractCostTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp2.append(MathUtil.format(distractCostTotalAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyCostStr.toString().endsWith("\uff0c") ? subCompanyCostStr.substring(0, subCompanyCostStr.length() - 1) : subCompanyCostStr)).append("\u3011");
            }
            else {
                temp2.append("0 \u4e07");
            }
            costAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp2);
            final StringBuffer temp3 = new StringBuffer();
            if (companyWorkArrivedAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp3.append(MathUtil.format(companyWorkArrivedAmount)).append(" \u4e07\uff08\u5176\u4e2d\uff1a").append((CharSequence)(companyWorkArrivedStr.toString().endsWith("\u3001") ? companyWorkArrivedStr.substring(0, companyWorkArrivedStr.length() - 1) : companyWorkArrivedStr)).append("\uff09\uff1b");
            }
            else {
                temp3.append("0 \u4e07");
            }
            workArrivedStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp3);
        }
        allStr.append("\u5de5\u7a0b\u62b5\u6b3e\u4f59\u989d  ").append(MathUtil.format(workArrivedAllAmount)).append(" \u4e07\uff1a<br/>").append(workArrivedStr).append("<br/>").append("<br/>\u5de5\u7a0b\u62b5\u6b3e\u6536\u652f\u660e\u7ec6\uff1a\u6536\u5165\u5408\u8ba1 ").append(MathUtil.format(incomeTotalAllAmount)).append(" \u4e07\uff0c").append("\u652f\u51fa\u5408\u8ba1 ").append(MathUtil.format(costTotalAllAmount)).append(" \u4e07\u3002<br/><br/>").append("\u6536\u5165\u660e\u7ec6\uff1a").append(incomeAllStr).append("<br/><br/>").append("\u652f\u51fa\u660e\u7ec6\uff1a").append(costAllStr);
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectJointVentureTemplate" })
    @ResponseBody
    public FinanceReportBo selectJointVentureTemplate(final HttpServletRequest request) {
        final String currentDate = DateUtil.getCurrentDateString();
        final StringBuffer allStr = new StringBuffer();
        final StringBuffer invalidAllStr = new StringBuffer();
        final StringBuffer balanceAllStr = new StringBuffer();
        final StringBuffer incomeAllStr = new StringBuffer();
        final StringBuffer costAllStr = new StringBuffer();
        final String[] indexArray = { "\u2460", "\u2461", "\u2462", "\u2463", "\u2464" };
        BigDecimal balanceTotalAllAmount = new BigDecimal(0);
        BigDecimal invalidTotalAllAmount = new BigDecimal(0);
        BigDecimal unInvalidTotalAllAmount = new BigDecimal(0);
        BigDecimal gongdiBalanceAmount = new BigDecimal(0);
        final StringBuffer str1 = new StringBuffer();
        final StringBuffer str2 = new StringBuffer();
        BigDecimal totalAmount1 = new BigDecimal(0);
        BigDecimal totalAmount2 = new BigDecimal(0);
        final BigDecimal preInvalidAmount = new BigDecimal(0);
        BigDecimal diffAmount = new BigDecimal(0);
        final FinanceReportBo bo = new FinanceReportBo();
        String sql = " select sum(t.change_amount) amount from c_his_invalid_capital t where t.sub_company_id in (  select t1.id from c_sub_company t1 where t1.type = 1) and t.date = '" + currentDate + "' ";
        bo.setSql(sql);
        final List<FinanceReportBo> changeList = this.financeReportService.selectcDataBysql(bo);
        if (null != changeList && changeList.size() > 0 && changeList.get(0) != null) {
            diffAmount = changeList.get(0).getAmount();
        }
        sql = " select distinct t3.id,t3.name from c_sub_company t1, c_branch_company t2, c_district t3  where t1.branch_company_id = t2.id and t2.district_id = t3.id and t1.type = 1 ";
        bo.setSql(sql);
        final List<FinanceReportBo> distractList = this.financeReportService.selectcDataBysql(bo);
        for (int i = 0; i < distractList.size(); ++i) {
            final FinanceReportBo distract = distractList.get(i);
            final StringBuffer compValidToInvalidStr = new StringBuffer();
            BigDecimal compValidToInvalidTotalAllAmount = new BigDecimal(0);
            final StringBuffer compInvalidToValidStr = new StringBuffer();
            BigDecimal compInvalidToValidTotalAllAmount = new BigDecimal(0);
            sql = " select t2.name, sum(t1.amount) amount from c_his_invalid_capital t1, c_invalid_capital_type t2  where t1.invalid_capital_type_id = t2.id  and t1.date = '" + currentDate + "' and t1.sub_company_id in (  select t3.id from c_sub_company t3, c_branch_company t4 where t3.type = 1 and  t3.branch_company_id = t4.id and t4.district_id = " + distract.getId() + ") group by t2.name";
            distract.setSql(sql);
            final List<FinanceReportBo> invalidTypeList = this.financeReportService.selectcDataBysql(distract);
            BigDecimal invalidTypeAmount = new BigDecimal(0);
            final StringBuffer invalidTypeStr = new StringBuffer();
            for (int j = 0; j < invalidTypeList.size(); ++j) {
                final FinanceReportBo bean = invalidTypeList.get(j);
                invalidTypeStr.append(bean.getName()).append(' ').append(MathUtil.format(bean.getAmount())).append(" \u4e07\uff0c");
                invalidTypeAmount = invalidTypeAmount.add(bean.getAmount());
                invalidTotalAllAmount = invalidTotalAllAmount.add(bean.getAmount());
            }
            StringBuffer temp5 = new StringBuffer();
            if (invalidTypeAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp5.append(MathUtil.format(invalidTypeAmount)).append(' ').append("\u4e07\u3010").append(invalidTypeStr).append("<br/>\u5176\u4e2d\uff1a");
            }
            else {
                temp5.append("0 \u4e07");
            }
            invalidAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp5);
            BigDecimal balanceTotalAmount = new BigDecimal(0);
            BigDecimal invalidTotalAmount = new BigDecimal(0);
            final StringBuffer balanceStr = new StringBuffer();
            final StringBuffer companyInvalidStr = new StringBuffer();
            sql = " select t1.id, t1.name from c_sub_company t1 where t1.id in (  select t2.id from c_sub_company t2, c_branch_company t3 where t2.type = 1 and  t2.branch_company_id = t3.id and t3.district_id = " + distract.getId() + ") ";
            distract.setSql(sql);
            final List<FinanceReportBo> companyList = this.financeReportService.selectcDataBysql(distract);
            BigDecimal canUseredTotalAmount = new BigDecimal(0);
            final StringBuffer subCompanyIncomeStr = new StringBuffer();
            BigDecimal incomeTotalAllAmount = new BigDecimal(0);
            final StringBuffer subCompanyCostStr = new StringBuffer();
            BigDecimal costTotalAllAmount = new BigDecimal(0);
            for (int k = 0; k < companyList.size(); ++k) {
                final FinanceReportBo bean2 = companyList.get(k);
                final StringBuffer compInvalidDetailStr = new StringBuffer();
                BigDecimal canUseredAmount = new BigDecimal(0);
                BigDecimal gongdiAmount = new BigDecimal(0);
                BigDecimal invalidAmount = new BigDecimal(0);
                sql = " select t2.name, t1.amount  from c_his_invalid_capital t1, c_invalid_capital_type t2  where t1.invalid_capital_type_id = t2.id and  t1.date = '" + currentDate + "' and  t1.sub_company_id  = " + bean2.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> companyInvalidList = this.financeReportService.selectcDataBysql(distract);
                if (null != companyInvalidList && companyInvalidList.size() > 0 && companyInvalidList.get(0) != null) {
                    for (int l = 0; l < companyInvalidList.size(); ++l) {
                        final FinanceReportBo invalidBean = companyInvalidList.get(l);
                        compInvalidDetailStr.append(invalidBean.getName()).append(' ').append(MathUtil.format(invalidBean.getAmount())).append(" \u4e07");
                        invalidAmount = invalidAmount.add(invalidBean.getAmount());
                        if (l != companyInvalidList.size() - 1) {
                            compInvalidDetailStr.append("\u3001");
                        }
                        invalidTotalAmount = invalidTotalAmount.add(invalidBean.getAmount());
                    }
                    companyInvalidStr.append(bean2.getName()).append(' ').append(MathUtil.format(invalidAmount)).append(" \u4e07").append("\uff08").append(compInvalidDetailStr).append("\uff09");
                    invalidTotalAmount = invalidTotalAmount.add(invalidAmount);
                    if (k != companyList.size() - 1) {
                        companyInvalidStr.append("\u3001");
                    }
                }
                sql = " select sum(t1.amount) amount from c_gongdi_balance t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> gongdiList = this.financeReportService.selectcDataBysql(distract);
                if (null != gongdiList && gongdiList.size() > 0 && gongdiList.get(0) != null) {
                    gongdiAmount = gongdiList.get(0).getAmount();
                }
                else {
                    BigDecimal lastdayongdiAmount = new BigDecimal(0);
                    BigDecimal gongdiIncomeAmount = new BigDecimal(0);
                    BigDecimal gongdiCostAmount = new BigDecimal(0);
                    sql = " select sum(t1.amount) amount from c_gongdi_balance t1 where t1.date = '" + DateUtil.getPreDate(currentDate) + "' and t1.sub_company_id  = " + bean2.getId();
                    distract.setSql(sql);
                    List<FinanceReportBo> list = this.financeReportService.selectcDataBysql(distract);
                    if (null != list && list.size() > 0 && list.get(0) != null) {
                        lastdayongdiAmount = list.get(0).getAmount();
                    }
                    sql = " select sum(t1.amount) amount from c_income t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId() + " and t1.income_type_id = " + 2;
                    distract.setSql(sql);
                    list = this.financeReportService.selectcDataBysql(distract);
                    if (null != list && list.size() > 0 && list.get(0) != null) {
                        gongdiIncomeAmount = list.get(0).getAmount();
                    }
                    sql = " select sum(t1.amount) amount from c_cost t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId() + " and t1.cost_type_id = " + 2;
                    distract.setSql(sql);
                    list = this.financeReportService.selectcDataBysql(distract);
                    if (null != list && list.size() > 0 && list.get(0) != null) {
                        gongdiCostAmount = list.get(0).getAmount();
                    }
                    gongdiAmount = lastdayongdiAmount.add(gongdiIncomeAmount).subtract(gongdiCostAmount);
                }
                gongdiBalanceAmount = gongdiBalanceAmount.add(gongdiAmount);
                BigDecimal balanceAmount = new BigDecimal(0);
                sql = " select sum(t1.amount) amount from c_sub_company_his_balance t1 where t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> list2 = this.financeReportService.selectcDataBysql(distract);
                if (null != list2 && list2.size() > 0 && list2.get(0) != null) {
                    final FinanceReportBo balanceBean = list2.get(0);
                    balanceAmount = balanceBean.getAmount();
                    balanceTotalAmount = balanceTotalAmount.add(((null == balanceBean.getAmount()) ? new BigDecimal(0) : balanceBean.getAmount()).subtract(invalidAmount));
                    balanceTotalAllAmount = balanceTotalAllAmount.add((null == balanceBean.getAmount()) ? new BigDecimal(0) : balanceBean.getAmount());
                }
                canUseredAmount = balanceAmount.subtract(invalidAmount);
                if (canUseredAmount.compareTo(BigDecimal.ZERO) != 0) {
                    balanceStr.append(bean2.getName()).append(' ').append(MathUtil.format(canUseredAmount)).append(" \u4e07");
                    if (i != distractList.size() - 1) {
                        balanceStr.append("\u3001");
                    }
                }
                canUseredTotalAmount = canUseredTotalAmount.add(canUseredAmount);
                sql = " select t2.name, t1.change_amount amount,t1.descs from c_his_invalid_capital t1,c_invalid_capital_type t2 where  t1.invalid_capital_type_id = t2.id and t1.date = '" + currentDate + "' and t1.sub_company_id = " + bean2.getId();
                bo.setSql(sql);
                final List<FinanceReportBo> validList = this.financeReportService.selectcDataBysql(bo);
                final StringBuffer validToInvalidStr = new StringBuffer();
                BigDecimal validToInvalidTotalAmount = new BigDecimal(0);
                final StringBuffer invalidToValidStr = new StringBuffer();
                BigDecimal invalidToValidTotalAmount = new BigDecimal(0);
                if (null != validList && validList.size() > 0 && validList.get(0) != null) {
                    for (int m = 0; m < validList.size(); ++m) {
                        final FinanceReportBo validBean = validList.get(m);
                        if (validBean.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                            validToInvalidStr.append(StringUtils.isNotBlank(validBean.getDescs()) ? validBean.getDescs() : validBean.getName()).append(' ').append(MathUtil.format(validBean.getAmount().negate())).append(" \u4e07");
                            if (m != validList.size() - 1) {
                                validToInvalidStr.append("\u3001");
                            }
                            validToInvalidTotalAmount = validToInvalidTotalAmount.add(MathUtil.format(validBean.getAmount()));
                        }
                        else {
                            invalidToValidStr.append(StringUtils.isNotBlank(validBean.getDescs()) ? validBean.getDescs() : validBean.getName()).append(' ').append(MathUtil.format(validBean.getAmount().negate())).append(" \u4e07");
                            if (m != validList.size() - 1) {
                                invalidToValidStr.append("\u3001");
                            }
                            invalidToValidTotalAmount = invalidToValidTotalAmount.add(MathUtil.format(validBean.getAmount()));
                        }
                    }
                    if (validToInvalidTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                        compValidToInvalidStr.append(bean2.getName()).append(' ').append(MathUtil.format(validToInvalidTotalAmount.negate())).append(" \u4e07\uff08").append((CharSequence)(validToInvalidStr.toString().endsWith("\u3001") ? validToInvalidStr.substring(0, validToInvalidStr.length() - 1) : validToInvalidStr)).append(" \uff09");
                        if (k != list2.size() - 1) {
                            compValidToInvalidStr.append("\uff0c");
                        }
                        compValidToInvalidTotalAllAmount = compValidToInvalidTotalAllAmount.add(validToInvalidTotalAmount);
                        totalAmount1 = totalAmount1.add(validToInvalidTotalAmount);
                    }
                    if (invalidToValidTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                        compInvalidToValidStr.append(bean2.getName()).append(' ').append(MathUtil.format(invalidToValidTotalAmount.negate())).append(" \u4e07\uff08").append((CharSequence)(invalidToValidStr.toString().endsWith("\u3001") ? invalidToValidStr.substring(0, invalidToValidStr.length() - 1) : invalidToValidStr)).append(" \uff09");
                        if (k != list2.size() - 1) {
                            compInvalidToValidStr.append("\uff0c");
                        }
                        compInvalidToValidTotalAllAmount = compInvalidToValidTotalAllAmount.add(invalidToValidTotalAmount);
                        totalAmount2 = totalAmount2.add(invalidToValidTotalAmount);
                    }
                }
                BigDecimal incomeTotalAmount = new BigDecimal(0);
                final StringBuffer incomeStr = new StringBuffer();
                sql = " select t1.name, t1.amount from c_income t1 where t1.income_type_id not in (1,2)  and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> incomeList = this.financeReportService.selectcDataBysql(distract);
                if (null != incomeList && incomeList.size() > 0 && incomeList.get(0) != null) {
                    for (int k2 = 0; k2 < incomeList.size(); ++k2) {
                        final FinanceReportBo incomeBean = incomeList.get(k2);
                        incomeStr.append(incomeBean.getName()).append(' ').append(MathUtil.format(incomeBean.getAmount())).append(" \u4e07");
                        if (k2 != incomeList.size() - 1) {
                            incomeStr.append("\u3001");
                        }
                        incomeTotalAmount = incomeTotalAmount.add(MathUtil.format(incomeBean.getAmount()));
                    }
                    subCompanyIncomeStr.append(bean2.getName()).append(MathUtil.format(incomeTotalAmount)).append(" \u4e07\uff08").append(incomeStr).append(" \uff09");
                    if (k != companyList.size() - 1) {
                        subCompanyIncomeStr.append("\uff0c");
                    }
                    incomeTotalAllAmount = incomeTotalAllAmount.add(incomeTotalAmount);
                }
                BigDecimal costTotalAmount = new BigDecimal(0);
                final StringBuffer costStr = new StringBuffer();
                sql = " select t1.name, t1.amount from c_cost t1 where t1.cost_type_id not in (1,2)  and t1.date = '" + currentDate + "' and t1.sub_company_id  = " + bean2.getId();
                distract.setSql(sql);
                final List<FinanceReportBo> costList = this.financeReportService.selectcDataBysql(distract);
                if (null != costList && costList.size() > 0 && costList.get(0) != null) {
                    for (int k3 = 0; k3 < costList.size(); ++k3) {
                        final FinanceReportBo costBean = costList.get(k3);
                        costStr.append(costBean.getName()).append(' ').append(MathUtil.format(costBean.getAmount())).append(" \u4e07");
                        if (k3 != costList.size() - 1) {
                            costStr.append("\u3001");
                        }
                        costTotalAmount = costTotalAmount.add(MathUtil.format(costBean.getAmount()));
                    }
                    subCompanyCostStr.append(bean2.getName()).append(MathUtil.format(costTotalAmount)).append(" \u4e07\uff08").append(costStr).append(" \uff09");
                    if (k != list2.size() - 1) {
                        subCompanyCostStr.append("\uff0c");
                    }
                    costTotalAllAmount = costTotalAllAmount.add(costTotalAmount);
                }
            }
            invalidAllStr.append(companyInvalidStr).append((invalidTypeAmount.compareTo(BigDecimal.ZERO) == 0) ? "" : "\u3011\uff1b");
            final StringBuffer temp2 = new StringBuffer();
            if (canUseredTotalAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp2.append(MathUtil.format(canUseredTotalAmount)).append(' ').append("\u4e07\uff08\u5176\u4e2d\uff1a").append((CharSequence)(balanceStr.toString().endsWith("\u3001") ? balanceStr.substring(0, balanceStr.length() - 1) : balanceStr)).append("\uff09\uff1b<br/>");
            }
            else {
                temp2.append("0 \u4e07<br/>");
            }
            balanceAllStr.append(indexArray[i]).append(distract.getName()).append(' ').append(temp2);
            final StringBuffer temp3 = new StringBuffer();
            if (incomeTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp3.append(MathUtil.format(incomeTotalAllAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyIncomeStr.toString().endsWith("\uff0c") ? subCompanyIncomeStr.substring(0, subCompanyIncomeStr.length() - 1) : subCompanyIncomeStr)).append("\u3011");
            }
            else {
                temp3.append("0 \u4e07");
            }
            incomeAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp3);
            final StringBuffer temp4 = new StringBuffer();
            if (costTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp4.append(MathUtil.format(costTotalAllAmount)).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(subCompanyCostStr.toString().endsWith("\uff0c") ? subCompanyCostStr.substring(0, subCompanyCostStr.length() - 1) : subCompanyCostStr)).append("\u3011");
            }
            else {
                temp4.append("0 \u4e07");
            }
            costAllStr.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp4);
            temp5 = new StringBuffer();
            if (compValidToInvalidTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp5.append(MathUtil.format(compValidToInvalidTotalAllAmount.negate())).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(compValidToInvalidStr.toString().endsWith("\uff0c") ? compValidToInvalidStr.substring(0, compValidToInvalidStr.length() - 1) : compValidToInvalidStr)).append("\u3011");
            }
            else {
                temp5.append("0 \u4e07");
            }
            str1.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp5);
            final StringBuffer temp6 = new StringBuffer();
            if (compInvalidToValidTotalAllAmount.compareTo(BigDecimal.ZERO) != 0) {
                temp6.append(MathUtil.format(compInvalidToValidTotalAllAmount.negate())).append(" \u4e07\u3010\u5176\u4e2d\uff1a").append((CharSequence)(compInvalidToValidStr.toString().endsWith("\uff0c") ? compInvalidToValidStr.substring(0, compInvalidToValidStr.length() - 1) : compInvalidToValidStr)).append("\u3011");
            }
            else {
                temp6.append("0 \u4e07");
            }
            str2.append("<br/>").append(indexArray[i]).append(distract.getName()).append(' ').append(temp6);
        }
        unInvalidTotalAllAmount = balanceTotalAllAmount.subtract(invalidTotalAllAmount).subtract(gongdiBalanceAmount);
        allStr.append("\u6c5f\u82cf\u4e8b\u4e1a\u90e8\u5408\u8d44\u516c\u53f8\u8d44\u91d1\u4f59\u989d\u660e\u7ec6\u5982\u4e0b\uff0c\u5176\u4e2d\u4e0d\u53ef\u4f7f\u7528 ").append(MathUtil.format(invalidTotalAllAmount)).append(" \u4e07\uff1a").append(invalidAllStr).append("<br/><br/>\u5b9e\u9645\u53ef\u7528\u8d44\u91d1(\u4e0d\u542b\u5de5\u62b5\uff0c\u5f53\u65e5\u4f59\u989d - \u4e0d\u53ef\u7528 - \u5de5\u62b5\u4f59\u989d) ").append(MathUtil.format(unInvalidTotalAllAmount)).append(" \u4e07\uff0c<br/> \u4f59\u989d\u5206\u522b\u662f\uff1a<br/>").append(balanceAllStr).append("<br/><br/>\u4e0d\u53ef\u7528\u8d44\u91d1\u4e0e\u6628\u65e5\u5f62\u6210\u5dee\u5f02 ").append(MathUtil.format(diffAmount.negate())).append(" \u4e07\uff0c\u539f\u56e0\u5982\u4e0b\uff1a<br/>").append("1\u3001\u53ef\u7528\u8d44\u91d1\u8f6c\u4e3a\u4e0d\u53ef\u7528\u8d44\u91d1 ").append(MathUtil.format(totalAmount1.negate())).append(" \u4e07").append((totalAmount1.compareTo(BigDecimal.ZERO) == 0) ? "" : "\uff08\u5176\u4e2d\uff1a").append((CharSequence)(str1.toString().endsWith("\u3001") ? str1.substring(0, str1.length() - 1) : str1)).append((totalAmount1.compareTo(BigDecimal.ZERO) == 0) ? "" : "\uff09").append("<br/>").append("2\u3001\u4e0d\u53ef\u7528\u8d44\u91d1\u8f6c\u4e3a\u53ef\u7528\u8d44\u91d1 ").append(totalAmount2.negate()).append(" \u4e07").append((totalAmount2.compareTo(BigDecimal.ZERO) == 0) ? "" : "\uff08\u5176\u4e2d\uff1a").append((CharSequence)(str2.toString().endsWith("\u3001") ? str2.substring(0, str2.length() - 1) : str2)).append((totalAmount2.compareTo(BigDecimal.ZERO) == 0) ? "" : "\uff09").append("<br/><br/>").append("\u6536\u5165\u660e\u7ec6\uff1a").append(incomeAllStr).append("<br/><br/>").append("\u652f\u51fa\u660e\u7ec6\uff1a").append(costAllStr);
        bo.setHtml(allStr.toString());
        return bo;
    }
    
    @RequestMapping({ "selectBalanceProcess" })
    @ResponseBody
    public List<FinanceReportBo> selectcDimData(final FinanceReportBo bo) {
        bo.setDateStr(DateUtil.getCurrentDateString());
        return this.financeReportService.selectBalanceProcess(bo);
    }
    
    @RequestMapping({ "insertBalanceProcess" })
    @ResponseBody
    public Map<String, Object> insertBalanceProcess(final FinanceReportBo bo) {
        return this.financeReportService.insertBalanceProcess(bo);
    }
    
    @RequestMapping({ "insertGongdiBalance" })
    @ResponseBody
    public Map<String, Object> insertGongdiBalance(final FinanceReportBo bo) {
        return this.financeReportService.insertGongdiBalance(bo);
    }*/
}
