package com.xxl.job.core.biz.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by xuxueli on 16/7/22.
 */

@Data
@ToString
public class TriggerParam implements Serializable{
    private static final long serialVersionUID = 42L;

    private Long jobId;

    private String executorHandler;
    private String executorParams;
    private String executorBlockStrategy;
    private int executorTimeout;

    private Long logId;
    private long logDateTime;

    private String glueType;
    private String glueSource;
    private long glueUpdatetime;

    private int broadcastIndex;
    private int broadcastTotal;


}
