package com.ld.saleseport.service;

import java.util.*;
import com.ld.saleseport.bo.*;
import com.ld.core.mybatis.page.*;
import org.springframework.web.multipart.*;

public interface HouseMgrService
{
    List<HouseBo> findHouseAll(Map<String, Object> p0);
    
    Pagination<HouseBo> findPage(Map<String, Object> p0, Integer p1, Integer p2);
    
    List<HouseBo> selectcDimData(HouseBo p0);
    
    List<HouseBo> selectcDimDataAll(HouseBo p0);
    
    List<HouseBo> selectcDimDataByNames(Map<String, Object> p0);
    
    List<HouseBo> selectcEarnestMoneyById(HouseBo p0);
    
    List<HouseBo> selectcEarnestMoneyByProject(HouseBo p0);
    
    Map<String, Object> updateHouseById(HouseBo p0);
    
    Map<String, Object> insertCityCompany(HouseBo p0);
    
    Map<String, Object> insertProjectCompany(HouseBo p0);
    
    Map<String, Object> insertEarnestMoneyById(HouseBo p0);
    
    Map<String, Object> updateEarnestMoneyById(HouseBo p0);
    
    List<HouseBo> selectHouseContractMsg(HouseBo p0);
    
    List<HouseBo> selectHouseMortgageDataMsg(HouseBo p0);
    
    List<HouseBo> selectHouseContractMoneyMsg(HouseBo p0);
    
    Map<String, Object> updateHouseContractMsgById(HouseBo p0);
    
    List<HouseBo> selectMoneyPayDetailById(HouseBo p0);
    
    List<HouseBo> selectReceiptInvoiceDataById(HouseBo p0);
    
    Map<String, Object> insertMoneyPayDetailData(HouseBo p0);
    
    Map<String, Object> updateMoneyPayDetailData(HouseBo p0);
    
    Map<String, Object> deleteMoneyPayDetailData(HouseBo p0);
    
    Map<String, Object> deleteEarnestMoneyDetailData(HouseBo p0);
    
    Map<String, Object> insertReceiptInvoiceData(HouseBo p0);
    
    Map<String, Object> updateReceiptInvoiceData(HouseBo p0);
    
    Map<String, Object> deleteReceiptInvoiceData(HouseBo p0);
    
    Map<String, Object> updateHouseMortgageDataById(HouseBo p0);
    
    Map<String, Object> updateHouseContractMoneyById(HouseBo p0);
    
    Map<String, Object> deleteHouseContractMoneyById(HouseBo p0);
    
    List<HouseBo> selectcProjectData(HouseBo p0);
    
    List<HouseBo> selectcBuildingData(HouseBo p0);
    
    List<HouseBo> selectcHouseData(HouseBo p0);
    
    Map<String, Object> insertProjectData(HouseBo p0);
    
    Map<String, Object> updateProjectData(HouseBo p0);
    
    Map<String, Object> insertBuindingData(HouseBo p0);
    
    Map<String, Object> insertHouseData(MultipartFile p0, HouseBo p1);
}
