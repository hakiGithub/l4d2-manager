package com.haki.l4d2.manage;

import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class SpiderTest {

    @Test
    public void testSpider(){
        Document document = JsoupUtil.buildDocument("http://www.kk175.com/plus/list-8.html");
        System.out.println(document);

    }
}
