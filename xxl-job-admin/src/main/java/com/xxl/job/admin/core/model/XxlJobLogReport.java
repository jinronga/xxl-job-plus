package com.xxl.job.admin.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName(value = "xxl_job_log_report")
public class XxlJobLogReport {

    private Long id;

    private Date triggerDay;

    private int runningCount;
    private int sucCount;
    private int failCount;
}
