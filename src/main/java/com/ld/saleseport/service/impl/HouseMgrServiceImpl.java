package com.ld.saleseport.service.impl;

import com.ld.core.mybatis.*;
import com.ld.common.dao.*;
import com.ld.saleseport.service.*;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import com.ld.saleseport.bo.*;
import com.ld.core.mybatis.page.*;

import org.springframework.transaction.interceptor.*;

import com.ld.common.utils.*;
import com.ld.common.utils.DateUtil;

import org.springframework.web.multipart.*;

import com.ld.core.shiro.token.manager.*;

import org.apache.commons.collections4.*;

import java.math.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.util.*;

@Service
public class HouseMgrServiceImpl extends BaseMybatisDao<HouseMapper> implements HouseMgrService
{
    private static Map<String, Long> houseStatusMap;
    private static Map<String, Long> businessBigTypeMap;
    private static Map<String, Long> businessSubTypeMap;
    @Autowired
    HouseMapper houseMapper;
    
    @Override
    public List<HouseBo> findHouseAll(final Map<String, Object> resultMap) {
        return this.houseMapper.findHouseAll(resultMap);
    }
    
    @Override
    public Pagination<HouseBo> findPage(final Map<String, Object> resultMap, final Integer pageNo, final Integer pageSize) {
        return (Pagination<HouseBo>)super.findPage(resultMap, pageNo, pageSize);
    }
    
    @Override
    public List<HouseBo> selectcDimData(final HouseBo bo) {
        return this.houseMapper.selectcDimData(bo);
    }
    
    @Override
    public List<HouseBo> selectcDimDataAll(final HouseBo bo) {
        return this.houseMapper.selectcDimDataAll(bo);
    }
    
    @Override
    public List<HouseBo> selectcDimDataByNames(final Map<String, Object> map) {
        return this.houseMapper.selectcDimDataByNames(map);
    }
    
    @Override
    public List<HouseBo> selectcEarnestMoneyById(final HouseBo bo) {
        return this.houseMapper.selectcEarnestMoneyById(bo);
    }
    
    @Override
    public List<HouseBo> selectcEarnestMoneyByProject(final HouseBo bo) {
        return this.houseMapper.selectcEarnestMoneyByProject(bo);
    }
    
    @Override
    public List<HouseBo> selectHouseContractMsg(final HouseBo bo) {
        return this.houseMapper.selectHouseContractMsg(bo);
    }
    
    @Override
    public List<HouseBo> selectHouseMortgageDataMsg(final HouseBo bo) {
        return this.houseMapper.selectHouseMortgageDataMsg(bo);
    }
    
    @Override
    public List<HouseBo> selectHouseContractMoneyMsg(final HouseBo bo) {
        return this.houseMapper.selectHouseContractMoneyMsg(bo);
    }
    
    @Override
    public List<HouseBo> selectReceiptInvoiceDataById(final HouseBo bo) {
        return this.houseMapper.selectReceiptInvoiceDataById(bo);
    }
    
    @Override
    public List<HouseBo> selectMoneyPayDetailById(final HouseBo bo) {
        return this.houseMapper.selectMoneyPayDetailById(bo);
    }
    
