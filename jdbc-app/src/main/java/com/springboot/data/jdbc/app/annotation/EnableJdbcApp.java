package com.springboot.data.jdbc.app.annotation;

import com.springboot.data.jdbc.app.config.ApplicationConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ApplicationConfig.class)
public @interface EnableJdbcApp {

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {ApplicationConfig.class};

}
