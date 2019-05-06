package com.haki.l4d2.manage.util.annotation;

import java.lang.annotation.*;

/** @Auther: haki
 * @Date: 2019/5/5 20:42
 * @Description:用于爬虫配置的注解
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpiderConfig {

  // 爬虫的规则
  String value();
  // 元素所在的位置
  int num() default 0;
  // 元素的属性
  String attribute() default "text";
}
