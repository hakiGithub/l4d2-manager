package com.haki.l4d2.manage.spider;


import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;

/**
 * 爬虫的抽象类
 */
public abstract class Spider {

    protected int num = 1;

    public int getNum() {
        return this.num;
    }

    public abstract <T> T spider(String url, Class<T> t);

    /**
     * @param url      爬取的页面
     * @param cssQuery 爬取的规则
     * @return 返回爬取的线程数
     */
    public abstract Integer spiderNum(String url, String cssQuery);

    protected String parseHtml(String url) {
        Document document = JsoupUtil.buildDocument(url);
        return null;
    }

}
