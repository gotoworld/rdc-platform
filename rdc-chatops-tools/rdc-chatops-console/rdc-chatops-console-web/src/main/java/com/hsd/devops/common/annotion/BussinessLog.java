package com.hsd.devops.common.annotion;

import java.lang.annotation.*;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLog {

    
    String value() default "";

    
    String key() default "id";

    
    String dict() default "SystemDict";
}
