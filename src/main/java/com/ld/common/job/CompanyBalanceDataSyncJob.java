package com.ld.common.job;

import com.ld.report.service.*;
import org.springframework.beans.factory.annotation.*;
import com.ld.report.bo.*;
import java.math.*;
import java.util.*;
import com.ld.common.utils.*;

public class CompanyBalanceDataSyncJob
{
    @Autowired
    FinanceReportService financeReportService;
    
    public void balanceDataSync() {
        System.out.println("execute balanceDataSync job date start: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
        final String currentDate = DateUtil.getCurrentDateString();
        final String preDate = DateUtil.getPreDate(currentDate);
        final FinanceReportBo bo = new FinanceReportBo();
        bo.setDateStr(preDate);
        final FinanceReportBo deleteBo = new FinanceReportBo();
        deleteBo.setDateStr(currentDate);
        List<FinanceReportBo> list = this.financeReportService.selectSubCompBalanceBasicData(bo);
        if (null != list && list.size() > 0) {
            this.financeReportService.deleteSubCompHisBalance(deleteBo);
            for (final FinanceReportBo bean : list) {
                bean.setDate(DateUtil.getCurrentDateTime());
                bean.setDateStr(currentDate);
                if (StringUtils.isNotBlank(bean.getFilepath())) {
                    bean.setFilepath(bean.getFilepath().replace("upload/" + preDate, "upload/" + currentDate));
                }
                if (StringUtils.isBlank(new Object[] { bean.getAmount() })) {
                    bean.setAmount(new BigDecimal(0));
                }
                this.financeReportService.insertSubCompBalanceForJob(bean);
            }
        }
        list = this.financeReportService.selectInvalidCapital(bo);
        if (null != list && list.size() > 0) {
            this.financeReportService.deleteHisInvalidCapital(deleteBo);
            for (final FinanceReportBo bean : list) {
                bean.setDate(DateUtil.getCurrentDateTime());
                bean.setDateStr(currentDate);
                bean.setChangeAmount(new BigDecimal(0));
                if (StringUtils.isBlank(new Object[] { bean.getAmount() })) {
                    bean.setAmount(new BigDecimal(0));
                }
                this.financeReportService.insertInvalidCapitalForJob(bean);
            }
        }
        this.copyBalanceDataFile();
        System.out.println("execute balanceDataSync job date end: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
    
    public void copyBalanceDataFile() {
        System.out.println("copy balanceDataFile start: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
        final String pathDir = FileUtil.getProperties("config.properties", "FILE_PATH");
        final String currentDate = DateUtil.getCurrentDateString();
        final String preDate = DateUtil.getPreDate(currentDate);
        FileUtil.copy(pathDir + preDate, pathDir + currentDate);
        System.out.println("copy balanceDataFile end: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
}
