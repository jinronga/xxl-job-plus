package com.xxl.job.admin.object.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobGroupPageDTO {

    @ApiModelProperty(value = "应用名", example = "dashboard")
    private String appName;

    @ApiModelProperty(value = "标题", example = "title")
    private String title;




}
