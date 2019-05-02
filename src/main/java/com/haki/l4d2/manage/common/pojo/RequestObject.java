package com.haki.l4d2.manage.common.pojo;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import org.jsoup.Connection;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class RequestObject implements Serializable {

    private static final long serialVersionUID = 7761477482839535823L;
    {
        this.header = Maps.newHashMap();
        this.header.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
    }
    /**
     * 传入的URL
     */
    private String url;

    /**
     * 传入的字符集
     */
    @Builder.Default
    private String charset ="UTF-8";
    /**
     * 传入的请求头
     */
    @Builder.Default
    private Map<String, String> header;

    /**
     * 是否忽略ContentType
     */
    @Builder.Default
    private boolean ignoreContentType = true;

    /**
     * 设置请求方式,默认Get请求
     */
    @Builder.Default
    private Connection.Method method = Connection.Method.GET;

    /**
     * 请求的数据通常用于Post请求
     */
    private Map<String,String> body;
}
