package com.haki.l4d2.manage.util;

import com.haki.l4d2.manage.common.pojo.RequestObject;
import com.haki.l4d2.manage.util.annotation.SpiderConfig;
import com.haki.l4d2.manage.util.exception.IllegalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 构建jsoup的工具类
 */
@Slf4j
public class JsoupUtil {
    /**
     *
     * @param request 请求的信息
     * @return 返回jsoup的连接
     */
    private static Connection buildConnection(RequestObject request) {
        if (request == null) {
            throw new IllegalException("请求信息不能为null");
        }
        //判断url是否合法与null;
        if (StringUtils.isBlank(request.getUrl())) {
            throw new IllegalException("非法的Url");
        }
        ;
        Connection connect = Jsoup.connect(request.getUrl());
        //构建数据模型
        if (!CollectionUtils.isEmpty(request.getBody())) {
            connect.data(request.getBody());
        }
        //构建请求头
        if (!CollectionUtils.isEmpty(request.getHeader())) {
            request.getHeader()
                    .forEach(connect::header);
        }
        connect.ignoreContentType(request.isIgnoreContentType());
        connect.method(request.getMethod());
        return connect;
    }

    /**
     * 根据请求参数返回
     *
     * @param request
     * @return
     */
    public static Document buildDocument(RequestObject request) {
        //获取构建成功的connection
        Connection connection = buildConnection(request);
        Document document = null;
        try {
            //执行连接，获取html
            String html = connection.execute()
                    .charset(request.getCharset())
                    .body();
            //利用jsoup解析成dom
            document = Jsoup.parse(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document buildDocument(String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalException("非法的Url");
        }
        RequestObject request = RequestObject.builder()
                .url(url)
                .build();
        return buildDocument(request);
    }

    /**
     * 功能描述:利用注解将解析的元素注入的实体中
     */
    public static <T> T mapperSpider(Class<T> clazz, Element element) {
        Field[] fields = clazz.getDeclaredFields();
        try {
            final T t = clazz.newInstance();
            Arrays.stream(fields).filter(f -> f.isAnnotationPresent(SpiderConfig.class))
                    .forEach(f -> setAttribute(clazz, t, f, element));
            return t;
        } catch (Exception e) {
           e.printStackTrace();
            return null;
        }
    }

    /***
     *设置对象的属性
     */
    private static <T> void setAttribute(Class<T> clazz, T t, Field field, Element element) {
        SpiderConfig config = field.getAnnotation(SpiderConfig.class);
        Element el = element.select(config.value()).get(config.num());
        String attr = null;
        if (Strings.isNotBlank(config.attribute())) {
            attr = el.absUrl(config.attribute());
        } else {
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
     * 主要用于setter方法构建对象的属性
     */
    private static <T> void setterField(T t, Class clazz, String value, Method method) {
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
}
