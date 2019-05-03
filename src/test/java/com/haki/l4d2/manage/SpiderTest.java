package com.haki.l4d2.manage;

import com.haki.l4d2.manage.pojo.L4d2Map;
import com.haki.l4d2.manage.spider.Spider;
import com.haki.l4d2.manage.spider.impl.L4D2ccSpider;
import com.haki.l4d2.manage.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SpiderTest {

    @Test
    public void testSpider() {
        Document document = JsoupUtil.buildDocument("http://www.kk175.com/plus/list-8.html");
        System.out.println(document);
    }

    @Test
    public void testSpiderList() {
        Spider spiders = new L4D2ccSpider();
        List<L4d2Map> list = spiders.spider("http://www.kk175.com/plus/list-8.html");
        list.forEach(System.out::println);
    }

    @Test
    public void testStr() {
        String str = "   下载次数:63818 大小:181MB时间：2018-11-27";
        String[] split = str.trim().split("[:：]");
        Arrays.stream(split)
                .map(x -> x.replaceAll("大小|时间", ""))
                .map(String::trim)
                .forEach(System.out::println);
    }
}
