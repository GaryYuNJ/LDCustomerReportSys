package com.ld.report.service;

import com.ld.report.bo.*;
import java.util.*;
import com.ld.core.mybatis.page.*;

public interface FinanceReportService
{
    List<FinanceReportBo> selectSubCompTotalBalanceByCond(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompBalanceBasicData(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompBalance(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompBalanceTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompBalanceTotalAllList(Map<String, Object> p0);
    
    List<FinanceReportBo> selectIncome(FinanceReportBo p0);
    
    List<FinanceReportBo> selectIncomeTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectCost(FinanceReportBo p0);
    
    List<FinanceReportBo> selectCostTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectInvalidCapital(FinanceReportBo p0);
    
    List<FinanceReportBo> selectInvalidCapitalTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompany(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompanyForTemplate(FinanceReportBo p0);
    
    List<FinanceReportBo> selectcBranchCompany(FinanceReportBo p0);
    
    List<FinanceReportBo> selectcDistrict();
    
    List<FinanceReportBo> selectIncomeType();
    
    List<FinanceReportBo> selectCostType();
    
    List<FinanceReportBo> selectInvalidCapitalType();
    
    List<FinanceReportBo> selectBank(FinanceReportBo p0);
    
    List<FinanceReportBo> selectBranchCompBalanceTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectBranchCompIncomeTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectBranchCompCostTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectBranchCompInvalidCapitalTotal(FinanceReportBo p0);
    
    int deleteSubCompBalance(FinanceReportBo p0);
    
    int deleteSubCompHisBalance(FinanceReportBo p0);
    
    int deleteInvalidCapital(FinanceReportBo p0);
    
    int deleteHisInvalidCapital(FinanceReportBo p0);
    
    Map<String, Object> insertSubCompBalance(String p0);
    
    void insertSubCompBalanceForJob(FinanceReportBo p0);
    
    Map<String, Object> insertIncome(String p0);
    
    Map<String, Object> insertCost(String p0);
    
    Map<String, Object> insertInvalidCapital(String p0);
    
    void insertInvalidCapitalForJob(FinanceReportBo p0);
    
    List<FinanceReportBo> selectMaxSubCompanyBalance(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSmsRecord(FinanceReportBo p0);
    
    Map<String, Object> insertSmsRecord(String p0);
    
    Map<String, Object> updateSmsRecord(String p0);
    
    Map<String, Object> sendSmsRecord(String p0);
    
    List<FinanceReportBo> selectMaxSubCompanyIncomeLength(FinanceReportBo p0);
    
    List<FinanceReportBo> selectMaxBranchCompanyIncomeLength(FinanceReportBo p0);
    
    Pagination<FinanceReportBo> findPage(Map<String, Object> p0, Integer p1, Integer p2);
    
    List<FinanceReportBo> selectSubCompanyIncomeTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompanyCostTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompanyInvalidCapitalTotal(FinanceReportBo p0);
    
    List<FinanceReportBo> selectInvalidCapitalForTemplate(FinanceReportBo p0);
    
    List<FinanceReportBo> selectcDataBysql(FinanceReportBo p0);
    
    List<FinanceReportBo> selectSubCompGongdiBalance(FinanceReportBo p0);
    
    List<FinanceReportBo> selectBalanceProcess(FinanceReportBo p0);
    
    Map<String, Object> insertBalanceProcess(FinanceReportBo p0);
    
    Map<String, Object> updateBalanceProcess(FinanceReportBo p0);
    
    List<FinanceReportBo> selectGongdiBalance(FinanceReportBo p0);
    
    Map<String, Object> insertGongdiBalance(FinanceReportBo p0);
}
