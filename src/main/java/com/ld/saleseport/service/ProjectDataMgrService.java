package com.ld.saleseport.service;

import java.util.*;
import com.ld.core.mybatis.page.*;
import com.ld.saleseport.bo.*;

public interface ProjectDataMgrService
{
    Pagination<HouseBo> findPage(Map<String, Object> p0, Integer p1, Integer p2);
    
    Map<String, Object> updateProjectMsgById(HouseBo p0);
}
