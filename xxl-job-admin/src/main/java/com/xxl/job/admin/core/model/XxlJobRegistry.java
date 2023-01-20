package com.xxl.job.admin.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
@TableName(value = "xxl_job_registry")
public class XxlJobRegistry {

    private Long id;
    private String registryGroup;
    private String registryKey;
    private String registryValue;
    private Date updateTime;
}
