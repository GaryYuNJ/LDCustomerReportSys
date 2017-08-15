package com.ld.report.service.impl;

import com.ld.core.mybatis.*;
import com.ld.common.dao.*;
import com.ld.report.service.*;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import com.ld.report.bo.*;

import java.util.*;

import org.apache.commons.lang.*;

import java.net.*;

import org.springframework.transaction.interceptor.*;

import com.alibaba.fastjson.*;

import java.math.*;

import com.ld.core.mybatis.page.*;
import com.ld.common.utils.*;
import com.ld.common.utils.StringUtils;

@Service
public class FinanceReportServiceImpl extends BaseMybatisDao<FinanceReportMapper> implements FinanceReportService
{
    @Autowired
    FinanceReportMapper financeReportMapper;
    
    @Override
    public List<FinanceReportBo> selectSubCompTotalBalanceByCond(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompTotalBalanceByCond(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompGongdiBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompGongdiBalance(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompBalance(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompBalanceBasicData(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompBalanceBasicData(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompBalanceTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompBalanceTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompBalanceTotalAllList(final Map<String, Object> map) {
        return this.financeReportMapper.selectSubCompBalanceTotalAllList(map);
    }
    
    @Override
    public List<FinanceReportBo> selectIncome(final FinanceReportBo bo) {
        return this.financeReportMapper.selectIncome(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectIncomeTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectIncomeTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectCost(final FinanceReportBo bo) {
        return this.financeReportMapper.selectCost(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompanyIncomeTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompanyIncomeTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompanyCostTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompanyCostTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompanyInvalidCapitalTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompanyInvalidCapitalTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectInvalidCapitalForTemplate(final FinanceReportBo bo) {
        return this.financeReportMapper.selectInvalidCapitalForTemplate(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectcDataBysql(final FinanceReportBo bo) {
        return this.financeReportMapper.selectcDataBysql(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectCostTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectCostTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectInvalidCapital(final FinanceReportBo bo) {
        return this.financeReportMapper.selectInvalidCapital(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectInvalidCapitalTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectInvalidCapitalTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompany(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompany(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSubCompanyForTemplate(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSubCompanyForTemplate(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectcBranchCompany(final FinanceReportBo bo) {
        return this.financeReportMapper.selectcBranchCompany(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectBranchCompBalanceTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBranchCompBalanceTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectBranchCompIncomeTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBranchCompIncomeTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectBranchCompCostTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBranchCompCostTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectBranchCompInvalidCapitalTotal(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBranchCompInvalidCapitalTotal(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectcDistrict() {
        return this.financeReportMapper.selectcDistrict();
    }
    
    @Override
    public List<FinanceReportBo> selectInvalidCapitalType() {
        return this.financeReportMapper.selectInvalidCapitalType();
    }
    
    @Override
    public List<FinanceReportBo> selectBank(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBank(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectIncomeType() {
        return this.financeReportMapper.selectIncomeType();
    }
    
    @Override
    public List<FinanceReportBo> selectCostType() {
        return this.financeReportMapper.selectCostType();
    }
    
    @Override
    public List<FinanceReportBo> selectMaxSubCompanyIncomeLength(final FinanceReportBo bo) {
        return this.financeReportMapper.selectMaxSubCompanyIncomeLength(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectMaxBranchCompanyIncomeLength(final FinanceReportBo bo) {
        return this.financeReportMapper.selectMaxBranchCompanyIncomeLength(bo);
    }
    
    @Override
    public int deleteSubCompBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.deleteSubCompBalance(bo);
    }
    
    @Override
    public int deleteSubCompHisBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.deleteSubCompHisBalance(bo);
    }
    
    @Override
    public int deleteInvalidCapital(final FinanceReportBo bo) {
        return this.financeReportMapper.deleteInvalidCapital(bo);
    }
    
    @Override
    public int deleteHisInvalidCapital(final FinanceReportBo bo) {
        return this.financeReportMapper.deleteHisInvalidCapital(bo);
    }
    
    @Override
    public Map<String, Object> insertSubCompBalance(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                final FinanceReportBo bo = new FinanceReportBo();
                bo.setSubCompanyId(paramObject.getLong("companyId"));
                bo.setDateStr(DateUtil.getCurrentDateString("yyyy-MM-dd"));
                this.financeReportMapper.deleteSubCompBalance(bo);
                this.financeReportMapper.deleteSubCompHisBalance(bo);
                final JSONArray jsonArray = paramObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.size(); ++i) {
                    final JSONObject object = jsonArray.getJSONObject(i);
                    final FinanceReportBo financeReportBo = new FinanceReportBo();
                    financeReportBo.setSubCompanyId(paramObject.getLong("companyId"));
                    financeReportBo.setName(object.getString("name"));
                    financeReportBo.setDescs(object.getString("descs"));
                    financeReportBo.setFilepath(object.getString("filepath"));
                    financeReportBo.setAmount(object.getBigDecimal("amount"));
                    financeReportBo.setDate(DateUtil.getCurrentDateTime());
                    final List<FinanceReportBo> bankList = this.financeReportMapper.selectBank(financeReportBo);
                    if (null != bankList && bankList.size() > 0) {
                        financeReportBo.setBankId(bankList.get(0).getId());
                    }
                    else {
                        this.financeReportMapper.insertBank(financeReportBo);
                        financeReportBo.setBankId(financeReportBo.getBankId());
                    }
                    this.financeReportMapper.insertSubCompBalance(financeReportBo);
                    financeReportBo.setId(null);
                    this.financeReportMapper.insertSubCompHisBalance(financeReportBo);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public void insertSubCompBalanceForJob(final FinanceReportBo bo) {
        try {
            bo.setId(null);
            this.financeReportMapper.insertSubCompHisBalance(bo);
        }
        catch (Exception e) {
            LoggerUtils.fmtError(this.getClass(), e, "\u540c\u6b65\u6628\u65e5\u94f6\u884c\u4f59\u989d\u51fa\u9519\u3002[%s]", "");
        }
    }
    
    @Override
    public void insertInvalidCapitalForJob(final FinanceReportBo bo) {
        try {
            bo.setId(null);
            this.financeReportMapper.insertHisInvalidCapital(bo);
        }
        catch (Exception e) {
            LoggerUtils.fmtError(this.getClass(), e, "\u540c\u6b65\u6628\u65e5\u4e0d\u53ef\u7528\u8d44\u91d1\u51fa\u9519\u3002[%s]", "");
        }
    }
    
    @Override
    public Map<String, Object> insertIncome(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                final FinanceReportBo bo = new FinanceReportBo();
                bo.setSubCompanyId(paramObject.getLong("companyId"));
                bo.setDateStr(DateUtil.getCurrentDateString("yyyy-MM-dd"));
                this.financeReportMapper.deleteIncome(bo);
                final JSONArray jsonArray = paramObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.size(); ++i) {
                    final JSONObject object = jsonArray.getJSONObject(i);
                    final FinanceReportBo financeReportBo = new FinanceReportBo();
                    financeReportBo.setSubCompanyId(paramObject.getLong("companyId"));
                    financeReportBo.setIncomeTypeId(object.getLong("incomeTypeId"));
                    financeReportBo.setName(object.getString("name"));
                    financeReportBo.setDescs(object.getString("descs"));
                    financeReportBo.setAmount(object.getBigDecimal("amount"));
                    financeReportBo.setDate(DateUtil.getCurrentDateTime());
                    this.financeReportMapper.insertIncome(financeReportBo);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public Map<String, Object> insertCost(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                final FinanceReportBo bo = new FinanceReportBo();
                bo.setSubCompanyId(paramObject.getLong("companyId"));
                bo.setDateStr(DateUtil.getCurrentDateString("yyyy-MM-dd"));
                this.financeReportMapper.deleteCost(bo);
                final JSONArray jsonArray = paramObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.size(); ++i) {
                    final JSONObject object = jsonArray.getJSONObject(i);
                    final FinanceReportBo financeReportBo = new FinanceReportBo();
                    financeReportBo.setSubCompanyId(paramObject.getLong("companyId"));
                    financeReportBo.setCostTypeId(object.getLong("costTypeId"));
                    financeReportBo.setName(object.getString("name"));
                    financeReportBo.setDescs(object.getString("descs"));
                    financeReportBo.setAmount(object.getBigDecimal("amount"));
                    financeReportBo.setDate(DateUtil.getCurrentDateTime());
                    this.financeReportMapper.insertCost(financeReportBo);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public Map<String, Object> insertInvalidCapital(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                final FinanceReportBo bo = new FinanceReportBo();
                bo.setSubCompanyId(paramObject.getLong("companyId"));
                bo.setDateStr(DateUtil.getCurrentDateString("yyyy-MM-dd"));
                this.financeReportMapper.deleteInvalidCapital(bo);
                this.financeReportMapper.deleteHisInvalidCapital(bo);
                final JSONArray jsonArray = paramObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.size(); ++i) {
                    final JSONObject object = jsonArray.getJSONObject(i);
                    final FinanceReportBo financeReportBo = new FinanceReportBo();
                    if (StringUtils.isBlank(object.getString("amount"))) {
                        financeReportBo.setAmount(new BigDecimal(0));
                    }
                    else {
                        financeReportBo.setAmount(object.getBigDecimal("amount"));
                    }
                    if (StringUtils.isBlank(object.getString("changeAmount"))) {
                        financeReportBo.setChangeAmount(new BigDecimal(0));
                    }
                    else {
                        financeReportBo.setChangeAmount(object.getBigDecimal("changeAmount"));
                    }
                    financeReportBo.setSubCompanyId(paramObject.getLong("companyId"));
                    financeReportBo.setInvalidCapitalTypeId(object.getLong("invalidCapitalTypeId"));
                    financeReportBo.setDescs(object.getString("descs"));
                    financeReportBo.setDate(DateUtil.getCurrentDateTime());
                    this.financeReportMapper.insertInvalidCapital(financeReportBo);
                    financeReportBo.setId(null);
                    this.financeReportMapper.insertHisInvalidCapital(financeReportBo);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public List<FinanceReportBo> selectMaxSubCompanyBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.selectMaxSubCompanyBalance(bo);
    }
    
    @Override
    public List<FinanceReportBo> selectSmsRecord(final FinanceReportBo bo) {
        return this.financeReportMapper.selectSmsRecord(bo);
    }
    
    @Override
    public Map<String, Object> insertSmsRecord(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            final FinanceReportBo financeReportBo = new FinanceReportBo();
            final String code = com.ld.common.utils.StringUtils.getUUID();
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                financeReportBo.setContent(paramObject.getString("content"));
                financeReportBo.setFieldName(paramObject.getString("fieldName"));
                financeReportBo.setDate(DateUtil.getCurrentDateTime());
                financeReportBo.setDateStr(DateUtil.getCurrentDateString());
                final List<FinanceReportBo> list = this.financeReportMapper.selectSmsRecord(financeReportBo);
                if (null != list && list.size() > 0) {
                    this.financeReportMapper.updateSmsRecord(financeReportBo);
                    resultMap.put("code", list.get(0).getRandom_code());
                }
                else {
                    financeReportBo.setRandomCode(code);
                    this.financeReportMapper.deleteSmsRecord(financeReportBo);
                    this.financeReportMapper.insertSmsRecord(financeReportBo);
                    resultMap.put("code", code);
                }
            }
            resultMap.put("id", financeReportBo.getId());
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public Map<String, Object> updateSmsRecord(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                final FinanceReportBo financeReportBo = new FinanceReportBo();
                financeReportBo.setId(paramObject.getLong("id"));
                financeReportBo.setContent(paramObject.getString("content"));
                this.financeReportMapper.updateSmsRecord(financeReportBo);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public Pagination<FinanceReportBo> findPage(final Map<String, Object> resultMap, final Integer pageNo, final Integer pageSize) {
        return (Pagination<FinanceReportBo>)super.findPage(resultMap, pageNo, pageSize);
    }
    
    @Override
    public Map<String, Object> sendSmsRecord(String param) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotBlank(param)) {
                param = URLDecoder.decode(param, "UTF-8").replace("params=", "");
                System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + param + "]*********************");
                final JSONObject paramObject = JSONObject.parseObject(param);
                MessageToolUtil.sendMessage(paramObject.getString("content"), paramObject.getString("receiver"));
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public List<FinanceReportBo> selectBalanceProcess(final FinanceReportBo bo) {
        return this.financeReportMapper.selectBalanceProcess(bo);
    }
    
    @Override
    public Map<String, Object> insertBalanceProcess(final FinanceReportBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            final Long status = bo.getStatus();
            bo.setStatus(null);
            bo.setDateStr(DateUtil.getCurrentDateString());
            bo.setDate(DateUtil.getCurrentDateTime());
            final List<FinanceReportBo> list = this.financeReportMapper.selectBalanceProcess(bo);
            bo.setStatus(status);
            if (null != list && list.size() > 0) {
                this.financeReportMapper.updateBalanceProcess(bo);
            }
            else {
                this.financeReportMapper.insertBalanceProcess(bo);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public Map<String, Object> updateBalanceProcess(final FinanceReportBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
    
    @Override
    public List<FinanceReportBo> selectGongdiBalance(final FinanceReportBo bo) {
        return this.financeReportMapper.selectGongdiBalance(bo);
    }
    
    @Override
    public Map<String, Object> insertGongdiBalance(final FinanceReportBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            bo.setDateStr(DateUtil.getCurrentDateString());
            bo.setDate(DateUtil.getCurrentDateTime());
            final List<FinanceReportBo> list = this.financeReportMapper.selectGongdiBalance(bo);
            if (null != list && list.size() > 0) {
                this.financeReportMapper.updateGongdiBalance(bo);
            }
            else {
                this.financeReportMapper.insertGongdiBalance(bo);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultMap;
    }
}
