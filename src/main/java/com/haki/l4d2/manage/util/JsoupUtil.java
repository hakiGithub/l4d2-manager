package com.haki.l4d2.manage.util;

import com.haki.l4d2.manage.common.pojo.RequestObject;
import com.haki.l4d2.manage.util.exception.IllegalException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

/**
 * 构建jsoup的工具类
 */
public class JsoupUtil {
    /**
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
}
