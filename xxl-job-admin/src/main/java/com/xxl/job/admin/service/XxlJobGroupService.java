package com.xxl.job.admin.service;

import cn.jinronga.common.mybatis.basic.GenericDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.object.dto.JobGroupPageDTO;


public interface XxlJobGroupService extends IService<XxlJobGroup> {


    Page<XxlJobGroup> pageList(GenericDTO<JobGroupPageDTO> dto);

}
