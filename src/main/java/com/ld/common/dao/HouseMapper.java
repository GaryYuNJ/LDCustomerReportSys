package com.ld.common.dao;

import java.util.*;
import com.ld.saleseport.bo.*;

public interface HouseMapper
{
    List<HouseBo> findHouseAll(Map<String, Object> p0);
    
    List<HouseBo> selectcDimData(HouseBo p0);
    
    List<HouseBo> selectcDimDataAll(HouseBo p0);
    
    List<HouseBo> selectcDimDataByNames(Map<String, Object> p0);
    
    List<HouseBo> selectcEarnestMoneyById(HouseBo p0);
    
    List<HouseBo> selectcEarnestMoneyByProject(HouseBo p0);
    
    int insertCityCompany(HouseBo p0);
    
    int insertProjectCompany(HouseBo p0);
    
    int updateHouseById(HouseBo p0);
    
    int updateHouseBuildingById(HouseBo p0);
    
    int insertEarnestMoneyById(HouseBo p0);
    
    int updateEarnestMoneyById(HouseBo p0);
    
    List<HouseBo> selectHouseContractMsg(HouseBo p0);
    
    List<HouseBo> selectHouseMortgageDataMsg(HouseBo p0);
    
    List<HouseBo> selectHouseContractMoneyMsg(HouseBo p0);
    
    int insertHouseContractMsgById(HouseBo p0);
    
    int updateHouseContractMsgById(HouseBo p0);
    
    List<HouseBo> selectMoneyPayDetailById(HouseBo p0);
    
    List<HouseBo> selectReceiptInvoiceDataById(HouseBo p0);
    
    int insertMoneyPayDetailData(HouseBo p0);
    
    int updateMoneyPayDetailData(HouseBo p0);
    
    int deleteMoneyPayDetailData(HouseBo p0);
    
    int deleteEarnestMoneyDetailData(HouseBo p0);
    
    int insertReceiptInvoiceData(HouseBo p0);
    
    int updateReceiptInvoiceData(HouseBo p0);
    
    int deleteReceiptInvoiceData(HouseBo p0);
    
    int insertHouseMortgageDataById(HouseBo p0);
    
    int updateHouseMortgageDataById(HouseBo p0);
    
    int insertHouseContractMoneyById(HouseBo p0);
    
    int updateHouseContractMoneyById(HouseBo p0);
    
    int deleteHouseContractMoneyById(HouseBo p0);
    
    List<HouseBo> selectcProjectData(HouseBo p0);
    
    List<HouseBo> selectcBuildingData(HouseBo p0);
    
    List<HouseBo> selectcHouseData(HouseBo p0);
    
    int insertProjectData(HouseBo p0);
    
    int updateProjectData(HouseBo p0);
    
    int insertBuindingData(HouseBo p0);
    
    int updateBuindingData(HouseBo p0);
    
    int insertHouseData(HouseBo p0);
}
