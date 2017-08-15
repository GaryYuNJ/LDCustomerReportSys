package com.ld.saleseport.service.impl;

import com.ld.core.mybatis.*;
import com.ld.common.dao.*;
import com.ld.saleseport.service.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.ld.saleseport.bo.*;
import com.ld.core.mybatis.page.*;

@Service
public class SalesReportMgrServiceImpl extends BaseMybatisDao<SalesReportMapper> implements SalesReportMgrService
{
    @Autowired
    SalesReportMapper salesReportMapper;
    
    @Override
    public List<SalesReportBo> findAllTableEarnestMoneyRpt(final Map<String, Object> resultMap) {
        return this.salesReportMapper.findAllTableEarnestMoneyRpt(resultMap);
    }
    
    @Override
    public List<SalesReportBo> findAllTableMoneyPayRpt(final Map<String, Object> resultMap) {
        return this.salesReportMapper.findAllTableMoneyPayRpt(resultMap);
    }
    
    @Override
    public List<SalesReportBo> findAllTableReceivableMoneyRpt(final Map<String, Object> resultMap) {
        return this.salesReportMapper.findAllTableReceivableMoneyRpt(resultMap);
    }
    
    @Override
    public List<SalesReportBo> findAllTableIncomeBillRpt(final Map<String, Object> resultMap) {
        return this.salesReportMapper.findAllTableIncomeBillRpt(resultMap);
    }
    
    @Override
    public List<SalesReportBo> findAllTableInvoiceBillRpt(final Map<String, Object> resultMap) {
        return this.salesReportMapper.findAllTableInvoiceBillRpt(resultMap);
    }
    
    @Override
    public Pagination<SalesReportBo> findPage(final Map<String, Object> resultMap, final Integer pageNo, final Integer pageSize) {
        return (Pagination<SalesReportBo>)super.findPageBySql(resultMap, pageNo, pageSize);
    }
}
