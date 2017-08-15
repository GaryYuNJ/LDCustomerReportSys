package com.ld.report.bo;

import com.ld.common.model.*;
import java.io.*;
import java.math.*;
import java.util.*;

public class FinanceReportBo extends FinanceReport implements Serializable
{
    private BigDecimal todayAmount;
    private BigDecimal lastdayAmount;
    private BigDecimal incomeAmount;
    private BigDecimal costAmount;
    private BigDecimal invalidAmount;
    private BigDecimal availableAmount;
    private String dateStr;
    private String tableName;
    private String fieldName;
    private int count;
    private String queryType;
    private String branchUserId;
    private String orderByType;
    private String ope;
    private String sql;
    private Long district_id;
    private Long branch_company_id;
    private Long income_type_id;
    private Long cost_type_id;
    private BigDecimal gongdiBalance;
    private String html;
    private List<FinanceReportBo> subCompLastDayBalanceList;
    private List<FinanceReportBo> subCompLastDayGongdiBalanceList;
    private List<FinanceReportBo> subCompBalanceList;
    private List<FinanceReportBo> subCompGongdiBalanceList;
    private List<FinanceReportBo> subCompIncomeList;
    private List<FinanceReportBo> subCompCostList;
    private List<FinanceReportBo> subCompInvalidCapitalList;
    private List<FinanceReportBo> cellList;
    private List<FinanceReportBo> workRoomIncomeList;
    private List<FinanceReportBo> notWorkRoomIncomeList;
    private List<FinanceReportBo> workRoomCostList;
    private List<FinanceReportBo> notWorkRoomCostList;
    private int notWorkRoomIncomeMaxLength;
    private int workRoomIncomeListMaxLength;
    private int notWorkRoomCostListMaxLength;
    private int workRoomCostListMaxLeng;
    private int invalidCapitalListMaxLeng;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;
    private String content6;
    private String content7;
    private String content8;
    private String content9;
    private String random_code;
    
    public BigDecimal getTodayAmount() {
        return this.todayAmount;
    }
    
    public void setTodayAmount(final BigDecimal todayAmount) {
        this.todayAmount = todayAmount;
    }
    
    public BigDecimal getLastdayAmount() {
        return this.lastdayAmount;
    }
    
    public void setLastdayAmount(final BigDecimal lastdayAmount) {
        this.lastdayAmount = lastdayAmount;
    }
    
    public BigDecimal getIncomeAmount() {
        return this.incomeAmount;
    }
    
    public void setIncomeAmount(final BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
    
    public BigDecimal getCostAmount() {
        return this.costAmount;
    }
    
    public void setCostAmount(final BigDecimal costAmount) {
        this.costAmount = costAmount;
    }
    
    public BigDecimal getInvalidAmount() {
        return this.invalidAmount;
    }
    
    public void setInvalidAmount(final BigDecimal invalidAmount) {
        this.invalidAmount = invalidAmount;
    }
    
    public String getDateStr() {
        return this.dateStr;
    }
    
    public void setDateStr(final String dateStr) {
        this.dateStr = dateStr;
    }
    
    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }
    
