package com.haki.l4d2.manage.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: haki
 * @Date: 2019/5/5 21:21
 * @Description:
 */
@Slf4j
public class AnnotationDemo {

    public <T> T mapperJsoup(Class<T> clazz,Element element) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        final T t = clazz.newInstance();
        Arrays.stream(fields).filter(f -> f.isAnnotationPresent(SpiderConfig.class))
                .forEach(f -> setAttribute(clazz, t, f,element));
        return t;
    }

    /***
     *设置对象的属性
     */
    private <T> void setAttribute(Class<T> clazz, T t, Field field, Element element) {
        SpiderConfig config = field.getAnnotation(SpiderConfig.class);
        Element el = element.select(config.value()).get(config.num());
        String attr = null;
        if ( Strings.isNotBlank(config.attribute())){
            attr = el.attr(config.attribute());
        }else{
            attr = el.text();
        }
        //构造注入属性方法
        StringBuilder sb = new StringBuilder("set");
        sb.append(field.getName().substring(0, 1).toUpperCase())
                .append(field.getName().substring(1));
        Method method = null;
        try {
            method = clazz.getMethod(sb.toString(), field.getType());
        } catch (NoSuchMethodException e) {
            log.info("{}中找不到注入的方法{}", clazz.getName(), sb.toString());
        }
        //调用setter方法
        setterField(t, field.getType(), attr, method);
    }

    /**
     *主要用于setter方法构建对象的属性
     */
    private <T> void setterField(T t, Class clazz, String value, Method method) {
        try {
            if (clazz == Integer.class) {
                method.invoke(t, Integer.parseInt(value));
                return;
            } else if (clazz == String.class) {
                method.invoke(t, value);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //直接获取类名
        try {
            String simpleName = clazz.getSimpleName();
            Method parse = clazz.getMethod("parse" + simpleName, String.class);
            method.invoke(t, parse.invoke(null, value));
        } catch (Exception e) {
            log.info("不符合规则的解析方式:[{}]", e.getMessage());
            return;
        }

    }


    @Test
    public void testReflex() {
        /*try {
            Student student = testAnnotation(Student.class);
            log.info("{}", student);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }
}
@Data
class Student{

    @SpiderConfig("黄坚")
    private String name;

    @SpiderConfig("20.3")
    private Double money;

    @SpiderConfig("12")
    private Integer age;

    @SpiderConfig("true")
    private Boolean flag;


}
