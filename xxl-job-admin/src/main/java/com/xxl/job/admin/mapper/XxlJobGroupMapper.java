package com.xxl.job.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxl.job.admin.core.model.XxlJobGroup;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
public interface XxlJobGroupMapper extends BaseMapper<XxlJobGroup> {

     List<XxlJobGroup> findAll();

     List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

     int save(XxlJobGroup xxlJobGroup);

     XxlJobGroup load(@Param("id") Long id);

     List<XxlJobGroup> pageList(@Param("offset") int offset,
                                      @Param("pagesize") int pagesize,
                                      @Param("appname") String appname,
                                      @Param("title") String title);

     int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("appname") String appname,
                             @Param("title") String title);

}
