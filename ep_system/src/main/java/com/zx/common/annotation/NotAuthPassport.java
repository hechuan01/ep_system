package com.zx.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Tony on 2017/3/8.
 * 权限控制
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAuthPassport {
}
