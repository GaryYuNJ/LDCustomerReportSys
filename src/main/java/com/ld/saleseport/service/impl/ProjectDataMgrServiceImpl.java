package com.ld.saleseport.service.impl;

import com.ld.core.mybatis.*;
import com.ld.common.dao.*;
import com.ld.saleseport.service.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ld.core.mybatis.page.*;
import com.ld.saleseport.bo.*;
import java.util.*;
import com.ld.common.utils.*;
import org.springframework.transaction.interceptor.*;

@Service
public class ProjectDataMgrServiceImpl extends BaseMybatisDao<ProjectDataMapper> implements ProjectDataMgrService
{
    @Autowired
    ProjectDataMapper projectDataMapper;
    
    @Override
    public Pagination<HouseBo> findPage(final Map<String, Object> resultMap, final Integer pageNo, final Integer pageSize) {
        return (Pagination<HouseBo>)super.findPage(resultMap, pageNo, pageSize);
    }
    
    @Override
    public Map<String, Object> updateProjectMsgById(final HouseBo bo) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println("************************\u8bf7\u6c42\u53c2\u6570[" + bo.getId() + "]*********************");
            bo.setDateStr(DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            bo.setUserId(bo.getUserId());
            this.projectDataMapper.updateProjectMsgById(bo);
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
