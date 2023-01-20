package com.xxl.job.admin.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author xuxueli 2019-05-04 16:43:12
 */
@Data
@TableName(value = "xxl_job_user")
public class XxlJobUser {

	private Long id;
	private String username;		// 账号
	private String password;		// 密码
	private int role;				// 角色：0-普通用户、1-管理员
	private String permission;	// 权限：执行器ID列表，多个逗号分割


	// plugin
	public boolean validPermission(Long jobGroup){
		if (this.role == 1) {
			return true;
		} else {
			if (StringUtils.hasText(this.permission)) {
				for (String permissionItem : this.permission.split(",")) {
					if (String.valueOf(jobGroup).equals(permissionItem)) {
						return true;
					}
				}
			}
			return false;
		}

	}

}