    public String getFieldName() {
        return this.fieldName;
    }
    
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }
    
    public BigDecimal getAvailableAmount() {
        return this.availableAmount;
    }
    
    public void setAvailableAmount(final BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }
    
    public List<FinanceReportBo> getSubCompLastDayBalanceList() {
        return this.subCompLastDayBalanceList;
    }
    
    public void setSubCompLastDayBalanceList(final List<FinanceReportBo> subCompLastDayBalanceList) {
        this.subCompLastDayBalanceList = subCompLastDayBalanceList;
    }
    
    public List<FinanceReportBo> getSubCompBalanceList() {
        return this.subCompBalanceList;
    }
    
    public void setSubCompBalanceList(final List<FinanceReportBo> subCompBalanceList) {
        this.subCompBalanceList = subCompBalanceList;
    }
    
    public List<FinanceReportBo> getSubCompIncomeList() {
        return this.subCompIncomeList;
    }
    
    public void setSubCompIncomeList(final List<FinanceReportBo> subCompIncomeList) {
        this.subCompIncomeList = subCompIncomeList;
    }
    
    public List<FinanceReportBo> getSubCompCostList() {
        return this.subCompCostList;
    }
    
    public void setSubCompCostList(final List<FinanceReportBo> subCompCostList) {
        this.subCompCostList = subCompCostList;
    }
    
    public List<FinanceReportBo> getSubCompInvalidCapitalList() {
        return this.subCompInvalidCapitalList;
    }
    
    public void setSubCompInvalidCapitalList(final List<FinanceReportBo> subCompInvalidCapitalList) {
        this.subCompInvalidCapitalList = subCompInvalidCapitalList;
    }
    
    public List<FinanceReportBo> getCellList() {
        return this.cellList;
    }
    
    public void setCellList(final List<FinanceReportBo> cellList) {
        this.cellList = cellList;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(final int count) {
        this.count = count;
    }
    
    public String getQueryType() {
        return this.queryType;
    }
    
    public void setQueryType(final String queryType) {
        this.queryType = queryType;
    }
    
    public String getBranchUserId() {
        return this.branchUserId;
    }
    
    public void setBranchUserId(final String branchUserId) {
        this.branchUserId = branchUserId;
    }
    
    public String getOrderByType() {
        return this.orderByType;
    }
    
    public void setOrderByType(final String orderByType) {
        this.orderByType = orderByType;
    }
    
    public List<FinanceReportBo> getWorkRoomIncomeList() {
        return this.workRoomIncomeList;
    }
    
    public void setWorkRoomIncomeList(final List<FinanceReportBo> workRoomIncomeList) {
        this.workRoomIncomeList = workRoomIncomeList;
    }
    
    public List<FinanceReportBo> getNotWorkRoomIncomeList() {
        return this.notWorkRoomIncomeList;
    }
    
    public void setNotWorkRoomIncomeList(final List<FinanceReportBo> notWorkRoomIncomeList) {
        this.notWorkRoomIncomeList = notWorkRoomIncomeList;
    }
    
    public List<FinanceReportBo> getWorkRoomCostList() {
        return this.workRoomCostList;
    }
    
    public void setWorkRoomCostList(final List<FinanceReportBo> workRoomCostList) {
        this.workRoomCostList = workRoomCostList;
    }
    
    public List<FinanceReportBo> getNotWorkRoomCostList() {
        return this.notWorkRoomCostList;
    }
    
    public void setNotWorkRoomCostList(final List<FinanceReportBo> notWorkRoomCostList) {
        this.notWorkRoomCostList = notWorkRoomCostList;
    }
    
    public int getNotWorkRoomIncomeMaxLength() {
        return this.notWorkRoomIncomeMaxLength;
    }
    
    public void setNotWorkRoomIncomeMaxLength(final int notWorkRoomIncomeMaxLength) {
        this.notWorkRoomIncomeMaxLength = notWorkRoomIncomeMaxLength;
    }
    
    public int getWorkRoomIncomeListMaxLength() {
        return this.workRoomIncomeListMaxLength;
    }
    
    public void setWorkRoomIncomeListMaxLength(final int workRoomIncomeListMaxLength) {
        this.workRoomIncomeListMaxLength = workRoomIncomeListMaxLength;
    }
    
    public int getNotWorkRoomCostListMaxLength() {
        return this.notWorkRoomCostListMaxLength;
    }
    
    public void setNotWorkRoomCostListMaxLength(final int notWorkRoomCostListMaxLength) {
        this.notWorkRoomCostListMaxLength = notWorkRoomCostListMaxLength;
    }
    
    public int getWorkRoomCostListMaxLeng() {
        return this.workRoomCostListMaxLeng;
    }
    
    public void setWorkRoomCostListMaxLeng(final int workRoomCostListMaxLeng) {
        this.workRoomCostListMaxLeng = workRoomCostListMaxLeng;
    }
    
    public int getInvalidCapitalListMaxLeng() {
        return this.invalidCapitalListMaxLeng;
    }
    
    public void setInvalidCapitalListMaxLeng(final int invalidCapitalListMaxLeng) {
        this.invalidCapitalListMaxLeng = invalidCapitalListMaxLeng;
    }
    
    public String getOpe() {
        return this.ope;
    }
    
    public void setOpe(final String ope) {
        this.ope = ope;
    }
    
    public Long getBranch_company_id() {
        return this.branch_company_id;
    }
    
    public void setBranch_company_id(final Long branch_company_id) {
        this.branch_company_id = branch_company_id;
    }
    
    public Long getDistrict_id() {
        return this.district_id;
    }
    
    public void setDistrict_id(final Long district_id) {
        this.district_id = district_id;
    }
    
    public String getSql() {
        return this.sql;
    }
    
    public void setSql(final String sql) {
        this.sql = sql;
    }
    
    public Long getIncome_type_id() {
        return this.income_type_id;
    }
    
    public void setIncome_type_id(final Long income_type_id) {
        this.income_type_id = income_type_id;
    }
    
    public Long getCost_type_id() {
        return this.cost_type_id;
    }
    
    public void setCost_type_id(final Long cost_type_id) {
        this.cost_type_id = cost_type_id;
    }
    
    public String getHtml() {
        return this.html;
    }
    
    public void setHtml(final String html) {
        this.html = html;
    }
    
    public String getContent1() {
        return this.content1;
    }
    
    public void setContent1(final String content1) {
        this.content1 = content1;
    }
    
    public String getContent2() {
        return this.content2;
    }
    
    public void setContent2(final String content2) {
        this.content2 = content2;
    }
    
    public String getContent3() {
        return this.content3;
    }
    
    public void setContent3(final String content3) {
        this.content3 = content3;
    }
    
    public String getContent4() {
        return this.content4;
    }
    
    public void setContent4(final String content4) {
        this.content4 = content4;
    }
    
    public String getContent5() {
        return this.content5;
    }
    
    public void setContent5(final String content5) {
        this.content5 = content5;
    }
    
    public String getContent6() {
        return this.content6;
    }
    
    public void setContent6(final String content6) {
        this.content6 = content6;
    }
    
    public String getContent7() {
        return this.content7;
    }
    
    public void setContent7(final String content7) {
        this.content7 = content7;
    }
    
    public String getContent8() {
        return this.content8;
    }
    
    public void setContent8(final String content8) {
        this.content8 = content8;
    }
    
    public String getContent9() {
        return this.content9;
    }
    
    public void setContent9(final String content9) {
        this.content9 = content9;
    }
    
    public String getRandom_code() {
        return this.random_code;
    }
    
    public void setRandom_code(final String random_code) {
        this.random_code = random_code;
    }
    
    public List<FinanceReportBo> getSubCompLastDayGongdiBalanceList() {
        return this.subCompLastDayGongdiBalanceList;
    }
    
    public void setSubCompLastDayGongdiBalanceList(final List<FinanceReportBo> subCompLastDayGongdiBalanceList) {
        this.subCompLastDayGongdiBalanceList = subCompLastDayGongdiBalanceList;
    }
    
    public List<FinanceReportBo> getSubCompGongdiBalanceList() {
        return this.subCompGongdiBalanceList;
    }
    
    public void setSubCompGongdiBalanceList(final List<FinanceReportBo> subCompGongdiBalanceList) {
        this.subCompGongdiBalanceList = subCompGongdiBalanceList;
    }
    
    public BigDecimal getGongdiBalance() {
        return this.gongdiBalance;
    }
    
    public void setGongdiBalance(final BigDecimal gongdiBalance) {
        this.gongdiBalance = gongdiBalance;
    }
}
