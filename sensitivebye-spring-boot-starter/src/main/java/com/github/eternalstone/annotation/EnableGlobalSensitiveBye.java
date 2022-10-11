package com.github.eternalstone.annotation;

import com.github.eternalstone.configuration.GlobalSensitiveByeAutoConfiguration;
import com.github.eternalstone.configuration.MybatisSensitiveByeConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启全局配置注解
 *
 * @author Justzone on 2022/9/4 14:55
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({GlobalSensitiveByeAutoConfiguration.class, MybatisSensitiveByeConfiguration.class})
public @interface EnableGlobalSensitiveBye {

}
