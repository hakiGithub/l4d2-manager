package com.haki.l4d2.manage.demo;

import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Auther: haki
 * @Date: 2019/5/5 21:21
 * @Description:
 */
public class AnnotationDemo {

    @Test
    public void testAnnotation() throws Exception {
        Class clazz = Student.class;
        //Class clazz = Class.forName("com.haki.l4d2.manage.demo.Student");
        Field[] fields = clazz.getDeclaredFields();
        Object obj = null;
             obj =clazz.newInstance();
            for (Field field : fields) {
            if (field.isAnnotationPresent(SpiderConfig.class)){
                SpiderConfig config = field.getAnnotation(SpiderConfig.class);
                StringBuilder sb = new StringBuilder("set");
                sb.append(field.getName().substring(0,1).toUpperCase())
                        .append(field.getName().substring(1,field.getName().length()));
                    Method method = clazz.getMethod(sb.toString(),field.getType());
                setMethod(obj, field.getType(), config, method);
            }
        }
            System.out.println(obj);
    }

    private void setMethod(Object obj, Class clazz, SpiderConfig config, Method method) throws IllegalAccessException,
            InvocationTargetException, InstantiationException {

        if (clazz == Integer.class){
             method.invoke(obj, Integer.parseInt(config.value()));
        }else if (clazz == Double.class){
            method.invoke(obj, Double.parseDouble(config.value()));
        } else {
             method.invoke(obj,config.value());
        }
    }


    @Test
    public void testToUpper(){
        String name = "haki";
    System.out.println(name.substring(0,1).toUpperCase());
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

}