    @Override
    public Map<String, Object> insertCityCompany(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            this.houseMapper.insertCityCompany(bo);
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> insertProjectCompany(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            this.houseMapper.insertProjectCompany(bo);
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> updateHouseById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.updateHouseById(bo);
            this.houseMapper.updateHouseBuildingById(bo);
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
    public Map<String, Object> insertEarnestMoneyById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.insertEarnestMoneyById(bo);
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> updateEarnestMoneyById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.updateEarnestMoneyById(bo);
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> updateHouseContractMsgById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            if (StringUtils.isBlank(new Object[] { bo.getId() })) {
                this.houseMapper.insertHouseContractMsgById(bo);
            }
            else {
                this.houseMapper.updateHouseContractMsgById(bo);
            }
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> updateHouseMortgageDataById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            if (StringUtils.isBlank(new Object[] { bo.getId() })) {
                this.houseMapper.insertHouseMortgageDataById(bo);
            }
            else {
                this.houseMapper.updateHouseMortgageDataById(bo);
            }
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> updateHouseContractMoneyById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            if (StringUtils.isBlank(new Object[] { bo.getId() })) {
                this.houseMapper.insertHouseContractMoneyById(bo);
            }
            else {
                this.houseMapper.updateHouseContractMoneyById(bo);
            }
            resultMap.put("id", bo.getId());
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
    public Map<String, Object> deleteHouseContractMoneyById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            this.houseMapper.deleteHouseContractMoneyById(bo);
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
    public Map<String, Object> insertMoneyPayDetailData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.insertMoneyPayDetailData(bo);
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
    public Map<String, Object> updateMoneyPayDetailData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.updateMoneyPayDetailData(bo);
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
    public Map<String, Object> deleteMoneyPayDetailData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            this.houseMapper.deleteMoneyPayDetailData(bo);
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
    public Map<String, Object> deleteEarnestMoneyDetailData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            this.houseMapper.deleteEarnestMoneyDetailData(bo);
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
    public Map<String, Object> insertReceiptInvoiceData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.insertReceiptInvoiceData(bo);
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
    public Map<String, Object> updateReceiptInvoiceData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.updateReceiptInvoiceData(bo);
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
    public Map<String, Object> deleteReceiptInvoiceData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            this.houseMapper.deleteReceiptInvoiceData(bo);
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
    public List<HouseBo> selectcProjectData(final HouseBo bo) {
        return this.houseMapper.selectcProjectData(bo);
    }
    
    @Override
    public List<HouseBo> selectcBuildingData(final HouseBo bo) {
        return this.houseMapper.selectcBuildingData(bo);
    }
    
    @Override
    public List<HouseBo> selectcHouseData(final HouseBo bo) {
        return this.houseMapper.selectcHouseData(bo);
    }
    
    @Override
    public Map<String, Object> insertProjectData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.insertProjectData(bo);
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
    public Map<String, Object> updateProjectData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            bo.setUserId(bo.getUserId());
            this.houseMapper.updateProjectData(bo);
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
    public Map<String, Object> insertBuindingData(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            this.houseMapper.insertBuindingData(bo);
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
    public Map<String, Object> insertHouseData(final MultipartFile file, final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        Workbook wb = null;
        try {
            final Map<String, Long> cityCompanyMap = new HashMap<String, Long>();
            final Map<String, Long> projectCompanyMap = new HashMap<String, Long>();
            wb = this.processData(file, bo, cityCompanyMap, projectCompanyMap);
            final Sheet sheet = wb.getSheetAt(0);
            final Long userId = TokenManager.getToken().getId();
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); ++i) {
                final Row row = sheet.getRow(i);
                if (StringUtils.isBlank(new Object[] { row.getCell(17) })) {
                    System.out.println("\u7ed3\u675f\u8bfb\u53d6\u7b2c[" + i + "]\u884c");
                    break;
                }
                System.out.println("\u5f00\u59cb\u8bfb\u53d6\u7b2c[" + i + "]\u884c");
                Long projectId = null;
                final HouseBo projectBo = new HouseBo();
                projectBo.setErp_code(row.getCell(7).toString().trim());
                List<HouseBo> dataList = this.houseMapper.selectcProjectData(projectBo);
                projectBo.setName(row.getCell(8).toString().trim());
                projectBo.setErp_code(row.getCell(7).toString().trim());
                projectBo.setCity_company_id(cityCompanyMap.get(row.getCell(3).toString().trim()));
                projectBo.setProject_company_id(projectCompanyMap.get(row.getCell(5).toString().trim()));
                projectBo.setUserId(userId);
                projectBo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                if (CollectionUtils.isEmpty((Collection)dataList)) {
                    this.houseMapper.insertProjectData(projectBo);
                    projectId = projectBo.getId();
                }
                else {
                    bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                    this.houseMapper.updateProjectData(projectBo);
                    projectId = dataList.get(0).getId();
                }
                Long buildingId = null;
                String buildingErpCode = null;
                String buildingErpName = null;
                if (StringUtils.isNotBlank(row.getCell(17).toString().trim()) && row.getCell(17).toString().trim().split("\\.").length >= 3) {
                    final String[] code = row.getCell(17).toString().trim().split("\\.");
                    final HouseBo buildingBo = new HouseBo();
                    buildingBo.setBuilding_erp_code(code[0] + "." + code[1] + "." + code[2]);
                    dataList = this.houseMapper.selectcBuildingData(buildingBo);
                    buildingBo.setProject_id(projectId);
                    buildingBo.setName(row.getCell(15).toString().trim());
                    buildingBo.setBuilding_erp_code(code[0] + "." + code[1] + "." + code[2]);
                    buildingBo.setProject_phase(row.getCell(9).toString().trim());
                    if (CollectionUtils.isEmpty((Collection)dataList)) {
                        this.houseMapper.insertBuindingData(buildingBo);
                        buildingId = buildingBo.getId();
                        buildingErpCode = buildingBo.getBuilding_erp_code();
                        buildingErpName = buildingBo.getName();
                    }
                    else {
                        bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                        this.houseMapper.updateBuindingData(buildingBo);
                        buildingId = dataList.get(0).getId();
                        buildingErpCode = buildingBo.getBuilding_erp_code();
                        buildingErpName = buildingBo.getName();
                    }
                }
                if (StringUtils.isNotBlank(row.getCell(17).toString().trim()) && row.getCell(17).toString().trim().split("\\.").length >= 3) {
                    final HouseBo houseBo = new HouseBo();
                    houseBo.setHouse_code(row.getCell(17).toString().trim());
                    dataList = this.houseMapper.selectcHouseData(houseBo);
                    houseBo.setHouse_name(row.getCell(18).toString().trim());
                    houseBo.setHouse_code(row.getCell(17).toString().trim());
                    houseBo.setErp_name(houseBo.getHouse_name());
                    houseBo.setErp_code(houseBo.getHouse_code());
                    houseBo.setIs_large_amount(0L);
                    if (StringUtils.isNotBlank(row.getCell(39).toString().trim())) {
                        houseBo.setPre_unit_price(BigDecimal.valueOf(Double.valueOf(row.getCell(39).toString().trim())));
                    }
                    houseBo.setBuilding_id(buildingId);
                    houseBo.setStatus_id(HouseMgrServiceImpl.houseStatusMap.get(row.getCell(20).toString().trim()));
                    houseBo.setBusiness_type_id(HouseMgrServiceImpl.businessBigTypeMap.get(row.getCell(10).toString().trim()));
                    houseBo.setBusiness_sub_type_id(HouseMgrServiceImpl.businessSubTypeMap.get(row.getCell(11).toString().trim()));
                    if (StringUtils.isNotBlank(row.getCell(31).toString().trim())) {
                        houseBo.setPre_size(BigDecimal.valueOf(Double.valueOf(row.getCell(31).toString().trim())));
                    }
                    houseBo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                    houseBo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                    houseBo.setUserId(userId);
                    if (CollectionUtils.isEmpty((Collection)dataList)) {
                        this.houseMapper.insertHouseData(houseBo);
                    }
                    else {
                        bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
                        this.houseMapper.updateHouseById(projectBo);
                    }
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
    
    public Workbook processData(final MultipartFile file, final HouseBo bo, final Map<String, Long> cityCompanyMap, final Map<String, Long> projectCompanyMap) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        Workbook wb = null;
        try {
            bo.setTableName(" house_status ");
            List<HouseBo> dataList = this.selectcDimData(bo);
            if (CollectionUtils.isNotEmpty((Collection)dataList) && HouseMgrServiceImpl.houseStatusMap.isEmpty()) {
                for (final HouseBo bean : dataList) {
                    HouseMgrServiceImpl.houseStatusMap.put(bean.getName(), bean.getId());
                }
            }
            bo.setTableName(" house_business_type ");
            dataList = this.selectcDimData(bo);
            if (CollectionUtils.isNotEmpty((Collection)dataList) && HouseMgrServiceImpl.businessBigTypeMap.isEmpty()) {
                for (final HouseBo bean : dataList) {
                    HouseMgrServiceImpl.businessBigTypeMap.put(bean.getName(), bean.getId());
                }
            }
            bo.setTableName(" house_business_sub_type ");
            dataList = this.selectcDimData(bo);
            if (CollectionUtils.isNotEmpty((Collection)dataList) && HouseMgrServiceImpl.businessSubTypeMap.isEmpty()) {
                for (final HouseBo bean : dataList) {
                    HouseMgrServiceImpl.businessSubTypeMap.put(bean.getName(), bean.getId());
                }
            }
            List<String> cityCompanyList = new ArrayList<String>();
            List<String> projectCompanyList = new ArrayList<String>();
            if (StringUtils.isExcel2003(file.getOriginalFilename())) {
                wb = (Workbook)new HSSFWorkbook(file.getInputStream());
            }
            else {
                wb = (Workbook)new XSSFWorkbook(file.getInputStream());
            }
            final Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); ++i) {
                final Row row = sheet.getRow(i);
                if (StringUtils.isBlank(new Object[] { row.getCell(0) })) {
                    System.out.println("\u7ed3\u675f\u8bfb\u53d6\u7b2c[" + i + "]\u884c");
                    break;
                }
                System.out.println("\u5f00\u59cb\u8bfb\u53d6\u7b2c[" + i + "]\u884c");
                for (int j = 0; j < row.getPhysicalNumberOfCells(); ++j) {
                    if (j == 3) {
                        final String cellValue = row.getCell(j).toString().trim();
                        if (StringUtils.isNotBlank(new Object[] { row.getCell(j) })) {
                            cityCompanyList.add(cellValue);
                        }
                    }
                    if (j == 5) {
                        final String cellValue = row.getCell(j).toString().trim();
                        if (StringUtils.isNotBlank(new Object[] { row.getCell(j) })) {
                            projectCompanyList.add(cellValue);
                        }
                    }
                }
            }
            if (cityCompanyList.size() > 0) {
                cityCompanyList = new ArrayList<String>(new TreeSet<String>(cityCompanyList));
                final Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", cityCompanyList);
                map.put("tableName", " city_company ");
                final List<HouseBo> list = this.selectcDimDataByNames(map);
                if (CollectionUtils.isNotEmpty((Collection)list)) {
                    final Map<String, String> dataMap = new HashMap<String, String>();
                    for (final HouseBo houseBo : list) {
                        dataMap.put(houseBo.getName(), houseBo.getId() + "");
                    }
                    for (final String name : cityCompanyList) {
                        if (StringUtils.isBlank(dataMap.get(name))) {
                            final HouseBo insertBo = new HouseBo();
                            insertBo.setName(name);
                            final Map<String, Object> res = this.insertCityCompany(insertBo);
                            cityCompanyMap.put(name, Long.valueOf(res.get("id").toString()));
                        }
                        else {
                            cityCompanyMap.put(name, Long.valueOf(dataMap.get(name)));
                        }
                    }
                }
                else {
                    for (final String name2 : cityCompanyList) {
                        final HouseBo insertBo2 = new HouseBo();
                        insertBo2.setName(name2);
                        final Map<String, Object> res2 = this.insertCityCompany(insertBo2);
                        cityCompanyMap.put(name2, Long.valueOf(res2.get("id").toString()));
                    }
                }
            }
            if (projectCompanyList.size() > 0) {
                projectCompanyList = new ArrayList<String>(new TreeSet<String>(projectCompanyList));
                final Map<String, Object> map = new HashMap<String, Object>();
                map.put("list", projectCompanyList);
                map.put("tableName", " project_company ");
                final List<HouseBo> list = this.selectcDimDataByNames(map);
                if (CollectionUtils.isNotEmpty((Collection)list)) {
                    final Map<String, String> dataMap = new HashMap<String, String>();
                    for (final HouseBo houseBo : list) {
                        dataMap.put(houseBo.getName(), houseBo.getId() + "");
                    }
                    for (final String name : projectCompanyList) {
                        if (StringUtils.isBlank(dataMap.get(name))) {
                            final HouseBo insertBo = new HouseBo();
                            insertBo.setName(name);
                            final Map<String, Object> res = this.insertProjectCompany(insertBo);
                            projectCompanyMap.put(name, Long.valueOf(res.get("id").toString()));
                        }
                        else {
                            projectCompanyMap.put(name, Long.valueOf(dataMap.get(name)));
                        }
                    }
                }
                else {
                    for (final String name2 : projectCompanyList) {
                        final HouseBo insertBo2 = new HouseBo();
                        insertBo2.setName(name2);
                        final Map<String, Object> res2 = this.insertProjectCompany(insertBo2);
                        projectCompanyMap.put(name2, Long.valueOf(res2.get("id").toString()));
                    }
                }
            }
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            resultMap.put("status", 200);
            resultMap.put("message", "\u64cd\u4f5c\u6210\u529f");
        }
        catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("message", "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return wb;
    }
    
    static {
        HouseMgrServiceImpl.houseStatusMap = new HashMap<String, Long>();
        HouseMgrServiceImpl.businessBigTypeMap = new HashMap<String, Long>();
        HouseMgrServiceImpl.businessSubTypeMap = new HashMap<String, Long>();
    }
}
