package com.xxl.job.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxl.job.admin.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */
public interface XxlJobLogGlueMapper extends BaseMapper<XxlJobLogGlue> {
	
	 int save(XxlJobLogGlue xxlJobLogGlue);
	

	 int removeOld(@Param("jobId") Long jobId, @Param("limit") int limit);

	 int deleteByJobId(@Param("jobId") Long jobId);
	
}
