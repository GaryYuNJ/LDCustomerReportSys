package com.ld.common.dao;

import java.util.*;
import com.ld.saleseport.bo.*;

public interface SalesReportMapper
{
    List<SalesReportBo> findAllTableEarnestMoneyRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableMoneyPayRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableReceivableMoneyRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableIncomeBillRpt(Map<String, Object> p0);
    
    List<SalesReportBo> findAllTableInvoiceBillRpt(Map<String, Object> p0);
}
