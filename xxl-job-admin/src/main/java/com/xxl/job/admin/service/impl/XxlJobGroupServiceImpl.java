package com.xxl.job.admin.service.impl;

import cn.jinronga.common.mybatis.basic.GenericDTO;
import cn.jinronga.common.mybatis.core.query.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.util.JacksonUtil;
import com.xxl.job.admin.mapper.XxlJobGroupMapper;
import com.xxl.job.admin.object.dto.JobGroupPageDTO;
import com.xxl.job.admin.service.XxlJobGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class XxlJobGroupServiceImpl extends ServiceImpl<XxlJobGroupMapper, XxlJobGroup> implements XxlJobGroupService {


    private final XxlJobGroupMapper xxlJobGroupMapper;


    @Override
    public Page<XxlJobGroup> pageList(GenericDTO<JobGroupPageDTO> dto) {
        Page page = JacksonUtil.readValue(JacksonUtil.writeValueAsString(dto), Page.class);
        return this.baseMapper.selectPage(page,new LambdaQueryWrapperX<XxlJobGroup>()
                .likeIfPresent(XxlJobGroup::getAppName,dto.getDto().getAppName())
                .likeIfPresent(XxlJobGroup::getTitle,dto.getDto().getTitle()));
    }
}
