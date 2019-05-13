package com.haki.l4d2.manage.spider;


import com.haki.l4d2.manage.pojo.dto.L4d2MapDTO;
import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 爬虫的抽象类
 */
public abstract class Spider implements Callable<List>{

    protected int num = 1;

    protected String url;

    public int getNum() {
        return this.num;
    }

    public abstract List<L4d2MapDTO> spider();

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
