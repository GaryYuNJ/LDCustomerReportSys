package com.ld.saleseport.service;

import java.util.*;
import com.ld.saleseport.bo.*;
import com.ld.core.mybatis.page.*;

public interface SalesReportMgrService
{
    List<SalesReportBo> findAllTableEarnestMoneyRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableMoneyPayRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableReceivableMoneyRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableIncomeBillRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableInvoiceBillRpt(Map<String, Object> p0);
    
    Pagination<SalesReportBo> findPage(Map<String, Object> p0, Integer p1, Integer p2);
}
