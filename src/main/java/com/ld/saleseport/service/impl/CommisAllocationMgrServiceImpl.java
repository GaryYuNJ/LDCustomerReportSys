package com.ld.saleseport.service.impl;

import com.ld.core.mybatis.*;
import com.ld.common.dao.*;
import com.ld.saleseport.service.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CommisAllocationMgrServiceImpl extends BaseMybatisDao<HouseMapper> implements CommisAllocationMgrService
{
    @Autowired
    HouseMapper houseMapper;
}
