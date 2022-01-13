package com.springboot.data.jpa.app.annotation;

import com.springboot.data.jpa.app.config.ApplicationConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ApplicationConfig.class)
public @interface EnableJpaApp {

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {ApplicationConfig.class};

}
