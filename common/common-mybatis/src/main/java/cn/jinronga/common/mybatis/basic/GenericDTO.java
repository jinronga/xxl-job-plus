package cn.jinronga.common.mybatis.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO<T>  extends Page<T>  {

    @ApiModelProperty(value = "检索关键字DTO", required = true,example = "")
    private T dto;
}
