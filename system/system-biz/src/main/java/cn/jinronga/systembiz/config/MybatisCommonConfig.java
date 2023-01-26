package cn.jinronga.systembiz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@MapperScan(value = "${mybatis-plus.info.base-package}")
public class MybatisCommonConfig {



}
