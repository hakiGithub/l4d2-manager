package com.haki.l4d2.manage.spider;


import com.haki.l4d2.manage.pojo.L4d2Map;
import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 爬虫的抽象类
 */
public abstract class Spider {

    protected int num = 1;

    public int getNum() {
        return this.num;
    }

    public abstract List<L4d2Map> spider(String url);

    /**
     * @param url      爬取的页面
     * @return 返回爬取的线程数
     */
    public abstract void spiderNum(String url);

    protected Document parseHtml(String url) {
        Document document = JsoupUtil.buildDocument(url);
        return document;
    }

}
